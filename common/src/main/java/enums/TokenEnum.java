package enums;


/**
 * @author fanbaolin
 * token配置项
 */

public enum TokenEnum {
    /**
     * token头
     */
    TOKEN_HEADER("Authorization"),

    /**
     * token前缀
     */
    TOKEN_PREFIX("Bearer "),

    /**
     * token秘钥
     */
    SECRET("ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI"),

    ISS("echisan"),

    /**
     * token过期时间 一个小时
     */
    EXPIRATION(3600L),

    /**
     * 选择记住我 七天
     */
    EXPIRATION_REMEMBER(604800L),

    /**
     * 添加角色的key
     */
    ROLE_CLAIMS("rol");

    private String value;
    private Long time;


    TokenEnum(String value) {
        this.value = value;
    }

    TokenEnum(Long time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public Long getTime() {
        return time;
    }
}
