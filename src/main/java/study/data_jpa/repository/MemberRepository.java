package study.data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.data_jpa.dto.MemberDto;
import study.data_jpa.entity.Member;

//import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

// 인터페이스 형태로만 되어있고, 구현체가 없음
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Query MEthod로 구현 가능하다. 단순히 바로 구현 가능
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(name = "Member.findByUsername") // 이 line 없어도 동일하게 실행 됨.
    List<Member> findByUsername(@Param("username") String username);
    // Member entity를 보면 jpql 형식으로 (:username)으로 되어 있어서 @Param 써줘야 함.

    // jpql 바로 입력 가능
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username")String username, @Param("age") int age);

    // username list 조회하는 거는 이걸로 끝임.
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.data_jpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names); //List 아닌 상위의 Collection으로

    // 반환 타입을 정말 유용하게 사용할 수 있음.
    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username); // 단건
    Optional<Member> findOptionalByUsername(String username); // 단건 Optional

    // 인터페이스로만 해도, 알아서 pageable interface만 넘기는 바로 사용가능
//    Page<Member> findByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m left join m.team t")
    Page<Member> findByAge(int age, Pageable pageable);
}


