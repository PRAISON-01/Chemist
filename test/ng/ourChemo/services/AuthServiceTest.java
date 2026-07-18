package ng.ourChemo.services;

import ng.ourChemo.data.models.User;
import ng.ourChemo.data.repositories.UserRepository;
import ng.ourChemo.data.repositories.UserRepositoryImpl;
import ng.ourChemo.dtos.requests.LogOutUserRequest;
import ng.ourChemo.dtos.requests.LoginUserRequest;
import ng.ourChemo.dtos.requests.RegisterUserRequest;
import ng.ourChemo.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    private UserRepository users;
    private AuthService authService;
    private RegisterUserRequest request;

    @BeforeEach
    public void setUp() {
        users = new UserRepositoryImpl();
        users.deleteAll();
        authService = new AuthServiceImpl();
        request = new RegisterUserRequest();
    }

    @Test
    public void newAuthService_saveUser_userCountIs1() {
        request.setUserName("Praise");
        request.setFullName("Praise Nwankwo");
        request.setPassword("password");

        authService.register(request);
        assertEquals(1, users.count());
    }


    @Test
    public void newAuthServiceTwice_saveUser_throwsError_userCountIs1() {
        AuthService authService = new AuthServiceImpl();
        RegisterUserRequest request = new RegisterUserRequest();

        request.setUserName("@praise");
        request.setFullName("Praise Nwankwo");
        request.setPassword("password");

        authService.register(request);

        request.setUserName("@praise");
        request.setFullName("Praise Nwankwo");
        request.setPassword("password");
        assertThrows(IllegalArgumentException.class, ()-> authService.register(request));
        assertEquals(1, users.count());
    }

    @Test
    public void newAuthService_saveUser_loginUser_IsLoggedIn_True() {
        request.setUserName("@praise");
        request.setFullName("Praise Nwankwo");
        request.setPassword("password");

        RegisterUserResponse result = authService.register(request);
        assertEquals(1,  users.count());

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUserName("@praise");
        loginRequest.setPassword("password");
        authService.login(loginRequest);
        User user = users.findUserName("@praise");
        assertTrue(user.isLoggedIn());
    }

    @Test
    public void newAuthService_loginUser_throwException() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUserName("@praise");
        loginRequest.setPassword("password");
        assertThrows(IllegalArgumentException.class, ()-> authService.login(loginRequest));
    }

    @Test
    public void newAuthService_saveUser_loginUser_WrongPassword_throwException() {
        request.setFullName("Praise Nwankwo");
        request.setUserName("@praise");
        request.setPassword("password");

        authService.register(request);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUserName("@praise");
        loginUserRequest.setPassword("pass");
        assertThrows(IllegalArgumentException.class, ()-> authService.login(loginUserRequest));
    }

    @Test
    public void newAuthService_saveUser_LoginUser_LogOutUser_wrongUserName() {
        request.setFullName("Praise Nwankwo");
        request.setUserName("@praise");
        request.setPassword("password");

        authService.register(request);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUserName("@praise");
        loginUserRequest.setPassword("password");

        authService.login(loginUserRequest);
        User user = users.findUserName("@Praise");
        assertTrue(user.isLoggedIn());

        LogOutUserRequest logOutUserRequest = new LogOutUserRequest();
        logOutUserRequest.setUserName("@prais");
        assertThrows(IllegalArgumentException.class, ()-> authService.logOut(logOutUserRequest));

    }

    @Test
    public void newAuthService_saveUser_LoginUser_LogOutUser_isLoggedIn_False() {
        request.setFullName("Praise Nwankwo");
        request.setUserName("@praise");
        request.setPassword("password");

        authService.register(request);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUserName("@praise");
        loginUserRequest.setPassword("password");

        authService.login(loginUserRequest);
        User user = users.findUserName("@Praise");
        assertTrue(user.isLoggedIn());

        LogOutUserRequest logOutUserRequest = new LogOutUserRequest();
        logOutUserRequest.setUserName("@praise");
        authService.logOut(logOutUserRequest);
        assertFalse(user.isLoggedIn());
    }

}
