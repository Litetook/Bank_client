package com.pragmatic.service;

import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.controller.exception.ObjNotFoundException;
import com.pragmatic.dto.UserDto;
import com.pragmatic.controller.dto.request.UserCreateRequest;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findUserById(Long userId) throws ObjNotFoundException;
    UserDto createUserFromRequest(UserCreateRequest userRequest) throws ObjAlreadyExistsException;
}
