import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ChatComponent} from "./chat/chat.component";
import {MdButtonModule, MdIconModule, MdInputModule} from "@angular/material";
import {FormsModule} from "@angular/forms";
import {ChatService} from "./chat.service";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MdButtonModule,
    MdIconModule,
    MdInputModule
  ],
  providers: [ChatService],
  declarations: [ChatComponent],
  exports: [ChatComponent]
})
export class ChatModule { }
