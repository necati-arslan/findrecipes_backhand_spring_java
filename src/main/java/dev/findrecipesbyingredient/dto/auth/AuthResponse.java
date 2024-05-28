package dev.findrecipesbyingredient.dto.auth;



public class AuthResponse {
    private String jwtToken;
    private String userName;

    // Constructor
    public AuthResponse(String jwtToken, String userName) {
        this.jwtToken = jwtToken;
        this.userName = userName;
    }

    // Getter
    public String getJwtToken() {
        return jwtToken;
    }

    // Setter (if needed)
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
