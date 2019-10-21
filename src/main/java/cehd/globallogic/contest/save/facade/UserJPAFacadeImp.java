package cehd.globallogic.contest.save.facade;

import cehd.globallogic.contest.save.entity.PhoneEntity;
import cehd.globallogic.contest.save.entity.User;
import cehd.globallogic.contest.save.exception.UserEmailTwiceException;
import cehd.globallogic.contest.save.repository.PhoneRepository;
import cehd.globallogic.contest.save.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserJPAFacadeImp {

    private UserRepository userRepository;
    private PhoneRepository phoneRepository;

    public UserJPAFacadeImp(UserRepository userRepository, PhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }



    public PhoneEntity createPhone(PhoneEntity phoneEntity) {
        return phoneRepository.saveAndFlush(phoneEntity);
    }

    public User createUser(User build) {
        Optional<User> byEmail = userRepository.findByEmail(build.getEmail());
        if (emailDoesNotHaveBeenRegistredBefore(byEmail)) {
            return userRepository.saveAndFlush(build);
        }
        throw new UserEmailTwiceException();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public Optional<User> retrieveUserFromDb(String id){
        return userRepository.findById(id);
    }

    private boolean emailDoesNotHaveBeenRegistredBefore(Optional<User> byEmail) {
        return !byEmail.isPresent();
    }


}
