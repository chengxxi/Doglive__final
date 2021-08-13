<template>
  <video ref="video" autoplay />
</template>

<style scoped>
.auto {
  width: auto;
}
.full {
  width: 99%;
}
</style>
<script>
import { ref, onMounted, reactive, computed, onBeforeMount } from 'vue'

export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
    spanEachVideo: Number,
    videoStyle: String,
	},

	setup(props) {
		const video = ref(null)
    const state = reactive({
      videoStyle: '',
    })

    onBeforeMount(() => {
      console.log("부모에서 넘어온 값 🔽 ")
      console.log(props.spanEachVideo)
      if(props.spanEachVideo == 1) state.videoStyle='auto';
      else state.videoStyle='99%';
      console.log('OvVide > videoStyle 값 🔽')
      console.log(state.videoStyle)
    })
    onMounted(() => {
			console.log('openVidu 비디오 그 자체 실행❗')
			console.log(props.streamManager)

      console.log('비디오 크기 조절 🔽')
      console.log(props.spanEachVideo)
      state.spanEachVideo = props.spanEachVideo
      if(state.spanEachVideo == 1) {
        console.log('사용자 한명!')
        state.widthComponent=true;
      }
      else {
        console.log('사용자 두 명~!')
        state.widthComponent=false;
      }
      console.log('비디오 속성 : ' + state.widthComponent)

      console.log('video.value 🔽')
			console.log(video.value)

			props.streamManager.addVideoElement(video.value);
		})
		return { state, video }
	}
};
</script>
