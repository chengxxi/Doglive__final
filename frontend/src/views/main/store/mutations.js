// setter

export function setLoginUserInfo (state, loginUserInfo) {
  state.loginUserInfo.userId = loginUserInfo;

}

export function setCurrentPath (state, currentPath) {
  state.currentPath = currentPath
}

export function setCurrentPathParam (state, currentPathParam) {
  state.currentPathParam = currentPathParam
}

export function setBreadcrumbInfo(state, breadCrumbInfo){
  state.breadCrumbInfo = breadCrumbInfo;
}

export function setBoardDetail(state, payload){
  state.board.boardId = payload.boardId;
  state.board.boardType = payload.boardType;
  state.board.title = payload.title;
  state.board.address = payload.address;
  state.board.mbti = payload.mbti;
  state.board.colorType = payload.colorType;
  state.board.gender = payload.gender;
  state.board.hairType = payload.hairType;
  state.board.neutralization = payload.neutralization;
  state.board.writer = payload.writer;
  state.board.weight = payload.weight;
  state.board.ageType = payload.ageType;
  state.board.regDate = payload.regDate;
  state.board.fileList = payload.fileList;
  state.board.description = payload.description;
  state.board.isOwner = payload.isOwner;
  state.board.thumbnailUrl = payload.thumbnailUrl;
}
