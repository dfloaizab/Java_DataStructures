package model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    public Employee root;

    public Company(){
        this.root = null;
    }

    public boolean addEmployee(String ident, String na, int of) {
        if(add(root, new Employee(ident,na,of)) != null){
            return true;
        }
        else{
            return false;
        }
    }

    private Employee add(Employee currentNode, Employee newEmp){
        if(currentNode == null){
            currentNode = newEmp;
            return currentNode;
        }
        else{
            if(currentNode.compareTo(newEmp) == 0){
                return null;
            }
            else if(currentNode.compareTo(newEmp) < 0){
                Employee left = add(currentNode.getLeft(), newEmp);
                currentNode.setLeft(left);

            }
            else if(currentNode.compareTo(newEmp) > 0){
                Employee right = add(currentNode.getRight(), newEmp);
                currentNode.setRight(right);
            }
            return currentNode;
        }
    }

    public boolean addIterative(String ident, String na, int of){
        Employee currentNode = root;
        Employee newEmp = new Employee(ident, na, of);
        Employee parent;

        if(root == null){
            root = newEmp;
            return true;
        }
        else{
            while(currentNode != null){
                parent = currentNode;
                if(currentNode.compareTo(newEmp) == 0){
                    return false;
                }
                else if(currentNode.compareTo(newEmp) == -1){
                    if(currentNode.getLeft() == null) {
                        parent.setLeft(newEmp);
                        newEmp.setParent(parent);
                        return true;
                    }
                    currentNode = currentNode.getLeft();
                }
                else{
                    if(currentNode.getRight() == null){
                        parent.setRight(newEmp);
                        newEmp.setParent(parent);
                        return true;
                    }
                    currentNode = currentNode.getRight();
                }
            }
        }
        return true;
    }

    public Employee search(String ident){
        return search(ident, root);
    }

    private Employee search(String ident, Employee currentNode){
        if(root != null){
            if(currentNode.getId().equals(ident)){
                return currentNode;
            }
            else if(currentNode.getId().compareTo(ident) < 0){
                return search(ident, currentNode.getRight());
            }
            else if(currentNode.getId().compareTo(ident) > 0){
                return search(ident, currentNode.getLeft());
            }
        }
        return null;
    }

    public Employee searchIterative(String ident){
        if(root != null){
            Employee currentNode = root;
            while(currentNode != null){
                if(currentNode.getId().equals(ident)){
                    return currentNode;
                }
                else if(currentNode.getId().compareTo(ident) < 0){
                    if(currentNode.getRight().getId().equals(ident)){
                        return currentNode;
                    }
                    currentNode = currentNode.getRight();
                }
                else{
                    if(currentNode.getLeft().getId().equals(ident)){
                        return currentNode;
                    }
                    currentNode = currentNode.getLeft();
                }
            }
        }
        return null;
    }

    public Employee minimum(){
        return minimum(root);
    }

    private Employee minimum(Employee currentRoot){
        if(currentRoot.getLeft() == null){
            return currentRoot;
        }
        else{
            return minimum(currentRoot.getLeft());
        }
    }

    public Employee minimumIterative(){
        Employee currentNode = root;
        while(currentNode.getLeft()!=null){
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    public Employee maximum(){
        return maximum(root);
    }

    private Employee maximum(Employee currentRoot){
        if(currentRoot.getRight() == null){
            return currentRoot;
        }
        else{
            return maximum(currentRoot.getRight());
        }
    }

    public Employee maximumIterative(){
        Employee currentNode = root;
        while(currentNode.getRight()!=null){
            currentNode = currentNode.getRight();
        }
        return currentNode;
    }

    public Employee remove(String ident){
        return remove(search(ident));
    }

    private Employee remove(Employee empRemv){
        if(root == null){
            return root;
        }

        //Case 1: Leaf Node
        if(empRemv.getLeft() == null && empRemv.getRight() == null){
            if(empRemv.getParent().compareTo(empRemv)<0){
                empRemv.getParent().setRight(null);
                return empRemv;
            }
            else if(empRemv.getParent().compareTo(empRemv)>0){
                empRemv.getParent().setLeft(null);
                return empRemv;
            }
        }
        //Case 2: If one of the children is empty
        if(empRemv.getLeft() == null){

        }
        else if(empRemv.getRight() == null){

        }
        //Case 3: If both children exists
        if(empRemv.getLeft() != null && empRemv.getRight() != null){

        }

        return null;
    }

    public int weight(){
        return weight(root);
    }

    private int weight(Employee currentRoot){
        return 0;
    }

    @Override
    public String toString(){
        return toString(root);
    }

    private String toString(Employee currentRoot){
        return currentRoot.toString();
    }

    public List<Employee> preOrder(Employee currentRoot){
        List<Employee> employees = new ArrayList<Employee>();
            
        
        if(currentRoot == null)
        {
            return currentRoot;
        }
        else{
            employees.add(currentRoot);
            employees.add(preOrder(currentRoot.getLeft()));
            employees.add(preOrder(currentRoot.getRight()));
        }
    }
    public List<Employee> inOrder(){
        return null;
    }
    public List<Employee> postOrder(){
        return null;
    }
}
