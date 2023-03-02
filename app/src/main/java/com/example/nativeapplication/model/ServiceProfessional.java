package com.example.nativeapplication.model;

public class ServiceProfessional {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private String city;
    private Float rating;
    private String serviceName;
    private String serviceDescription;
    private Double price;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public Float getRating() {
        return rating;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public Double getPrice() {
        return price;
    }

    public String getServiceSubcategory() {
        return serviceSubcategory;
    }

    private String serviceSubcategory;
}

