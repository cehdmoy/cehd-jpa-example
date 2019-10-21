package cehd.globallogic.contest.service;

import cehd.globallogic.contest.dto.request.UserCreateRequest;
import cehd.globallogic.contest.dto.response.UserCreateResponse;
import cehd.globallogic.contest.save.entity.User;
import cehd.globallogic.contest.save.facade.UserJPAFacadeImp;
import cehd.globallogic.contest.save.mapper.MapperSaveCase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private MapperSaveCase mapperSaveCase;

    private UserJPAFacadeImp userJPAFacadeImp;

    public UserServiceImp(MapperSaveCase mapperSaveCase, UserJPAFacadeImp userJPAFacadeImp) {
        this.mapperSaveCase = mapperSaveCase;
        this.userJPAFacadeImp = userJPAFacadeImp;
    }

    @Override
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        User user = mapperSaveCase.mapFromUserCreateRequestToUser(userCreateRequest);
        User userFromJPA = userJPAFacadeImp.createUser(user);
        return mapperSaveCase.mapFromUserToCreateUserResponse(userFromJPA);
    }

    @Override
    public UserCreateResponse retrieveUSer(String id) {
        Optional<User> user = userJPAFacadeImp.retrieveUserFromDb(id);
        return user.map(userToMap -> mapperSaveCase.mapFromUserToCreateUserResponse(userToMap))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void deleteUser(String id) {
        userJPAFacadeImp.deleteUser(id);
    }
}
