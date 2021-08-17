<template>
  <el-header class="main-header" :style="{ height: height }">
    <!-- Desktop 전용 화면 -->
    <div class="hide-on-small">
      <div class="menu-wrapper">
        <ul class="depth0" @mouseleave="changeDisplay('leave')">
          <li></li>
          <li>
            <div class="logo" @click="clickLogo"></div>
          </li>
          <li>
            <a href="/adopt" @mouseover="changeDisplay('over')">입양/임보</a>
          </li>

          <li>
            <a href="/find" @mouseover="changeDisplay('over')">실종/보호</a>
          </li>
          <li>
            <a href="/mbti" @mouseover="changeDisplay('over')">MPTI</a>
          </li>
          <li>
            <a href="/community" @mouseover="changeDisplay('over')">독립신문</a>
          </li>
          <li>
            <!-- User 아이콘에 마우스 hover했을 때 나올 popover 정의 -->
            <el-popover
              placement="bottom"
              width="200"
              trigger="hover"
              v-model="state.showUserModal"
            >
              <table :style="{ margin: '0 auto' }">
                <tr
                  v-for="(path, key, index) in state.userMenu"
                  v-bind:key="index"
                >
                  <td>
                    <a :href="path">{{ key }}</a>
                  </td>
                </tr>
              </table>

              <template #reference>
                <i
                  class="el-icon-s-custom userBtn"
                  @click="changeUserModal"
                ></i>
              </template>
            </el-popover>
          </li>
          <li></li>
        </ul>
      </div>
    </div>
  </el-header>
</template>

<style scoped>
.main-header {
  z-index: 100; /* 헤더 맨위 고정 */
  position: sticky;
  top: 0;
  left: 0;
  text-align: center;
  overflow: visible;
}
.main-header .hide-on-small .menu-wrapper {
  width: 100%;
}
/* 상단 메뉴 */
.main-header .hide-on-small .menu-wrapper .depth0 {
  display: table;
  table-layout: fixed;
  width: 100%;
  font-size: 1rem;
  font-weight: 600;
  background-color: rgba(249, 240, 231, 0.6);
}
.main-header .hide-on-small .menu-wrapper .depth0 ul {
  width: 100%;
  list-style: none;
}
.main-header .hide-on-small .menu-wrapper .depth0 > li {
  display: table-cell;
  line-height: 70px;
  vertical-align: top;
}
.main-header .hide-on-small .menu-wrapper .depth0 a:hover {
  color: #d7aea4;
}
/* 하단 메뉴 */
.main-header .hide-on-small .menu-wrapper .depth1 {
  text-align: center;
  margin-bottom: 20px;
  font-size: 1rem;
  font-weight: 500;
}
.main-header .hide-on-small .menu-wrapper .depth1 > li {
  width: 100%;
  line-height: 40px;
}
.main-header .hide-on-small .logo {
  width: 150px;
  height: 50px;
  margin-top: 10px;
  margin-left: 20px;
  cursor: pointer;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url("../../../assets/images/logo2.png");
}
.main-header .hide-on-small .userBtn {
  cursor: pointer;
}
</style>

<script>
import { computed, reactive } from "vue"; // watchEffect, ref
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import Cookies from "universal-cookie";

export default {
  name: "main-header",
  props: {
    height: {
      type: String,
      default: "70px"
    }
  },

  setup() {
    const store = useStore();
    const router = useRouter();
    const cookies = new Cookies();

    console.log(cookies.get("loginUserInfo"));

    const notLoggedIn = {
      로그인: "/login"
    };
    const loggedIn = {
      마이페이지: "/mypage/profile",
      로그아웃: "/userLogout"
    };
    const state = reactive({
      showHiddenMenu: "none",
      showUserModal: false,
      userMenu: computed(() => {
        const userId = store.getters["root/getLoginUserInfo"].userId;
        if (userId === null || userId === undefined || userId == "") {
          return notLoggedIn;
        } else {
          return loggedIn;
        }
      })
    });

    const clickLogo = function() {
      router.push({ name: "Main" }); // vue-router.js 밑에 정의해둔 메인페이지 경로로 이동
    };
    const changeUserModal = function() {
      state.showUserModal = !state.showUserModal;
    };
    const changeDisplay = function(mouse) {
      if (mouse === "over") state.showHiddenMenu = "block";
      else state.showHiddenMenu = "none";
    };

    return { state, clickLogo, changeUserModal, changeDisplay };
  }
};
</script>
