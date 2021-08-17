<template>
  <div class="row h-100 justify-content-center pt-3 mbtiCard">

    <div class="card h-100 hover-top rounded-3 shadow">
      <div class="card-body">
        <hr class="text-100" />

        <div class="d-flex align-items-center">
          <img
            style="width:100%; height:300px; margin-left:5px margin-right:5px"
            :src="card.imageUrl"
            alt="blog"
          />
        </div>

        <div class="my-3 fs--1">
          <div style="padding-top:10px; margin-left:10px; margin-right:10px;">
            <p class="mb-0 fw-bold" style="font-size:20pt;">
              {{ card.name }}
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
      // name: this.name,
      // title: this.title,
      // desc: this.desc,
      // image_url: this.image_url,

    };
  }



}
</script>
