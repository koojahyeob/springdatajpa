package study.data_jpa.dto;

import lombok.Data;

@Data // entity에는 @Data 하면 안 됨 (다 상속 받아와서 꼬일 수 있음)
public class MemberDto {

    private  Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
