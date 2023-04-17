import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
  vus: 5000,
  duration: '30s',
};
export default function () {
  http.get('http://ec2-54-249-170-143.ap-northeast-1.compute.amazonaws.com:8080/subscribe/397178638');
  sleep(1);
}
  