package com.ssafy.api.service;

import com.ssafy.api.request.CommentPostReq;
import com.ssafy.api.request.CommunityParamDto;
import com.ssafy.api.request.CommunityParamDtoInterface;
import com.ssafy.api.request.CommunityRegisterPostReq;
import com.ssafy.db.entity.auth.User;
import com.ssafy.db.entity.auth.UserProfile;
import com.ssafy.db.entity.community.Community;
import com.ssafy.db.entity.community.CommunityComment;
import com.ssafy.db.entity.community.CommunityImage;
import com.ssafy.db.repository.auth.UserProfileRepository;
import com.ssafy.db.repository.auth.UserRepository;
import com.ssafy.db.repository.community.CommunityCommentRepository;
import com.ssafy.db.repository.community.CommunityImageRepository;
import com.ssafy.db.repository.community.CommunityRepository;
import org.h2.store.fs.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("communityService")
public class CommunityServiceImpl implements  CommunityService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommunityImageRepository communityImageRepository;

    @Autowired
    CommunityCommentRepository communityCommentRepository;

    @Autowired
    S3Uploader s3Uploader;

    /* 커뮤니티 글 작성하기 */
    @Override
    public Community registerCommunityBoard(CommunityRegisterPostReq communityRegisterPostReq) throws IOException {


        Community community = new Community();
        community.setUserId(communityRegisterPostReq.getUserId());
        community.setTitle(communityRegisterPostReq.getTitle());
        community.setDescription(communityRegisterPostReq.getDescription());
        community.setCategory(communityRegisterPostReq.getCategory());

        communityRepository.save(community);

        // 이미지 저장
        for(MultipartFile file : communityRegisterPostReq.getFileList()){
            String saveUrl = s3Uploader.upload(file, "static");
            CommunityImage image = new CommunityImage();
//            image.setCommunityId(community);
            image.setFilename(saveUrl);
            image.setFilePath("https://"+S3Uploader.CLOUD_FRONT_DOMAIN_NAME+"/"+saveUrl);
            image.addCommunity(community);
            communityImageRepository.save(image);
        }

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
    public Community updateCommunityBoard(Long id, CommunityRegisterPostReq communityRegisterPostReq) throws IOException {
        Optional<Community> community = communityRepository.findById(id);

        if(community.isPresent()){
            if(communityRegisterPostReq.getDelList()!=null){
                System.out.println("========= delete List ! ");
                deleteSomeCommunityImagesByUrl(communityRegisterPostReq.getDelList());
            }

            if(communityRegisterPostReq.getFileList()!=null){
                for(MultipartFile file : communityRegisterPostReq.getFileList()){
                    String saveUrl = s3Uploader.upload(file, "static");
                    CommunityImage image = new CommunityImage();
//            image.setCommunityId(community);
                    image.setFilename(saveUrl);
                    image.setFilePath("https://"+S3Uploader.CLOUD_FRONT_DOMAIN_NAME+"/"+saveUrl);
                    image.addCommunity(community.get());
                    communityImageRepository.save(image);
                }
            }
            community.get().setTitle(communityRegisterPostReq.getTitle());
            community.get().setCategory(communityRegisterPostReq.getCategory());
            community.get().setDescription(communityRegisterPostReq.getDescription());
            communityRepository.save(community.get());
        }
        return community.get();
    }
    /* userId로 커뮤니티 글 찾기 */
    @Override
    public List<CommunityParamDto> getCommunityListByUserId(String userId) {
        List<Community> userCommunity = communityRepository.findCommunitiesByUserId(userId).get();
        User user = userRepository.findUserById(userId).get();
        UserProfile profile = userProfileRepository.findByUserId(user).get();
        List<CommunityParamDto> dtoList = new ArrayList<>();
        for (Community community:userCommunity) {
            CommunityParamDto dto = new CommunityParamDto(null,null,null,null,null,null,null);
            List<CommunityImage> communityImages = communityImageRepository.findCommunityImagesByCommunityId(community).get();
            List<String> fileList = new ArrayList<>();
            for (CommunityImage communityImage: communityImages) {
                String filePath = communityImage.getFilePath();
                fileList.add(filePath);
            }
            dto.setUserId(userId);
            dto.setName(profile.getName());
            dto.setDescription(community.getDescription());
            dto.setProfileImageUrl(profile.getProfileImageUrl());
            dto.setTitle(community.getTitle());
            dto.setCategory(community.getCategory());
            dto.setId(community.getId());
            dto.setFileList(fileList);
            dtoList.add(dto);
        }
        if(dtoList!=null){
            Collections.reverse(dtoList);
            return dtoList;
        }
        return null;
    }

    @Override
    public Community getCommunityById(Long id) {
        Optional<Community> community = communityRepository.findCommunityByIdOrderByIdDesc(id);
        if(community.isPresent()){
            return community.get();
        }
        return null;
    }

    @Override
    public List<CommunityImage> getCommunityImagesByCommunity(Community community) {
        Optional<List<CommunityImage>> communityImageList = communityImageRepository.findCommunityImagesByCommunityId(community);
        if(communityImageList.isPresent()){
            return communityImageList.get();
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
                s3Uploader.delete(communityImage.getFilename());
            }
        }
    }

    @Override
    public void deleteSomeCommunityImagesByUrl(List<String> delList) {
        if(delList!=null){
            for (String url: delList) {
                Optional<List<CommunityImage>> delImgList = communityImageRepository.findCommunityImagesByFilePath(url);
                if(delImgList.isPresent()){
                    for (CommunityImage image: delImgList.get()) {
                        communityImageRepository.delete(image);
                        s3Uploader.delete(image.getFilename());
                    }
                }
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
    public List<CommunityParamDto> communityList(int page){
        PageRequest pageRequest = PageRequest.of(page,10);
        System.out.println("page : " + page + " pageRequest: " + pageRequest);
        Page<CommunityParamDtoInterface> communityList = communityRepository.findAllDesc(pageRequest).orElse(null);

        List<CommunityParamDto> list = new ArrayList<CommunityParamDto>(); // DB에서 받아온 community 값을 저장할 배열
        for (CommunityParamDtoInterface dto : communityList) {
            CommunityParamDto paramDto = new CommunityParamDto(dto.getId(), dto.getDescription(), dto.getTitle(), dto.getUser_Id(), dto.getName(), dto.getProfile_image_url(), dto.getCategory());
            Community community = communityRepository.findCommunityByIdOrderByIdDesc(dto.getId()).get();
            List<CommunityImage> communityImages = getCommunityImagesByCommunity(community);
            List<String> fileList = new ArrayList<>();
            for (CommunityImage communityImage: communityImages) {
                String filePath = communityImage.getFilePath();
                fileList.add(filePath);
            }
            paramDto.setFileList(fileList);
            list.add(paramDto);
        }
        System.out.println("Total Pages : " + communityList.getTotalPages());
        System.out.println("Total Count : " + communityList.getTotalElements());
        System.out.println("Next : " + communityList.nextPageable());

        return list;
    }

    @Override
    public CommunityComment addComment(CommentPostReq commentPostReq) {
        CommunityComment comment = new CommunityComment();
        Community community = communityRepository.findCommunityByIdOrderByIdDesc(commentPostReq.getCommunityId()).get();

        comment.setCommunityId(community);
        comment.setUserId(commentPostReq.getUserId());

        User user = userRepository.findUserById(commentPostReq.getUserId()).get(); // userID를 통해 username 받아오기
        String username = userProfileRepository.findByUserId(user).get().getName();
        comment.setName(username);
        comment.setComment(commentPostReq.getComment());
        comment.setIsDelete(true);

        CommunityComment newComment = communityCommentRepository.save(comment);

        return newComment;
    }

    @Override
    public void deleteComment(Long id) {
        CommunityComment communityComment = communityCommentRepository.findById(id).get();
        communityComment.setIsDelete(false);

        communityCommentRepository.save(communityComment);
    }

    @Override
    public List<CommunityComment> commentList(Long id) {
        Community community = communityRepository.findCommunityByIdOrderByIdDesc(id).get();
        List<CommunityComment> communityCommentList = communityCommentRepository.findCommunityCommentsByCommunityId(community).get();

        return communityCommentList;
    }

    @Override
    public List<CommunityParamDto> getThreeCommunities() {
        List<CommunityParamDto> communities = new ArrayList<>();
        List<CommunityParamDto> AllList = communityList(0);
        if(AllList.size()>=3){
            for(int i=0; i<3; i++){
                System.out.println(AllList.get(i));
                List<String> fileList = new ArrayList<>();
                Community community = getCommunityById(AllList.get(i).getId());
                List<CommunityImage> images = getCommunityImagesByCommunity(community);
                for (CommunityImage communityImage: images) {
                    String filePath = communityImage.getFilePath();
                    fileList.add(filePath);
                }
                CommunityParamDto dto = AllList.get(i);
                dto.setFileList(fileList);
                communities.add(dto);
            }
        }else{
            for(int i=0; i<AllList.size(); i++){
                System.out.println(AllList.get(i));
                List<String> fileList = new ArrayList<>();
                Community community = getCommunityById(AllList.get(i).getId());
                List<CommunityImage> images = getCommunityImagesByCommunity(community);
                for (CommunityImage communityImage: images) {
                    String filePath = communityImage.getFilePath();
                    fileList.add(filePath);
                }
                CommunityParamDto dto = AllList.get(i);
                dto.setFileList(fileList);
                communities.add(dto);
            }
        }
        return communities;
    }
}

