package ng.ourChemo.services;

import ng.ourChemo.data.models.User;
import ng.ourChemo.data.repositories.UserRepository;
import ng.ourChemo.data.repositories.UserRepositoryImpl;
import ng.ourChemo.dtos.requests.LogOutUserRequest;
import ng.ourChemo.dtos.requests.LoginUserRequest;
import ng.ourChemo.dtos.requests.RegisterUserRequest;
import ng.ourChemo.dtos.responses.LogOutUserResponse;
import ng.ourChemo.dtos.responses.LoginUserResponse;
import ng.ourChemo.dtos.responses.RegisterUserResponse;

import static ng.ourChemo.utils.Mappers.*;

public class AuthServiceImpl implements AuthService {
    private final UserRepository users = new UserRepositoryImpl();

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        if(users.findUserName(registerUserRequest.getUserName()) != null)
            throw new IllegalArgumentException("Already registered!!!") ;
        User user = mapToUserRequest(registerUserRequest);
        users.save(user);
        return mapToUserResponse(user);
    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginRequest) {
        User user = users.findUserName(loginRequest.getUserName());
        if(users.findUserName(loginRequest.getUserName()) == null) throw  new IllegalArgumentException("User not found");
        if(!user.getPassword().equals(loginRequest.getPassword())) throw  new IllegalArgumentException("incorrect password");
        user.setLoggedIn(true);
        users.save(user);
        LoginUserResponse response = new LoginUserResponse();
        response.setUserName(user.getUserName());
        return response;
    }

    @Override
    public LogOutUserResponse logOut(LogOutUserRequest logOutUserRequest) {
        User user = users.findUserName(logOutUserRequest.getUserName());
        if(user == null) throw new IllegalArgumentException("Username not found");
        user.setLoggedIn(false);
        users.save(user);
        LogOutUserResponse response = new LogOutUserResponse();
        response.setUserName(user.getUserName());
        return response;
    }

}
