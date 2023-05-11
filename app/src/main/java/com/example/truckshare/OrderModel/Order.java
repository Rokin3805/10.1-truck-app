package com.example.truckshare.OrderModel;

public class Order {

    private String user;
    private String sender;
    private String recipient;
    private String truckType;
    private String location;
    private String pickUpTime;
    private String pickUpDate;
    private String goodsType;
    private String weight;
    private String width;
    private String length;
    private String height;
    private String quantity;

    public Order(String user, String sender, String recipient, String truckType, String location, String pickUpTime, String pickUpDate,
                 String goodsType, String weight, String width, String length, String height, String quantity)
    {
        this.user = user;
        this.sender = sender;
        this.truckType = truckType;
        this.location = location;
        this.pickUpTime = pickUpTime;
        this.pickUpDate = pickUpDate;
        this.goodsType = goodsType;
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.height = height;
        this.quantity = quantity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
