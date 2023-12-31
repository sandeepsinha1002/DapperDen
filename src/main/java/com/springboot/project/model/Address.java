package com.springboot.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    	
	@Column(name="name")
	private String name;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Address(Long id, String name, String streetAddress, String city, String state, String pincode, User user,
            String phoneNumber) {
        this.id = id;
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.user = user;
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    private String phoneNumber;


    public Address(){
        
    }


    public Long getId() {
        return id;
    }


    public String getStreetAddress() {
        return streetAddress;
    }


    public String getCity() {
        return city;
    }


    public String getState() {
        return state;
    }


    public String getPincode() {
        return pincode;
    }


    public User getUser() {
        return user;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public void setState(String state) {
        this.state = state;
    }


    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


    public void setUser(User user) {
        this.user = user;
    }


}
