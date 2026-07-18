package ng.ourChemo.services;

import ng.ourChemo.dtos.requests.LogOutUserRequest;
import ng.ourChemo.dtos.requests.LoginUserRequest;
import ng.ourChemo.dtos.requests.RegisterUserRequest;
import ng.ourChemo.dtos.responses.LogOutUserResponse;
import ng.ourChemo.dtos.responses.LoginUserResponse;
import ng.ourChemo.dtos.responses.RegisterUserResponse;

public interface AuthService {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    LogOutUserResponse logOut(LogOutUserRequest logOutUserRequest);

    LoginUserResponse login(LoginUserRequest loginRequest);
}
