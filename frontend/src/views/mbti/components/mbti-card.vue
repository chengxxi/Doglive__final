<template>

  <!-- <el-col> -->
    <el-card class="mbtiCard" shadow="hover" style="background-color: snowflake; ">
      <img :src="require('@/assets/images/profile-image.jpg')" class="img"/>
      <h3 class="mbtiTitle">
        MBTI
      </h3>
      <p>description</p>
     </el-card>
  <!-- </el-col> -->

</template>

<style scoped>
.mbtiCard {
  align-content: center;
  display: flex;
  justify-content: center;
  margin: 10px;
  height: 300px;
}

.img {
  width: 100%;
}

.mbtiTitle {
  margin-top: 20px;
  align-content: center;
  display: flex;
  justify-content: center;
  font-weight: 700;
}

el-col {
  position: relative;
}

.el-card:hover {
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}

</style>



<script>
import MbtiDetail from './mbti-detail.vue'

export default {
  name: 'MbtiCard',
  components: {
    MbtiDetail,
  },
  props: {
    card: Object
  },
  data() {
    return {
      age: this.card.age.name.split('(')[0],
      weight: this.card.weight.name.split('(')[0],
      title:
        this.card.boardId.title.length <= 12
          ? this.card.boardId.title
          : this.card.boardId.title.substr(0, 10) + '...'
    };
  }



}
</script>
