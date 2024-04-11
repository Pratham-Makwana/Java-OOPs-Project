package com.Pratham;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHandlingJDBC {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "Pratham@2911";
        String withdrawQuery = "UPDATE accounts SET balance = balance - ? where account_number = ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? where account_number = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection Establish Successfully");
            connection.setAutoCommit(false);

                PreparedStatement withdrawStatement = connection.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = connection.prepareStatement(depositQuery);

                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "account456");

                depositStatement.setDouble(1, 750.00);
                depositStatement.setString(2, "account123");

                int rowAffectedWithdraw =withdrawStatement.executeUpdate();
                int rowAffectedDeposit =depositStatement.executeUpdate();

                if (rowAffectedWithdraw > 0 && rowAffectedDeposit > 0){
                    connection.commit();
                    System.out.println("Transaction Successfully");

                }else {
                    connection.rollback();
                    System.out.println("Transaction Failed");
                }
            connection.close();
            System.out.println("Connection Close Successfully");
        }catch (SQLException e){

            System.out.println(e.getMessage());
        }
    }
}
