import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBfunctions {

    //DB: tutdb

    public Connection connect_to_db(String dbName, String user, String pass){
        Connection conn = null;

        try{
            // Connect to PostgresSQL
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5429/"+ dbName,user,pass);

            //^ Checking if connection is successful
            if(conn != null){
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Faild");
            }



        } catch(Exception e){
            System.out.println(e);
        }

        // Returning Connection Object
        return conn;
    }

    public void createTable(Connection conn, String tableName){
        // Create a statement object
        Statement statement;

        try{
            String query = "CREATE TABLE " + tableName + " (empid SERIAL, name VARCHAR(200), address VARCHAR(200), primary key(empid));";
            statement = conn.createStatement(); // Like a cursor
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_row(Connection conn,String tableName, String name, String address){
        Statement statement;

        try{
            String query = String.format("INSERT INTO %s(name,address) VALUES ('%s', '%s');", tableName, name, address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Inserted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void read_data(Connection conn, String tableName){
        ResultSet rs = null; // Output of the query, need this to read from DB.
        try {
            String query = String.format("SELECT * FROM %s", tableName);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(query); // Using this as we need output

            while(rs.next()){
                System.out.print(rs.getString("empid") + " " + rs.getString("name") + " " + rs.getString("address") + "\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update_name(Connection conn, String tableName, String old_name, String new_name){
        Statement statement;

        try{
            String query = String.format("UPDATE %s SET name='%s' WHERE name='%s';",tableName, new_name, old_name);
            statement = conn.createStatement();
            statement.executeUpdate(query); // Use this as we do not need any output back.
            System.out.println("Table updated");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void search_by_name(Connection conn, String tableName, String name){
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE name='%s';", tableName, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()){
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("address") + "\n");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void search_by_id(Connection conn, String tableName, String id){
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE empid='%s';", tableName, id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()){
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("address") + "\n");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete_by_name(Connection conn, String tableName, String name){
        Statement statement;

        try{
            String query = String.format("DELETE FROM %s WHERE name='%s'", tableName, name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Delteded");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete_by_id(Connection conn, String tableName, String id){
        Statement statement;

        try{
            String query = String.format("DELETE FROM %s WHERE empid='%s'", tableName, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete_table(Connection conn, String tableName){
        Statement statement;

        try{
            String query = String.format("DROP TABLE %s", tableName);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
