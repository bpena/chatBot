import { Component, OnInit } from '@angular/core';
import {ChatService} from "../chat.service";
import {isNullOrUndefined} from "util";
import {isEmpty} from "rxjs/operator/isEmpty";
import {String} from "../../../services/String";

@Component({
  selector: 'chat-bot',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  private message: string;
  private messages: string[];

  constructor(private chatService: ChatService) { }

  ngOnInit() {
    this.messages = [];
  }

  onkeypress(event: KeyboardEvent): void {
    if (event.charCode === 13 && !isNullOrUndefined(this.message) && !String.isNullOrEmpty(this.message.trim()))
      this.sendMessage();
  }

  private sendMessage(): void {
    this.messages.push(this.message);
    this.chatService.get(this.message).subscribe(response => {
      this.messages.push(response.text());
      console.log(response)
    });
    this.message = '';

  }

}
