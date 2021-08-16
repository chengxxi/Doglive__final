package com.ssafy.api.service;

import com.ssafy.api.request.CommentPostReq;
import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityComment;
import com.ssafy.db.entity.community.CommunityImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CommunityService {

    /* 커뮤니티 글 작성하기 */
    Community registerCommunityBoard(CommunityRegisterPostReq communityRegisterPostReq) throws IOException;

    /* 커뮤니티 글 삭제하기 */
    boolean deleteCommunityBoard(Long id);

    /* 커뮤니티 글 수정하기 */
    Community updateCommunityBoard(Long id,CommunityRegisterPostReq communityRegisterPostReq ) throws IOException;

    /* userId로 커뮤니티 글 찾기 */
    List<CommunityParamDto> getCommunityListByUserId(String userId);

    /* Id로 커뮤니티 글 찾기 */
    Community getCommunityById(Long id);

    /* Community로 CommunityImage 찾기 */
    List<CommunityImage> getCommunityImagesByCommunity(Community community);

    /* CommunityBoard로 CommunityImage 전부 지우기 */
    void deleteAllCommunityImagesByCommunity(Community community);

    void deleteSomeCommunityImagesByUrl(List<String> delList);

    /* CommunityBoard로 CommunityComment 전부 지우기 */
    void deleteAllCommunityCommentsByCommunity(Community community);

    /* community 모든 게시글 출력 */
    List<CommunityParamDto> communityList(int page);

    /* 댓글 달기 */
    CommunityComment addComment(CommentPostReq commentPostReq);

    /* 댓글 삭제 */
    void deleteComment(Long id);

    List<CommunityComment> commentList(Long id);

    /* 메인화면에 띄울 최신 커뮤니티 3건 불러오기 */
    List<CommunityParamDto> getThreeCommunities();



}

