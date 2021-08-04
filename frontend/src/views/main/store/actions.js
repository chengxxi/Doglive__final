// 비동기 API
import $axios from 'axios'

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

// 채팅방 생성 요청
export function createChatRoom({ state }, payload){
  const url = '/chatroom'
  let roomName = payload
  return $axios.post(url, roomName)
}
