package com.hongseng.app.config.jwtfilter;

import enums.TokenEnum;
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

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(TokenEnum.TOKEN_HEADER.getValue());
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(TokenEnum.TOKEN_PREFIX.getValue())) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
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
}
