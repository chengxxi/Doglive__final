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
            class="fit-image"
            style="width:100%; height:auto; margin-left:5px margin-right:5px"
            :src="card.imageUrl"
            alt="blog"
          />
        </div>

        <div class="my-3 fs--1">
          <div
            style="padding-top:10px; margin-left:10px; margin-right:10px; text-align:right;"
          >
            <p class="mb-0 fw-bold" style="font-size:26pt; font-weight:700;">
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
</template>

<style scoped>
.mbtiCard {
  align-content: center;
  display: flex;
  justify-content: center;

  margin: 10px;
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

.fit-image {
  object-fit: cover;
  height: 200px;
}
</style>

<script>
import MbtiDetail from './mbti-detail.vue'

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
