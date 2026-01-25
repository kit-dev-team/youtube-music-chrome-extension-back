package kr.ac.kumoh.s20210041.example.yt_music_extension_back.dto;

import kr.ac.kumoh.s20210041.example.yt_music_extension_back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String email;
    private String name;
    private String picture;
    private String googleSubId;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .googleSubId(googleSubId)
                .build();
    }
}
