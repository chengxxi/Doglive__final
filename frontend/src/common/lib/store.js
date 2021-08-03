import { createStore } from "vuex";
import root from '@/views/main/store'
import VuexPersistence from "vuex-persist";

const vuexLocal = new VuexPersistence({
  storage: window.localStorage,
  supportCircular: true,
});

export default createStore({
  modules: { root },
  plugins: [ vuexLocal.plugin ],
});
