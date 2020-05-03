package com.rsandor.fridgepantry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Item {
    private String itemName;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date = new Date();
    private int quanitiy;
    private UUID id;

    public Item(){
        id = UUID.randomUUID();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuanitiy() {
        return quanitiy;
    }

    public void setQuanitiy(int quanitiy) {
        this.quanitiy = quanitiy;
    }
}
