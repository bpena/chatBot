import { Injectable } from '@angular/core';
import {Http, RequestOptionsArgs, Response} from '@angular/http';
import {Observable} from "rxjs/Observable";

@Injectable()
export class ChatService {
  private url = "/bot/talk/";

  constructor(private http:Http) { }

  get(message: string, options?: RequestOptionsArgs): Observable<Response> {
      return this.http.get(this.url + message, options);
  }
}
