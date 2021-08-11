import { createRouter, createWebHistory } from "vue-router";
import Main from "@/views/main/components/main-content.vue";
import Login from "@/views/main/components/login-dialog.vue";
import Logout from "@/views/main/components/logout-dialog.vue";
import Mypage from "@/views/user/mypage.vue";
import Conference from "@/views/main/components/conference.vue";
import KakaoCallback from "@/views/main/components/kakao-callback.vue";
import Conferenceroom from "@/views/conferences/conferenceroom.vue";
import Chat from "@/views/chat/chat.vue";
import AdoptDetail from "@/views/adopt/adopt-detail.vue";
import AdoptDetailTest from "@/views/adopt/adopt-detail-test.vue";
import Adopt from "@/views/adopt/adopt.vue";
import AdoptRegister from "@/views/adopt/adopt-register.vue";
import AdoptModify from "@/views/adopt/adopt-modify.vue";
import AdoptForm from "@/views/adopt/adopt-form.vue";
import FindDetail from "@/views/find/find-detail.vue";
import FindRegister from "@/views/find/find-register.vue";
import FindModify from "@/views/find/find-modify.vue";
import Community from "@/views/community/community.vue";
import MyCommunity from "@/views/community/mycommunity.vue";

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main
    // beforeEnter: requireAuth,
  },
  {
    path: "/conference",
    name: "conference",
    component: Conference
  },
  {
    path: "/conference/room",
    name: "conferenceroom",
    component: Conferenceroom
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
    path: "/chat",
    redirect: "/chat/rooms",
    name: "Chat",
    component: Chat,
    children: [
      {
        path: "rooms",
        name: "chat-list",
        component: () => import("@/views/chat/components/chat-list.vue")
      },
      {
        path: "room",
        name: "chat-detail",
        component: () => import("@/views/chat/components/chat-detail.vue")
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
    path: "/adopt/detail-test",
    name: "AdoptDetailTest",
    component: AdoptDetailTest
  },
  {
    path: "/adopt/register",
    name: "AdoptRegister",
    component: AdoptRegister
  },
  {
    path: "/adopt/form",
    name: "AdoptForm",
    component: AdoptForm
  },
  {
    path: "/adopt/modify",
    name: "AdoptModify",
    component: AdoptModify
  },
  {
    path: "/find/detail",
    name: "FindDetail",
    component: FindDetail
  },
  {
    path: "/find/register",
    name: "FindRegister",
    component: FindRegister
  },
  {
    path: "/find/modify",
    name: "FindModify",
    component: FindModify
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
        component: () => import("@/views/community/components/community-board-list.vue")
      },
      {
        path: "register",
        name: "community-board-register",
        component: () => import("@/views/community/components/community-board-register.vue")
      },
      {
        path: "update",
        name: "community-board-update",
        component: () => import("@/views/community/components/community-board-update.vue")
      },
    ]
  },
  {
    path: "/community/mylist",
    name: "MyCommunity",
    component : MyCommunity,
    component: () => import("@/views/community/mycommunity.vue")
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

// URL이 변경되기전 거쳐가는 함수
router.beforeEach(function(to, from, next) {
  next();
});

export default router;
