import http from 'k6/http';
import { check, sleep } from 'k6';
import { vu } from 'k6/execution';

export const options = {
  scenarios: {
    telemetry_load: {
      executor: 'ramping-vus',
      startVUs: 0,
      stages: [
        { duration: '1m', target: 100 },
        { duration: '3m', target: 300 },
        { duration: '2m', target: 0 },
      ],
    },
  },
};

function randomInt(max) { return Math.floor(Math.random() * max) + 1; }

const BASE_URL = 'http://localhost:8081';

export default function () {
  const signalType = ["HEART_RATE", "OXYGEN_SATURATION", "BLOOD_PRESSURE"][Math.floor(Math.random()*3)];

  const payload = {
    deviceId: randomInt(4),
    patientId: randomInt(10),
    type: signalType,
    timestamp: new Date().toISOString(),
    measuredValue: Math.random() * 100 + 50,
    unit: signalType === "OXYGEN_SATURATION" ? "%" : "bpm"
  };

  const res = http.post(`${BASE_URL}/api/telemetry`, JSON.stringify(payload), {
    headers: { 'Content-Type': 'application/json' },
  });

  // Check melhorado para diagnóstico
  check(res, {
    'status is 2xx': (r) => r.status >= 200 && r.status < 300,
    'status is 200 or 202': (r) => r.status === 200 || r.status === 202,
    'status 400': (r) => r.status === 400,
    'status 500': (r) => r.status === 500,
  });

  // Mostrar resposta de erro no console (muito útil)
  if (res.status !== 200 && res.status !== 202) {
    console.log(`❌ Status: ${res.status} | Body: ${res.body}`);
  }

  sleep(Math.random() * 15 + 5); // 5 a 20 segundos
}