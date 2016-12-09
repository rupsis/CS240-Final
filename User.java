/*
    Author - Nate Rupsis
    File - User.java
    Desc - User class decleration, object created to hold/update all of the information from users database 
*/

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.*;

import javax.mail.Transport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class User{

    private String fname;
    private String lname;
    private String email;
    private double balance;
    private List transactions = new ArrayList();  // list of transactions and dates
    private String currentLogin; // the current login date


    // our default constructor
    public User(){
        this.fname = "";
        this.lname = "";
        this.email = "";
        this.balance = 0.0;
        this.transactions.add("");
        this.currentLogin = getDate();

    }

    public User(String json){
        JSONObject obj = new JSONObject(json);

        this.fname = obj.getString("fname");
        this.lname = obj.getString("lname");
        this.email = obj.getString("email");
        this.balance = obj.getDouble("balance");
        try{
            JSONArray transactionsArr = obj.getJSONArray("transactions");


            int size = transactionsArr.length();
            for(int i = 0; i < size; i++){
                //System.out.println(transactionsArr.getString(i));
                //System.out.println(transactionsArr.getJSONArray(i).getString(0));
                this.transactions.add(transactionsArr.getString(i));
                //System.out.println(this.transactions.get(i));
            }
        }catch(Exception e){

            System.out.println("Error: " + e.getMessage());
        }

        //this.transactions; */
        this.currentLogin = getDate(); // add the current date
    }



    public String getDate(){
        // Define a formate for the date
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // defining a date format
        Date date = new Date(); // get current date
        return df.format(date); // return formated date
    }

    // add method
    public void deposit(double amount){
        this.balance += amount;
    }

    // subtract method
    public boolean withdraw(double amount){
        if((this.balance < amount)){
            return false;
        }else{
            this.balance -= amount;
            return true;
        }
    }

    public double getBalance(){
        return this.balance;
    }

    public String getFName(){
        return this.fname;
    }

    public String getLName(){
        return this.lname;
    }

    public String getEmail(){
        return this.email;
    }

    public String printTransactions(){
        String transactionList = "Date\t Action\t Amount\t Balance\n";

        int transactionLength = this.transactions.size();
        for(int i = 0; i < transactionLength; i++){
            transactionList += this.transactions.get(i);
            transactionList += "\n";
        }
        return transactionList;
    }

    public void addTransaction(String transaction){
        this.transactions.add(transaction);
    }
}
