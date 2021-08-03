<template>
<div>
  <div>

    <video controls width="250" :key="state.recordUrl">
      <source :src="state.recordUrl" type="video/mp4">
      Sorry, your browser doesn't support embedded videos.
    </video>
  </div>
  <div v-if="state.loadEnd">
    <div v-if="state.deviceLoadEnd">
      오디오 선택:
      <select @change="changeDevice()" v-model="state.audioDevicesIndex">
        <option v-for="(audioDevice, index) in state.audioDevices" :key="index" :value="index"> {{audioDevice.label}} </option>
      </select>
      <br>
      비디오 선택:
      <select @change="changeDevice()" v-model="state.videoDevicesIndex">
        <option v-for="(videoDevice, index) in state.videoDevices" :key="index" :value="index"> {{videoDevice.label}} </option>
      </select>
      <br>
      <button v-if="!state.isRecoding" @click="startRecoding()"> 녹화 시작 </button>
      <button v-if="state.isRecoding" @click="endRecoding()"> 녹화 종료 </button>
    </div>

    <div id="main-video" class="col-md-6">
			<user-video :stream-manager="state.mainStreamManager" :key="state.mainStreamManager"/>
		</div>

    <div>
      <hr>
        <div v-for="chat in state.chatArray" :key="chat">
          <div>{{ chat }}</div>
        </div>
      <hr>
    </div>

    <div>
      <input id="msg" type="text" v-model="state.chatString">
      <button @click="sendChat"> 전송 </button>
    </div>

		<div id="video-container" class="col-md-6">
			<user-video v-for="sub in state.subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"/>
		</div>
  </div>
</div>
</template>

<style>
</style>

<script>
import { reactive, onMounted, onUnmounted, onBeforeMount } from 'vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from './UserVideo.vue'
import axios from 'axios'

export default {
  components: { UserVideo },
  name: 'Conferenceroom',
  setup () {
    const route = useRoute()
    const store = useStore()
    const state = reactive({

      // 채팅용 객체
      chatString: '',
      chatArray: ["안녕", "반가워"],

      // 오픈비두 연결용 객체
      conferenceId: '',
      OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: '',
      myUserName: '',
      loadEnd: false,
      isCreator: true,

      // 디바이스
      videoDevices: undefined,
      videoDevicesIndex: 0,
      audioDevices: undefined,
      audioDevicesIndex: 0,
      deviceLoadEnd: false,

      // 녹화용 변수
      isRecoding: false,
      recordName: "",
      recordUrl: "",

      // 원래는 상수로 처리되야되는 값
      OPENVIDU_SERVER_URL: "https://i5a501.p.ssafy.io",
      OPENVIDU_SERVER_SECRET: "doglivedoggi"
    })

    const startRecoding = function () {
      console.log("startRecoding() called...")
      state.recordName = "MyRecording_" + state.mySessionId + "_" + state.myUserName
      axios.post(`${state.OPENVIDU_SERVER_URL}/openvidu/api/recordings/start`, JSON.stringify({
        "session": state.mySessionId,
        "name": state.recordName,
        "outputMode":"COMPOSED",
        "hasAudio": true,
        "hasVideo": true,
        "recordingLayout":"BEST_FIT",
        "resolution": "640x480"
			}), {
				auth: {
					username: 'OPENVIDUAPP',
					password: state.OPENVIDU_SERVER_SECRET,
				},
			}).then(response => {
        console.log("the then in startRecoding()...")
        console.log(response)
      }).catch(error => {
        console.log("the error in startRecoding()...")
        console.log(error)
			});
      state.isRecoding = true
    }

    const endRecoding = function () {
      console.log("endRecoding() called...")
      axios.post(`${state.OPENVIDU_SERVER_URL}/openvidu/api/recordings/stop/${state.mySessionId}`, JSON.stringify({
			}), {
				auth: {
					username: 'OPENVIDUAPP',
					password: state.OPENVIDU_SERVER_SECRET,
				},
			}).then(response => {
        console.log("the then in endRecoding()...")
        console.log(response)
        state.recordUrl = response.data.url
        console.log("저장된 URL: " + state.recordUrl)
      }).catch(error => {
        console.log("the error in endRecoding()...")
        console.log(error)
			});
      state.isRecoding = false
    }

    const changeDevice = function () {
      console.log("changeDevice() called...")
      console.log("AUDIO: " + state.videoDevicesIndex)
      console.log("VIDEO: " + state.audioDevicesIndex)

      let newPublisher = state.OV.initPublisher(undefined, {
				audioSource: state.audioDevices[state.audioDevicesIndex].deviceId, // The source of audio. If undefined default microphone
				videoSource: state.videoDevices[state.videoDevicesIndex].deviceId, // The source of video. If undefined default webcam
				publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
				publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
				resolution: '640x480',  // The resolution of your video
				frameRate: 30,			// The frame rate of your video
				insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
				mirror: false       	// Whether to mirror your local video or not
			});

      state.session.unpublish(state.publisher).then(() => {
        console.log('Old publisher unpublished!');
        state.publisher = newPublisher;
        state.mainStreamManager = state.publisher;
        state.session.publish(state.publisher).then(() => {
            console.log('New publisher published!');
        });
      })
    }

    const sendChat = function () {
      console.log("sendChat() called...")
      console.log(state.chatString)
      state.session.signal({
        data: state.chatString,  // Any string (optional)
        to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
        type: 'my-chat'             // The type of message (optional)
      })
      .then(() => {
          console.log('Message successfully sent');

      })
      .catch(error => {
          console.error(error);
      })
    }

    const createSession = function (sessionId) {
      console.log("createSession() called...")
      console.log(sessionId)
			return new Promise((resolve, reject) => {
				axios
					.post(`${state.OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
            "recordingMode": "MANUAL",
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: state.OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => {
            console.log("the first then in createSession()...")
            console.log(response)
            return response.data
          })
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
              console.log("이미 존재하는 세션입니다.")
              state.isCreator = false
							resolve(sessionId)
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${state.OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${state.OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${state.OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response)
						}
					})
			})
		}

    const createToken = function (sessionId) {
      console.log("createToken() called...")
      console.log(sessionId)
			return new Promise((resolve, reject) => {
				axios
					.post(`${state.OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: state.OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			})
		}

    const getToken = function (mySessionId) {
      console.log("getToken() called...")
      console.log(mySessionId)
			return createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		}

    const leaveSession = function() {
			if (state.session) state.session.disconnect();

			state.session = undefined;
			state.mainStreamManager = undefined;
			state.publisher = undefined;
			state.subscribers = [];
			state.OV = undefined;

			window.removeEventListener('beforeunload', leaveSession);
		}

    const getAllDevices = function() {
      state.OV.getDevices().then(devices => {
        console.log("완료 -1")
        console.log(devices)
        state.videoDevices = devices.filter(device => device.kind === 'videoinput')
        state.audioDevices = devices.filter(device => device.kind === 'audioinput')
        console.log(state.videoDevices)
        console.log(state.audioDevices)
        state.deviceLoadEnd = true
      })
    }

    onBeforeMount(() => {
      state.conferenceId = route.params.conferenceId
      store.commit('root/setMenuActiveMenuName', 'home')
      state.mySessionId = 'session' + state.conferenceId
      state.myUserName = 'user' + Math.floor(Math.random() * 100)

      state.OV = new OpenVidu()
      state.session = state.OV.initSession()

      getAllDevices()

      console.log("완료0")

      state.session.on('connectionCreated', event => {
        console.log('found connection!=======================', { event })
      })

      state.session.on('streamCreated', ({ stream }) => {
        let subscriber = state.session.subscribe(stream, undefined);
        console.log("새로운 사람 추가! (" + subscriber.id + ")==============================")
        console.log(subscriber)
        state.mainStreamManager = subscriber
        state.subscribers.push(subscriber)
      })

      console.log("완료1")

			state.session.on('streamDestroyed', ({ stream }) => {
				const index = state.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					state.subscribers.splice(index, 1);
				}
			})

      console.log("완료2")

      state.session.on('exception', ({ exception }) => {
				console.warn(exception);
			})

      state.session.on('signal:my-chat', (event) => {
        console.log("채팅이 도착!==============================")
        console.log(event.data); // Message
        console.log(JSON.parse(event.from.data).clientData) // Connection object of the sender
        console.log(event.type); // The type of message ("my-chat")
        let userId = JSON.parse(event.from.data).clientData
        state.chatArray.push(userId + ": " + event.data)
      })

      console.log("완료3")
      console.log(state.mySessionId)

      createSession(state.mySessionId).then(sessionId => createToken(sessionId))
      .then(token => {
        console.log("session connect run...")
        console.log(token)
        state.session.connect(token, { clientData: state.myUserName })
					.then(() => {
            if(state.isCreator) {
              let publisher = state.OV.initPublisher(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                resolution: '640x480',  // The resolution of your video
                frameRate: 30,			// The frame rate of your video
                insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                mirror: false       	// Whether to mirror your local video or not
              });

              state.mainStreamManager = publisher;
              state.publisher = publisher;

              state.session.publish(state.publisher);

              console.log("크리에이터의 현재 Data State")
              console.log("OV")
              console.log(state.OV)
              console.log("session")
              console.log(state.session)
              console.log("mainStreamManager")
              console.log(state.mainStreamManager)
              console.log("publisher")
              console.log(state.publisher)
              console.log("subscribers")
              console.log(state.subscribers)
              console.log("mySessionId")
              console.log(state.mySessionId)
              console.log("myUserName")
              console.log(state.myUserName)
            } else {
              state.mainStreamManager = state.subscribers[0]
            }


            state.loadEnd = true
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					})
      })
      console.log("완료4")

			window.addEventListener('beforeunload', leaveSession)
      console.log("완료5")
    })

    // 페이지 진입시 불리는 훅
    onMounted(() => {
      console.log("마운트!======================")
    })

    // 페이지 이탈시 불리는 훅
    onUnmounted(() => {
      state.conferenceId = ''
    })

    return { state, leaveSession, createToken, createSession, getToken, sendChat, changeDevice, startRecoding, endRecoding }
  }
}
</script>
