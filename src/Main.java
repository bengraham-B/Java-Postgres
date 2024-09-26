import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        //Calling the function to connect to db
        DBfunctions db = new DBfunctions(); // From DBfunctions.java
        Connection conn = db.connect_to_db("tutdb","root","root");
//        db.createTable(conn, "employee");
//        db.insert_row(conn, "employee","Ben", "4 blue Road");
//        db.insert_row(conn, "employee","Ben", "1 green Road");
//        db.insert_row(conn, "employee","Ben", "9 purple Road");

//        db.update_name(conn, "employee", "Sam", "James P diddy");

//        db.search_by_name(conn, "employee","Ben");
//        db.delete_by_name(conn, "employee", "James");
//        db.delete_by_id(conn, "employee", "14");
        db.delete_table(conn, "employee");
//        db.search_by_id(conn, "employee", "3");

//        db.read_data(conn, "employee");

    }


}