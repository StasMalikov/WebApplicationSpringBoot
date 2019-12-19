package com.example.sweater.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Product {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "product_store",
            joinColumns = @JoinColumn(name = "product__id"),
            inverseJoinColumns = @JoinColumn(name = "store__id")
    )
    private Set<Store> stores= new HashSet<>();

    private Integer price;

    private Integer bonuses;

    public Product() {}

    public Product(Long id, String name, Set<Store> stores, Integer price, Integer bonuses) {
        this.id = id;
        this.name = name;
        this.stores = stores;
        this.price = price;
        this.bonuses = bonuses;
    }

    public void addStore(Store s) {
        stores.add(s);
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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBonuses() {
        return bonuses;
    }

    public void setBonuses(Integer bonuses) {
        this.bonuses = bonuses;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stores=" + stores +
                ", price=" + price +
                ", bonuses=" + bonuses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getStores(), product.getStores()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getBonuses(), product.getBonuses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStores(), getPrice(), getBonuses());
    }
}

