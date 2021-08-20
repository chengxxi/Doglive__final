<template>
  <div class="col-12 col-sm-9 col-md-4 mt-4">
    <div class="card h-100 hover-top shadow" style="margin:10px;  ">
      <div class="card-body">
        <el-row style="margin-top:5px; margin-right:15px; margin-left:15px;">
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
            class="mb-2"
            color="#87CEDC"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ card.boardId.type.name }}</el-tag
          >
        </el-row>
        <div class="mt-2"></div>

        <div class="d-flex align-items-center ">
          <img
            class="rounded-2 fit-image"
            style="width:100%; height:300px; margin-left:5px margin-right:5px"
            :src="card.boardId.thumbnailUrl"
            alt="blog"
          />
        </div>
        <div class="my-3 fs--1">
          <div style="margin-left:10px; margin-right:10px;">
            <h3
              style="font-weight:800; float:right; max-width:58%; overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"
              class="mb-0 "
            >
              {{ card.dogType.name }}
            </h3>
            <p
              style="font-size:16px; font-weight:600; overflow:hidden;text-overflow:ellipsis;white-space:nowrap; margin-bottom:0px;"
            >
              {{ card.gugun.sidoCode.name }} {{ card.gugun.name }}
            </p>

            <p
              style="font-size:14px; margin-top:0px;  overflow:hidden;text-overflow:ellipsis;white-space:nowrap;  font-weight:600; color:#727272;"
            >
              <i v-if="card.gender.name == '여'" class="el-icon-female " />
              <i v-if="card.gender.name == '남'" class="el-icon-male " />
              <i v-if="card.gender.name == '기타'" class="el-icon-question" />
              / {{ card.weight.name.split("(")[0] }} /
              {{ card.boardId.regDate.split(" ")[0] }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "../../../common/css/main-content.css";

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
