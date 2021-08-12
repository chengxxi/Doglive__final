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
    TableData: [],
  },

  community:{
    communityId:"",
    title: "",
    category: "",
    description: ""
  },

  currentPath: "", // 현재 경로의 Path 파트 ex) /login?code=1234 에서 '/login'
  currentPathParam: "", // 현재 경로의 Param 파트 ex) /login?code=1234 에서 'code=1234',

  breadCrumbInfo: {
    isHome: false,
    title: "Home",
    subTitle: "test"
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
    hairType: {},
    neutralization: false,
    userName: true,
    weight: {},
    ageType: {},
    regDate: {},
    readCount: {},
    fileList: [],
    isOwner: false,
    isbookmarked: false
  }
};
