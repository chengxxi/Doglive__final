<template>
  <el-row
    class="main-footer   vertical-center">
    <div class="contents ">
      <ul>
        <li class="img_area"><img :src="require('@/assets/images/logo2.png')" style="height: 3rem; !important" /></li>

        <li class="  vertical-center"><h6><a href="https://lab.ssafy.com/s05-webmobile1-sub3/S05P13A501">Gitlab</a></h6></li>
        <li class="  vertical-center"><h6>|</h6></li>
        <li class="  vertical-center"><h6><a href="https://ssafyseoul5-1.notion.site/1-a1fcecce811a42f4bc399dd562d1ce2f">Notion</a></h6></li>
        <li class="  vertical-center"><h6>|</h6></li>
        <li class="  vertical-center"><h6><a href="/community">SNS</a></h6></li>

      </ul>
    </div>
  </el-row>
</template>

<style scoped>
@media (max-width: 749px) { /* 작은 화면 */
  .main-footer {
    height: 90px;
    background-color: #F2EFED;
  }
  .main-footer .contents {
    /* line-height: 90px; */
    text-align: center;
    margin: 0 auto;
  }
}

@media (min-width: 750px) { /* 큰 화면 */
  .main-footer {
    height: 70px;
    background-color: #F2EFED;


  }
  .main-footer .contents {
    /* line-height: 70px; */
    text-align: center;
    margin: 0 auto;
  }
}


/* main-footer {
  display: flex;
  position: absolute;
  left: 0px;
  bottom: 0px;
  height: 70px;
  width: 100%;
  margin-top: -30rem;
  background-color: #F2EFED;
} */

main-footer .title {
  color: #755744 !important;
}

.title:hover {
  color: #A91919 !important;
}

ul {
  display: inline-grid;
  grid-auto-flow: row;
  grid-gap: 50px; /* item 간 거리 */
  justify-items: center;
  margin: auto;
  list-style: none;
}

@media (min-width: 500px) {
  ul {
    grid-auto-flow: column;
  }
}

a {
  color: #524E4C;
  text-decoration: none;
}

.img_area {
  height: 60px;
  font-size: 0;
}

.img_area:after {
  display:inline-block;
  height:100%;
  content:"";
  vertical-align:middle;
}

.img_area img {
  vertical-align: middle;
}

</style>

<script>
import { reactive } from 'vue'

export default {
  name: 'main-footer',

  props: {
    height: {
      type: String,
      default: '110px'
    }
  },

  setup() {
    const state = reactive({})

    return { state }
  }
}

</script>
