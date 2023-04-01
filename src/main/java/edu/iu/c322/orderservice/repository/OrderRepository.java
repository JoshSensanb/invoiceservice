package edu.iu.c322.orderservice.repository;

import edu.iu.c322.customerservice.model.Customer;
import edu.iu.c322.orderservice.model.Items;
import edu.iu.c322.orderservice.model.Order;
import edu.iu.c322.orderservice.model.Return;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
@Repository
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public List<Order> findAll(){
        return orders;
    }
    @PostMapping
    public int create(Order order){
        int id = orders.size()+1;
        order.setOrderId(id);
        orders.add(order);
        return id;

    }
    public void update(Return returna){
        Order x = getOrderByID(returna.getOrderNumber());
        if(x!=null){
            x.setOrderId(returna.getOrderNumber());


        }
        else{
            throw new IllegalStateException("Order id is not valid.");
        }

    }

    private Order getOrderByID(int id) {
        return orders.stream().filter(x -> x.getOrderId() == id).findAny().orElse(null);

    }
    public void delete(int id){
        Order x = getOrderByID(id);
        if(x!=null){
            orders.remove(x);
        }
        else{
            throw new IllegalStateException("not a valid Order id");
        }
    }
}