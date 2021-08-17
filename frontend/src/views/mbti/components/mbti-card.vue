<template>
  <div class="row h-100 justify-content-center pt-3 mbtiCard">
    <div class="card h-100 hover-top rounded-3 shadow">
      <div class="card-body">
        <el-tag
          class="mb-2"
          effect="dark"
          size="small"
          style="height:20px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none; font-weight:600;  color:#FFFFFF; float:left;"
        >
          {{ card.name }}
        </el-tag>
        <div class="mt-5"></div>

        <div class="d-flex align-items-center">
          <img
            style="max-width:95%; height:auto; margin-left:5px margin-right:5px"
            :src="card.imageUrl"
            alt="blog"
          />
        </div>

        <div class="my-3 fs--1">
          <div style="padding-top:10px; margin-left:10px; margin-right:10px;">
            <p
              class="mb-0 fw-bold"
              style="font-size:18pt; font-weight:700; float: right;"
            >
              {{ card.name }}
            </p>
            <p class=" mb-0" style="font-size:11pt;">
              {{ card.title }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- <el-card
    shadow="hover"
    style="background:linear-gradient( to top,#f0ebe0, #f6ede9  );"
    class="mbtiCard scale-up-2"

    <el-row
      style="margin-top:5px; display:flex; display: flex;
      justify-content: center;">
      <img :src="card.imageUrl" class="image" />
    </el-row>

    <el-row
      style="margin-bottom:10px; margin-right:15px; margin-left:15px; vertical-align: middle; ">
      <h3 style="font-weight:800; float: right;">
        {{ card.name }}
      </h3>
    </el-row>

  </el-card> -->
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

.image {
  width: 200px;
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
import MbtiDetail from ''./mbti-detail.vue';

export default {
  name: 'MbtiCard',
  components: {
    MbtiDetail
  },
  props: {
    card: Object
  },
  data() {
    return {
      // name: this.name,
      // title: this.title,
      // desc: this.desc,
      // image_url: this.image_url,
    };
  }
};
</script>
