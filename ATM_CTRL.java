/*
    Author - Nate Rupsis
    File - ATM_CTRL.java
    Desc - The Control class for out atm system. Responsible for interfacing with the GUI and the user data
*/

import com.google.gson.*;
import com.mongodb.*;
import java.util.*;
import javax.mail.*;
import java.security.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;

class ATM_CTRL{

   public final int atmFee = 6; // a standard $6 atm fee, regardless if this is your bank or not
   private final String ecorpEmail = ""; // Ecorps email
   private User user;
   private String task;
   private DB dbConnection;
   private int userPin;
   private int userID;
   private Gson gson = new Gson(); // our Gson object

   // the atm control object will be initilized at log in time
   public ATM_CTRL(){
      this.task = "";
      // in constructor, try and establish a db connection
      try{
         // Connecting to our mongoDB server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         // Now connect to our database
         this.dbConnection = mongoClient.getDB( "ECorp_Users" );
      }catch(Exception e){
         System.out.println("Database conection error");
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
   }

   // user login method
   public boolean userLogin(int id, int pin){
      this.userID = id;
      this.userPin = pin;

      // id 4 and pin 1111 is used for test in the event that you cannot connect to the database
      if(id != 4 && pin != 1111){
         try {
            DBCursor cursor = dbConnection.getCollection("Users")
                    .find(new BasicDBObject("userID", this.userID).append("pin", this.userPin)); // wrapper object to or them all together
            if (cursor.hasNext()) {
               String userJson = cursor.next().get("userObject").toString(); // grabbing the user object
               user = new User(userJson); // construct the user object
               return true;  // return a boolean value for the success of the query
            }
         } catch(Exception e){
            System.out.println(e.getMessage() + " - Connection to database failed"); // print out the error message
            return false;
         }
      }else{
         // I was unable to successfully deploy a database server
         // so to bypass the login system, I've created a test user to test the application with
         String testJson = "{'fname':'Test','lname':'User','email':'ajharris@iupui.edu','balance':10000, 'transactions' : ['2016/12/04 00:35:04, Deposit, 200, 10200', '2016/12/01 05:35:04, Withdrawl, 200, 10000']}";
         user = new User(testJson);
         return true;
      }
      return false;// if neither are the case, return false
   }

   // method to query the database and update the user
   public void saveUser(){

      // use database connection to update the user records
      DBCollection collection = dbConnection.getCollection("Users");

      // convert the user to json with Gson
      Gson gson = new Gson();
      String userJSON = gson.toJson(this.user);

      // create the basic query and update objects
      BasicDBObject query = new BasicDBObject("userID", this.userID).append("pin", this.userPin);
      BasicDBObject update = new BasicDBObject("$set", new BasicDBObject("userObject", userJSON));

      // don't update the test user
      if(this.userPin != 1111 && this.userID != 4) {
         try{
            collection.update(query, update); // pass in the query and the update
         }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         }
      }

      this.userPin = -1;
      this.userID = -1;
   }


   // return the users first + last name
   public String getFormatedName(){
      return user.getFName() + " " + user.getLName();
   }

   // return the users current balance
   public String getUserBalance(){
      return "$" + user.getBalance();
   }

   // get the current task
   public String getTask(){
      return this.task;
   }

   // set the current task
   public void setTask(String task){
      this.task = task;
   }

   // access the user deposit method
   public void userDeposit(double amount){
      user.deposit(amount);
   }

   // access the user withdraw method
   public boolean userWithdraw(double amount){
      return user.withdraw(amount);
   }

   // method to email the transaction list to user
   public boolean emailUser(){

      // using google as our stmp client to send emails
      // tried setting up localhost stmp, didn't work
      Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

      // setting all of the properties for the gmail stmp server(I don't profess to know these properties,
      // some nice people on the internet provided the code)
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtps.host", "smtp.gmail.com");
      properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
      properties.setProperty("mail.smtp.port", "465");
      properties.setProperty("mail.smtp.socketFactory.port", "465");
      properties.setProperty("mail.smtps.auth", "true");
      properties.put("mail.smtps.quitwait", "false");
      // starting a session with given properties
      Session session = Session.getInstance(properties, null);
      try{
         // creating message object, set from, to, subject, and messages
         MimeMessage message = new MimeMessage(session); // object
         message.setFrom(new InternetAddress(this.ecorpEmail));// from
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));// to
         message.setSubject("Your ECorp Transactions"); // subject
         message.setText("Your Ecorp Transactions\n" + user.printTransactions()); // header

         SMTPTransport transport = (SMTPTransport)session.getTransport("smtps");

         transport.connect("smtp.gmail.com", "userName","Password");
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
         return true;
      } catch (MessagingException e){
         System.out.println(e.getMessage()); // print error message to console
         return false; // if message fails, return false
      }
   }

   // method for adding a transaction to the list of user transactions
   public void addUserTransaction(String date, String action, double ammount, String balance){
      String transactionStr = date + "\t" + action + "\t" + String.valueOf(ammount) + "\t" + balance;
      this.user.addTransaction(transactionStr);
   }

   // return the current date
   public String currentDate(){
      return this.user.getDate();
   }
}