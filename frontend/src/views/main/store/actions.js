// 비동기 API
import $axios from 'axios'
import { getLoginUserInfo } from './getters'

// Kakao 서버로 받은 code로 AccessToken 가져오기
export function requestAccessToken({ state }, payload){
  const url = '/kakao/oauth?code=' + payload
  return $axios.get(url)
}

// Kakao 서버로 받은 AccessToken으로 UserInfo 가져오기
export function requestUserInfo({ state }, payload){
  const url = '/kakao/login'
  let body = payload
  console.log(body)
  return $axios.post(url, body)
}

// Kakao 로그아웃
export function requestKakaoLogout({ state }){
  const url = '/kakao/logout'
  return $axios.get(url)
}

export function requestBookmarkList({ state },payload){
  const url = '/api/v1/users/bookmark/' + payload;
  let body = payload;
  console.log(body);
  return $axios.get(url)
}
