<template>
  <footer>
    <ul>
      <li class="img_area"><img :src="require('@/assets/images/temp-logo.png')" style="height: 3rem; !important" /></li>
      <li><h3 class="title">Dog-Live</h3></li>

      <li><h5><a href="#">오시는 길</a></h5></li>
      <li><h5>|</h5></li>
      <li><h5><a href="#">문의처</a></h5></li>
      <li><h5>|</h5></li>
      <li><h5><a href="#">SNS</a></h5>
        <!-- <ul style="height: 50px;">
          <li><img :src="require('@/assets/images/youtube.png')" /></li>
          <li><img :src="require('@/assets/images/facebook.png')" /></li>
          <li><img :src="require('@/assets/images/twitter.png')" /></li>
        </ul> -->
      </li>


    </ul>
  </footer>
</template>

<style scoped>
footer {
  display: flex;
  position: fixed; /* 하단 고정 */
  left: 0px;
  bottom: 0px;
  height: 70px; /* main.vue에 footer 기준은 110 */
  width: 100%;
  background-color: #F2EFED;
  /* vertical-align: middle; */
}

footer .title {
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
export default {
  name: 'main-footer',
  setup() {

  }
}
</script>
