package com.hongseng.app.config.jwtfilter;

import com.hongseng.app.config.exception.RefreshTokenException;
import enums.TokenEnum;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import utils.JwtTokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: spring-security
 * @description: 验证成功当然就是进行鉴权了，每一次需要权限的请求都需要检查该用户是否有该权限去操作该资源，当然这也是框架帮我们做的，那么我们需要做什么呢？
 * 很简单，只要告诉spring-security该用户是否已登录，是什么角色，拥有什么权限就可以了。
 * @author: fbl
 * @create: 2020-12-02 14:25
 **/
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private static final String LOGIN_URL = "/login";
    /**
     * 防止并发修改token，使用ThreadLocal
     */
    private static ThreadLocal<String> TOKEN = new ThreadLocal<>();

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


        String tokenHeader = request.getHeader(TokenEnum.TOKEN_HEADER.getValue());
        // 如果请求头中没有Authorization信息或者是登录接口直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(TokenEnum.TOKEN_PREFIX.getValue()) || request.getRequestURL().toString().contains(LOGIN_URL)) {
            TOKEN.remove();
            chain.doFilter(request, response);
            return;
        }

        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            if (JWTAuthorizationFilter.TOKEN.get() != null) {
                refreshToken(TOKEN.get());
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(TOKEN.get()));
            } else {
                refreshToken(tokenHeader);
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
            }
        } catch (RefreshTokenException | ExpiredJwtException e) {
            // 异常捕获，发送到expiredJwtException
            request.setAttribute("expiredJwtException", e);
            //将异常分发到/expiredJwtException控制器
            request.getRequestDispatcher("/expiredJwtException").forward(request, response);
        } catch (SignatureException | AccessDeniedException e) {
            // 异常捕获，发送到signatureException
            request.setAttribute("signatureException", e);
            //将异常分发到/signatureException控制器
            request.getRequestDispatcher("/signatureException").forward(request, response);
        }
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 这里从token中获取用户信息并新建一个token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(TokenEnum.TOKEN_PREFIX.getValue(), "");

        String username = JwtTokenUtils.getUsername(token);
        String permission = JwtTokenUtils.getUserPermission(token);
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String p : Arrays.asList(permission.split(","))) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(p));
        }
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
        }
        return null;
    }

    /**
     * token刷新
     *
     * @param tokenHeader
     */
    private void refreshToken(String tokenHeader) throws RefreshTokenException {
        String token = tokenHeader.replace(TokenEnum.TOKEN_PREFIX.getValue(), "");
        // 客户端token有没有过期
        boolean expiration = JwtTokenUtils.isExpiration(token);
        // 是否过期时间已将超出两倍
        if (expiration) {
            boolean twoTimesTokenExpiration = JwtTokenUtils.isTwoTimesTokenExpiration(token);
            // 没有，续期，否则抛出自定义异常
            if (!twoTimesTokenExpiration) {
                String username = JwtTokenUtils.getUsername(token);
                String permission = JwtTokenUtils.getUserPermission(token);
                JWTAuthorizationFilter.TOKEN.set(JwtTokenUtils.createToken(username, permission, false));
            } else {
                throw new RefreshTokenException();
            }
        }
    }
}
