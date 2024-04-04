package com.Pratham;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee{
    private String name;
    private  int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee[name = "+ name +", id = "+ id +", salary = "+calculateSalary()+"]";
    }
}

class FullTimeEmployee extends Employee{
    private double monthlySalary;

    public FullTimeEmployee(String name, int id,double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }


    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee{
    private  int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id,int hoursWorked,int hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate ;
    }
}

class PayrollSystem{
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public  void addEmployee(Employee employee){
        employeeList.add(employee);
    }


    public void removeEmployee(int id){
        Employee employeeToRemove = null;

        for (Employee employee : employeeList){
            if (employee.getId() == id){
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove != null){
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployee(){
        for (Employee employee : employeeList){
            System.out.println(employee);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();

        FullTimeEmployee emp1 = new FullTimeEmployee("Vikash",1,50000);
        PartTimeEmployee emp2 = new PartTimeEmployee("Rahul",2,40,100);

        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);

        System.out.println("Initial Employee Details");
        payrollSystem.displayEmployee();

        System.out.println("Enter EmployeeId Want To Remove: ");
        int id = sc.nextInt();
        payrollSystem.removeEmployee(id);
        System.out.println("Removing Employee");
        System.out.println("Remaining Employee Details");
        payrollSystem.displayEmployee();

    }
}

