package ru.geekbrains.persist;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@NamedQueries({
        @NamedQuery(name = "findAllOrderItems", query = "from OrderItem "),
        @NamedQuery(name = "deleteByIdOrderItem", query = "delete from OrderItem o where o.id = :id"),
        @NamedQuery(name = "countAllOrderItems", query = "select count(*) from OrderItem")
})
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public OrderItem(){

    }

    public OrderItem(Long id, Order order, Product product) {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
