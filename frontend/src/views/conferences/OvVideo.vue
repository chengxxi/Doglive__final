<template>
	<video ref="video" autoplay/>
</template>

<script>
import { ref, onMounted, reactive, getCurrentInstance  } from 'vue'

export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
	},

	setup(props, { emit }) {
		const video = ref(null)
		const state = reactive({
		})

    	onMounted(() => {
			console.log("OpenVidu비디오")
			console.log(props.streamManager)
			console.log(video.value)

			props.streamManager.addVideoElement(video.value);
		})
		return { state, video }
	}
};
</script>
