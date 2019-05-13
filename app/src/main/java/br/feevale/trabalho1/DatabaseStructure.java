package br.feevale.trabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DatabaseStructure {

    private Context ctx;
    public static final String DATABASE_NAME = "trabalho1.db";
    public static final Integer DATABASE_VERSION = 3;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DatabaseStructure(Context ctx){
        this.ctx = ctx;
        dbHelper = new DatabaseHelper();
        db = dbHelper.getWritableDatabase();
    }

    public static class ClientTable implements BaseColumns{
        public static final String TABLE_NAME = "Client";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CONTACT_EMAIL= "contactEmail";
        public static final String COLUMN_TYPE= "type";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME          + " TEXT, " +

                    COLUMN_ADDRESS         +" TEXT, " +
                    COLUMN_CONTACT_EMAIL         +" TEXT, " +
                    COLUMN_TYPE        + " INTEGER " +
                    ")";
            return sql;
        }
    }

    public static class FoodTable implements BaseColumns{
        public static final String TABLE_NAME = "Food";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CALORIES = "calories";
        public static final String COLUMN_EXPIRATION_DATE = "expirationDate";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_PRICE = "price";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME          + " TEXT, " +
                    COLUMN_CALORIES       + " TEXT, " +
                    COLUMN_EXPIRATION_DATE          + " TEXT, " +
                    COLUMN_TYPE        + " INTEGER, " +
                    COLUMN_PRICE       +" REAL)";
            return sql;
        }



    }

    public static class DrinkTable implements BaseColumns{
        public static final String TABLE_NAME = "drink";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_VOLUME = "volume";
        public static final String COLUMN_IS_ALCOOLIC = "isAlcoolic";
        public static final String COLUMN_PRICE = "price";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME          + " TEXT, " +
                    COLUMN_VOLUME       + " TEXT, " +
                    COLUMN_IS_ALCOOLIC          + " INTEGER, " +
                    COLUMN_PRICE       +" REAL)";
            return sql;
        }
    }

    public static class FoodSaleTable implements BaseColumns{
        public static final String TABLE_NAME = "sale";
        public static final String COLUMN_CLIENT = "client";
        public static final String COLUMN_FOOD = "food";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_CLIENT        + " INTEGER, " +
                    COLUMN_FOOD        + " INTEGER, " +
                    "FOREIGN KEY("       + COLUMN_CLIENT +
                    ") REFERENCES "      + ClientTable.TABLE_NAME + "(" + ClientTable._ID + ")" +
                    "FOREIGN KEY("       + COLUMN_FOOD+
                    ") REFERENCES "      + FoodTable.TABLE_NAME + "(" + FoodTable._ID + ")" +
                    ")";
            return sql;
        }
    }

    public static class DrinkSaleTable implements BaseColumns{
        public static final String TABLE_NAME = "Drinksale";
        public static final String COLUMN_CLIENT = "client";
        public static final String COLUMN_DRINK = "drink";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_CLIENT        + " INTEGER, " +
                    COLUMN_DRINK        + " INTEGER, " +
                    "FOREIGN KEY("       + COLUMN_CLIENT +
                    ") REFERENCES "      + ClientTable.TABLE_NAME + "(" + ClientTable._ID + ")" +
                    "FOREIGN KEY("       + COLUMN_DRINK+
                    ") REFERENCES "      + DrinkTable.TABLE_NAME + "(" + DrinkTable._ID + ")" +
                    ")";
            return sql;
        }
    }

    public Client getClient(Long id){
        String cols[] = {ClientTable._ID, ClientTable.COLUMN_NAME, ClientTable.COLUMN_ADDRESS, ClientTable.COLUMN_CONTACT_EMAIL, ClientTable.COLUMN_TYPE};
        String args[] = {id.toString()};
        Cursor cursor = db.query(ClientTable.TABLE_NAME, cols, ClientTable._ID+"=?", args, null, null, ClientTable._ID);


        if(cursor.getCount() == 0){
            return null;
        }

        cursor.moveToNext();
        Client c = new Client();
        c.setId(cursor.getLong(cursor.getColumnIndex(ClientTable._ID)));
        c.setName(cursor.getString(cursor.getColumnIndex(ClientTable.COLUMN_NAME)));
        c.setAddress(cursor.getString(cursor.getColumnIndex(ClientTable.COLUMN_ADDRESS)));
        c.setContactEmail(cursor.getString(cursor.getColumnIndex(ClientTable.COLUMN_CONTACT_EMAIL)));
        c.setType(cursor.getInt(cursor.getColumnIndex(ClientTable.COLUMN_TYPE)));

        return c;
    }

    public Food getFood(Long id){
        String cols[] = {FoodTable._ID, FoodTable.COLUMN_NAME, FoodTable.COLUMN_CALORIES, FoodTable.COLUMN_PRICE, FoodTable.COLUMN_TYPE,FoodTable.COLUMN_EXPIRATION_DATE};
        String args[] = {id.toString()};
        Cursor cursor = db.query(FoodTable.TABLE_NAME, cols, FoodTable._ID+"=?", args, null, null, FoodTable._ID);


        if(cursor.getCount() == 0){
            return null;
        }

        cursor.moveToNext();
        Food f = new Food();
        f.setId(cursor.getLong(cursor.getColumnIndex(FoodTable._ID)));
        f.setName(cursor.getString(cursor.getColumnIndex(FoodTable.COLUMN_NAME)));
        f.setCalories(cursor.getInt(cursor.getColumnIndex(FoodTable.COLUMN_CALORIES)));
        f.setExpirationDate(cursor.getString(cursor.getColumnIndex(FoodTable.COLUMN_EXPIRATION_DATE)));
        f.setPrice(cursor.getDouble(cursor.getColumnIndex(FoodTable.COLUMN_PRICE)));
        f.setType(cursor.getInt(cursor.getColumnIndex(FoodTable.COLUMN_TYPE)));



        return f;
    }

    public Drink getDrink(Long id){
        String cols[] = {DrinkTable._ID, DrinkTable.COLUMN_NAME, DrinkTable.COLUMN_IS_ALCOOLIC, DrinkTable.COLUMN_PRICE, DrinkTable.COLUMN_VOLUME};
        String args[] = {id.toString()};
        Cursor cursor = db.query(DrinkTable.TABLE_NAME, cols, DrinkTable._ID+"=?", args, null, null, DrinkTable._ID);

        if(cursor.getCount() == 0){
            return null;
        }

        cursor.moveToNext();
        Drink d;
        d = new Drink();
        d.setId(cursor.getLong(cursor.getColumnIndex(DrinkTable._ID)));
        d.setName(cursor.getString(cursor.getColumnIndex(DrinkTable.COLUMN_NAME)));
        d.setPrice(cursor.getDouble(cursor.getColumnIndex(DrinkTable.COLUMN_PRICE)));
        d.setVolume(cursor.getInt(cursor.getColumnIndex(DrinkTable.COLUMN_VOLUME)));
        boolean value = cursor.getInt(cursor.getColumnIndex(DrinkTable.COLUMN_IS_ALCOOLIC)) > 0;
        d.setAlcoolic(value);

        return d;
    }


    public Long addFood(Food f){
        ContentValues values = new ContentValues();

        values.put(FoodTable.COLUMN_CALORIES,f.getCalories());
        values.put(FoodTable.COLUMN_NAME,f.getName());
        values.put(FoodTable.COLUMN_EXPIRATION_DATE,f.getCalories());
        values.put(FoodTable.COLUMN_PRICE,f.getPrice());
        values.put(FoodTable.COLUMN_TYPE,f.getType());

        return db.insert(FoodTable.TABLE_NAME, null, values);
    }

    public Long addDrink(Drink d){
        ContentValues values = new ContentValues();

        values.put(DrinkTable.COLUMN_NAME,d.getName());
        values.put(DrinkTable.COLUMN_IS_ALCOOLIC,d.isAlcoolic());
        values.put(DrinkTable.COLUMN_PRICE,d.getPrice());
        values.put(DrinkTable.COLUMN_VOLUME,d.getVolume());

        return db.insert(DrinkTable.TABLE_NAME, null, values);
    }

    public Long addClient(Client c){
        ContentValues values = new ContentValues();

        values.put(ClientTable.COLUMN_NAME, c.getName());
        values.put(ClientTable.COLUMN_ADDRESS, c.getAddress());
        values.put(ClientTable.COLUMN_CONTACT_EMAIL, c.getContactEmail());
        values.put(ClientTable.COLUMN_TYPE, c.getType());

        return db.insert(ClientTable.TABLE_NAME, null, values);
    }

    public Long addFoodSale(Sale s){
        ContentValues values = new ContentValues();

        values.put(FoodSaleTable.COLUMN_CLIENT, s.getClient());
        values.put(FoodSaleTable.COLUMN_FOOD,s.getFood());

        return db.insert(FoodSaleTable.TABLE_NAME, null, values);
    }

    public Long addDrinkSale(Sale s){
        ContentValues values = new ContentValues();

        values.put(DrinkSaleTable.COLUMN_CLIENT, s.getClient());
        values.put(DrinkSaleTable.COLUMN_DRINK,s.getDrink());

        return db.insert(DrinkSaleTable.TABLE_NAME, null, values);
    }



    public List<Client> getClients(){
        String cols[] = {ClientTable._ID, ClientTable.COLUMN_NAME, ClientTable.COLUMN_CONTACT_EMAIL, ClientTable.COLUMN_TYPE, ClientTable.COLUMN_ADDRESS};
        Cursor cursor = db.query(ClientTable.TABLE_NAME, cols, null, null, null, null, ClientTable._ID);
        List<Client> clients = new ArrayList<>();
        Client s;

        while(cursor.moveToNext()){
            s = new Client();
            s.setId(cursor.getLong(cursor.getColumnIndex(ClientTable._ID)));
            s.setName(cursor.getString(cursor.getColumnIndex(ClientTable.COLUMN_NAME)));
            s.setContactEmail(cursor.getString(cursor.getColumnIndex(ClientTable.COLUMN_CONTACT_EMAIL)));
            s.setAddress(cursor.getString(cursor.getColumnIndex(ClientTable.COLUMN_ADDRESS)));
            clients.add(s);
        }

        return clients;
    }

    public List<Sale> getFoodSales(Long id){
        String cols[] = {FoodSaleTable._ID, FoodSaleTable.COLUMN_CLIENT, FoodSaleTable.COLUMN_FOOD};
        String args[] = {id.toString()};
        Cursor cursor = db.query(FoodSaleTable.TABLE_NAME, cols, FoodSaleTable.COLUMN_CLIENT+"=?", args, null, null, FoodSaleTable._ID);
        List<Sale> sales = new ArrayList<>();
        Sale s;

        while(cursor.moveToNext()){
            s = new Sale();
            s.setId(cursor.getLong(cursor.getColumnIndex(FoodSaleTable._ID)));
            s.setFood(cursor.getLong(cursor.getColumnIndex(FoodSaleTable.COLUMN_FOOD)));

            sales.add(s);
        }

        return sales;
    }

    public List<Sale> getDrinkSales(Long id){
        String cols[] = {DrinkSaleTable._ID, DrinkSaleTable.COLUMN_CLIENT, DrinkSaleTable.COLUMN_DRINK};
        String args[] = {id.toString()};
        Cursor cursor = db.query(DrinkSaleTable.TABLE_NAME, cols, DrinkSaleTable.COLUMN_CLIENT+"=?", args, null, null, DrinkSaleTable._ID);
        List<Sale> sales = new ArrayList<>();
        Sale s;

        while(cursor.moveToNext()){
            s = new Sale();
            s.setId(cursor.getLong(cursor.getColumnIndex(DrinkSaleTable._ID)));
            s.setDrink(cursor.getLong(cursor.getColumnIndex(DrinkSaleTable.COLUMN_DRINK)));

            sales.add(s);
        }

        return sales;
    }

    public List<Drink> getDrinks(){
        String cols[] = {DrinkTable._ID, DrinkTable.COLUMN_NAME, DrinkTable.COLUMN_IS_ALCOOLIC, DrinkTable.COLUMN_PRICE, DrinkTable.COLUMN_VOLUME};
        Cursor cursor = db.query(DrinkTable.TABLE_NAME, cols, null, null, null, null, DrinkTable._ID);
        List<Drink> drinks = new ArrayList<>();
        Drink d;

        while(cursor.moveToNext()){
            d = new Drink();
            d.setId(cursor.getLong(cursor.getColumnIndex(DrinkTable._ID)));
            d.setName(cursor.getString(cursor.getColumnIndex(DrinkTable.COLUMN_NAME)));
            d.setPrice(cursor.getDouble(cursor.getColumnIndex(DrinkTable.COLUMN_PRICE)));
            d.setVolume(cursor.getInt(cursor.getColumnIndex(DrinkTable.COLUMN_VOLUME)));
            boolean value = cursor.getInt(cursor.getColumnIndex(DrinkTable.COLUMN_IS_ALCOOLIC)) > 0;
            d.setAlcoolic(value);
            drinks.add(d);
        }

        return drinks;
    }

    public List<Food> getFoods(){
        String cols[] = {FoodTable._ID, FoodTable.COLUMN_NAME, FoodTable.COLUMN_EXPIRATION_DATE, FoodTable.COLUMN_TYPE, FoodTable.COLUMN_PRICE,FoodTable.COLUMN_CALORIES};
        Cursor cursor = db.query(FoodTable.TABLE_NAME, cols, null, null, null, null, FoodTable._ID);
        List<Food> foods = new ArrayList<>();
        Food s;

        while(cursor.moveToNext()){
            s = new Food();
            s.setId(cursor.getLong(cursor.getColumnIndex(FoodTable._ID)));
            s.setName(cursor.getString(cursor.getColumnIndex(FoodTable.COLUMN_NAME)));
            s.setCalories(cursor.getInt(cursor.getColumnIndex(FoodTable.COLUMN_CALORIES)));
            s.setPrice(cursor.getDouble(cursor.getColumnIndex(FoodTable.COLUMN_PRICE)));
            s.setType(cursor.getInt(cursor.getColumnIndex(FoodTable.COLUMN_TYPE)));
            s.setExpirationDate(cursor.getString(cursor.getColumnIndex(FoodTable.COLUMN_EXPIRATION_DATE)));
            foods.add(s);
        }

        return foods;
    }

    public boolean deleteClient(long id){

        return db.delete(ClientTable.TABLE_NAME,ClientTable._ID + "=" + id,null) > 0;

    }

    public boolean deleteFoodSale(long id){

        return db.delete(FoodSaleTable.TABLE_NAME,FoodSaleTable._ID + "=" + id,null) > 0;

    }

    public boolean deleteDrinkSale(long id){

        return db.delete(DrinkSaleTable.TABLE_NAME,DrinkSaleTable._ID + "=" + id,null) > 0;

    }

    public boolean deleteDrink(long id){

        return db.delete(DrinkTable.TABLE_NAME,DrinkTable._ID + "=" + id,null) > 0;

    }

    public boolean deleteFood(long id){

        return db.delete(FoodTable.TABLE_NAME,ClientTable._ID + "=" + id,null) > 0;

    }

    public void updateClient(Client c){
        ContentValues cv = new ContentValues();

        cv.put(ClientTable.COLUMN_NAME,c.getName());
        cv.put(ClientTable.COLUMN_ADDRESS,c.getAddress());
        cv.put(ClientTable.COLUMN_CONTACT_EMAIL,c.getContactEmail());
        cv.put(ClientTable.COLUMN_TYPE,c.getType());

        db.update(ClientTable.TABLE_NAME, cv, ClientTable._ID+"="+c.getId(), null);
    }

    public void updateDrink(Drink d){
        ContentValues cv = new ContentValues();

        cv.put(DrinkTable.COLUMN_NAME,d.getName());
        cv.put(DrinkTable.COLUMN_VOLUME,d.getVolume());
        cv.put(DrinkTable.COLUMN_PRICE,d.getPrice());
        cv.put(DrinkTable.COLUMN_IS_ALCOOLIC,d.isAlcoolic());

        db.update(DrinkTable.TABLE_NAME, cv, DrinkTable._ID+"="+d.getId(), null);
    }

    public void updateFood(Food c){
        ContentValues cv = new ContentValues();

        cv.put(FoodTable.COLUMN_NAME,c.getName());
        cv.put(FoodTable.COLUMN_TYPE,c.getType());
        cv.put(FoodTable.COLUMN_EXPIRATION_DATE,c.getExpirationDate());
        cv.put(FoodTable.COLUMN_CALORIES,c.getCalories());
        cv.put(FoodTable.COLUMN_PRICE,c.getPrice());


        db.update(FoodTable.TABLE_NAME, cv, FoodTable._ID+"="+c.getId(), null);
    }


    private class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(){
            super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ClientTable.getSQL());
            db.execSQL(FoodTable.getSQL());
            db.execSQL(DrinkTable.getSQL());
            db.execSQL(FoodSaleTable.getSQL());
            db.execSQL(DrinkSaleTable.getSQL());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + ClientTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + FoodTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DrinkTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + FoodSaleTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DrinkSaleTable.TABLE_NAME);
            /*
            db.execSQL("DROP TABLE IF EXISTS CLIENT");
            db.execSQL("DROP TABLE IF EXISTS " + FoodTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DrinkTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + FoodSaleTable.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DrinkSaleTable.TABLE_NAME);
            */
            onCreate(db);
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

}
