package kr.ac.kumoh.s20210041.example.yt_music_extension_back.repository;

import kr.ac.kumoh.s20210041.example.yt_music_extension_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
