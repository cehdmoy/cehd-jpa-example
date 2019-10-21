package cehd.globallogic.contest.save.mapper;

import cehd.globallogic.contest.dto.request.Phone;
import cehd.globallogic.contest.dto.request.UserCreateRequest;
import cehd.globallogic.contest.dto.response.UserCreateResponse;
import cehd.globallogic.contest.save.entity.PhoneEntity;
import cehd.globallogic.contest.save.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MapperSaveCase {

    private MapperHelper mapperHelper;

    public MapperSaveCase(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }

    public User mapFromUserCreateRequestToUser(UserCreateRequest userCreateRequest) {
        User user = buildBasicUserData(userCreateRequest);
        addPhonesToUser(userCreateRequest, user);
        return user;
    }

    public UserCreateResponse mapFromUserToCreateUserResponse(User user) {
        return UserCreateResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .token(user.getToken())
                .isActive(user.getIsActive())
                .lastLogin(user.getLastLogin())
                .modified(user.getModified())
                .created(user.getCreated())
                .phoneList(buildPhoneList(user.getPhoneEntityHashSet()))
                .build();
    }

    private List<Phone> buildPhoneList(Set<PhoneEntity> phoneEntityHashSet) {
      if(null!=phoneEntityHashSet)
        return phoneEntityHashSet.stream()
                .map(buildPhoneListFromPhoneSet())
                .collect(Collectors.toList());
      else
        return new ArrayList<>();
    }

    private Function<PhoneEntity, Phone> buildPhoneListFromPhoneSet() {
        return c->
        Phone
                .builder()
                .contrycode(c.getContrycode())
                .citycode(c.getCitycode())
                .number(c.getNumber())
                .build();
    }

    private void addPhonesToUser(UserCreateRequest userCreateRequest, User user) {
        Set<PhoneEntity> phoneEntities = mapPhonListToPhoneEntitySet(userCreateRequest, user);

        phoneEntities.forEach(c -> user.getPhoneEntityHashSet().add(c));
    }

    private User buildBasicUserData(UserCreateRequest userCreateRequest) {
        return User.builder()
                .id(mapperHelper.provideUserID())
                .email(userCreateRequest.getEmail())
                .name(userCreateRequest.getName())
                .password(userCreateRequest.getPassword())
                .token(mapperHelper.provideToken())
                .phoneEntityHashSet(new HashSet<>())
                .isActive(true)
                .modified(mapperHelper.provideLocalDateNow())
                .created(mapperHelper.provideLocalDateNow())
                .lastLogin(mapperHelper.provideLocalDateNow())
                .build();
    }

    private Set<PhoneEntity> mapPhonListToPhoneEntitySet(UserCreateRequest userCreateRequest, User user) {
        List<Phone> phones = userCreateRequest.getPhones();
        if (phones != null) {
            return phones
                    .stream()
                    .map(buildPhoneData(user))
                    .collect(Collectors.toSet());
        } else
            return new HashSet<>();
    }

    private Function<Phone, PhoneEntity> buildPhoneData(User user) {
        return c -> PhoneEntity
                .builder()
                .citycode(c.getCitycode())
                .contrycode(c.getContrycode())
                .number(c.getNumber())
                .user(user)
                .build();
    }



}
