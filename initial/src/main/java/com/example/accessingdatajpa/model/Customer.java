package com.example.accessingdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(fluent = true)
@Setter
@Entity
@Table(name = "customer") // Default is 'Customer'
public class Customer {

    @Getter @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter private String firstName, lastName;

    @ManyToOne
    Portfolio portfolio;

    @Override
    public String toString () {
        return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s', portfolio='%s']",
            id, this.firstName, lastName, portfolio);
    }

}



