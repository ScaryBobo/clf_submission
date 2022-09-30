package vttp2022.assessment.csf.orderbackend.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;
import vttp2022.assessment.csf.orderbackend.services.OrderService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api")
public class OrderRestController {
    @Autowired
    private OrderService orderSvc;

    private Logger logger = Logger.getLogger(OrderRestController.class.getName());
    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody String payload){
        logger.info(">>>>>>> payload is: %s ".formatted(payload));
        Gson gson = new Gson();
        Order newOrder = gson.fromJson(payload, Order.class);

        System.out.println(">>>> after mapping"+ newOrder.toString());
        orderSvc.createOrder(newOrder);

        return null;
    }

    @GetMapping (path = "/order/{email}/all")
    public ResponseEntity<List<OrderSummary>> getListOfOrders (@PathVariable String email){

        List<OrderSummary> orderSummaryList = orderSvc.getOrdersByEmail(email);
        return ResponseEntity.accepted().body(orderSummaryList);

    }


}
