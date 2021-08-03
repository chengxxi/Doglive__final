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

@Getter
@Setter
@ApiModel("BoardDetailGetRes")
public class BoardDetailGetRes extends BaseResponseBody {

    @ApiModelProperty
    boolean isOwner;

    @ApiModelProperty
    String userName;

    @ApiModelProperty
    Board board;

    @ApiModelProperty
    DogInformation dogInformation;

    @ApiModelProperty
    List<BoardImage> boardImageList;

    @ApiModelProperty
    List<BoardComment> boardCommentList;


    public static BoardDetailGetRes of(Integer statusCode, String message, boolean isOwner, String userName, Board board, DogInformation dogInformation, List<BoardImage> boardImageList, List<BoardComment> boardComments) {
        BoardDetailGetRes res = new BoardDetailGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBoard(board);
        res.setDogInformation(dogInformation);
        res.setBoardImageList(boardImageList);
        res.setBoardCommentList(boardComments);
        return res;
    }

}
