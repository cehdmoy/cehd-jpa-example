package cehd.globallogic.contest.save.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class MapperHelperImp implements MapperHelper {
    @Override
    public String provideUserID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String provideToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public LocalDate provideLocalDateNow() {
        return LocalDate.now();
    }
}
