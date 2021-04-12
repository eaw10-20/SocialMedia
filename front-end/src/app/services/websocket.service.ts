import { JsonpClientBackend } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

webSocket: WebSocket;
allM

  constructor() { }


  public openWebSocket(){
    this.webSocket = new WebSocket('ws://localhost:9005');

    this.webSocket.onopen = (event) => {
      console.log('Open: ', event)
    }

    this.webSocket.onmessage = (event) => {
        const chatMessageDto = JSON.parse(event.data)
        this.chatMessages.push(chatMessageDto);
    }

    this.webSocket.onclose = (event) => {
      console.log('Close: ', event)
    }
  }

}
