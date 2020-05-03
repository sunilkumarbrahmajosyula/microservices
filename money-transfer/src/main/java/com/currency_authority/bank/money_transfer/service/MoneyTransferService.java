package com.currency_authority.bank.money_transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currency_authority.bank.money_transfer.model.ExchangeRate;
import com.currency_authority.bank.money_transfer.model.User;

@Service
public class MoneyTransferService {

	@Autowired
	protected RestTemplate restTemplate;

	private static final String EXCHANGE_RATE_URL = "http://web-service:3333/api/exchange-rates/exchange-rate";

	public ExchangeRate getExchangeRate(String currencyFrom, String currencyTo) {
		return restTemplate.getForObject(EXCHANGE_RATE_URL + "?" + currencyFrom + "&" + currencyTo, ExchangeRate.class);
	}

	public String transferMoney(User fromUser, User toUser) {
		ExchangeRate exchangeRate = getExchangeRate(fromUser.getFromCurrency(), fromUser.getToCurrency());

		boolean isRateExist = exchangeRate.getRates().values().stream().findFirst().isPresent();
		if (isRateExist) {
			String value = exchangeRate.getRates().values().stream().findFirst().get();
			Long amount = fromUser.getAmountToTransfer() * Long.parseLong(value);
			toUser.setTotalBalance(toUser.getTotalBalance() + amount);
			fromUser.setTotalBalance(fromUser.getTotalBalance() - fromUser.getAmountToTransfer());
			return "Transfer Successful";
		}
		return "Failure";

	}

}
