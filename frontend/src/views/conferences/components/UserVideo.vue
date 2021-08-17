<template>
<div v-if="streamManager">
	<ov-video :stream-manager="streamManager" :span-each-video='spanEachVideo' :video-style="videoStyle"/>
	<div><p>{{ state.clientData }}</p></div>
</div>
</template>

<script>
import { reactive, computed } from 'vue';
import OvVideo from './OvVideo.vue';
export default {
	name: 'UserVideo',
	components: {
		OvVideo,
	},
  props: {
		streamManager: Object,
    spanEachVideo: Number,
    videoStyle : Boolean
	},

  setup(props) {
    const state = reactive({
      clientData: computed(() => {
        console.log('UserVide.vue > props')
        console.log('UserVideo.vue > spanEachVideo 🔽 ')
        console.log(props.spanEachVideo)
        const {connection} = props.streamManager.stream;
        console.log('UserVideo.vue > connection.data 🔽')
        console.log(JSON.parse(connection.data))
        return JSON.parse(connection.data).clientData;
      }),
    })

    return {state}
  }
};
</script>
