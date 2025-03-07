package study.data_jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // 연결해주는 mappedBy 적용할 때, FK가 없는 entity에 작성
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
