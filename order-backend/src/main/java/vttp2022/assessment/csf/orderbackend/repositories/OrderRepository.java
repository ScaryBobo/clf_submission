package vttp2022.assessment.csf.orderbackend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vttp2022.assessment.csf.orderbackend.mapper.OrderRowMapper;
import vttp2022.assessment.csf.orderbackend.models.Order;

import java.util.List;

@Repository
public class OrderRepository {

    private static String SQL_INSERT_ORDER = "insert into orders (name, email, pizza_size, thick_crust, sauce, toppings, comments) values (?,?,?,?,?,?,?)";
    private static String SQL_GET_ORDER_BY_EMAIL = "select * from orders where email=?";
    @Autowired
    private JdbcTemplate template;

    public boolean insertNewOrder(String name, String email, Integer pizzaSize,
                                  Boolean crust, String sauce, String toppings, String comments){

        final int rowCount = template.update(SQL_INSERT_ORDER, name, email, pizzaSize, crust, sauce, toppings, comments );
        return rowCount > 0;

    }

    public List<Order> getOrdersByEmail (String email){
        return template.query(SQL_GET_ORDER_BY_EMAIL, new OrderRowMapper(), email);
    }




}
