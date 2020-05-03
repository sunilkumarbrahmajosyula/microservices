package com.currency_authority.forex_trade.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.currency_authority.forex_trade.model.ExchangeRate;
import com.google.gson.Gson;

@Service
public class CurrencyConverterService {

	private static final String EXCHANGE_RATE_URL = "https://api.exchangerate-api.com/v4/latest/";

	public ExchangeRate getExchangeRates(String currencyFrom) throws IOException {
		URL url = new URL(EXCHANGE_RATE_URL + currencyFrom);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		InputStreamReader input = new InputStreamReader((InputStream) request.getContent());
		String string = IOUtils.toString(input);
		Gson gson = new Gson();
		return gson.fromJson(string, ExchangeRate.class);

	}

	public ExchangeRate convertCurrency(String currencyFrom, String currencyTo) throws IOException {
		ExchangeRate exchangeRate = this.getExchangeRates(currencyFrom);
		Map<String, String> rates = new HashMap<>();
		
		
		rates.put(currencyTo, exchangeRate.getRates().get(currencyTo));
		exchangeRate.getRates().clear();
		exchangeRate.setRates(rates);
		return exchangeRate;
	}

}
