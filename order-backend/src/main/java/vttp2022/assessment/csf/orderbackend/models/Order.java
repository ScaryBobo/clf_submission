package vttp2022.assessment.csf.orderbackend.models;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

// IMPORTANT: You can add to this class, but you cannot delete its original content
@Data
public class Order {
	private Integer orderId;
	private String name;
	private String email;
	private Integer size;
	private String sauce;
	private Boolean thickCrust;
	private List<String> toppings = new LinkedList<>();
	private String comments;
}
