// src/services/websocketService.js

import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

class WebSocketService {
  constructor() {
    this.client = new Client({
      brokerURL: "ws://localhost:8080/ws",
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      webSocketFactory: () => new SockJS("http://localhost:8080/ws"),
    });

    this.client.onConnect = () => {
      console.log("Connected");
      this.client.subscribe("/topic/vehicleLocation", (message) => {
        if (this.onVehicleLocationUpdate) {
          this.onVehicleLocationUpdate(JSON.parse(message.body));
        }
      });
    };

    this.client.onStompError = (frame) => {
      console.error("Broker reported error: " + frame.headers["message"]);
      console.error("Additional details: " + frame.body);
    };

    this.client.activate();
  }

  sendVehicleLocationUpdate(vehicle) {
    this.client.publish({
      destination: "/app/updateLocation",
      body: JSON.stringify(vehicle),
    });
  }
}

const webSocketService = new WebSocketService();
export default webSocketService;
