package dto;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
public class Credentials {
    private String appId, appSecret;

    public Credentials() {
    }

    public Credentials(String appId, String appSecret) {
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
