<template>
<div v-if="streamManager">
	<div><p>{{ state.clientData }}</p></div>
	<ov-video :streamManager="streamManager" />
</div>
</template>

<script>
import { reactive, onMounted, computed, onBeforeMount } from 'vue'
import OvVideo from './OvVideo.vue'

export default {
  components: { OvVideo },
	name: 'UserVideo',

	props: {
		streamManager: Object,
	},

  	setup(props, { emit }) {
		const state = reactive({
			clientData: '',
		})
		const getConnectionData = function () {
			const connection = props.streamManager.stream;
			return JSON.parse(connection.data);
		}
    	onBeforeMount(() => {
			console.log("유저비디오실행")
			const { connection } = props.streamManager.stream
			state.clientData = JSON.parse(connection.data)
			console.log(state.clientData)
		})
		return {state, getConnectionData}
	}
};
</script>
