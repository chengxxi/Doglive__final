import { createRouter, createWebHistory } from 'vue-router'
import Main from '@/views/main/components/main-content.vue'
import Login from '@/views/main/components/login-dialog.vue'
import Logout from '@/views/main/components/logout-dialog.vue'
import Mypage from '@/views/user/mypage.vue'
import Chat from '@/views/chat/chat.vue'
import KakaoCallback from '@/views/main/components/kakao-callback.vue'
import { useStore } from 'vuex'

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
    // beforeEnter: requireAuth,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/userLogout',
    name: 'UserLogout',
    component: Logout,
  },
  {
    path : '/kakao/callback',
    name: 'KakaoCallback',
    component : KakaoCallback,
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage,
    children: [
      {
        path: "profile",
        name: "mypage-profile",
        component: () => import("@/views/user/components/user-profile.vue"),
      },
      {
        path: "mypost",
        name: "mypage-mypost",
        component: () => import("@/views/user/components/user-post-list.vue"),
      },
      {
        path: "applyList",
        name: "mypage-apply-list",
        component: () => import("@/views/user/components/user-apply-list.vue"),
      },
      {
        path: "applyResultList",
        name: "mypage-result-list",
        component: () => import("@/views/user/components/user-apply-result-list.vue"),
      },
    ]
  },
  {
    path : '/chat',
    redirect : '/chat/rooms',
    name: 'Chat',
    component : Chat,
    // meta: {requiresAuth : true},
    children: [
      {
        path: "rooms",
        name: "chat-list",
        component: () => import("@/views/chat/components/chat-list.vue"),
      },
      {
        path: "room",
        name: "chat-detail",
        component: () => import("@/views/chat/components/chat-detail.vue"),
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

// URL이 변경되기전 거쳐가는 함수
router.beforeEach(function(to, from, next){
  const store = useStore()
  console.log(store)

  if(to.matched.some(record => record.meta.requiresAuth)){ // 로그인이 필요한 페이지라면
    // if(store.getters["root/getLoginUserInfo"].userId === null){ // 현재 로그인된 상태인지 확인
    //   next({ path: '/', query: {redirect: to.fullPath}})
    // }else{
    //   next()
    // }
  }else{
    next()
  }
})

export default router
