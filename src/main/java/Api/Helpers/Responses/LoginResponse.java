package Api.Helpers.Responses;

public class LoginResponse {
    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String token) {
        this.jwtToken = token;
    }
}
