package study.data_jpa.entity;


import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Lazy;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from Member m where m.username = :username"
)
@ToString(of = {"id", "username", "age"}) //team은 연관관계 때문에 무한루프 발생할 수 있으므로 안 하는게 나음
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) // JPA에서 연관관계는 전부 LAZY로 해야 성능 최적화하기 수월해짐 (실무에선 무조건 LAZY로 설정)
    @JoinColumn(name = "team_id") // FK로 가져오게 됨
    private Team team; // attribute 하나

    // constructor 단축키 alt + insert
//    protected Member() {
//    }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
