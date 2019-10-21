package cehd.globallogic.contest.dto.response;

import cehd.globallogic.contest.dto.request.Phone;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class UserCreateResponse {

    private String id;
    private String email;
    private String name;
    private String password;
    private String token;
    private Boolean isActive;
    private LocalDate modified;
    private LocalDate created;
    private LocalDate lastLogin;
    List<Phone> phoneList;

}
