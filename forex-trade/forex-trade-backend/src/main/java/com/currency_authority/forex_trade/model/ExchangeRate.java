package com.currency_authority.forex_trade.model;

import java.util.Date;
import java.util.Map;

public class ExchangeRate {
	
	private String base;
	
	private Date date;
	
	private String time_last_updated;
	
	private Map<String, String> rates;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime_last_updated() {
		return time_last_updated;
	}

	public void setTime_last_updated(String time_last_updated) {
		this.time_last_updated = time_last_updated;
	}

	public Map<String, String> getRates() {
		return rates;
	}

	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}
	
}
