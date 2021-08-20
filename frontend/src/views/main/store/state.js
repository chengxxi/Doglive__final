// 저장소

export default {
  loginUserInfo: {
    userId: ""
  },

  userProfile: {
    profileImageUrl: "",
    name: "",
    email: "",
    birth: "",
    phoneNumber: ""
  },

  writeList: {},

  bookmark: {
    TableData: []
  },

  community: {
    communityId: "",
    title: "",
    category: "",
    description: "",
    fileList: []
  },

  currentPath: "", // 현재 경로의 Path 파트 ex) /login?code=1234 에서 '/login'
  currentPathParam: "", // 현재 경로의 Param 파트 ex) /login?code=1234 에서 'code=1234',

  breadCrumbInfo: {
    isHome: false,
    title: "Home",
    subTitle: "test",
    path: "/"
  },

  board: {
    // detail, update, delete
    boardId: 0,
    boardType: {},
    dogName: "",
    thumbnailUrl: "",
    title: "",
    address: "",
    mbti: "",
    description: "",
    colorType: {},
    gender: {},
    dogType: {},
    neutralization: false,
    userName: true,
    weight: {},
    ageType: {},
    regDate: {},
    readCount: {},
    fileList: [],
    isOwner: false,
    isbookmarked: false,
    sido: {},
    gugun: {},
    listSimilarDog: []
  },

  chat: {
    open: false,
    menu: 0 /* 0: 채팅 목록, 1: 채팅방, 2: 화상회의 목록 */,
    title: "",
    roomId: "",
    exitMessage: false
  },

  adoptForm: {
    formId: "",
    roomId: ""
  },

  conference: {
    roomId: "",
    title: "",
    thumbnailUrl: "",
    writer: "", // 글 작성자
    reader: "", // 신청자
    fromChat: false
  },

  adoptForm: {
    formId: "",
    roomId: ""
  },

  conference: {
    roomId: "",
    title: "",
    thumbnailUrl: "",
    writer: "", // 글 작성자
    reader: "", // 신청자
    fromChat: false
  },

  mbti: {
    id: "",
    name: "",
    title: "",
    desc: "",
    image_url: "",
    matchedBoardList: []
  }
};
