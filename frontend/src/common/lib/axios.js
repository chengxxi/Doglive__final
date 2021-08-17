
import VueAxios from 'vue-axios'
import axios from 'axios'
import store from './store'
import { createToast } from 'mosha-vue-toastify'

const BASE_URL = "/api/v1";
const DEFAULT_ACCEPT_TYPE = "application/json";

axios.defaults.baseURL = BASE_URL
axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE
axios.defaults.withCredentials = true;

axios.interceptors.response.use((response) => response, (error) => {
  console.log(error.response.status)

  if(error.response.status === 403){ // 로그인이 정상적으로 되어있지 않았다면

    // 스토어 초기화 (로그아웃과 마찬가지로)
    store.commit("root/setLoginUserInfo", null);
    const boardDetail = {
      boardId: 1,
      boardType: 1,
      dogName: "",
      thumbnailUrl: "",
      title: "",
      address: "",
      mbti: "",
      description: "",
      colorType: {},
      gender: {},
      hairType: {},
      neutralization: {},
      userName: "",
      weight: {},
      ageType: {},
      regDate: {},
      readCount: {},
      fileList: [],
      isOwner: false,
      isbookmarked: false
    };
    store.commit("root/setBoardDetail", boardDetail);
    store.commit("root/setChatOpen", false)
    store.commit("root/setChatMenu", 0);
    store.commit("root/setChatTitle", "");
    store.commit("root/setChatRoomId", "");
    store.commit("root/setExitMessage", false);

    window.location.href='/login'

    createToast("로그인해야 이용 가능하개🐕‍🦺💨", {
      hideProgressBar: "true",
      timeout: 4500,
      showIcon: "true",
      toastBackgroundColor: "#c49d83",
      position: "bottom-left",
      transition: "bounce",
      type: "warning"
    });
  }
  throw error;
});

export default { VueAxios, axios };
