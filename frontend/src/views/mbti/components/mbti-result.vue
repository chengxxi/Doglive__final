<template>
  <span v-for='(card, idx) in boardList' :key='idx'>
    <el-col :span='6'>
      <MbtiCard/>
    </el-col>
  </span>
</template>

<style scoped>
</style>


<script>
import MbtiCard from './mbti-card.vue'


export default {
  name: 'mbti-result',
  components: { MbtiCard },
}
</script>
