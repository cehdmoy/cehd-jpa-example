
package cehd.globallogic.contest.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Phone {

    private String citycode;
    private String contrycode;
    private String number;

}
