package com.pragmatic.dto;

import lombok.*;

@Getter  @Setter @ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String email;
//    private String password;
    private String name;
}
