package com.rsandor.fridgepantry;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ItemDao {


    // allowing the insert of the same Item multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item item);

    @Query("DELETE FROM item_table")
    void deleteAll();

    @Query("SELECT * from item_table ORDER BY itemName ASC")
    LiveData<List<Item>> getAlphabetizedItems();

    @Query("DELETE  from item_table WHERE itemName='Beans'")
    void removeBeans();
}
