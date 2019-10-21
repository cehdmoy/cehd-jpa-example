package cehd.globallogic.contest.save.repository;

import cehd.globallogic.contest.save.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {
}
