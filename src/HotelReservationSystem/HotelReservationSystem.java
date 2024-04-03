package HotelReservationSystem;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";

    private static final String username = "root";

    private static final String password = "Pratham@2911";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            while (true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYETEM");
                Scanner scanner = new Scanner(System.in);

                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                System.out.println("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        reserveRoom(connection,scanner);
                        break;
                    case 2:
                        viewReservation(connection,scanner);
                        break;
                    case 3:
                        getRoomNumber(connection,scanner);
                        break;
                    case 4:
                        updateReservation(connection,scanner);
                        break;
                    case 5:
                        deleteReservation(connection,scanner);
                        break;
                    case 0:
                        exit();
                        scanner.close();
                        break;
                    default:
                        System.out.println("Invalid Choice. Try again...");
                }

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }/*catch (InterruptedException e){
            throw new RuntimeException(e);
        }*/
    }

    private static void reserveRoom(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter a guest name: ");
            String guestName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter room number: ");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter contact number: ");
            String conatctnumber = scanner.next();

            String sql = "INSERT INTO reservations (guest_name,room_number,contact_number)"+
                    "VALUES ('"+guestName+"'"+roomNumber+",'"+conatctnumber+"')";
            try (Statement statement = connection.createStatement()){
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0){
                    System.out.println("Reservation successfully");
                }else {
                    System.out.println("Reservation failed.");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void viewReservation(Connection connection, Scanner scanner) throws SQLException {

            String sql = "SELECT reservation_id,guest_name,room_number,contact_number,reservation_date FROM reservations";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)){
                int affectedRows = statement.executeUpdate(sql);

                System.out.println("Current Reservations:");
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
                System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

                while (resultSet.next()){
                    int reservationId = resultSet.getInt("reservation_id");
                    String guestName = resultSet.getString("guest_name");
                    int roomNumber = resultSet.getInt("room_number");
                    String contactNumber = resultSet.getString("contact_number");
                    String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                    // Format and display the reservation data in a table-like format
                    System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                            reservationId, guestName, roomNumber, contactNumber, reservationDate);
                }
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            }
    }

    private static void getRoomNumber(Connection connection, Scanner scanner) {

    }

    private static void updateReservation(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter a guest name: ");
            String guestName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter room number: ");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter contact number: ");
            String conatctnumber = scanner.next();

            String sql = "INSERT INTO reservations (guest_name,room_number,contact_number)"+
                    "VALUES ('"+guestName+"'"+roomNumber+",'"+conatctnumber+"')";
            try (Statement statement = connection.createStatement()){
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0){
                    System.out.println("Reservation successfully");
                }else {
                    System.out.println("Reservation failed.");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void deleteReservation(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter a guest name: ");
            String guestName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter room number: ");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter contact number: ");
            String conatctnumber = scanner.next();

            String sql = "INSERT INTO reservations (guest_name,room_number,contact_number)"+
                    "VALUES ('"+guestName+"'"+roomNumber+",'"+conatctnumber+"')";
            try (Statement statement = connection.createStatement()){
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0){
                    System.out.println("Reservation successfully");
                }else {
                    System.out.println("Reservation failed.");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void exit() {
    }



}
