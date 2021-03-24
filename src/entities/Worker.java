package entities;

import entities.enums.WorkerLevel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Worker {
    private String name;
    private WorkerLevel level;
    private Double baseSalary;
    private Department department;
    private List<HourContract> contracts = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void addContract(HourContract contract) {
        contracts.add(contract);
    }

    public void removeContract(HourContract contract) {
        contracts.remove(contract);
    }

    public Double income(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        double sum = baseSalary;
        for (HourContract contract : contracts) {
            calendar.setTime(contract.getDate());
            int contractYear =  calendar.get(Calendar.YEAR);
            int contractMonth = 1 + calendar.get(Calendar.MONTH);
            if (contractYear == year && contractMonth == month) {
                sum += contract.totalValue();
            }
        }
        return sum;
    }

    public void report(){
        System.out.println("Worker : "+this.name);
        System.out.println("Level: "+this.level);
        System.out.println("Base Salary: "+this.baseSalary);
        System.out.println("Department: "+this.department.getName());
        for(int i = 0; i<contracts.size();i++){
            System.out.println("Contract #"+(i+1));
            System.out.println("Date: "+dateFormat.format(contracts.get(i).getDate()));
            System.out.println("ValuerPerHour: "+contracts.get(i).getValuePerHour());
            System.out.println("Hours: "+contracts.get(i).getHours());
            System.out.println("---------------------------------------------");
        }
    }
    public Worker() {
    }

    public Worker(String name, WorkerLevel level, Double baseSalary,Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", baseSalary=" + baseSalary +
                ", department=" + department.getName() +
                ", contracts=" +listContracts()+
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkerLevel getLevel() {
        return level;
    }

    public void setLevel(WorkerLevel level) {
        this.level = level;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public List<HourContract> getContracts() {
        return contracts;
    }
    public String listContracts(){
        for(HourContract a : contracts){
            return "Date: "+dateFormat.format(a.getDate())+"ValuePerHour: "+a.getValuePerHour()+"Hours: "+a.getHours();
        }
        return "Dont have contracts";
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
