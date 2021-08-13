// setter

export function setIsbookmarked(state, isbookmarked) {
  state.board.isbookmarked = isbookmarked;
}

export function setLoginUserInfo(state, loginUserInfo) {
  state.loginUserInfo.userId = loginUserInfo;
}

export function setCurrentPath(state, currentPath) {
  state.currentPath = currentPath;
}

export function setCurrentPathParam(state, currentPathParam) {
  state.currentPathParam = currentPathParam;
}

export function setBreadcrumbInfo(state, breadCrumbInfo) {
  state.breadCrumbInfo = breadCrumbInfo;
}

export function setBoardDetail(state, payload) {
  state.board.boardId = payload.boardId;
  state.board.boardType = payload.boardType;
  state.board.title = payload.title;
  state.board.address = payload.address;
  state.board.mbti = payload.mbti;
  state.board.colorType = payload.colorType;
  state.board.gender = payload.gender;
  state.board.dogType = payload.dogType;
  state.board.neutralization = payload.neutralization;
  state.board.writer = payload.writer;
  state.board.weight = payload.weight;
  state.board.ageType = payload.ageType;
  state.board.regDate = payload.regDate;
  state.board.fileList = payload.fileList;
  state.board.description = payload.description;
  state.board.isOwner = payload.isOwner;
  state.board.thumbnailUrl = payload.thumbnailUrl;
  state.board.dogName = payload.dogName;
  state.board.isbookmarked = payload.isBookmarked;
}
export function setProfile(state, payload) {
  state.userProfile.imgURL = payload.imgURL;
  state.userProfile.name = payload.name;
  state.userProfile.email = payload.email;
  state.userProfile.birth = payload.birth;
  state.userProfile.phoneNumber = payload.phoneNumber;
}

export function setBoardList(state, payload) {
  state.board.list = payload;
}

export function setBoardTotalListItemCnt(state, count) {
  state.board.totalListItemCount = count;
}

export function setBoardMovePage(state, index) {
  state.board.offset = (index - 1) * state.board.listRowCount;
  state.board.currentPageIndex = index;
}

export function setMbtiDetail(state, data) {
  state.mbti.id = data.id;
  state.mbti.title = data.title;
  state.mbti.name = data.name;
  state.mbti.desc = data.desc;
  state.mbti.image_url = data.imageUrl;
}
