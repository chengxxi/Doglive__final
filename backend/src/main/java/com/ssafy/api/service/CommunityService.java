package com.ssafy.api.service;

import com.ssafy.api.request.CommentPostReq;
import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityComment;

import java.util.List;

public interface CommunityService {

    /* 커뮤니티 글 작성하기 */
    Community registerCommunityBoard(CommunityRegisterPostReq communityRegisterPostReq);

    /* 커뮤니티 글 삭제하기 */
    boolean deleteCommunityBoard(Long id);

    /* 커뮤니티 글 수정하기 */
    Community updateCommunityBoard(Long id,CommunityRegisterPostReq communityRegisterPostReq );

    /* userId로 커뮤니티 글 찾기 */
    List<Community> getCommunityListByUserId(String userId);

    /* Id로 커뮤니티 글 찾기 */
    Community getCommunityById(Long id);

    /* CommunityBoard로 CommunityImage 전부 지우기 */
    void deleteAllCommunityImagesByCommunity(Community community);

    /* CommunityBoard로 CommunityComment 전부 지우기 */
    void deleteAllCommunityCommentsByCommunity(Community community);

    /* community 모든 게시글 출력 */
    List<CommunityParamDto> communityList(int page);

    /* 댓글 달기 기능 */
    CommunityComment addComment(CommentPostReq commentPostReq);

    /* 댓글 삭제 기능 */
    void deleteComment(Long id);

}
