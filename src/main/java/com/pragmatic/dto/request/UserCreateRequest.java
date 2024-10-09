package com.pragmatic.dto.request;

import lombok.*;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class UserCreateRequest {
        private String email;
        private String password;
        private String name;
    }
