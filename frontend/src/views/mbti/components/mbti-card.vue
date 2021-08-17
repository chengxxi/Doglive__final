<template>
  <div class="row h-100 justify-content-center pt-3">
            <div
              class="col-12 col-sm-9 col-md-4 mt-4"
              v-for="(card, idx) in state.communityList"
              :key="idx"
              :card="card"
              @click="goCommunity"
              style="cursor:pointer;"
            >
              <div class="card h-100 hover-top rounded-3 shadow">
                <div class="card-body">
                  <el-tag
                    v-if="card.category == '입양일기'"
                    color="#D7AFA4"
                    effect="dark"
                    style="border:none; border-radius: 30px; font-size:14px;"
                    >{{ card.category }}</el-tag
                  >
                  <hr class="text-100" />

                  <div class="d-flex align-items-center">
                    <img
                      style="width:100%; height:300px;
                    margin-left:5px margin-right:5px"
                      :src="card.fileList[0]"
                      alt="blog"
                    />
                  </div>
                  <div class="my-3 fs--1">
                    <div class="user">
                      <img class="user-profile" :src="card.profileImageUrl" />
                      <span class="mb-0 fw-bold">{{ card.name }}</span>
                    </div>
                    <div
                      style="padding-top:10px; margin-left:10px; margin-right:10px;"
                    >
                      <p class="mb-0 fw-bold" style="font-size:12pt;">
                        {{ card.title }}
                      </p>
                      <p class=" mb-0" style="font-size:11pt;">
                        <span>{{
                          card.description.length < 28
                            ? card.description
                            : card.description.substr(0, 27) + ".."
                        }}</span>
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>


  <el-card
    shadow="hover"
    style="background:linear-gradient( to top,#f0ebe0, #f6ede9  );"
    class="mbtiCard scale-up-2"
  >
    <el-row style="margin-right:15px; margin-left:15px;">
      <!-- <el-tag
        class="mb-2"
        effect="dark"
        size="small"
        style="height:20px; background:linear-gradient( to right, #D7AFA4, #E9CDA4, #B4D9A7, #87CEDC ); border:none; font-weight:600;  color:#FFFFFF; float:left;"
      >
        {{ card.name }}
      </el-tag> -->

      <!-- <el-tag
        class="mb-2 scale-up-2"
        color="#D7AFA4"
        effect="dark"
        size="small"
        style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
      >
        {{ card.title }}
      </el-tag> -->
    </el-row>
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

  </el-card>

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
