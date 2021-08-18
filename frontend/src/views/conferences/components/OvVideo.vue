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

    onMounted(() => {
      state.spanEachVideo = props.spanEachVideo
      if(state.spanEachVideo == 1) {
        state.widthComponent=true;
      }
      else {
        state.widthComponent=false;
      }
			props.streamManager.addVideoElement(video.value);
		})
		return { state, video }
	}
};
</script>
