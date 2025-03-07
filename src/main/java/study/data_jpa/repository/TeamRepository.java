package study.data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.entity.Team;

// Spring Data JPA가 자동으로 다 상속받아서 기능들 수행한다.
public interface TeamRepository extends JpaRepository<Team, Long> { //<Entity 이름, entity에 mapping된 PK의 type
}
