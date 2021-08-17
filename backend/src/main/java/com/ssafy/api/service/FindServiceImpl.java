package com.ssafy.api.service;


import com.ssafy.db.entity.board.*;
import com.ssafy.db.repository.board.BoardCategoryRepository;
import com.ssafy.db.repository.board.BoardRepository;
import com.ssafy.db.repository.board.DogInformationRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("findService")
public class FindServiceImpl implements FindService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    DogInformationRepository dogInformationRepository;

    @Autowired
    BoardCategoryRepository boardCategoryRepository;
    /* 실종보호 게시물 전체 목록 보기 */
    @Override
    public List<Board> getFindBoardList() {
        Optional<List<Board>> boardList = boardRepository.findFindBoard();

        if(boardList.isPresent()) return boardList.get();

        return null;
    }


    @Override
    public List<DogInformation> getBoardSimilarListByBoard(Board board) {
        Optional<BoardCategory> boardCategory = boardCategoryRepository.findById(board.getType().getId() == (long)3 ? (long)4 : (long)3);
        System.out.println("찾을 공고 타입 ==> " + boardCategory.get().getId() + ", " + boardCategory.get().getName());

        Optional<DogInformation> boardDogInfo = dogInformationRepository.findDogInformationByBoardId(board);

        // 자신이 작성한 글이라면 유사 공고에 뜨지 않도록!
        String userId = board.getUserId();
        Code gender = boardDogInfo.get().getGender();
        DogType sort = boardDogInfo.get().getDogType(); // 변경해아할 부분 -> 품종으로 변경
        Long sidoCode = boardDogInfo.get().getGugun().getSidoCode().getId();

        System.out.println("현재 공고 강아지 성별 : " + gender.getName());
        System.out.println("현재 공고 강아지 종 : " + sort.getName());
        System.out.println("현재 공고 강아지 시도 코드 : " + sidoCode);

        // 실종 -> 보호 공고 / 보호 -> 실종 공고 가져오기 V
        // 성별 / 품종 / 시로 거르기(아예 후보에서 제외) (sql)
        // 순서대로 공고 타입 / 성별 / 품종 / 시도
        Optional<List<Board>> listByOption = boardRepository.findBoardsByType(boardCategory.get(), userId, gender, sort, sidoCode);

        // 색 / 나이 / 무게 / 구로 점수 부여
        // [] : 옵셥 / [] 옵션 항목 / [] 각 옵션 점수
        int[][][] scoreByOptions = new int[4][][];

        // 색
        scoreByOptions[0] = new int[5][5];

        scoreByOptions[0][0] = new int[]{35, 20, 0, 0, 0};          // 흰
        scoreByOptions[0][1] = new int[]{20, 35, 15, 0, 0};         // 베
        scoreByOptions[0][2] = new int[]{0, 15, 35, 0, 0};          // 갈
        scoreByOptions[0][3] = new int[]{0, 0, 0, 35, 20};          // 회
        scoreByOptions[0][4] = new int[]{0, 0, 0, 20, 35};          // 검

        // 나이
        scoreByOptions[1] = new int[4][4];
        scoreByOptions[1][0] = new int[]{15, 10, 5, 0};              // Puppy
        scoreByOptions[1][1] = new int[]{10, 15, 10, 5};             // Junior
        scoreByOptions[1][2] = new int[]{5, 10, 15, 10};             // Adult
        scoreByOptions[1][3] = new int[]{0, 10, 10, 15};             // Senior

        // 무게
        scoreByOptions[2] = new int[3][3];
        scoreByOptions[2][0] = new int[]{15, 10, 0};                 // 소
        scoreByOptions[2][1] = new int[]{10, 15, 5};                 // 중
        scoreByOptions[2][2] = new int[]{0, 5, 15};                  // 대

        // 구
        scoreByOptions[3] = new int[1][2];
        scoreByOptions[3][0] = new int[]{35, 15};                    // 같으면 35점 / 다르면 15점

        // 리턴할 데이터
        List<Board> listSimilar = new ArrayList<>();
        List<DogInformation> listSimilarDog = new ArrayList<>();
        List<DogInfoWithScore> listDogByScore = new ArrayList<>();
        // 기준이 될 색, 나이, 무게, 구
        int colorIndex = Math.toIntExact(boardDogInfo.get().getColorType().getId()) == 17 ? 17 : Math.toIntExact(boardDogInfo.get().getColorType().getId()) - 12;
        int ageIndex = Math.toIntExact(boardDogInfo.get().getAge().getId()) == 17 ? 17 : Math.toIntExact(boardDogInfo.get().getAge().getId()) - 4;
        int weightIndex = Math.toIntExact(boardDogInfo.get().getWeight().getId()) == 17 ? 17 : Math.toIntExact(boardDogInfo.get().getWeight().getId()) - 1;
        int gugunIndex = Math.toIntExact(boardDogInfo.get().getGugun().getId());
        System.out.println("현재 공고에 있는 강아지 특징 >>> 컬러 : " + boardDogInfo.get().getColorType().getName()
                +" / 나이 : "+ boardDogInfo.get().getAge().getName() + " / 무게  : " + boardDogInfo.get().getWeight().getName()
                +" / 구  : "+ boardDogInfo.get().getGugun().getName());

        if(colorIndex == 17 || ageIndex == 17 || weightIndex == 17) return null;
        for(int i = 0; i<listByOption.get().size();i++) {
            // 각 공고에 맞는 강아지 정보 불러오기
            int score = 0;
            DogInformation curInfo = dogInformationRepository.findDogInformationByBoardId(listByOption.get().get(i)).get();
            
            // code 17 : 기타 => 기타에 대한 처리
            int curColor = Math.toIntExact(curInfo.getColorType().getId()) == 17 ? 17 : Math.toIntExact(curInfo.getColorType().getId()) - 12;
            int curAge = Math.toIntExact(curInfo.getAge().getId()) == 17 ? 17 : Math.toIntExact(curInfo.getAge().getId()) - 4;
            int curWeight = Math.toIntExact(curInfo.getWeight().getId()) == 17 ? 17 : Math.toIntExact(curInfo.getWeight().getId()) - 1;
            int curGugun = Math.toIntExact(curInfo.getGugun().getId());

            System.out.println("비교 공고에 있는 강아지 특징 >>> 컬러 : " + curInfo.getColorType().getName()
                    +" / 나이 : "+ curInfo.getAge().getName() + " / 무게  : " + curInfo.getWeight().getName()
                    +" / 구  : "+ curInfo.getGugun().getName());

            // 기타가 있다면 점수를 부여하지 않는다.
            if(curColor == 17) {
                System.out.println("색이 기타");
                continue;
            }
            if(curAge == 17) {
                System.out.println("나이가 기타");
                continue;
            }
            if(curWeight == 17) {
                System.out.println("무게가 기타");
                continue;
            }
            if(scoreByOptions[0][colorIndex][curColor] == 0) {
                System.out.println("색에서 걸렸습니다.");
                continue;
            }
            if(scoreByOptions[1][ageIndex][curAge] == 0) {
                System.out.println("나이에서 걸렸습니다.");
                continue;
            }
            if(scoreByOptions[2][weightIndex][curWeight] == 0) {
                System.out.println("무게에서 걸렸습니다.");
                continue;
            }
            
            score += scoreByOptions[0][colorIndex][curColor];
            score += scoreByOptions[1][ageIndex][curAge];
            score += scoreByOptions[2][weightIndex][curWeight];


            if(gugunIndex == curGugun) {
                score += scoreByOptions[3][0][0];
            } else {
                score += scoreByOptions[3][0][1];
            }

            System.out.println("해당 강아지 점수 >>> " + score);

            // 공고 강아지의 종이 => 믹스이면 기준 점수 70점
            if(sort.getName().equals("믹스")) {
                if(score >= 70) {
                    listDogByScore.add(new DogInfoWithScore(curInfo, score));
                }
            } else {    // => 종이 있다면 기준 점수 60점
                if(score >= 60) {
                    listDogByScore.add(new DogInfoWithScore(curInfo, score));
                }
            }
        }

        // 점수순으로 내림차순
        Collections.sort(listDogByScore);
        for(int i = 0; i<listDogByScore.size();i++) {
            if(i == 10) break;      // 유사 공고 10개 이상 => 점수순으로 잘라서 10개만 선별
            listSimilarDog.add(listDogByScore.get(i).dogInfo);
        }

        if(listSimilarDog.size() != 0) {
            return listSimilarDog;
        } else return null;
    }

    // 강아지 정보와 각 점수를 함께 저장할 클래스
    static class DogInfoWithScore implements Comparable<DogInfoWithScore>{
        DogInformation dogInfo;
        int score;
        public DogInfoWithScore(DogInformation dogInfo, int score) {
            this.dogInfo = dogInfo;
            this.score = score;
        }


        @Override
        public int compareTo(DogInfoWithScore o) {
            return o.score - this.score;
        }
    }
}
