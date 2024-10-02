package ui;

import model.Company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        Company company = new Company();

        while(!flag){

            System.out.println("Company menu" + "\n");
            System.out.println("1. Add Emp (recursive)\n" + "2. Add Emp (iterative)\n" +
                    "3. Search Emp (recursive)\n" + "4. Search Emp (iterative)");
            int opt = sc.nextInt();
            sc.nextLine();

            switch(opt){
                case 1:
                    System.out.println("Id number for employee");
                    String idt = sc.nextLine();
                    System.out.println("Name for employee");
                    String na = sc.nextLine();
                    System.out.println("Office for employee");
                    int of = sc.nextInt();
                    if(company.addEmployee(idt, na, of)){
                        System.out.println("Employee Added!!");
                    };
                    break;
                case 2:
                    System.out.println("Id number for employee");
                    String id = sc.nextLine();
                    System.out.println("Name for employee");
                    String nam = sc.nextLine();
                    System.out.println("Office for employee");
                    int off = sc.nextInt();
                    if(company.addIterative(id, nam, off)){
                        System.out.println("Employee Added!!");
                    };
                    break;
                case 3:
                    System.out.println("Id for required employee");
                    String idemp = sc.nextLine();
                    System.out.println("The employee was found" + company.search(idemp));
                    break;
                case 4:
                    System.out.println("Id for required employee");
                    String idEmp = sc.nextLine();
                    System.out.println("The employee was found" + company.searchIterative(idEmp));
                    break;
                default:
                    System.out.println("Not a valid option");
                    break;
            }
        }
    }
}