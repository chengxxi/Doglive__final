<template>
  <el-card
    shadow="hover"
    style="background:linear-gradient( to bottom,#f3e8dc, #f5edea );border-radius:6px;  min-width:120px; height:360px;"
    class="scale-up-2"
  >
    <el-row style="margin-top:15px; margin-right:15px; margin-left:15px;">
      <el-tag
        v-if="card.boardId.type.id == 3"
        class="mb-2"
        color="#B4D9A7"
        effect="dark"
        size="small"
        style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
        >{{ card.boardId.type.name }}</el-tag
      >
      <el-tag
        v-if="card.boardId.type.id != 3"
        class="mb-2 "
        color="#87CEDC"
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
      <h3
        style="font-size:1.8em;overflow:hidden;text-overflow:ellipsis;white-space:nowrap; max-width:60%;font-weight:800; float:right;"
        class="mb-0 "
      >
        {{ card.dogType.name }}
      </h3>

      <!-- <p style="font-size:13px; font-weight:600; margin-bottom:0px;">
        {{ card.boardId.title }}
      </p> -->
      <p
        style="font-size:13px; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;  font-weight:600; margin-bottom:0px;"
      >
        {{ card.gugun.sidoCode.name }} {{ card.gugun.name }}
      </p>

      <p
        style="font-size:10px; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;  margin-top:0px; font-weight:600; color:#727272;"
      >
        <i v-if="card.gender.name == '여'" class="el-icon-female " />
        <i v-if="card.gender.name == '남'" class="el-icon-male " />
        <i v-if="card.gender.name == '기타'" class="el-icon-question" />
        / {{ card.weight.name.split("(")[0] }} /
        {{ card.boardId.regDate.split(" ")[0] }}
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
export default {
  name: "FindCard",

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
