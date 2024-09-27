package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cart_items")
@Getter
@Setter

public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id")
    private Long id;

    @Column(name="create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name="last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacation vacation;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "excursion_cartItem",
            joinColumns = @JoinColumn(name="cart_item_id"),
            inverseJoinColumns = @JoinColumn(name="excursion_id"))
    private Set<Excursion> excursions = new HashSet<>();*/

    @ManyToMany
    @JoinTable(name = "excursion_cartitem",
            joinColumns = @JoinColumn(name="cart_item_id"),
            inverseJoinColumns = @JoinColumn(name="excursion_id"))
    private Set<Excursion> excursions = new HashSet<>();

}