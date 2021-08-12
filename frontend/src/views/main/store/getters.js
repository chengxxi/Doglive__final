// getter

// 현재 로그인 된 UserInfo 가져오기
export function getLoginUserInfo(state) {
  console.log("state 내용 : " + state);
  return state.loginUserInfo;
}

// 현재 Current Path 가져오기
export function getCurrentPath(state) {
  return state.currentPath;
}

// 현재 Current Path Param 가져오기
export function getCurrentPathParam(state) {
  return state.currentPathParam;
}

export function getBreadCrumbInfo(state) {
  return state.breadCrumbInfo;
}

//게시판 공고 세부 정보 가져오기
export function getBoardDetail(state) {
  return state.board;
}

//북마크 정보 가져오자
export function getIsbookmarked(state) {
  return state.board.isbookmarked;
}
// 변경할 사용자의 정보 가져오기
export function getUpdateUserInfo(state) {
  return state.userProfile;
}

//현재 게시판 목록 가져오기
export function getBoardList(state) {
  return state.board.list;
}

// 채팅방 정보 여부 가져오기
export function getChat(state) {
  return state.chat;
}
