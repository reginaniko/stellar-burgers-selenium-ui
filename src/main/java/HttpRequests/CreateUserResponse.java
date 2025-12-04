package HttpRequests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateUserResponse {
    private User user;
    private String accessToken;
    private String refreshToken;
    private Boolean success;
    private String message;
}
