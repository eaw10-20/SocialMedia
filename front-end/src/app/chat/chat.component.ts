import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ChatMessage } from '../models/chatMessage';
import { WebsocketService } from '../services/websocket.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  constructor(public webSocketService: WebsocketService) { }

  ngOnInit(): void {
    this.webSocketService.openWebSocket();
  }

  ngOnDestroy(): void {
    this.webSocketService.closeWebSocket();
  }


  sendMessage(sendForm: NgForm){
    const chatMessageObject = new ChatMessage(sendForm.value.user, sendForm.value.message)
    this.webSocketService.sendMessage(chatMessageObject);
    sendForm.controls.message.reset();
  }

}
