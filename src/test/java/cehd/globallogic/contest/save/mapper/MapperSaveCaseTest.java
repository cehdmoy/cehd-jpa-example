package cehd.globallogic.contest.save.mapper;

import cehd.globallogic.contest.dto.request.Phone;
import cehd.globallogic.contest.dto.request.UserCreateRequest;
import cehd.globallogic.contest.dto.response.UserCreateResponse;
import cehd.globallogic.contest.save.entity.PhoneEntity;
import cehd.globallogic.contest.save.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapperSaveCaseTest {

    private static final String FAKE_UUID = "FAKE_UUID";
    private static final String USER_EMAIL = "rmoya@rmoya.com";
    private static final String USER_NAME = "Roberto";
    private static final String FAKE_PASS_USER = "FAKE_PASS_USER";
    private static final String FAKE_TOKEN_USER = "FAKE_TOKEN_USER";

    private MapperSaveCase mapperSaveCase;

    private LocalDate mockLocalDateNow;

    @Before
    public void setUp() {
        mockLocalDateNow = LocalDate.now();
        MapperHelper mapperHelper = new TestMapperHelper();
        mapperSaveCase = new MapperSaveCase(mapperHelper);
    }

    @Test
    public void isClassThere() {
        Assert.assertNotNull(mapperSaveCase);
    }

    @Test
    public void checkMapperFromInputDTOToUserEntityThereIsNullPhoneListFromInput() {
        UserCreateRequest userCreateRequest = UserCreateRequest
                .builder()
                .email(USER_EMAIL)
                .name(USER_NAME)
                .password(FAKE_PASS_USER)
                .build();
        User user = mapperSaveCase.mapFromUserCreateRequestToUser(userCreateRequest);
        Assert.assertEquals(FAKE_UUID, user.getId());
        Assert.assertEquals(USER_EMAIL, user.getEmail());
        Assert.assertEquals(USER_NAME, user.getName());
        Assert.assertEquals(FAKE_PASS_USER, user.getPassword());
        Assert.assertEquals(FAKE_TOKEN_USER, user.getToken());
        Assert.assertEquals(true, user.getIsActive());
        Assert.assertEquals(mockLocalDateNow, user.getModified());
        Assert.assertEquals(mockLocalDateNow, user.getCreated());
        Assert.assertEquals(mockLocalDateNow, user.getLastLogin());
        Assert.assertNotNull(user.getPhoneEntityHashSet());
        Assert.assertEquals(0, user.getPhoneEntityHashSet().size());
    }


    @Test
    public void checkMapperFromInputDTOToUserEntityThereIsPhoneListFromInput() {
        UserCreateRequest userCreateRequest = UserCreateRequest
                .builder()
                .email(USER_EMAIL)
                .name(USER_NAME)
                .password(FAKE_PASS_USER)
                .phones(buildPhoneList())
                .build();
        User user = mapperSaveCase.mapFromUserCreateRequestToUser(userCreateRequest);
        Assert.assertEquals(FAKE_UUID, user.getId());
        Assert.assertEquals(USER_EMAIL, user.getEmail());
        Assert.assertEquals(USER_NAME, user.getName());
        Assert.assertEquals(FAKE_PASS_USER, user.getPassword());
        Assert.assertEquals(FAKE_TOKEN_USER, user.getToken());
        Assert.assertEquals(true, user.getIsActive());
        Assert.assertEquals(mockLocalDateNow, user.getModified());
        Assert.assertEquals(mockLocalDateNow, user.getCreated());
        Assert.assertEquals(mockLocalDateNow, user.getLastLogin());
        Set<PhoneEntity> phoneEntityHashSet = user.getPhoneEntityHashSet();
        Assert.assertNotNull(phoneEntityHashSet);
        Assert.assertEquals(2, phoneEntityHashSet.size());
    }



    @Test
    public void mapFromUserToUserCreateResponse() {
        User user = User
                .builder()
                .id(FAKE_UUID)
                .email(USER_EMAIL)
                .name(USER_NAME)
                .password(FAKE_PASS_USER)
                .token(FAKE_TOKEN_USER)
                .isActive(true)
                .lastLogin(mockLocalDateNow)
                .modified(mockLocalDateNow)
                .created(mockLocalDateNow)
                .build();
        UserCreateResponse userCreateResponse = mapperSaveCase.mapFromUserToCreateUserResponse(user);
        Assert.assertNotNull(userCreateResponse);
        Assert.assertEquals(FAKE_UUID, userCreateResponse.getId());
        Assert.assertEquals(USER_EMAIL, userCreateResponse.getEmail());
        Assert.assertEquals(USER_NAME, userCreateResponse.getName());
        Assert.assertEquals(FAKE_PASS_USER, userCreateResponse.getPassword());
        Assert.assertEquals(FAKE_TOKEN_USER, userCreateResponse.getToken());
        Assert.assertEquals(true, userCreateResponse.getIsActive());
        Assert.assertEquals(mockLocalDateNow, userCreateResponse.getLastLogin());
        Assert.assertEquals(mockLocalDateNow, userCreateResponse.getModified());
        Assert.assertEquals(mockLocalDateNow,userCreateResponse.getCreated());
    }

    @Test
    public void mapFromUserToUserCreateResponseForANullSetFromUser() {
        User user = User
                .builder()
                .id(FAKE_UUID)
                .email(USER_EMAIL)
                .name(USER_NAME)
                .password(FAKE_PASS_USER)
                .token(FAKE_TOKEN_USER)
                .isActive(true)
                .lastLogin(mockLocalDateNow)
                .modified(mockLocalDateNow)
                .created(mockLocalDateNow)
                .build();
        UserCreateResponse userCreateResponse = mapperSaveCase.mapFromUserToCreateUserResponse(user);
        Assert.assertNotNull(userCreateResponse.getPhoneList());
    }

    @Test
    public void mapFromUserToUserCreateResponseForAEmptySetFromUser() {
        User user = User
                .builder()
                .id(FAKE_UUID)
                .email(USER_EMAIL)
                .name(USER_NAME)
                .password(FAKE_PASS_USER)
                .token(FAKE_TOKEN_USER)
                .isActive(true)
                .lastLogin(mockLocalDateNow)
                .modified(mockLocalDateNow)
                .created(mockLocalDateNow)
                .phoneEntityHashSet(new HashSet<>())
                .build();
        UserCreateResponse userCreateResponse = mapperSaveCase.mapFromUserToCreateUserResponse(user);
        Assert.assertNotNull(userCreateResponse.getPhoneList());
    }

    @Test
    public void mapFromUserToUserCreateResponseForASetFromUser() {
        User user = User
                .builder()
                .id(FAKE_UUID)
                .email(USER_EMAIL)
                .name(USER_NAME)
                .password(FAKE_PASS_USER)
                .token(FAKE_TOKEN_USER)
                .isActive(true)
                .lastLogin(mockLocalDateNow)
                .modified(mockLocalDateNow)
                .created(mockLocalDateNow)
                .phoneEntityHashSet(buildSetPhoneNumbers())
                .build();
        UserCreateResponse userCreateResponse = mapperSaveCase.mapFromUserToCreateUserResponse(user);
        List<Phone> phoneList = userCreateResponse.getPhoneList();
        Assert.assertNotNull(phoneList);
        Assert.assertEquals(2,phoneList.size());
    }

    private Set<PhoneEntity> buildSetPhoneNumbers() {
        Set<PhoneEntity> set= new HashSet<>();
        PhoneEntity phone1 = PhoneEntity.builder().citycode("22").contrycode("+56").number("123456789").build();
        PhoneEntity phone2 = PhoneEntity.builder().citycode("46").contrycode("+56").number("123456789").build();
        set.add(phone1);
        set.add(phone2);
        return set;
    }

    private List<Phone> buildPhoneList() {
        List<Phone> phones = new ArrayList<>();
        Phone phone1 = Phone.builder().citycode("22").contrycode("+56").number("123456789").build();
        Phone phone2 = Phone.builder().citycode("46").contrycode("+56").number("123456789").build();
        phones.add(phone1);
        phones.add(phone2);
        return phones;
    }

    class TestMapperHelper implements MapperHelper {
        @Override
        public String provideUserID() {
            return FAKE_UUID;
        }

        @Override
        public String provideToken() {
            return FAKE_TOKEN_USER;
        }

        @Override
        public LocalDate provideLocalDateNow() {
            return mockLocalDateNow;
        }
    }
}
