package com.verifone.infra;

public class Company extends User {
    private String companyName;
    private String country;
    private String address;
    private String city;
    private int zipCode;
    private int countryCode;
    private int contactNumber;
    private String countryTaxCode;

    public Company(String companyName, String address, String city, int zipCode, int countryCode,
                   int contactNumber) {
        this.companyName = companyName;
        this.country = "CA";
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.countryTaxCode = "CA";
    }

    public Company() {
        this.companyName = generateNames();
        this.country = "CA";
        this.address = generateNames();
        this.city = generateNames();
        this.zipCode = getRandomNumber();
        this.countryCode = getRandomNumber();
        this.contactNumber = getRandomNumber();
        this.countryTaxCode = "CA";
    }
//

    public String getCompanyName() {
        return companyName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getCountryTaxCode() {
        return countryTaxCode;
    }
}