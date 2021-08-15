<template>
  <div>
<<<<<<< HEAD
    <el-row>
      <el-col :span="6" v-for="(o, idx) in card" :key="idx">
        <!-- :offset="index > 0 ? 2 : 0" -->
        <el-card
          :body-style="{ padding: '10px' }"
          style="margin: 10px !important;"
          shadow="hover"
        >
          <img :src="require('@/assets/images/logo2.png')" class="image" />
          <div style="padding: 14px;">
            <!-- 입양/임보 구분 -->
            <el-tag class="type" style="mini">{{ o.type.name }}</el-tag>
            <div class="cardtitle">
              <p>{{ o.title }}</p>
            </div>
            <div class="bottom">
              <div class="detail">
                <el-button type="text" class="button" @click="readDetail(o.id)"
                  >글 보러가기</el-button
                >
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
=======
    <el-row :gutter="20">
    <el-col :span="6" v-for="(o, idx) in card" :key="idx"> 
      <el-card :body-style="{ padding: '10px' }" style="background-color:#f9f4f0; width:90%; height:360px;" shadow="hover">
        <el-row style="margin-top:1%; margin-right:1%; margin-left:1%;">
          <el-tag
            v-if="o.type.name == '입양'"
            class="mb-2"
            color="#D7AFA4"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF; float:right; "
            >{{ o.type.name }}</el-tag
          >
          <el-tag
            v-if="o.type.name == '임보'"
            class="mb-2"
            color="#E9CDA4"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ o.type.name }}</el-tag
          >
          <el-tag
            v-if="o.type.name == '실종'"
            class="mb-2"
            color="#bdaf9f"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ o.type.name }}</el-tag
          >
          <el-tag
            v-if="o.type.name == '보호'"
            class="mb-2"
            color="#a3723c"
            effect="dark"
            size="small"
            style="border:none; font-size:14px; height:20px; font-weight:700; border-radius: 30px; color:#FFFFFF;  float:right;"
            >{{ o.type.name }}</el-tag
          >
        </el-row>
        <el-row style="margin-top:1%; display:flex; display: flex; justify-content: center;">
          <img src="https://placedog.net/500/500?random" class="image" />
        </el-row>
        <el-row style="margin-top:3%; margin-right:2.5%; margin-left:2.5%; vertical-align: middle;">
          <h6 style="font-weight:700; margin-top:10%;" class="mb-0">
            {{ o.title.length < 15
                ? o.title
                : o.title.substr(0, 12) + "..."}}
          </h6>
        </el-row>
        <el-row>
          <div class="bottom" style="padding: 2%;">
            <div class="detail"><el-button style="color:black;" type="text" class="button" @click="readDetail(o.id)" >글 보러가기</el-button></div>
          </div>
        </el-row>
      </el-card>
    </el-col>
>>>>>>> feat-36/comment2
    </el-row>
  </div>
</template>

<style scoped>
<<<<<<< HEAD
.type {
  margin-top: 10px;
  float: left;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: right;
  justify-content: space-between;
  align-items: center;
}

/* .button {
    padding: 0;
    min-height: auto;
  } */

.image {
  width: 100%;
  display: block;
}
.cardtitle {
  float: left;
  display: block;
  margin: auto;
}
.detail {
  float: right;
  display: block;
}
=======
  .bottom {
    margin-top: 13px;
    line-height: 12px;
    display: right;
    justify-content: space-between;
    align-items: center;
  }

  .image {
    width: 100%;
    display: block;
  }
  .detail{
    float : right;
    display: block;
  }


>>>>>>> feat-36/comment2
</style>

<script>
import $axios from "axios";
import { onBeforeMount, onMounted, reactive, computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  name: "post-list-card",
  props: {
    card: {
      type: String
    }
  },
  setup() {
    const store = new useStore();
    const router = new useRouter();

    const readDetail = function(id) {
      const userid = store.getters["root/getLoginUserInfo"].userId;

      store
        .dispatch("root/requestBoardDetail", {
          boardId: id,
          userId: userid
        })
        .then(function(result) {
          console.log(result);

          const boardDetail = {
            boardId: result.data.dogInformation.boardId.id,
            boardType: result.data.dogInformation.boardId.type,
            thumbnailUrl: result.data.dogInformation.boardId.thumbnailUrl,
            title: result.data.dogInformation.boardId.title,
            address: result.data.dogInformation.address,
            mbti: result.data.dogInformation.mbti,
            colorType: result.data.dogInformation.colorType,
            gender: result.data.dogInformation.gender,
            dogType: result.data.dogInformation.dogType,
            neutralization: result.data.dogInformation.neutralization,
            writer: result.data.writer,
            weight: result.data.dogInformation.weight,
            ageType: result.data.dogInformation.age,
            regDate: result.data.dogInformation.boardId.regDate,
            fileList: result.data.boardImageList,
            isOwner: result.data.owner,
            gugun: result.data.dogInformation.gugun,
            sido: result.data.dogInformation.gugun.sidoCode,
            description: result.data.dogInformation.description,
            dogName: result.data.dogInformation.dogName,
            isBookmarked: result.data.bookmarked
          };

          store.commit("root/setBoardDetail", boardDetail);

          if (result.data.dogInformation.boardId.type.id <= 2) {
            router.push({ name: "AdoptDetail" });
          } else {
            router.push({ name: "FindDetail" });
          }
        })
        .catch(function(err) {
          console.log(err);
        });
    };
    return { readDetail };
  }
};
</script>
