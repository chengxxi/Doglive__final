<template>
  <div id='join' v-if='!state.session' class='main-body'>
    <el-card class='con-join-wrapper'>
      <el-row class = "boardContent">
        <el-col :span="10">
          <img
            class="dog-thumbnail"
            :src="require('@/assets/images/mbti_isfp.png')"
            style="max-width: 60%;"
          />
        </el-col>
        <el-col :span="14">
          <div class="div-boardTitle">
            {{state.conference.title}}
          </div>

        </el-col>
      </el-row>
      <el-row style="height:20px;"></el-row>
      <el-form>
        <p class='text-center'>
          <el-button id='joinBtn' @click="joinSession">Join!</el-button>
        </p>
      </el-form>
    </el-card>
  </div>

  <div v-if='state.session' class='main-body'>
    <el-main>
      <el-row class="con-row-button">
        <!-- <h1 id='session-title'>{{ state.mySessionId }}</h1> -->
        <el-button type='button' id='buttonLeaveSession' @click='leaveSession'>Leave Session</el-button>
        <el-button v-if='state.videoEnabled' class = "off-content" icon='el-icon-video-camera' @click='turnCamera'></el-button>
        <el-button v-else type='info' icon='el-icon-loading' @click='turnCamera'></el-button>
        <el-button v-if='state.audioEnabled' class = "off-content" icon='el-icon-microphone' @click='turnAudio' />
        <el-button v-else type='info' icon='el-icon-turn-off-microphone' @click='turnAudio' />
        <el-button v-if='state.chatEnabled' class = "off-content" @click="turnChat" icon='el-icon-chat-round'/>
        <el-button v-else type='info' icon="el-icon-chat-dot-round" @click="turnChat"/>
        <!-- <el-drawer v-model="drawer" :with-header='false'>
          <conferenceChat
            class='chat'
            :session='state.session'
            :client-data='state.myUserName'
          />
        </el-drawer> -->
      </el-row>
      <el-row class="con-row-body">
        <el-col id='video-container' :span='state.spanVideo'>
          <!-- 비디오 -->
          <el-row>
            <el-col class="video-container" :span="24/state.spanEachVideo" >
              <user-video :stream-manager='state.publisher' :span-each-video='state.spanEachVideo'/>
            </el-col>
            <el-col class="video-container" :span="24/state.spanEachVideo">
              <user-video
                v-for='sub in state.subscribers'
                :key='sub.stream.connection.connectionId'
                :stream-manager='sub'
                :span-each-video='state.spanEachVideo'
              />
            </el-col>
          </el-row>
        </el-col>
        <!-- 채팅 -->
        <el-col :span='4' class='con-col-wrapper' v-if='!state.chatEnabled'>
          <div class="chat-main">
            <div class="chat-header">
              <i class="el-icon-arrow-right close-btn" @click="turnChat"></i>
            </div>
            <div @scroll="scroll" class="chatlog" dropzone="true" id = "chatlog">
              <!-- <div v-for="chat in chatArray" :key="chat" class="chatContent">
                {{chat}}
              </div> -->
              <ConferenceChatMessage
                v-for="chat in state.chatArray"
                :key="chat"
                :message="chat"
                :userName="state.myUserName"
                class="chatContent"
              />
            </div>
              <el-divider>🐶</el-divider>
              <textarea
                class='chatinput'
                v-model='state.chatString'
                @keyup.enter='sendMessage'
              />
            </div>
        </el-col>
      </el-row>
    </el-main>
  </div>
</template>
<style>
video {
  width: 99%;
  max-width: 1000px;
}
</style>
<style scoped>
/* 페이지 만들 때, 이 구조가 기준이 됩니다! (양옆 여백 10%) */
.main-body {
  display: flex;
  width: 100%;
  margin-left: 10%; /* 페이지 양옆 200px여백 -> 10% */
  margin-right: 10%;
  margin: 0 auto;
  overflow:hidden;
}
:deep(.el-main) {
  overflow-y: hidden;
}
.con-row-button{
  height: 10%;
}
.con-row-body {
  height : 80%
}
.con-join-wrapper {
  width: auto;
  height: 500px;
  margin: auto;
  border-radius: 5px;
  text-align: center;
}
.con-col-wrapper {
  align-content: center;
  height: 65vh;
}
.con-chat-wrapper {
  align-content: center;
  width: 100%;
  margin: auto;
}
.video-container {
  display: inline-block;
  align-content: center;
  margin : auto;
}
.chat-main {
  border: 2px solid #755744;
  height: 100%;
}
.chat-header{
  position: relative;
  width: 100%;
  height: 30px;
  padding: 20px;
  background-color: #F9F0E7;
}
.close-btn{
  cursor: pointer;
  position: absolute;
  top: 10px;
  right: 10px;
}
.chatlog {
  width: 100%;
  height: calc(100% - 140px);
  padding: 9px;
  overflow: auto;
}
.chatlog::-webkit-scrollbar{
  display: none;
}
:deep(.el-divider) {
  margin-top: 10px;
  margin-bottom: 2px;
}
.chatinput {
  outline-color: orangered;
  padding: 5px 5px;
  width: 100%;
  height: 70px;
  text-align: left;
}
.chatContent {
  text-align: left;
}
.boardContent {
  height: 80%;
}
.div-boardTitle {
  width :100%;
  height: 100%;
  text-align: center;
  padding-top: 25%;
  font-size: 150%;
}
/* 화상회의 참여 버튼 커스텀 */
#joinBtn {
  border:#F9F0E7 1px solid;
  font-size: 17px;
}
#joinBtn:hover {
  background: #F9F0E7;
  border:#F9F0E7 1px solid;
  color: black;
}
/* 화상회의 나가기 버튼 커스텀 */
#buttonLeaveSession {
  border:#F9F0E7 1px solid;
  font-size: 20px;
}
#buttonLeaveSession:hover {
  background: #F9F0E7;
  border:#F9F0E7 1px solid;
  color: black;
}
/* icon 크기 조절 */
:deep(.el-icon-turn-off-microphone::before) {
  font-size: 20px;
}
:deep(.el-icon-microphone::before) {
  font-size: 20px;
}
:deep(.el-icon-video-camera::before) {
  font-size: 20px;
}
:deep(.el-icon-loading::before) {
  font-size: 20px;
}
:deep(.el-icon-chat-round::before) {
  font-size: 20px;
}
:deep(.el-icon-chat-dot-round::before) {
  font-size: 20px;
}
/* off 버튼 커스텀 */
.off-content {
  color: white;
  background: #d7aea4;
  border-color: #d7aea4;
}
/* textarea 우측 하단 /// 안보이게 + 스크롤 기능 O + 스크롤바 X */
textarea {
  border: none;
  outline: none;
  -ms-overflow-style: none;
  resize: none;
}
textarea::-webkit-scrollbar {
  display: none;
}textarea:focus {
  outline: none;
}
div::-webkit-scrollbar {
  display: none;
}
</style>
<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './components/UserVideo';
import ConferenceChatMessage from './components/conferenceChatMessage.vue'
import { computed, onBeforeMount, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://i5a501.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "doglivedoggi";
export default {
	name: 'App',
	components: {
		UserVideo,
    ConferenceChatMessage,
	},

  setup(props) {
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: 'SessionA',
      userId:'',
			myUserName: '',
      videoEnabled: true,
      audioEnabled : true,
      chatEnabled : true,
      chatArray: [],
      chatString:'',
      spanVideo : 24,
      spanEachVideo : computed(() => {
        if(state.subscribers.length == 0) {
          return 1;
        }
        else {
          return 2;
        }
      }),
      videoStyle : computed(() => {
        if(state.spanEachVideo == 1) return true;
        else return false;
      }),
      userProfile:{},
      conference:{},
    })

    onBeforeMount(() => {
      state.conference = store.getters['root/getConference'];
      console.log(state.conference);

    })
    const joinSession = function() {
      // --- Get an OpenVidu object ---
      // 사용자 아이디
      state.userId = store.getters['root/getLoginUserInfo'].userId;
      // 사용자 이름
      state.myUserName = store.getters['root/getUpdateUserInfo'].name;
      // 채팅방 번호로 seesionId 만들기
      state.mySessionId = 'Session' + state.conference.roomId;
      state.OV = new OpenVidu();
      // --- Init a session ---
      state.session = state.OV.initSession();
      // --- Specify the actions when events take place in the session ---
      // On every new Stream received...

      // 채팅 내역 받기
      state.session.on('signal:chatSend', (event) => {
        state.chatArray.push(JSON.parse(event.data))
        var chatlogScroll = document.getElementById('chatlog');
        chatlogScroll.scrollTop = chatlogScroll.scrollHeight + chatlogScroll.clientHeight;
      })

      state.session.on('streamCreated', ({ stream }) => {
        const subscriber = state.session.subscribe(stream);
        state.subscribers.push(subscriber);
      });
      // On every Stream destroyed...
      state.session.on('streamDestroyed', ({ stream }) => {
        const index = state.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          state.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      state.session.on('exception', ({ exception }) => {
        console.warn(exception);
      });
      // --- Connect to the session with a valid user token ---
      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      getToken(state.mySessionId).then(token => {
        state.session
          .connect(token, { clientData: state.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = state.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: state.audioEnabled, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: state.videoEnabled, // Whether you want to start publishing with your video enabled or not
              resolution: '640x480', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false // Whether to mirror your local video or not
            });
            state.mainStreamManager = publisher;
            state.publisher = publisher;
            // --- Publish your stream ---
            state.session.publish(state.publisher);
          })
          .catch(error => {
            console.log(
              'There was an error connecting to the session:',
              error.code,
              error.message
            );
          });
      });
      window.addEventListener('beforeunload', leaveSession);
    }
    const leaveSession = function() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (state.session) state.session.disconnect();
      state.session = undefined;
      state.mainStreamManager = undefined;
      state.publisher = undefined;
      state.subscribers = [];
      state.OV = undefined;
      state.chatArray = [];  // Session 나가면 채팅 사라짐

      const conferenceInfo = {
        titll:'',
        thumbnailUrl:'',
        writer:'',    // 글 작성자
        reader:'',    // 신청자
        fromChat:false,
      }
      store.commit('root/setConference',conferenceInfo)
      router.push({name : 'Main'});

      window.removeEventListener('beforeunload', state.leaveSession);

    }

    const getToken = function(mySessionId) {
      return createSession(mySessionId).then(sessionId =>
        createToken(sessionId)
      );
    }

    //See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    const createSession = function(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId
            }),
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET
              }
            }
          )
          .then(response => response.data)
          .then(data => resolve(data.id))
          .catch(error => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at '${OPENVIDU_SERVER_URL}'`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    }

    const createToken = function(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET
              }
            }
          )
          .then(response => response.data)
          .then(data => resolve(data.token))
          .catch(error => reject(error.response));
      });
    }

    // 오픈비두 채팅
    const sendMessage = function() {
      if(state.chatString.trim().length == 0) return;
      // 사용자 아이디도 추가
      const msg = {
        userName : state.myUserName,
        chatMessage : state.chatString.trim()
      }
      state.session.signal({
        data: JSON.stringify(msg),
        to:[],
        type: 'chatSend'
      })
      .then(()=> {
        state.chatString='';
      })
      .catch(error => {
      })
    }
    // turn on / off
    const turnCamera = function() {
      state.videoEnabled = !state.videoEnabled;
      state.publisher.publishVideo(state.videoEnabled);
    }

    const turnAudio = function() {
      state.audioEnabled = !state.audioEnabled;
      state.publisher.publishAudio(state.audioEnabled);
    }

    const turnChat = function() {
      state.chatEnabled = !state.chatEnabled;
      if(!state.chatEnabled) {
        state.spanVideo = 20;
      }
      else {
        state.spanVideo = 24;
      }
    }
    return {router, store, state, onBeforeMount, joinSession, leaveSession, getToken, createSession, createToken, sendMessage, turnCamera, turnAudio, turnChat }
  }
}
</script>
