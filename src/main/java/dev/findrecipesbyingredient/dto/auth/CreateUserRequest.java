package dev.findrecipesbyingredient.dto.auth;

import lombok.Builder;

@Builder
public record CreateUserRequest(String username, String password, String roles) {
}
