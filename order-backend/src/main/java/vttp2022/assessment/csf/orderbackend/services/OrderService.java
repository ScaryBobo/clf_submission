package vttp2022.assessment.csf.orderbackend.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private PricingService priceSvc;

	@Autowired
	private OrderRepository orderRepo;

	// POST /api/order
	// Create a new order by inserting into orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public void createOrder(Order order) {
		String toppings = order.getToppings().toString().replaceAll("\\[", "").replaceAll("\\]","");

		orderRepo.insertNewOrder(order.getName(), order.getEmail(), order.getSize()
											,order.getThickCrust(), order.getSauce(), toppings,order.getComments());
	}

	// GET /api/order/<email>/all
	// Get a list of orders for email from orders table in pizzafactory database
	// IMPORTANT: Do not change the method's signature
	public List<OrderSummary> getOrdersByEmail(String email) {

		List<OrderSummary> orderSummaryListFromRepo = new ArrayList<>();

		// Use priceSvc to calculate the total cost of an order
		List<Order> listOfOrders = orderRepo.getOrdersByEmail(email);

		listOfOrders.stream().forEach(x ->{
			Float saucePrice = priceSvc.sauce(x.getSauce());
			System.out.println(">>>> length of array: " + x.getToppings().size());
			System.out.println(">>> price of sauce each"+ saucePrice);

			Float toppingPrice = 0f;
			for (int i = 0; i < x.getToppings().size(); i++){
				String arrayElement = x.getToppings().get(i).toString().replaceAll("[^a-zA-Z0-9]", "");
				System.out.println(">>>> topping price of " + arrayElement +
						priceSvc.topping(arrayElement));
				toppingPrice += priceSvc.topping(arrayElement);
			}
			Float crustPrice = 0f;
			if (x.getThickCrust()) {crustPrice = 1.5f;}
			if (!x.getThickCrust()){crustPrice = 1.1f;}

			Float sizePrice = priceSvc.size(x.getSize());

			Float totalPrice = saucePrice + toppingPrice + crustPrice + sizePrice;
			System.out.println(">>>>> total cost is >>>>>>" + totalPrice);

			OrderSummary orderSummary = new OrderSummary();
			orderSummary.setOrderId(x.getOrderId());
			orderSummary.setName(x.getName());
			orderSummary.setEmail(x.getEmail());
			orderSummary.setAmount(totalPrice);
			orderSummaryListFromRepo.add(orderSummary);
		});

		return orderSummaryListFromRepo;
	}
}
