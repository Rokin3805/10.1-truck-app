package com.example.truckshare.Data;

import static com.example.truckshare.Util.Util.ORDER_DATABASE;
import static com.example.truckshare.Util.Util.ORDER_TABLE;
import static com.example.truckshare.Util.Util.USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.truckshare.OrderModel.Order;
import com.example.truckshare.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabaseHelper extends SQLiteOpenHelper {

    public OrderDatabaseHelper(@Nullable Context context) {
        super(context, ORDER_DATABASE, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ORDER_TABLE = "CREATE TABLE " + Util.ORDER_TABLE + "("
                + Util.ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER + " TEXT, "
                + Util.SENDER + " TEXT, "
                + Util.RECIPIENT + " TEXT, "
                + Util.TRUCK_TYPE + " TEXT, "
                + Util.LOCATION + " TEXT, "
                + Util.PICK_UP_TIME + " TEXT, "
                + Util.PICK_UP_DATE + " TEXT, "
                + Util.GOODS_TYPE + " TEXT, "
                + Util.WEIGHT + " TEXT, "
                + Util.WIDTH + " TEXT, "
                + Util.LENGTH + " TEXT, "
                + Util.HEIGHT + " TEXT, "
                + Util.QUANTITY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_ORDER_TABLE = "DROP TABLE IF EXISTS " + Util.ORDER_TABLE;
        sqLiteDatabase.execSQL(DROP_ORDER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER, order.getUser());
        contentValues.put(Util.SENDER, order.getSender());
        contentValues.put(Util.RECIPIENT, order.getRecipient());
        contentValues.put(Util.TRUCK_TYPE, order.getTruckType());
        contentValues.put(Util.LOCATION, order.getLocation());
        contentValues.put(Util.PICK_UP_TIME, order.getPickUpTime());
        contentValues.put(Util.PICK_UP_DATE, order.getPickUpDate());
        contentValues.put(Util.GOODS_TYPE, order.getGoodsType());
        contentValues.put(Util.WEIGHT, order.getWeight());
        contentValues.put(Util.WIDTH, order.getWidth());
        contentValues.put(Util.LENGTH, order.getLength());
        contentValues.put(Util.HEIGHT, order.getHeight());
        contentValues.put(Util.QUANTITY, order.getQuantity());
        long newOrderId = db.insert(Util.ORDER_TABLE, null, contentValues);
        db.close();
        return newOrderId;
    }

    public ArrayList<Order> retrieveOrders(String username) {
        ArrayList<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ORDER_TABLE +
                " WHERE " + USER + " = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            do {
                Order order = new Order(
                        //user
                        cursor.getString(1),
                        //sender
                        cursor.getString(2),
                        //recipient
                        cursor.getString(3),
                        //truckType
                        cursor.getString(4),
                        //location
                        cursor.getString(5),
                        //pickUpTime
                        cursor.getString(6),
                        //pickUpDate
                        cursor.getString(7),
                        //goodsType
                        cursor.getString(8),
                        //weight
                        cursor.getString(9),
                        //width
                        cursor.getString(10),
                        //length
                        cursor.getString(11),
                        //height
                        cursor.getString(12),
                        //quantity
                        cursor.getString(13)
                );
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return orderList;
    }



}