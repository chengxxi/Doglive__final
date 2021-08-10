<template>
	<video ref="video" autoplay/>
</template>

<script>
import { ref, onMounted } from 'vue'

export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
	},

	setup(props) {
		const video = ref(null)

    onMounted(() => {
			console.log('openVidu 비디오 그 자체 실행❗')
			console.log(props.streamManager)
      console.log('video.value 🔽')
			console.log(video.value)

			props.streamManager.addVideoElement(video.value);
		})
		return { video }
	}
};
</script>
