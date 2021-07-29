// 비동기 API
import $axios from 'axios'

export function requestLogin({ state }){
  console.log('requestLogin', state)
  const url = '/kakao/oauth';
  return $axios.get(url);
}
