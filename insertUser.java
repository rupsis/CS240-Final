/**
 * Created by Nate on 11/28/2016.
 */

import com.mongodb.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;


public class insertUser implements Serializable{

    public static void main( String args[] ) {

        try{

            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // Now connect to your database
            DB db = mongoClient.getDB( "ECorp_Users" );
            System.out.println("Connect to database successfully");
            //boolean auth = db.authenticate("nate", "Password");
            //System.out.println("Authentication: "+auth);
            String json = "{'fname':'Bob','lname':'Loblaw','email':'bob@loblaw.com','balance':1205668, 'transactions' : ['2016/12/04 00:35:04, Deposit, 2500, 1205668']}";
            Gson gson = new Gson(); // Creating a new gson object to serialize and export as a json oject for the database
            User user1 = new User(json);

            String userJson = gson.toJson(user1); // creating a user json object

            // create the basic query and update objects
            BasicDBObject query = new BasicDBObject("userID", 3);
            BasicDBObject update = new BasicDBObject("$set", new BasicDBObject("userObject", userJson));

            // grab the collection
            DBCollection collection = db.getCollection("Users"); // get the collection
            try{
                collection.update(query, update); // pass in the query and the update
                System.out.println("updated?");
            }catch(Exception e){
                System.out.println("error!");
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }

            DBCursor cursor = db.getCollection("Users")
                    .find(new BasicDBObject("userID", 1)); // wrapper object to or them all together

            while(cursor.hasNext()){
                System.out.println(cursor.next());
            }




        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

}
