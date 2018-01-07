package dto;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
public class Authentication {
    private String userToken;
    private String errorMessage;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
