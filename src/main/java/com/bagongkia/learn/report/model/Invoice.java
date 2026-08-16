package com.bagongkia.learn.report.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Invoice {

	private String orderNumber;
	private String trackingCode;
	private String shippingName;
}
