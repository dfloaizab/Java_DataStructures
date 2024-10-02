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
                    "3. Search Emp (recursive)\n" + "4. Search Emp (iterative)\n" +
                    "5. Find the minimum\n" + "6. Find the maximum\n" +
                    "7. Remove Emp\n" + "8. Show weight\n" + "9. InOrder\n" + "10. PreOrder\n" + "11. PostOrder\n"
                    + "12. Exit\n");
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
                case 5:
                    System.out.println("The minimum value for an employee with ID is " + company.minimum(company.root).toString());
                    break;
                case 6:
                    System.out.println("The maximum value for an employee with ID is " + company.maximum(company.root).toString());
                    break;
                case 7:
                    System.out.println("Id for required employee to be remove");
                    String idRM = sc.nextLine();
                    if(company.remove(idRM) != null){
                        System.out.println("Employee Removed");
                        System.out.println(company.toString());
                    }
                    else{
                        System.out.println("Could not be removed");
                    }
                case 8:
                    System.out.println("Current weight of the tree is " + company.weight());
                    break;
                case 9:
                    System.out.println("Showing inorder traversal of the tree\n" + company.show(1));
                    break;
                case 10:
                    System.out.println("Showing preorder traversal of the tree\n" + company.show(2));
                    break;
                case 11:
                    System.out.println("Showing postorder traversal of the tree\n" + company.show(3));
                    break;
                case 12:
                    System.out.println("Be back soon!!");
                    flag = true;
                    break;
                default:
                    System.out.println("Not a valid option");
                    break;
            }
        }
    }
}