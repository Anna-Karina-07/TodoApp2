/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Líssia Oliveira
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todoApp2";
    public static final String USER = "root";
    public static final String PASS = "";
    
      public static Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
      }
      
      public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
      }

    public static void closeConnection(Connection connection, PreparedStatement statement) {
      try {
          if (connection != null) {
              connection.close();
          }
          
          if (statement != null) {
              statement.close(); 
          }
      } catch (Exception ex) {
          throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
      }
    }
    
    
    public static void closeConnection(Connection connection, 
            PreparedStatement statement, ResultSet resultSet) {
      try {
          if (connection != null){
              connection.close();
          }
          
          if(statement != null){
              statement.close();
          }
          
          if(resultSet != null){
              resultSet.close();
          }
      } catch (Exception ex) {
          throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
    }
}

    public static void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
