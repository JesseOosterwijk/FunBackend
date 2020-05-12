package Api.Helpers.Responses;

import java.util.UUID;

public class RegisterResponse {
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
