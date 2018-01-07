package dto;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
public class OAuth2Token {
    private String token;
    private String refreshToken;
    private Long expiration;

    public OAuth2Token() {
    }

    public OAuth2Token(String token, String refreshToken, Long expiration) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
