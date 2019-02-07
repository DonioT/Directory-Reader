/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Donio
 */
public class Database {
    private final String SERVER = "localhost";
    private final String USER = "root";
    private final String PASSWORD = "";
    private final String DATABASE = "test_db";
    
   
    private Application obj = null;
    private MysqlDataSource dataSource = null;
     public Database(Application app) throws SQLException{
            this.obj = app;
           dataSource = new MysqlDataSource();
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);
            dataSource.setServerName(SERVER);
            dataSource.setDatabaseName(DATABASE);
      }
     public Connection getConnection() throws SQLException{
            Connection con = null;
            con = dataSource.getConnection();
            return con;
     }
     
     
     public Boolean insert() throws SQLException{
          int result = 0;
          Connection con = getConnection();
        try(Statement stmt = con.createStatement()){
            result = stmt.executeUpdate("INSERT INTO directorydata (DirectoryName,TotalLines,TotalCharacters,TotalWords,TotalWhiteSpace,TotalEmptyLines) "
                    + "VALUES ('" + obj.getDirectory() + "','" + 
                    obj.getTotalLineCount() + "','" + 
                    obj.getTotalCharacterCount()+ "','" + 
                    obj.getTotalWordCount() + "','" + 
                    obj.getTotalEmptySpaces() + "','" + 
                    obj.getTotalEmptyLines() + "')");
            
           stmt.close();
           con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result > 0;
     }
}
