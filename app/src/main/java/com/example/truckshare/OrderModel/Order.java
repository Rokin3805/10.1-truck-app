package com.example.truckshare.OrderModel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Order implements Parcelable {

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

    protected Order(Parcel in) {
        user = in.readString();
        sender = in.readString();
        recipient = in.readString();
        truckType = in.readString();
        location = in.readString();
        pickUpTime = in.readString();
        pickUpDate = in.readString();
        goodsType = in.readString();
        weight = in.readString();
        width = in.readString();
        length = in.readString();
        height = in.readString();
        quantity = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(user);
        parcel.writeString(sender);
        parcel.writeString(recipient);
        parcel.writeString(truckType);
        parcel.writeString(location);
        parcel.writeString(pickUpTime);
        parcel.writeString(pickUpDate);
        parcel.writeString(goodsType);
        parcel.writeString(weight);
        parcel.writeString(width);
        parcel.writeString(length);
        parcel.writeString(height);
        parcel.writeString(quantity);
    }
}
