package com.currency_authority.forex_trade.rest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currency_authority.forex_trade.model.ExchangeRate;
import com.currency_authority.forex_trade.service.CurrencyConverterService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/exchange-rates")
@Produces("application/json")
@Consumes("application/json")
public class CurrencyConverterResource {

	@Autowired
	private CurrencyConverterService service;

	@RequestMapping("/all")
	public ExchangeRate getExchangeRates(@RequestParam(value = "currencyFrom", required = false) String currencyFrom)
			throws IOException {
		return service.getExchangeRates(currencyFrom);
	}

	@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ExchangeRate testing() {
		ExchangeRate rate = new ExchangeRate();
		rate.setBase("xxxx");
		rate.setTime_last_updated("sssss");
		return rate;
	}

	@RequestMapping
	public ExchangeRate convertCurrency(@RequestParam(value = "currencyFrom", required = false) String currencyFrom,
			@RequestParam(value = "currencyTo", required = false) String currencyTo) throws IOException {
		return service.convertCurrency(currencyFrom, currencyTo);
	}

	@GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamFlux() {
		return Flux.interval(Duration.ofSeconds(1)).map(sequence -> "CurrentTime - " + LocalTime.now().toString());
	}
}
