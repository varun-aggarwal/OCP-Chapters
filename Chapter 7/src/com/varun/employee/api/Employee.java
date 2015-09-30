package com.varun.employee.api;

public class Employee {

    private String name;

    private String address;

    private String postcode;

    public Employee(String name, String address, String postcode) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String toString() {
        return ("Employee  : " + name + "\t Address : " + address + "\t Postcode : " + postcode + "\n");
    }

}
