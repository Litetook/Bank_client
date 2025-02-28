package com.pragmatic.service.impl;

import com.pragmatic.controller.exception.ObjAlreadyExistsException;
import com.pragmatic.controller.exception.ObjNotFoundException;
import com.pragmatic.converter.DtoConverter;
import com.pragmatic.dao.UserDao;
import com.pragmatic.dto.UserDto;
import com.pragmatic.controller.dto.request.UserCreateRequest;
import com.pragmatic.model.User;
import com.pragmatic.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDaoImpl;

    @Autowired
    DtoConverter dtoConverter;

    public UserServiceImpl(UserDao userDaoImpl, DtoConverter dtoConverter) {
        this.userDaoImpl = userDaoImpl;
        this.dtoConverter = dtoConverter;
    }


    @Override
    public UserDto createUserFromRequest(UserCreateRequest userRequest) throws ObjAlreadyExistsException {
        Optional<User> existingUser = userDaoImpl.findByNameAndEmail(userRequest.getName(), userRequest.getEmail()); //TODO переписати до примітивів
        if (existingUser.isEmpty()) {
            User inputUser = User.builder()
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .password(userRequest.getPassword())
                    .build();
            userDaoImpl.save(inputUser);
            return dtoConverter.convertUserToDto(inputUser);
        }
        throw new ObjAlreadyExistsException(String.format("User already exists with id %d",  existingUser.get().getId()));
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto>  userDtoList = new ArrayList<>();
        userDaoImpl.findAll()
                .forEach(user -> {
                    userDtoList.add(dtoConverter.convertUserToDto(user));
                });
        return userDtoList;
    }


    @Override
    public UserDto findUserById(Long userId) throws ObjNotFoundException {
        Optional<User> user = userDaoImpl.findById(userId);
        if (user.isEmpty()) {
            throw new ObjNotFoundException(String.format("There is no user with id %d", userId));
        }
        return dtoConverter.convertUserToDto(user.get());
    }
}
