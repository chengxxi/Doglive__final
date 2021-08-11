package com.ssafy.api.service;

import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityComment;
import com.ssafy.db.entity.community.CommunityImage;
import com.ssafy.db.repository.auth.UserRepository;
import com.ssafy.db.repository.community.CommunityCommentRepository;
import com.ssafy.db.repository.community.CommunityImageRepository;
import com.ssafy.db.repository.community.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("communityService")
public class CommunityServiceImpl implements  CommunityService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommunityImageRepository communityImageRepository;

    @Autowired
    CommunityCommentRepository communityCommentRepository;

    /* 커뮤니티 글 작성하기 */
    @Override
    public Community registerCommunityBoard(CommunityRegisterPostReq communityRegisterPostReq) {
        Community community = new Community();
        community.setUserId(communityRegisterPostReq.getUserId());
        community.setTitle(communityRegisterPostReq.getTitle());
        community.setDescription(communityRegisterPostReq.getDescription());
        community.setCategory(communityRegisterPostReq.getCategory());

        communityRepository.save(community);

        return community;
    }
    /* 커뮤니티 글 삭제하기 */
    @Override
    public boolean deleteCommunityBoard(Long id) {
        Community deleteCommunity = communityRepository.findById(id).get();
        if(deleteCommunity!=null){
            deleteAllCommunityCommentsByCommunity(deleteCommunity);
            deleteAllCommunityImagesByCommunity(deleteCommunity);
            communityRepository.delete(deleteCommunity);
        }
        return false;
    }
    /* 커뮤니티 글 수정하기 */
    @Override
    public Community updateCommunityBoard(Long id, CommunityRegisterPostReq communityRegisterPostReq) {
        Optional<Community> community = communityRepository.findById(id);
        if(community.isPresent()){
            community.get().setTitle(communityRegisterPostReq.getTitle());
            community.get().setDescription(communityRegisterPostReq.getDescription());
            communityRepository.save(community.get());
        }
        return community.get();
    }
    /* userId로 커뮤니티 글 찾기 */
    @Override
    public List<Community> getCommunityListByUserId(String userId) {
        Optional<List<Community>> communityList = communityRepository.findCommunitiesByUserId(userId);
        if(communityList.isPresent()){
            return communityList.get();
        }
        return null;
    }

    @Override
    public Community getCommunityById(Long id) {
        Optional<Community> community = communityRepository.findCommunityById(id);
        if(community.isPresent()){
            return community.get();
        }
        return null;
    }


    /* CommunityBoard로 CommunityImage 전부 지우기 */
    @Override
    public void deleteAllCommunityImagesByCommunity(Community community) {
        Optional<List<CommunityImage>> communityImages = communityImageRepository.findCommunityImagesByCommunityId(community);
        if(communityImages.isPresent()){
            for (CommunityImage communityImage: communityImages.get()) {
                communityImageRepository.delete(communityImage);
            }
        }
    }
    /* CommunityBoard로 CommunityComment 전부 지우기 */
    @Override
    public void deleteAllCommunityCommentsByCommunity(Community community) {
        Optional<List<CommunityComment>> communityComments = communityCommentRepository.findCommunityCommentsByCommunityId(community);
        if(communityComments.isPresent()){
            for (CommunityComment communityComment: communityComments.get()) {
                communityCommentRepository.delete(communityComment);
            }
        }
    }

    /* 커뮤니티 게시글 전체 목록 불러오기 */
    @Override
    public List<Community> communityList(){
        List<Community> communityList = communityRepository.findAll();
        System.out.println(communityList);
//        if(communityList.size()!=0){
//            return communityList;
//        }
        return communityList;
    }
}
