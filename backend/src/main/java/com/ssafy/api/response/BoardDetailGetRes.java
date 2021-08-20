package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.board.Board;
import com.ssafy.db.entity.board.BoardComment;
import com.ssafy.db.entity.board.BoardImage;
import com.ssafy.db.entity.board.DogInformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 유기동물 상세페이지에서 필요한 정보
 */


@Getter
@Setter
@ApiModel("BoardDetailGetRes")
public class BoardDetailGetRes extends BaseResponseBody {

    @ApiModelProperty
    boolean isOwner;

    @ApiModelProperty
    boolean isBookmarked;

    @ApiModelProperty
    String writer;

    @ApiModelProperty
    DogInformation dogInformation;

    @ApiModelProperty
    List<String> boardImageList;

    @ApiModelProperty
    List<BoardComment> boardCommentList;

    @ApiModelProperty
    List<Board> listSimilar;

    @ApiModelProperty
    List<DogInformation> listSimilarDog;

    public static BoardDetailGetRes of(Integer statusCode, String message, boolean isBookmarked, boolean isOwner, String writer, DogInformation dogInformation, List<String> boardImageList, List<BoardComment> boardComments, List<Board> listSimilar, List<DogInformation> listSimilarDog) {
        BoardDetailGetRes res = new BoardDetailGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setOwner(isOwner);
        res.setWriter(writer);
        res.setBookmarked(isBookmarked);
        res.setDogInformation(dogInformation);
        res.setBoardImageList(boardImageList);
        res.setBoardCommentList(boardComments);
        res.setListSimilar(listSimilar);
        res.setListSimilarDog(listSimilarDog);
        return res;
    }

}
