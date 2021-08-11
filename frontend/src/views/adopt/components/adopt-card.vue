<template>
  <!-- 개별 입양 공고 card -->

  <el-card shadow="hover" style="background-color:#f8f4f1;">
    <el-row class="mt-2" style="margin-right:15px; margin-left:15px;">
      <el-tag
        class="mb-2"
        color="#b3b3b3"
        effect="dark"
        size="small"
        style="border:none; font-weight:700;   color:#FFFFFF;  float:left;"
        >{{ card.mbti }}</el-tag
      >
      <el-tag
        v-if="card.boardId.type.id == 1"
        class="mb-2"
        color="#D7AFA4"
        effect="dark"
        size="small"
        style="border:none; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
        >{{ card.boardId.type.name }}</el-tag
      >
      <el-tag
        v-if="card.boardId.type.id != 1"
        class="mb-2"
        color="#E9CDA4"
        effect="dark"
        size="small"
        style="border:none; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
        >{{ card.boardId.type.name }}</el-tag
      >
    </el-row>
    <el-row
      style="display:flex; display: flex;
  justify-content: center;"
    >
      <img :src="require('@/assets/images/mbti_isfp.png')" class="image" />
      <!-- <img :src="require('https://placedog.net/200/200?random')" class="image"> -->
    </el-row>

    <el-row
      style="margin-top:10px; margin-right:20px; margin-left:20px; vertical-align: middle; "
    >
      <h3 style="font-weight:800; float:right;" class="mb-0">
        {{ card.dogName }}
      </h3>
      <p style="font-size:12px; margin-bottom:0px;">
        {{ title }}
      </p>

      <p style="font-size:10px; margin-top:0px; color:#727272;">
        {{ card.gender.name }} / {{ age }} / {{ weight }}
      </p>
    </el-row>
  </el-card>
</template>

<style scoped>
.image {
  width: 90%;
  max-height: 300px;
}

* {
  color: #606266;
}

:deep(.el-card__body) {
  padding: 0px;
}
</style>

<script>
import AdoptDetail from "../adopt-detail.vue";

export default {
  name: "adopt-card",
  components: {
    AdoptDetail
  },
  props: {
    card: Object
  },
  data() {
    return {
      age: this.card.age.name.split("(")[0],
      weight: this.card.weight.name.split("(")[0],
      title:
        this.card.boardId.title.length <= 12
          ? this.card.boardId.title
          : this.card.boardId.title.substr(0, 10) + "..."
    };
  }
};
</script>
