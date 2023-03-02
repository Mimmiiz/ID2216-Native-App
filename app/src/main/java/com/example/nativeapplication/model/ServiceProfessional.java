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

    public ServiceProfessional(ServiceProfessional serviceProfessional) {
        this.name = serviceProfessional.getName();
        this.email = serviceProfessional.getEmail();
        this.phoneNumber = serviceProfessional.getPhoneNumber();
        this.address = serviceProfessional.getAddress();
        this.postalCode = serviceProfessional.getPostalCode();
        this.city = serviceProfessional.getCity();
        this.rating = serviceProfessional.getRating();
        this.serviceName = serviceProfessional.getServiceName();
        this.serviceDescription = serviceProfessional.getServiceDescription();
        this.price = serviceProfessional.getPrice();
        this.serviceSubcategory = serviceProfessional.getServiceSubcategory();
    }

    public ServiceProfessional(Integer id, String name, String email, String phoneNumber, String address, String postalCode, String city, Float rating, String serviceName, String serviceDescription, Double price, String serviceSubcategory) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.rating = rating;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.price = price;
        this.serviceSubcategory = serviceSubcategory;
    }

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

