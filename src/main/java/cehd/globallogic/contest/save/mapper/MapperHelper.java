package cehd.globallogic.contest.save.mapper;

import java.time.LocalDate;

public interface MapperHelper {
    String provideUserID();
    String provideToken();

    LocalDate provideLocalDateNow();
}
