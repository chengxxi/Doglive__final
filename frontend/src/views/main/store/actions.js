// 비동기 API
import $axios from "axios";
import { getLoginUserInfo } from "./getters";

// Kakao 서버로 받은 code로 AccessToken 가져오기
export function requestAccessToken({ state }, payload) {
  const url = "/kakao/oauth?code=" + payload;
  return $axios.get(url);
}

// Kakao 서버로 받은 AccessToken으로 UserInfo 가져오기
export function requestUserInfo({ state }, payload) {
  const url = "/kakao/login";
  let body = payload;
  console.log(body);
  return $axios.post(url, body);
}

// Kakao 로그아웃
export function requestKakaoLogout({ state }) {
  const url = "/kakao/logout";
  return $axios.get(url);
}

// 채팅방 생성 요청
export function createChatRoom({ state }, payload) {
  const url = "/chatroom";
  let roomName = payload;
  return $axios.post(url, roomName);
}

// 채팅방 입장 요청
// export function enterChatRoom({ state }, payload){
//   const url = '/chatroom'
//   let roomName = payload
//   return $axios.post(url, roomName)
// }

// 채팅방 목록 요청
export function requestChatRoomList({ state }) {
  const url = "/chatroom";
  return $axios.get(url);
}

// 채팅방 이전 로그 목록 요청
export function requestChatMessageList({ state }, payload) {
  console.log("payload : " + payload);
  let roomId = payload.roomId;
  const url = "/chatroom/" + roomId + "/messages/";
  return $axios.get(url);
}

// 사용자 북마크 리스트를 불러오기
export function requestBookmarkList({ state }, payload) {
  const url = "/users/bookmark/" + payload;
  let body = payload;
  return $axios.get(url);
}

// 사용자가 작성한 글 리스트 불러오기
export function requestUserPostList({ state }, payload) {
  const url = "/board/myboard/" + payload;
  let body = payload;
  return $axios.get(url);
}

// 신청자 리스트 불러오기
export function requestApplicant({ state }, payload) {
  const url = "/users/applicant/" + payload;
  let body = payload;
  return $axios.get(url);
}

// 신청 결과 리스트 불러오기
export function requestApplyResult({ state }, payload) {
  const url = "/users/counseling/" + payload;
  let body = payload;
  return $axios.get(url);
}

// 게시판 공고 등록
export function requestRegisterBoard({ state }, payload) {
  const url = "/board";
  console.log(payload);
  return $axios.post(url, payload);
}

//게시판 공고 수정
export function requestModifyBoard({ state }, payload) {
  const url = "/board/" + payload.boardId;
  console.log(payload);
  return $axios.put(url, payload.data);
}

//게시판 공고 삭제
export function requestDeleteBoard({ state }, payload) {
  const url = "/board/" + payload;
  console.log(payload);
  return $axios.delete(url);
}

//게시판 공고 읽기
export function requestReadBoard({ state }, payload) {
  const url = "/board/" + payload.boardId + "/" + payload.userId;
  console.log(payload);
  return $axios.get(url);
}

// 사용자 프로필 정보 가져오기
export function requestUserProfile({ state }, payload) {
  const url = "/users/" + payload;
  return $axios.get(url);
}

// 사용자 프로필 수정
export function changeUserInfo({ state }, payload) {
  const userId = payload.userId;
  console.log(payload.data);
  const url = "/users/" + userId;
  return $axios.put(url, payload.data);
}

// 신청결과 수정
export function changeResult({ state }, payload) {
  const id = payload.id;
  const url = "/adopt/" + id;
  return $axios.put(url, payload.status);
}
//시도코드 리스트
export function requestSidoCodeList({ state }, payload) {
  const url = "/board/sido";
  return $axios.get(url);
}

//시구군 코드 리스트
export function requestGugunCodeList({ state }, payload) {
  const url = "/board/gugun/" + payload;
  return $axios.get(url);
}

//입양신청서 제출
export function registerAdoptForm({ state }, payload) {
  const url = "/adopt/form/" + payload.userId;
  return $axios.post(url, payload.data);
}

//입양신청서 작성했는지 체크
export function existedForm({ state }, payload) {
  const url = "/adopt/check/" + payload.userId + "/" + payload.boardId;
  return $axios.get(url);
}

//입양,임보 게시판 목록 가져오기
export function requestAdoptList({ state }, payload) {
  const url = "/board/adopt";
  return $axios.get(url, {
    params: {
      page: state.board.offset,
      size: state.board.limit,
      searchWord: payload.searchWord,
      age: payload.age,
      weight: payload.weight,
      boardType: payload.boardType,
      gender: payload.gender
    }
  });
}

// MBTI 전체 정보 가져오기
export function requestMbtiList({ state }, payload) {
  const url = '/mbti';
  return $axios.get(url)
}

// MBTI detail 가져오기
export function requestMbtiDetail({ state }, payload) {
  const url = '/mbti/id/' + payload;
  return $axios.get(url)
}
