package study.data_jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.data_jpa.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository { // Rename 단축키 -> shift + f6

    @PersistenceContext
    private EntityManager em;

    //CRUD 진행

    // CREATE
    public Team save(Team team) {
        em.persist(team);
        return team;
    }

    // Delete
    public void delete(Team team) {
        em.remove(team);
    }

    // READ
    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class)
                .getResultList();
    }

    public Optional<Team> findById(Long id) {
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }

    public long count() {
        return em.createQuery("select count(t) from Team t", Long.class)
                .getSingleResult();
    }

    // UPDATE -> 값 바꿔주기만 하면 알아서 변경됨
    // JPA는 기본적으로 엔터티 변경할 때 변경 감지 기능으로 데이터 바꿈
}
