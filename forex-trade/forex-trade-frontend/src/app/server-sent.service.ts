import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServerSentService {

  constructor() { }


  getEventSource(url: string): EventSource {
    return new EventSource(url);
  }
}
