import { createRouter, createWebHistory } from 'vue-router'
import Main from '@/views/main/components/main-content.vue'
import Login from '@/views/main/components/login-dialog.vue'
import Logout from '@/views/main/components/logout-dialog.vue'
import Mypage from '@/views/user/mypage.vue'
import KakaoCallback from '@/views/main/components/kakao-callback.vue'

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
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

// URL이 변경되기전 거쳐가는 함수
router.beforeEach(function(to, from, next){
  next()
})

export default router
