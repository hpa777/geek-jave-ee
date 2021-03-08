package ru.geekbrains.persist;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "from Order "),
        @NamedQuery(name = "deleteByIdOrder", query = "delete from Order o where o.id = :id"),
        @NamedQuery(name = "countAllOrders", query = "select count(*) from Order")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;

    @Column
    private BigDecimal sum;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(){

    }

    public Order(Long id, User user, Date createdon, BigDecimal sum) {
        this.id = id;
        this.user = user;
        this.createdon = createdon;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
