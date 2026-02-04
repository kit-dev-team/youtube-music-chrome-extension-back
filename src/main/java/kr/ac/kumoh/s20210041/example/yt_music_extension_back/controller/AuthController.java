package kr.ac.kumoh.s20210041.example.yt_music_extension_back.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.dto.UserRequestDto;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.entity.User;
import kr.ac.kumoh.s20210041.example.yt_music_extension_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/google")
    public RedirectView loginWithGoogle() {
        return new RedirectView("/oauth2/authorization/google");
    }

    @GetMapping("/success")
    public RedirectView loginSuccess(@RequestParam String token) {
        return new RedirectView("https://music.youtube.com");
    }

    @PostMapping("/test-login")
    public ResponseEntity<User> testLogin(@RequestBody UserRequestDto requestDto) {
        User savedUser = userService.saveOrUpdate(requestDto);
        return ResponseEntity.ok(savedUser);
    }
}