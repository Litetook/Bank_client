package com.pragmatic.dto.impl;

import lombok.*;

@Getter  @Setter @ToString
@Builder
@AllArgsConstructor
public class UserDtoImpl {
    private Integer userId;
    private String email;
    private String password;
    private String name;
}
