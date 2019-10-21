package cehd.globallogic.contest.controller;

import cehd.globallogic.contest.dto.request.UserCreateRequest;
import cehd.globallogic.contest.dto.response.UserCreateResponse;
import cehd.globallogic.contest.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;

    private UserService userService = mock(UserService.class);

    @Before
    public void setUp() {
        userController = new UserController(userService);
    }

    @Test
    public void givenAUserCreateRequestThereIsAMethodToHandleThisRequest() {
        UserCreateRequest userCreateRequest = UserCreateRequest.builder().build();
        when(userService.createUser(userCreateRequest)).thenReturn(UserCreateResponse.builder().build());
        UserCreateResponse userCreateResponse = userController.createUser(userCreateRequest);
        Assert.assertNotNull(userCreateResponse);
    }

    @Test
    public void checkServiceIsBeenCalledUsingContract() {
        UserCreateRequest userCreateRequest = UserCreateRequest.builder().build();
        UserCreateResponse mockUserCreateResponse = UserCreateResponse.builder().build();
        when(userService.createUser(userCreateRequest)).thenReturn(mockUserCreateResponse);
        userController.createUser(userCreateRequest);
        verify(userService).createUser(userCreateRequest);
    }


}
