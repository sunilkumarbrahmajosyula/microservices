package com.currency_authority.bank.money_transfer.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.currency_authority.bank.money_transfer.model.ExchangeRate;
import com.currency_authority.bank.money_transfer.model.User;
import com.currency_authority.bank.money_transfer.service.MoneyTransferService;

@RestController
@RequestMapping("/api/money-transfer")
@Produces("application/json")
@Consumes("application/json")
public class MoneyTransferResource {

	@Autowired
	private MoneyTransferService moneyTransferService;

	private Map<String, User> userList = new HashMap<>();

	@PostMapping("/create-user")
	public User createUser(User user) {
		this.userList.put(user.getUserName(), user);
		return user;
	}

	@PostMapping(value = "/transfer-money", produces = MediaType.APPLICATION_JSON)
	public String transferMoney(User fromUser, User toUser) throws Exception {
		if (userList.containsKey(fromUser.getUserName())) {
			return moneyTransferService.transferMoney(fromUser, toUser);
		} else {
			throw new Exception("user is not registered yet");
		}

	}

	@GetMapping(value = "/exchange-rate", produces = MediaType.APPLICATION_JSON)
	public ExchangeRate getExchangeRate(@RequestParam("currencyFrom") String currencyFrom,
			@RequestParam("currencyTo") String currencyTo) {
		return moneyTransferService.getExchangeRate(currencyFrom, currencyTo);
	}

}
