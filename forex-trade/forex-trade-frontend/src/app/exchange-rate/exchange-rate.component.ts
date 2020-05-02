import { Component, OnInit } from '@angular/core';
import { ExchangeRateService } from '../exchange-rate.service';

import { FormBuilder } from '@angular/forms';

import { ExchangeRate } from '../exchange-rate';

@Component({
  selector: 'app-exchange-rate',
  templateUrl: './exchange-rate.component.html',
  styleUrls: ['./exchange-rate.component.css']
})
export class ExchangeRateComponent implements OnInit {

  currencyForm = this.fb.group({
    currencyFrom: [''],
    currencyTo: [''],
  });

  exchangeRate: ExchangeRate;
  date: Date;


  constructor(private exchangeRateService: ExchangeRateService, private fb: FormBuilder) {

  }

  ngOnInit() {
    this.exchangeRateService.getServerSentEvent("/stream-flux").subscribe(data => {
      this.date = data.data;
    });
  }



  countries: string[] = ["INR", "AED", "ARS", "AUD", "BGN", "BRL", "BSD", "CAD", "CHF", "CLP", "CNY", "COP", "CZK", "DKK", "DOP", "EGP", "EUR", "FJD",
    "GBP", "GTQ", "HKD", "HRK", "HUF", "IDR", "ILS", "ISK", "JPY", "KRW", "KZT", "MXN", "MYR", "NOK", "NZD", "PAB", "PEN", "PHP", "PKR", "PLN", "PYG",
    "RON", "RUB", "SAR", "SEK", "SGD", "THB", "TRY", "TWD", "UAH", "USD", "UYU", "VND", "ZAR"]

  onSubmit() {
    // TODO: Use EventEmitter with form value
    const obj = this.currencyForm.value;
    this.exchangeRateService.getExchangeRates(obj.currencyFrom, obj.currencyTo).subscribe(data => {
      this.exchangeRate = data;
    });

  }

}
