package com.springapp.mvc.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by arifen on 7/5/16.
 */
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customerId")
    private long customerId;

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
    @Column(name = "userId")
    private String userId;

    @NotEmpty
    @Size(min = 6, max = 15)
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "packageName")
    private String packageName;

    @NotNull
    @Column(name = "amount")
    private int amount;

    @NotEmpty
    @Pattern(regexp = "^(?:\\+?88)?01[15-9]\\d{8}", message = "Please provide a validate SMS number")
    @Column(name = "mobileNumber")
    private String mobileNumber;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", userId='" + userId + '\'' +
                ", packageName='" + packageName + '\'' +
                ", amount=" + amount +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
