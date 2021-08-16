<template>
<el-card
    shadow="hover"
    style="background:linear-gradient( to top,#f0ebe0, #f6ede9  );"
    class="scale-up-2"
  >
    <el-row style="margin-top:15px; margin-right:15px; margin-left:15px;">
      <el-tag
        class="mb-2"
        effect="dark"
        size="small"
        style="height:20px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none; font-weight:600;  color:#FFFFFF; float:left;"
        >{{ card.mbtiList.name }}</el-tag
      >
      <el-tag
        class="mb-2 scale-up-2"
        color="#D7AFA4"
        effect="dark"
        size="small"
        style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
        >{{ card.mbtiList.title }}</el-tag
      >
    </el-row>
    <el-row
      style="margin-top:5px; display:flex; display: flex;
  justify-content: center;"
    >
      <img :src="require('@/assets/images/mbti_infj.png')" class="image" />
    </el-row>

    <el-row
      style="margin-top:10px; margin-right:15px; margin-left:15px; vertical-align: middle; "
    >
      <h3 style="font-weight:800; float:right;" class="mb-0 ">
        {{ card.mbtiList.id }}
      </h3>


    </el-row>
  </el-card>









  <!-- <el-col> -->
    <!-- <el-card class="mbtiCard" shadow="hover" style="background-color: snowflake; ">
      <img :src="require('@/assets/images/profile-image.jpg')" class="img"/>
      <h3 class="mbtiTitle">
        MBTI
      </h3>
      <p>description</p>
     </el-card> -->
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
      name: this.name,
      title: this.title,
      desc: this.desc,
      image_url: this.image_url,

    };
  }



}
</script>
