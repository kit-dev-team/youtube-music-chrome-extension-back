package kr.ac.kumoh.s20210041.example.yt_music_extension_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertUserRequest {
    private String email;
    private String name;
    private String picture;
    private String googleSubId;
}
