package kr.ac.kumoh.s20210041.example.yt_music_extension_back.service;

import jakarta.transaction.Transactional;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.dto.UpsertUserRequest;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.entity.User;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User saveOrUpdate(UpsertUserRequest requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .map(entity -> entity.update(requestDto.getName(), requestDto.getPicture()))
                .orElseGet(() -> User.builder()
                        .email(requestDto.getEmail())
                        .name(requestDto.getName())
                        .picture(requestDto.getPicture())
                        .googleSubId(requestDto.getGoogleSubId())
                        .build());

        return userRepository.save(user);
    }
}
