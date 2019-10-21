package cehd.globallogic.contest.save.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    private String token;
    private Boolean isActive;
    private LocalDate modified;
    private LocalDate created;
    private LocalDate lastLogin;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    Set<PhoneEntity> phoneEntityHashSet;
}
