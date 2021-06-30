package br.com.zupacademy.rayllanderson.ecommerce.users.reponses;

public class LoginUserResponse {

    private final String token;
    private final String authenticationType;

    public LoginUserResponse(String token, String authenticationType) {
        this.token = token;
        this.authenticationType = authenticationType;
    }

    public LoginUserResponse(String token) {
        this.token = token;
        this.authenticationType = "Bearer";
    }

    public String getToken() {
        return token;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }
}
