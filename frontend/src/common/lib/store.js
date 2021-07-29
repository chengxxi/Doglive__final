import { createStore } from "vuex";
import root from '@/views/main/store'

export default createStore({
  modules: { root },
   // store를 localStorage에 저장하기 위한 플러그인
});
