package com.rsandor.fridgepantry;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//TODO exportSchema needs to be set to true. I need to handle migrations.
//I have some sample src to show me how it is done.
//architecture-components-samples/PersistenceMigrationsSample/app/src/room3/java/com/example/android/persistence/migration
@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemRoomDatabase extends RoomDatabase {
    public abstract ItemDao ItemDao();

    private static volatile ItemRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ItemRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (ItemRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemRoomDatabase.class, "item_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{
                ItemDao dao = INSTANCE.ItemDao();

                Item item = new Item("Hamburger", 1, "pound");
                dao.insert(item);
                item = new Item("buns", 12, "pack");
                dao.insert(item);

            });
        }
    };




}
