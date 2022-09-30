package vttp2022.assessment.csf.orderbackend.models;

import lombok.Data;

// IMPORTANT: You can add to this class, but you cannot delete its original content
@Data
public class OrderSummary {
	private Integer orderId;
	private String name;
	private String email;
	private Float amount;

}
