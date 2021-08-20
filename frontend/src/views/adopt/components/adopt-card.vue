<template>
  <el-card
    shadow="hover"
    style="background:linear-gradient( to top, #f3ede7, #f5e9e4  ); border-radius:6px; height:360px;"
    class="scale-up-2"
  >
    <el-row style="margin-top:15px; margin-right:15px; margin-left:15px;">
      <el-tag
        class="mb-2"
        effect="dark"
        size="small"
        style="height:20px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none; font-weight:600;  color:#FFFFFF; float:left;"
        >{{ card.mbti }}</el-tag
      >
      <el-tag
        v-if="card.boardId.type.id == 1"
        class="mb-2 "
        color="#D7AFA4"
        effect="dark"
        size="small"
        style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
        >{{ card.boardId.type.name }}</el-tag
      >
      <el-tag
        v-if="card.boardId.type.id != 1"
        class="mb-2 "
        color="#E9CDA4"
        effect="dark"
        size="small"
        style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
        >{{ card.boardId.type.name }}</el-tag
      >
    </el-row>
    <el-row
      style="margin-top:5px; display:flex; display: flex;
  justify-content: center;"
    >
      <img :src="card.boardId.thumbnailUrl" class="image fit-image" />
    </el-row>

    <el-row
      style="margin-top:10px; margin-right:15px; margin-left:15px; vertical-align: middle; "
    >
      <p
        style="font-size:27px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;  max-width:50%; font-weight:800; float:right; text-align:right;"
        class="mb-0 "
      >
        {{ card.dogName }}
      </p>

      <p
        style="font-size:13px; font-weight:600; overflow:hidden;text-overflow:ellipsis;white-space:nowrap; margin-bottom:0px;"
      >
        {{ card.boardId.title }}
      </p>

      <p
        style="font-size:11px; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;margin-top:0px; font-weight:600; color:#727272;"
      >
        <i v-if="card.gender.name == '여'" class="el-icon-female " />
        <i v-if="card.gender.name == '남'" class="el-icon-male " /> /
        <i v-if="card.gender.name == '기타'" class="el-icon-question" />
        {{ card.age.name.split("(")[0] }} /
        {{ card.dogType.name }}
      </p>
    </el-row>
  </el-card>
</template>

<style scoped>
.image {
  width: 90%;
  height: 250px;
}

* {
  color: #606266;
  cursor: pointer;
}

:deep(.el-card__body) {
  padding: 0px;
}

.scale-up-2:hover {
  transform: scale(1.03);
}

.scale-up-hover-2:hover .scale {
  transform: scale(1.03);
}

.fit-image {
  object-fit: cover;
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
