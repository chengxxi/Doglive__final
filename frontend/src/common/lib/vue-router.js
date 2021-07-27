import { createRouter, createWebHistory } from 'vue-router'
import Main from '@/views/main/components/main-content.vue'

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
