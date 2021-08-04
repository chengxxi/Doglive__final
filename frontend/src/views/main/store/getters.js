// getter

// 현재 로그인 된 UserInfo 가져오기
export function getLoginUserInfo(state) {
  console.log("state 내용 : " + state.loginUserInfo)
  return state.loginUserInfo
}

// 현재 Current Path 가져오기
export function getCurrentPath(state) {
  return state.currentPath
}

// 현재 Current Path Param 가져오기
export function getCurrentPathParam(state) {
  return state.currentPathParam
}

export function getBreadCrumbInfo(state) {
  return state.breadCrumbInfo
}

//게시판 공고 세부 정보 가져오기
export function getBoardDetail(state) {
  return state.board
}
