package vttp2022.assessment.csf.orderbackend.mapper;
import org.springframework.jdbc.core.RowMapper;
import vttp2022.assessment.csf.orderbackend.models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

public class OrderRowMapper implements RowMapper<Order> {

    public Order mapRow (ResultSet rs, int numRow) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setName(rs.getString("name"));
        order.setEmail(rs.getString("email"));
        order.setSize(rs.getInt("pizza_size"));
        order.setThickCrust(rs.getBoolean("thick_crust"));
        order.setSauce(rs.getString("sauce"));

        String[] toppingString = rs.getString("toppings").split(",");

        LinkedList<String> getTopping = new LinkedList<>(Arrays.asList(toppingString));

        order.setToppings(getTopping);
        order.setComments(rs.getString("comments"));

        return order;

    }

}
