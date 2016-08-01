package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by arifen on 7/15/16.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Package")
public class Package {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;

    @NotEmpty
    @Size(min = 4, max = 15)
    @Column(name = "packageName")
    private String packageName;

    @NotNull
    @Column(name = "amount")
    private String amount;

    //@JsonBackReference
    @OneToMany(mappedBy = "aPackage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Customer> customerSet;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

/*@Override
    public String toString() {
        return "Package{" +
                "packageId=" + packageId +
                ", packageName='" + packageName + '\'' +
                ", amount='" + amount + '\'' +
                ", customerSet=" + customerSet +
                '}';
    }*/
}
