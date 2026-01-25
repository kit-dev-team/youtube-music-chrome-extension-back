package kr.ac.kumoh.s20210041.example.yt_music_extension_back.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;
    private String name;
    private String picture;
    private String googleSubId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String name, String picture, String googleSubId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.googleSubId = googleSubId;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .googleSubId((String) attributes.get("sub")) // 구글의 고유 식별자
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // UserService에 전달하기 위해 DTO로 변환
    public UserRequestDto toUserRequestDto() {
        UserRequestDto dto = new UserRequestDto();
        // UserRequestDto에 필드를 채우는 로직 (Setter가 없다면 생성자나 Builder 활용)
        // 기존 UserRequestDto 구조에 맞춰 작성하세요.
        return dto;
        // 참고: 아까 만드신 UserRequestDto에 @AllArgsConstructor나 Builder를 추가하면 편합니다.
    }
}
