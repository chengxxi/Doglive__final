package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.auth.Bookmark;
import com.ssafy.db.entity.board.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("BookmarkListGetResponse")
public class BookmarkListGetRes extends BaseResponseBody {
    @ApiModelProperty
    List<Bookmark> bookmarkList;

    @ApiModelProperty
    int size;

    public static BookmarkListGetRes of(Integer statusCode, String message, List<Bookmark> bookmarkList, Integer size) {
        BookmarkListGetRes res = new BookmarkListGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setBookmarkList(bookmarkList);
        res.setSize(size);
        return res;
    }

}
