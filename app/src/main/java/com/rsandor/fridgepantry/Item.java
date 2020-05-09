package com.rsandor.fridgepantry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity(tableName="item_table")
@TypeConverters(DateConverter.class)
public class Item {

    @NonNull
    @ColumnInfo(name = "itemName")
    private String itemName;


    @Ignore
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Nullable
    @ColumnInfo(name= "date")
    private Date date = new Date();

    @NonNull
    @ColumnInfo(name = "quantity")
    private int quantity;

    @Nullable
    @ColumnInfo(name= "unit")
    private String unit;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;


    public Item(@NonNull String itemName, @NonNull int quantity, @NonNull String unit){
        this.itemName   = itemName;
        this.quantity   = quantity;
        this.unit       = unit;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id){
        this.id = id;
    }

    @NonNull
    public String getItemName() {
        return itemName;
    }

    public void setItemName(@NonNull String itemName) {
        this.itemName = itemName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @NonNull
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull int quantity) {
        this.quantity = quantity;
    }

    @Nullable
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
