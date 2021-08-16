<template>
  <div class="col-12 col-sm-9 col-md-4 mt-4">
    <div class="card h-100 hover-top shadow" style="margin:10px;">
      <div class="card-body">
        <el-row style="margin-top:5px; margin-right:15px; margin-left:15px;">
          <el-tag
            class="mb-2"
            effect="dark"
            size="small"
            style="height:20px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none; font-weight:600;  color:#FFFFFF; float:left;"
            >{{ card.mbti }}</el-tag
          >

          <el-tag
            v-if="card.boardId.type.id == 1"
            class="mb-2"
            color="#D7AFA4"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
            >{{ card.boardId.type.name }}</el-tag
          >

          <el-tag
            v-if="card.boardId.type.id != 1"
            class="mb-2"
            color="#E9CDA4"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ card.boardId.type.name }}</el-tag
          >
        </el-row>
        <div class="mt-2"></div>

        <div class="d-flex align-items-center ">
          <img
            class="me-3 me-md-3 rounded-2 "
            style="width:100%; height:300px;"
            :src="card.boardId.thumbnailUrl"
            alt="blog"
          />
        </div>
        <div class="my-3 fs--1">
          <h3 style="font-weight:800; float:right;" class="mb-0 ">
            {{
              card.dogName.length < 6
                ? card.dogName
                : card.dogName.substr(0, 5) + ".."
            }}
          </h3>
          <p style="font-size:13px; font-weight:600; margin-bottom:0px;">
            {{
              card.boardId.title.length < 7
                ? card.boardId.title
                : card.boardId.title.substr(0, 6) + ".."
            }}
          </p>

          <p
            style="font-size:11px; margin-top:0px;  font-weight:600; color:#727272;"
          >
            <i v-if="card.gender.name == '여'" class="el-icon-female " />
            <i v-if="card.gender.name == '남'" class="el-icon-male " /> /
            <i v-if="card.gender.name == '기타'" class="el-icon-question" />
            {{ card.age.name.split("(")[0] }} /
            {{ card.dogType.name }}
          </p>
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
</style>

<script>
export default {
  name: "adopt-card",

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
