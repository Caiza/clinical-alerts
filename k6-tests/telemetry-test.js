import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 300,
  duration: '2m',
};

function randomInt(max) {
  return Math.floor(Math.random() * max) + 1;
}

function randomSignal() {
  const signals = ["HEART_RATE", "OXYGEN_SATURATION", "BLOOD_PRESSURE"];
  return signals[Math.floor(Math.random() * signals.length)];
}

function randomValue(type) {
  switch (type) {
    case "HEART_RATE":
      return Math.random() * (160 - 50) + 50;
    case "OXYGEN_SATURATION":
      return Math.random() * (100 - 80) + 80;
    case "BLOOD_PRESSURE":
      return Math.random() * (180 - 90) + 90;
    default:
      return 100;
  }
}

export default function () {

  const type = randomSignal();

  const payload = JSON.stringify({
    deviceId: randomInt(4),
    patientId: randomInt(10),
    type: type,
    timestamp: new Date().toISOString(),
    mensuredValue: randomValue(type),
    unit: type === "OXYGEN_SATURATION" ? "%" : "bpm"
  });

  let res = http.post('http://localhost:8081/api/telemetry', payload, {
    headers: { 'Content-Type': 'application/json' },
  });

  check(res, {
    'status ok': (r) => r.status === 200 || r.status === 202,
  });

  sleep(0.2);
}