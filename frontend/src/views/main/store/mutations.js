// setter

export function setAccessToken(state, accessToken){
  state.accessToken = accessToken
}

export function setRefreshToken(state, refreshToken){
  state.refreshToken = refreshToken
}

export function setLoginUserInfo (state, loginUserInfo) {
  state.loginUserInfo = loginUserInfo
}

export function setCurrentPath (state, currentPath) {
  state.currentPath = currentPath
}
