package com.example.accessingdatajpa.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(fluent = true)
@Entity
@Table(name = "portfolio")
public class Portfolio {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  private String name;

  @OneToMany(mappedBy="portfolio", fetch = FetchType.LAZY)
  List<Customer> customers;

  @Override
  public String toString() {
    return "Portfolio{" + "id=" + id + ", name='" + name + '\'' + ", customers? " +
    (customers == null) + '}';
  }
}