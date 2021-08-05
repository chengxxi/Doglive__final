<template>
  <router-view></router-view>
</template>

<style scoped>
</style>

<script>
import ChatList from './components/chat-list.vue'
import ChatDetail from './components/chat-detail.vue'

export default {
  name: 'chat',

  components: {
    ChatList,
    ChatDetail
  }
}
</script>
