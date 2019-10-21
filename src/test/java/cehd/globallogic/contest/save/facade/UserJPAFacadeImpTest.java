package cehd.globallogic.contest.save.facade;

import cehd.globallogic.contest.save.entity.PhoneEntity;
import cehd.globallogic.contest.save.entity.User;
import cehd.globallogic.contest.save.exception.UserEmailTwiceException;
import cehd.globallogic.contest.save.repository.PhoneRepository;
import cehd.globallogic.contest.save.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJPAFacadeImpTest {
    @Autowired
    private UserJPAFacadeImp userJPAFacadeImp;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @After
    public void tearDown() {
        userRepository.deleteAll();
        phoneRepository.deleteAll();
    }

    @Test
    public void contextIsFineToCreateBean() {
        Assert.assertNotNull(userJPAFacadeImp);
    }

    @Test
    public void createBasicUser() {
        User build = User.builder()
                .id("qwerty")
                .email("rmoya@rmoya.cl")
                .isActive(true)
                .name("Roberto")
                .password("Dañdslfkj1234r")
                .token("asñdfljhdlfksjghajdsñfjkv-frgtfrdew")
                .modified(LocalDate.now())
                .build();
        User user = userJPAFacadeImp.createUser(build);
        Assert.assertNotNull(user);
    }


    @Test(expected = UserEmailTwiceException.class)
    public void dontLetCreateUserWithSameEmailTwice() {
        User build1 = User.builder()
                .id("qwerty")
                .email("rmoya@rmoya.cl")
                .isActive(true)
                .name("Roberto")
                .password("Dañdslfkj1234r")
                .token("asñdfljhdlfksjghajdsñfjkv-frgtfrdew")
                .build();

        User build2 = User.builder()
                .id("qwertysedgf")
                .email("rmoya@rmoya.cl")
                .isActive(true)
                .name("Roberto Moya")
                .password("alñdsjfalñsf")
                .token("-frgtfrdew")
                .build();

        userJPAFacadeImp.createUser(build1);
        userJPAFacadeImp.createUser(build2);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void notAllowToCreateAPhoneNumberByItself() {
        PhoneEntity build = PhoneEntity.builder().build();
        userJPAFacadeImp.createPhone(build);
    }

    @Test
    public void InsertUserWithBasicPhoneCase() {
        User userToInsert = User.builder()
                .id("qwerty")
                .phoneEntityHashSet(new HashSet<>())
                .email("rmoya@rmoya.cl")
                .isActive(true)
                .name("Roberto")
                .password("Dañdslfkj1234r")
                .token("asñdfljhdlfksjghajdsñfjkv-frgtfrdew")
                .modified(LocalDate.now())
                .build();

        PhoneEntity phoneEntity1 = PhoneEntity.builder().number("12345").user(userToInsert).build();
        PhoneEntity phoneEntity2 = PhoneEntity.builder().number("54321").user(userToInsert).build();

        userToInsert.getPhoneEntityHashSet().add(phoneEntity1);
        userToInsert.getPhoneEntityHashSet().add(phoneEntity2);

        User user = userJPAFacadeImp.createUser(userToInsert);
        Assert.assertNotNull(user);
    }
}
