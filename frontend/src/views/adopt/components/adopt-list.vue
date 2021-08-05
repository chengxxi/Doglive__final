<template>
  <div>
  <!-- <el-row> -->

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
