package HttpRequests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
        private String email;
        private String password;
        private String name;
    }
