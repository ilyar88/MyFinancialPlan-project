import http from 'k6/http';
import { check } from 'k6';

export const options = {
  vus: 10,
  duration: '5s',
};

export default function () {
  const url = __ENV.TARGET_URL;
  const res = http.get(url);

  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}
