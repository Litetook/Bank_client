package com.pragmatic.service;

import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.controller.exception.ObjNotFoundException;
import com.pragmatic.dto.impl.UserDtoImpl;
import com.pragmatic.dto.request.UserCreateRequest;

import java.util.List;

public interface UserService {
    List<UserDtoImpl> findAll();
    UserDtoImpl findUserById(Integer userId) throws ObjNotFoundException;
    UserDtoImpl createUserFromRequest(UserCreateRequest userRequest) throws ObjAlreadyExistsException;
}
