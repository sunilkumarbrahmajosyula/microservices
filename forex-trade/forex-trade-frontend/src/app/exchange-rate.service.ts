import { Injectable, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServerSentService } from './server-sent.service';


@Injectable({
  providedIn: 'root'
})
export class ExchangeRateService {


  private currencyConverterUrl: string = "/api/exchange-rates";

  constructor(private _zone: NgZone, private httpClientService: HttpClient, private sSE: ServerSentService) {

  }

  getServerSentEvent(url: string) {

    return Observable.create(observer => {
      const eventSource = this.sSE.getEventSource(this.currencyConverterUrl + url);

      eventSource.onmessage = event => {
        this._zone.run(() => {
          observer.next(event);
        })
      }

      eventSource.onerror = error => {
        this._zone.run(() => {
          observer.error(error);
        })
      }



    })
  }

  getExchangeRates(currencyFrom: string, currencyTo: string): Observable<any> {
    return this.httpClientService.get(`${this.currencyConverterUrl}?currencyFrom=${currencyFrom}&currencyTo=${currencyTo}`);
  }



}
