package com.bagongkia.learn.report.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {

	private String currency;
	private BigDecimal amount;
	
}
