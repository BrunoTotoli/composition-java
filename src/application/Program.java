package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter department's name:");
        String departmentName = in.next();
        System.out.println("Enter worker data:");
        System.out.println("Name: ");
        String workerName = in.next();
        System.out.println("Level: ");
        String level = in.next();
        System.out.println("Base salary: ");
        Double salary = in.nextDouble();
        Worker worker = new Worker(workerName, WorkerLevel.valueOf(level), salary, new Department(departmentName));
        System.out.println("How many contracts to this worker?");
        int contracts = in.nextInt();

        for (int i = 0; i < contracts; i++) {
            System.out.println("Enter contract #" + (i + 1)+ "data:");
            System.out.println("Date (DD/MM/YYYY):");
            String dateInput = in.next();
            int year = Integer.valueOf(dateInput.substring(6, 10));
            int month = Integer.valueOf(dateInput.substring(3, 5));
            int day = Integer.valueOf(dateInput.substring(0, 2));
            System.out.println("Value per hour:");
            Double valuePerHour = in.nextDouble();
            System.out.println("Duration(hours):");
            Integer duration = in.nextInt();
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, (month - 1), day);
            Date date = calendar.getTime();
            HourContract contract = new HourContract(date, valuePerHour, duration);
            worker.addContract(contract);
        }

        System.out.println("Enter month and year to calculate income (MM/YYYY)");
        String monthAndYear = in.next();
        int monthIncome = Integer.valueOf(monthAndYear.substring(0, 2));
        int yearIncome = Integer.valueOf(monthAndYear.substring(3, 7));
        System.out.println("Name: "+worker.getName());
        System.out.println("Department: "+worker.getDepartment().getName());
        System.out.println("Income for "+monthAndYear+":"+String.format("%.2f",worker.income(yearIncome,monthIncome)));

        worker.report();
        in.close();
    }

}
