// 저장소

export default {
  loginUserInfo: {
    userId: ""
  },

  userProfile: {
    imageURL: "",
    name: "",
    email: "",
    birth: "",
    phoneNumber: ""
  },

  writeList: {},

  bookmark: {
    TableData: []
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
    // list
    list: [],
    limit: 12,
    offset: 0,
    searchWord: "",

    // pagination
    listRowCount: 12,
    pageLinkCount: 12,
    currentPageIndex: 1,

    totalListItemCount: 0,

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
    isbookmarked: false
  },

  chat: {
    open: false,
    menu: 0 /* 0: 채팅 목록, 1: 채팅방, 2: 화상회의 목록 */,
    title: "",
    roomId: ""
  }
};
