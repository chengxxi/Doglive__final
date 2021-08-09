package com.ssafy.api.service;

import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.community.Community;

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

    /* CommunityBoard로 CommunityImage 전부 지우기 */
    void deleteAllCommunityImagesByCommunity(Community community);

    /* CommunityBoard로 CommunityComment 전부 지우기 */
    void deleteAllCommunityCommentsByCommunity(Community community);

}
