package ru.geekbrains.persist;
import ru.geekbrains.service.ProductRepresentation;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product"),
        @NamedQuery(name = "deleteByIdProduct", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "countAllProducts", query = "select count(*) from Product"),
        @NamedQuery(name = "findByNameProduct", query = "from Product p where p.name = :name"),
        @NamedQuery(name = "findByCategoryIdProducts", query = "from Product p where p.category.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 1024)
    private String description;

    @Column
    private BigDecimal price;


    @ManyToOne
    private Category category;

    public Product() {
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Product)obj).id);
    }

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(ProductRepresentation prodRep, Category category) {
        this(prodRep.getId(), prodRep.getName(), prodRep.getDescription(), prodRep.getPrice());
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
