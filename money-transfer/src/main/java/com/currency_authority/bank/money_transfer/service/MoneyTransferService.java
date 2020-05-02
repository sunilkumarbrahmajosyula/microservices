package  com.currency_authority.bank.money_transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.currency_authority.bank.money_transfer.model.ExchangeRate;

@Service
public class MoneyTransferService {

	@Autowired
	protected RestTemplate restTemplate;

	public ExchangeRate getExchangeRate() {
		return restTemplate.getForObject("http://web-service:3333/api/exchange-rates/test", ExchangeRate.class);
		// return
		// restTemplate.getForObject(url:"http://localhost:3333/api/exchange-rates/test",
		// String.class);
	}

}
