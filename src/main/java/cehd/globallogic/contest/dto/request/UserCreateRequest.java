
package cehd.globallogic.contest.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class UserCreateRequest {
    @Pattern(regexp = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$")
    private String email;
    private String name;
    @Pattern(regexp = "^((.*?[A-Z])+(.*?[0-9]){2,}.*$)|(^(.*?[0-9]){2,}(.*?[A-Z])+.*$)")
    private String password;
    private List<Phone> phones;

}
