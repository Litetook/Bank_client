package com.pragmatic.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class UserCreateRequest {
        @NotNull
        @NotBlank
        @Size(min = 4, max = 20)
        private String email;

        @NotNull
        @NotBlank
        @Size(min = 8, max = 20)
        private String password;

        @NotNull
        @NotBlank
        @Size(min = 3, max = 20)
        private String name;
}
