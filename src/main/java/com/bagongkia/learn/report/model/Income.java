package com.bagongkia.learn.report.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Income {

	private String orderNumber;
	private BigDecimal amount;
	
}
