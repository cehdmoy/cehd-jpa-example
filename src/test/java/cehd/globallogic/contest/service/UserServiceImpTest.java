package cehd.globallogic.contest.service;

import cehd.globallogic.contest.save.facade.UserJPAFacadeImp;
import cehd.globallogic.contest.save.mapper.MapperSaveCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class UserServiceImpTest {

    private UserService userService;

    private UserJPAFacadeImp userJPAFacadeImp = mock(UserJPAFacadeImp.class);

    private MapperSaveCase mapperSaveCase=mock(MapperSaveCase.class);

    @Before
    public void setUp() {
        userService = new UserServiceImp(mapperSaveCase,userJPAFacadeImp);
    }

    @Test
    public void isCLassThere() {
        Assert.assertNotNull(userService);
    }
}
