package com.springapp.mvc.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Comparator;

/**
 * Created by arifen on 7/5/16.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private long id;

    @NotEmpty
    @Column(name = "name")
    @Size(min = 5, max = 20)
    private String name;

    @NotEmpty
    @Email
    @Column(name = "emailAddress")
    private String emailAddress;

    @NotEmpty
    @Column(name = "address")
    private String address;

    @NotEmpty
    @Size(min = 2, max = 15)
    @Column(name = "customerId")
    private String customerId;

    @NotEmpty
    @Size(min = 6, max = 15)
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "status")
    private String status;

    @NotEmpty
    @Pattern(regexp = "^(?:\\+?88)?01[15-9]\\d{8}", message = "Please provide a validate SMS number")
    @Column(name = "mobileNumber")
    private String mobileNumber;

    //@JsonManagedReference/*for json conversion recurring problem*/
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    private Package aPackage;
    public static Comparator<Customer> customerpackageComparator = new Comparator<Customer>() {

        public int compare(Customer c1, Customer c2) {
            String customerPackageName1 = c1.getaPackage().getPackageName();
            String customerPackageName2 = c2.getaPackage().getPackageName();
            return customerPackageName1.compareTo(customerPackageName2);
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", customerId='" + customerId + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", aPackage=" + aPackage +
                '}';
    }
}
