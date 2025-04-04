package com.example.dooor.user.domain.repository;

import com.example.dooor.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email); // 이메일로 사용자 검색
//    Optional<User> findByGoogleId(String googleId); // 구글 ID로 사용자 검색
}
