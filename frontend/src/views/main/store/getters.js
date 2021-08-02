// getter
// AccessToken 가져오기
export function getAccessToken(state){
  return state.accessToken
}

// RefreshToken
export function getRefreshToken(state){
  return state.refreshToken
}

// 현재 로그인 된 UserInfo 가져오기
export function getLoginUserInfo(state) {
  return state.loginUserInfo
}

// 현재 Current Path 가져오기
export function getCurrentPath(state) {
  return state.currentPath
}
