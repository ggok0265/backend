package com.example.dooor.service;

import com.example.dooor.domain.Ranking;
import com.example.dooor.domain.User;
import com.example.dooor.repository.RankingRepository;
import com.example.dooor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private UserRepository userRepository; // 사용자 정보를 조회하기 위한 UserRepository

    // 전체 랭킹 조회
    public List<Ranking> getAllRankings() {
        return rankingRepository.findAllByOrderByScoreDesc(); // 점수 기준으로 전체 랭킹 조회
    }

    // 사용자 개인 랭킹 조회
    public Optional<Ranking> getUserRanking(Integer userId) {
        List<Ranking> rankings = rankingRepository.findByUserId(userId);
        return rankings.isEmpty() ? Optional.empty() : Optional.of(rankings.get(0)); // 첫 번째 랭킹 반환
    }

    // 랭킹 점수 업데이트
    public boolean updateRankingScore(Integer userId, Integer newScore) {
        List<Ranking> rankings = rankingRepository.findByUserId(userId);
        if (!rankings.isEmpty()) {
            Ranking ranking = rankings.get(0);
            ranking.setScore(newScore); // 새로운 점수 설정
            ranking.setUpdatedAt(LocalDateTime.now()); // 업데이트 시간 설정
            rankingRepository.save(ranking); // 변경 사항 저장
            return true; // 업데이트 성공
        }
        return false; // 사용자 랭킹 없음
    }
}
