<template>
  <div>
  <!-- <el-row> -->
    <h1>adopt cards로 구성된 전체 list</h1>

    <AdoptCard />
      <!-- v-for="(card, idx) in cards"
      :key="idx"
      :card="card"
    /> -->

  <!-- </el-row> -->
  </div>
</template>



<style scoped>


</style>



<script>
import AdoptCard from "@/views/adopt/components/adopt-card";

export default {
  name: 'adopt-list',
  components: { AdoptCard },
  setup () {

  }
}
</script>
