package com.example.sneakershop.user;

import com.example.sneakershop.basket.Basket;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity // tells to create a table in db
@Table(name = "users") // creates table in the database with name "users"
@NoArgsConstructor // creates empty constructor
@AllArgsConstructor // creates all arguments constructor
@Getter // self-explanatory
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Means that is gonna be primary key in db
  @Column(name = "user_id") // names the column in db
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "e_mail", unique = true)
  private String email;

  @JsonIgnore
  @JsonBackReference
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "basket_id", referencedColumnName = "id")
  private Basket basket;

  public User(String name) {
    this.name = name;
  }
}
