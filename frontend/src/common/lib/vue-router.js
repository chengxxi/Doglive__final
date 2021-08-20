import { createRouter, createWebHistory } from "vue-router";
import { storeKey, useStore } from "vuex";
import Main from "@/views/main/components/main-content.vue";
import Login from "@/views/main/components/login-dialog.vue";
import Logout from "@/views/main/components/logout-dialog.vue";
import Mypage from "@/views/user/mypage.vue";
import Conference from "@/views/conferences/conference.vue";
import KakaoCallback from "@/views/main/components/kakao-callback.vue";
import AdoptDetail from "@/views/adopt/adopt-detail.vue";
import Adopt from "@/views/adopt/adopt.vue";
import AdoptRegister from "@/views/adopt/adopt-register.vue";
import AdoptModify from "@/views/adopt/adopt-modify.vue";
import AdoptForm from "@/views/adopt/adopt-form.vue";
import AdoptFormConfirm from "@/views/adopt/adopt-form-confirm.vue";
import AdoptFormReview from "@/views/adopt/adopt-form-review.vue";
import Find from "@/views/find/find.vue";
import FindDetail from "@/views/find/find-detail.vue";
import FindRegister from "@/views/find/find-register.vue";
import FindModify from "@/views/find/find-modify.vue";
import MBTI from "@/views/mbti/mbti.vue";
import MBTItest from "@/views/mbti/components/mbti-test.vue";
import MBTIinfo from "@/views/mbti/components/mbti-info.vue";
import MbtiDetail from "@/views/mbti/components/mbti-detail.vue";
import Community from "@/views/community/community.vue";
import store from '../../views/main/store/index.js'

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main
  },
  {
    path: "/conference",
    name: "conference",
    component: Conference,
    // meta: { requiredAuth: true },
    meta: { requiredFromChat: true }
    // beforeEnter: function(to, from, next) {
    //   console.log(to)
    //   console.log(store.state.conference);
    //   if(store.state.conference.fromChat) {
    //     next();
    //   } else {
    //     next('/');
    //   }
    // }
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/userLogout",
    name: "UserLogout",
    component: Logout
  },
  {
    path: "/kakao/callback",
    name: "KakaoCallback",
    component: KakaoCallback
  },
  {
    path: "/mypage",
    redirect: "/mypage/profile",
    name: "Mypage",
    component: Mypage,
    meta: { requiresAuth: true },
    children: [
      {
        path: "profile",
        name: "mypage-profile",
        component: () => import("@/views/user/components/user-profile.vue")
      },
      {
        path: "mypost",
        name: "mypage-mypost",
        component: () => import("@/views/user/components/user-post-list.vue")
      },
      {
        path: "bookmark",
        name: "mypage-bookmark",
        component: () => import("@/views/user/components/user-bookmark.vue")
      },
      {
        path: "applicantList",
        name: "mypage-applicant-list",
        component: () =>
          import("@/views/user/components/user-applicant-list.vue")
      },
      {
        path: "applyResultList",
        name: "mypage-result-list",
        component: () =>
          import("@/views/user/components/user-apply-result-list.vue")
      }
    ]
  },
  {
    path: "/adopt",
    name: "Adopt",
    component: Adopt
  },
  {
    path: "/adopt/detail",
    name: "AdoptDetail",
    component: AdoptDetail
  },
  {
    path: "/adopt/register",
    name: "AdoptRegister",
    component: AdoptRegister,
    meta: { requiresAuth: true }
  },
  {
    path: "/adopt/form",
    name: "AdoptForm",
    component: AdoptForm,
    meta: { requiresAuth: true }
  },
  {
    path: "/adopt/form/confirm",
    name: "AdoptFormConfirm",
    component: AdoptFormConfirm,
    meta: { requiresAuth: true }
  },
  {
    path: "/adopt/form/review",
    name: "AdoptFormReview",
    component: AdoptFormReview,
    meta: { requiresAuth: true }
  },
  {
    path: "/adopt/modify",
    name: "AdoptModify",
    component: AdoptModify,
    meta: { requiresAuth: true }
  },
  {
    path: "/find",
    name: "Find",
    component: Find
  },
  {
    path: "/find/detail",
    name: "FindDetail",
    component: FindDetail
  },
  {
    path: "/find/register",
    name: "FindRegister",
    component: FindRegister,
    meta: { requiresAuth: true }
  },
  {
    path: "/find/modify",
    name: "FindModify",
    component: FindModify,
    meta: { requiresAuth: true }
  },
  {
    path: "/community",
    redirect: "/community/list",
    name: "Community",
    component: Community,
    children: [
      {
        path: "list",
        name: "community-board-list",
        component: () =>
          import("@/views/community/components/community-board-list.vue")
      },
      {
        path: "register",
        name: "community-board-register",
        meta: { requiresAuth: true },
        component: () =>
          import("@/views/community/components/community-board-register.vue")
      },
      {
        path: "update",
        name: "community-board-update",
        meta: { requiresAuth: true },
        component: () =>
          import("@/views/community/components/community-board-update.vue")
      },
      {
        path: "mycommunity",
        name: "community-mycommunity",
        meta: { requiresAuth: true },
        component: () =>
          import("@/views/community/components/community-mycommunity.vue")
      }
    ]
  },

  {
    path: "/community",
    redirect: "/community/list",
    name: "Community",
    component: Community,
    children: [
      {
        path: "list",
        name: "community-board-list",
        component: () =>
          import("@/views/community/components/community-board-list.vue")
      },
      {
        path: "register",
        name: "community-board-register",
        component: () =>
          import("@/views/community/components/community-board-register.vue")
      },
      {
        path: "update",
        name: "community-board-update",
        component: () =>
          import("@/views/community/components/community-board-update.vue")
      },
      {
        path: "mycommunity",
        name: "community-mycommunity",
        component: () =>
          import("@/views/community/components/community-mycommunity.vue")
      }
    ]
  },

  {
    path: "/mbti", // introduction page
    name: "MBTI",
    component: MBTI
  },
  {
    path: "/mbti/test", // test page
    name: "MBTItest",
    component: MBTItest
  },
  {
    path: "/mbti/info", // information page
    name: "MBTIinfo",
    component: MBTIinfo
  },
  {
    path: "/mbtis/detail",
    name: "MbtiDetail",
    component: MbtiDetail
  },
  {
    path: "/notfound",
    name: "NotFound",
    component: () => import("@/views/main/components/not-found.vue")
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: '/notfound',
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
