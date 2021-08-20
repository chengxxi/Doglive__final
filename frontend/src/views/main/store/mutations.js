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
  state.board.sido = payload.sido;
  state.board.gugun = payload.gugun;
  state.board.listSimilarDog = payload.listSimilarDog;
}
export function setProfile(state, payload) {
  state.userProfile.profileImageUrl = payload.profileImageUrl;
  state.userProfile.name = payload.name;
  state.userProfile.email = payload.email;
  state.userProfile.birth = payload.birth;
  state.userProfile.phoneNumber = payload.phoneNumber;
}

export function setBoardId(state, payload) {
  state.board.boardId = payload;
}

export function setCommunityBoard(state, payload) {
  state.community.communityId = payload.communityId;
  state.community.title = payload.title;
  state.community.category = payload.category;
  state.community.description = payload.description;
  state.community.fileList = payload.fileList;
}

export function setChatOpen(state, payload) {
  state.chat.open = payload;
}

export function setChatTitle(state, payload) {
  state.chat.title = payload;
}

export function setChatRoomId(state, payload) {
  state.chat.roomId = payload;
}

export function setChatMenu(state, payload) {
  state.chat.menu = payload;
}

export function setExitMessage(state, payload) {
  state.chat.exitMessage = payload;
}

export function setAdoptFormId(state, payload) {
  state.adoptForm.formId = payload;
}

export function setChatParticipants(state, payload) {
  state.chat.participants.user1 = payload.user1;
  state.chat.participants.user2 = payload.user2;
}

export function setConference(state, payload) {
  state.conference.roomId = payload.roomId;
  state.conference.title = payload.title;
  state.conference.fromChat = payload.fromChat;
  // conference: {
  //   title:'',
  //   thumbnailUrl:'',
  //   writer:'',    // 글 작성자
  //   reader:'',    // 신청자
  // }
}

export function setMbtiDetail(state, data) {
  state.mbti.id = data.id;
  state.mbti.title = data.title;
  state.mbti.name = data.name;
  state.mbti.desc = data.desc;
  state.mbti.image_url = data.imageUrl;
  state.mbti.matchedBoardList = data.matchedBoardList;
}
