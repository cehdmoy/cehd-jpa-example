package cehd.globallogic.contest.service;

import cehd.globallogic.contest.dto.request.UserCreateRequest;
import cehd.globallogic.contest.dto.response.UserCreateResponse;

public interface UserService {
    UserCreateResponse createUser(UserCreateRequest userCreateRequest);

    UserCreateResponse retrieveUSer(String id);

    void deleteUser(String id);
}
