package BankingManagement;

import java.sql.*;
import java.util.Scanner;

public class AccountManager {

    private Connection connection;

    private Scanner scanner;

    public AccountManager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }


    public void debit_money(long account_number) throws SQLException{
        scanner.nextLine();
        System.out.print("Enter Account: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? and security_pin = ? ");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if (amount<=current_balance){
                        String debit_query = "UPDATE accounts SET balance = balance - ? WHERE  account_number = ? ";
                        PreparedStatement debitStatement = connection.prepareStatement(debit_query);
                        debitStatement.setDouble(1,amount);
                        debitStatement.setLong(2,account_number);
                        int rowAffected = debitStatement.executeUpdate();
                        if (rowAffected > 0){
                            System.out.println("Rs. "+amount+ " debited successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }else {
                            System.out.println("Transaction Failed!");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("Insufficient Balance!");
                    }
                }else {
                    System.out.println("Invalid Pin!");

                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }

    public void credit_money (long account_number) throws SQLException{
        scanner.nextLine();
        System.out.print("Enter Account: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = scanner.nextLine();
        try {
            connection.setAutoCommit(false);
            if (account_number!=0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? and security_pin = ? ");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                        String credit_query = "UPDATE accounts SET balance = balance + ? WHERE  account_number = ? ";
                        PreparedStatement debitStatement = connection.prepareStatement(credit_query);
                        debitStatement.setDouble(1,amount);
                        debitStatement.setLong(2,account_number);
                        int rowAffected = debitStatement.executeUpdate();
                        if (rowAffected > 0){
                            System.out.println("Rs. "+amount+ " credited successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }else {
                            System.out.println("Transaction Failed!");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }
                }else {
                System.out.println("Invalid Security Pin!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }

    public void transfer_money(long sender_accountNumber){
        scanner.nextLine();
        System.out.print("Enter Receiver Account Number: ");
        long receiver_accountNumber = scanner.nextLong();
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = scanner.nextLine();

        try {
            connection.setAutoCommit(false);
            if (sender_accountNumber != 0 && receiver_accountNumber != 0){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ?");
                preparedStatement.setLong(1,sender_accountNumber);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if (amount<=current_balance){
                        // Debit & Credit Query
                        String debit_query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? ";
                        String credit_query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ? ";

                        // Debit & Credit PrepareStatement
                        PreparedStatement debitpreparedStatement = connection.prepareStatement(debit_query);
                        PreparedStatement creditpreparedStatement = connection.prepareStatement(credit_query);

                        // set values for debit and credit prepare statement
                        debitpreparedStatement.setDouble(1,amount);
                        debitpreparedStatement.setLong(2,sender_accountNumber);
                        creditpreparedStatement.setDouble(1,amount);
                        creditpreparedStatement.setLong(2,receiver_accountNumber);
                        int  creditAffectedRows = creditpreparedStatement.executeUpdate();
                        int debitAffectedRows = debitpreparedStatement.executeUpdate();

                        if (creditAffectedRows > 0 && debitAffectedRows > 0){
                            System.out.println("Transaction Successful");
                            System.out.println("Rs. "+amount+" Transferred Successful");
                            connection.close();
                            connection.setAutoCommit(true);
                            return;
                        }else {
                            System.out.println("Transaction Failed!!");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("Insufficient Balance");
                    }
                }else {
                    System.out.println("Invalid Security Pin!!");
                }
            }else {
                System.out.println("Invalid Account Number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void getBalance(long account_number){
        scanner.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin = scanner.nextLine();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT balance FROM accounts WHERE account_number = ? and security_pin = ?");
            preparedStatement.setLong(1,account_number);
            preparedStatement.setString(2,security_pin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                double balance = resultSet.getDouble("balance");
                System.out.println("Balance: "+ balance);
            }else {
                System.out.println("Invalid Pin!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
