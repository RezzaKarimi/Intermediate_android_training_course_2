package com.example.season8.objects;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

public class E09Object implements Serializable {
    @SerializedName("f1") private int CustomerID;
    @SerializedName("f2") private String CustomerName;
    @SerializedName("f3")private String ContactName;
    @SerializedName("f4")private String Address;
    @SerializedName("f5")private String City;
    @SerializedName("f6")private String PostalCode;
    @SerializedName("f7")private String Country;
    @SerializedName("f8")private String Image;



    public E09Object(JSONObject jsonObject){
        //A second way
        // object.setCustomerID(jsonObject.getInt(Router.CustomerID));
        //                object.setCustomerName(jsonObject.getString(Router.CustomerName));
        //                object.setContactName(jsonObject.getString(Router.ContactName));
        //                object.setAddress(jsonObject.getString(Router.Address));
        //                object.setCity(jsonObject.getString(Router.City));
        //                object.setPostalCode(jsonObject.getString(Router.PostalCode));
        //                object.setCountry(jsonObject.getString(Router.Country));
    }

    public E09Object(){

    }


    public void setCustomerID(int id){
        this.CustomerID = id;
    }
    public int getCustomerID(){return this.CustomerID;}

    public void setCustomerName(String name){
        this.CustomerName = name;
    }
    public String getCustomerName(){return this.CustomerName;}

    public void setContactName(String contactName){
        this.ContactName = contactName;
    }
    public String getContactName(){return this.ContactName;}

    public void setAddress(String address){
        this.Address = address;
    }
    public String getAddress(){return this.Address;}

    public void setCity(String city){
        this.City = city;
    }
    public String getCity(){return this.City;}

    public void setPostalCode(String postalCode){
        this.PostalCode = postalCode;
    }
    public String getPostalCode(){return this.PostalCode;}

    public void setCountry(String country){
        this.Country = country;
    }
    public String getCountry(){return this.Country;}

    public void setImage(String image){ this.Image = image;}
    public String getImage(){return this.Image;}


}


