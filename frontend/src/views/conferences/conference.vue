<template>
  <div id='join' v-if='!session' class='main-body'>
    <el-card class='con-join-wrapper'>
      <h1>Join a video session</h1>
      <el-form>
        <p>
          <el-label>Participant</el-label>
          <el-input v-model='myUserName' type='text' required />
        </p>
        <p>
          <el-label>Session</el-label>
          <el-input v-model='mySessionId' type='text' required />
        </p>
        <p class='text-center'>
          <el-button class='joinBtn' @click="joinSession">Join!</el-button>
        </p>
      </el-form>
    </el-card>
  </div>

  <div v-if='session' class='main-body'>
    <el-main>
      <el-row class="con-row-button">
        <!-- <h1 id='session-title'>{{ state.mySessionId }}</h1> -->
        <el-button type='button' id='buttonLeaveSession' @click='leaveSession'>Leave Session</el-button>
        <el-button v-if='videoEnabled' type='info' @click='turnCamera'>
          <font-awesome-icon :icon="[ 'fas' , 'video']"/>
        </el-button>
        <el-button v-else type='danger' icon='el-icon-loading' @click='turnCamera'></el-button>
        <el-button v-if='audioEnabled' type='info' icon='el-icon-microphone' @click='turnAudio' />
        <el-button v-else type='danger' icon='el-icon-turn-off-microphone' @click='turnAudio' />
        <el-button v-if='chatEnabled' @click="turnChat" type="info" icon='el-icon-chat-round'/>
        <el-button v-else type="primary" icon="el-icon-chat-dot-round" @click="turnChat"/>
        <!-- <el-drawer v-model="drawer" :with-header='false'>
          <conferenceChat
            class='chat'
            :session='state.session'
            :client-data='state.myUserName'
          />
        </el-drawer> -->
      </el-row>
    <el-row class="con-row-body">
      <el-col id='video-container' :span='spanVideo'>
        <!-- 비디오 -->
        <el-row>
          <el-col class="video-container" :span="24/spanEachVideo" >
            <user-video :stream-manager='publisher' :span-each-video='spanEachVideo'/>
          </el-col>
          <el-col class="video-container" :span="24/spanEachVideo">
            <user-video
              v-for='sub in subscribers'
              :key='sub.stream.connection.connectionId'
              :stream-manager='sub'
              :span-each-video='spanEachVideo'
            />
          </el-col>
        </el-row>
      </el-col>
      <!-- 채팅 -->
      <el-col :span='4' class='con-col-wrapper' v-if='!chatEnabled'>
        <div class="chat-main">
          <div class="chat-header">
            <i class="el-icon-arrow-right close-btn" @click="turnChat"></i>
          </div>
          <div @scroll="scroll" class="chatlog" dropzone="true" id = "chatlog" :ref="el => { if(el) divs = el}">
            <!-- <div v-for="chat in chatArray" :key="chat" class="chatContent">
              {{chat}}
            </div> -->
            <ConferenceChatMessage
              v-for="chat in chatArray"
              :key="chat"
              :message="chat"
              :userName="myUserName"
              class="chatContent"
            />
          </div>
            <el-divider>🐶</el-divider>
            <textarea
              class='chatinput'
              v-model='chatString'
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
}
.con-row-button{
  height: 10%;
}
.con-join-wrapper {
  width: 400px;
  height: 400px;
  margin: auto;
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
user-video {
  width: 50px;
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
  background-color: aqua;
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
</style>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './components/UserVideo';
import ConferenceChatMessage from './components/conferenceChatMessage.vue'
import { computed } from '@vue/runtime-core';
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://i5a501.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "doglivedoggi";
export default {
	name: 'App',
	components: {
		UserVideo,
    ConferenceChatMessage,
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
      videoEnabled: true,
      audioEnabled : true,
      chatEnabled : true,
      chatArray: [],
      chatString:'',
      spanVideo : 24,
      spanEachVideo : computed(() => {
        if(this.subscribers.length == 0) {
          console.log('참가자 한명')
          return 1;
        }
        else {
          console.log('참여자 한 명 또 들어왔다~');
          return 2;
        }
      }),
      videoStyle : computed(() => {
        if(this.spanEachVideo == 1) return true;
        else return false;
      })
		}
	},
  methods: {
    joinSession() {
      // mySessionId : 채팅방 있는 두 명의 사용자 ID 합친 것 (26자리 숫자 || 앞자리 어떤 채팅방인지 + 26자리 숫자 => 27자리 숫자)
      // myUserName : 현재 유저의 카카오톡 고유 아이디로 지정
      // this.myUserName = this.$store.state.loginUserInfo;
      // console.log('로그인한 사용자 아이디 : ');
      // console.log(this.myUserName)
      // this.myUserName = '영주';
      // 먼저 화상회의 개설 (DB에 저장)

      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();
      // --- Init a session ---
      this.session = this.OV.initSession();
      // --- Specify the actions when events take place in the session ---
      // On every new Stream received...

      // 채팅 내역 받기
      this.session.on('signal:chatSend', (event) => {
        console.log('arrive chat')
        console.log(event.data)
        console.log(JSON.parse(event.data))
        console.log('After arrive chat ')
        this.chatArray.push(JSON.parse(event.data))
        console.log(this.chatArray)
        var chatlogScroll = document.getElementById('chatlog');
        chatlogScroll.scrollTop = chatlogScroll.scrollHeight + chatlogScroll.clientHeight;
      })

      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });
      // On every Stream destroyed...
      this.session.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      this.session.on('exception', ({ exception }) => {
        console.warn(exception);
      });
      // --- Connect to the session with a valid user token ---
      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then(token => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: this.audioEnabled, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: this.videoEnabled, // Whether you want to start publishing with your video enabled or not
              resolution: '640x480', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false // Whether to mirror your local video or not
            });
            this.mainStreamManager = publisher;
            this.publisher = publisher;
            // --- Publish your stream ---
            this.session.publish(this.publisher);
          })
          .catch(error => {
            console.log(
              'There was an error connecting to the session:',
              error.code,
              error.message
            );
          });
      });
      window.addEventListener('beforeunload', this.leaveSession);
    },
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      this.chatArray = [];  // Session 나가면 채팅 사라짐
      window.removeEventListener('beforeunload', this.leaveSession);
    },
    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */
    getToken(mySessionId) {
      return this.createSession(mySessionId).then(sessionId =>
        this.createToken(sessionId)
      );
    },
    //See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
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
    },
    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken(sessionId) {
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
    },

    // 오픈비두 채팅
    sendMessage() {
      console.log('sendMessage > 사용자가 enter 쳤다')
      console.log(this.chatString)
      console.log(this.myUserName)
      if(this.chatString.trim().length == 0) return;
      // 사용자 아이디도 추가
      const msg = {
        userName : this.myUserName,
        chatMessage : this.chatString.trim()
      }
      this.session.signal({
        data: JSON.stringify(msg),
        to:[],
        type: 'chatSend'
      })
      .then(()=> {
        console.log('Message successfully send❗')
        this.chatString='';
      })
      .catch(error => {
        console.log(error)
      })
    },
    // turn on / off
    turnCamera() {
      console.log('변경 전 > : ' + this.videoEnabled);
      this.videoEnabled = !this.videoEnabled;
      console.log('변경 후 > : ' + this.videoEnabled);

      console.log('카메라 상태 변경 버튼 클릭 > ');
      this.publisher.publishVideo(this.videoEnabled);
    },

    turnAudio() {
      console.log('변경 전 > : ' + this.audioEnabled);
      this.audioEnabled = !this.audioEnabled;
      console.log('변경 후 > : ' + this.audioEnabled);

      console.log('오디오 상태 변경 버튼 클릭 > ');
      this.publisher.publishAudio(this.audioEnabled);
    },

    turnChat() {
      console.log('변경 전 > : ' + this.chatEnabled);
      this.chatEnabled = !this.chatEnabled;
      console.log('변경 후 > : ' + this.chatEnabled);
      if(!this.chatEnabled) {
        this.spanVideo = 20;
      }
      else {
        this.spanVideo = 24;
      }
      console.log('채팅창 표시 변경 버튼 클릭 > ');
    },

    // 스크롤 하단 고정
    scrollToBottom(el){
      el.value.scrollTop = 99999
    },
  }
};
</script>
