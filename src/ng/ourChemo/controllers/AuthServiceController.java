package ng.ourChemo.controllers;

import ng.ourChemo.dtos.requests.LogOutUserRequest;
import ng.ourChemo.dtos.requests.LoginUserRequest;
import ng.ourChemo.dtos.requests.RegisterUserRequest;
import ng.ourChemo.dtos.responses.LogOutUserResponse;
import ng.ourChemo.dtos.responses.LoginUserResponse;
import ng.ourChemo.dtos.responses.RegisterUserResponse;
import ng.ourChemo.services.AuthService;
import ng.ourChemo.services.AuthServiceImpl;

public class AuthServiceController {
    private AuthService authService = new AuthServiceImpl();

    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        return authService.register(registerUserRequest);
    }

    public LoginUserResponse login(LoginUserRequest loginUserRequest) {
        return authService.login(loginUserRequest);
    }

    public LogOutUserResponse login(LogOutUserRequest logOutUserRequest) {
        return authService.logOut(logOutUserRequest);
    }

}
