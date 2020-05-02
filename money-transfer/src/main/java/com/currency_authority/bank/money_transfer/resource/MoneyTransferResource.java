package  com.currency_authority.bank.money_transfer.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	private Map<String, User> userList = new HashMap<String, User>();

	@RequestMapping("/create-user")
	public User createUser(User user) {
		this.userList.put(user.getUserName(), user);
		return user;
	}

	@RequestMapping("/transfer-money")
	public String transferMoney(User user) {
		if (userList.containsKey(user.getUserName())) {
			return "transferSuccessful";
		} else {
			return "transferFailure";
		}

	}

	@GetMapping("/transfer")
	public String test() {
		return "transferSuccessful";

	}

	@GetMapping("/exchange-rate")
	public ExchangeRate getExchangeRate() {
		return moneyTransferService.getExchangeRate();
	}

}
