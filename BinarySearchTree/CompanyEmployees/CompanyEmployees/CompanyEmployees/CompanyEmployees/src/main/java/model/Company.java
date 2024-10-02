package model;

import java.util.List;
import java.util.ArrayList;

public class Company {

    public Employee root;

    public Company(){
        this.root = new Employee("123456789", "Juan José",13);
    }

    public boolean addEmployee(String ident, String na, int of) {
        return add(root, null, new Employee(ident,na,of));
    }

    private boolean add(Employee currentNode, Employee parent, Employee newEmp){
        if(currentNode == null){
            currentNode = newEmp;
            currentNode.setParent(parent);
            if(parent.compareTo(newEmp)<0){
                parent.setLeft(newEmp);
            }
            else{
                parent.setRight(newEmp);
            }
        }
        else{
            if(currentNode.compareTo(newEmp) == 0){
                return false;
            }
            else if(currentNode.compareTo(newEmp) < 0){
                return add(currentNode.getLeft(),currentNode, newEmp);
            }
            else if(currentNode.compareTo(newEmp) > 0){
                return add(currentNode.getRight(),currentNode, newEmp);
            }
        }
        return true;
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
                else if(currentNode.compareTo(newEmp) < 0){
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
                return search(ident, currentNode.getLeft());
            }
            else if(currentNode.getId().compareTo(ident) > 0){
                return search(ident, currentNode.getRight());
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

    public Employee minimum(Employee currentRoot){
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

    public Employee maximum(Employee currentRoot){
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
        return remove(null,root,ident);
    }

    private Employee remove(Employee parent,Employee current,String ident) {
        if (current == null) {
            return null;
            // Node is not in the tree
        }
        //When we find the node
        //Base case
        if (current.getId().equals(ident)) {
            //Case 1: Leaf node
            if (current.getLeft() == null && current.getRight() == null) {
                //Node is the root
                if (current.equals(root)) {
                    this.root = null;
                }
                //Node is left child
                else if (parent.getLeft().equals(current)) {
                    parent.setLeft(null);
                }
                //Node is right child
                else if (parent.getRight().equals(current)) {
                    parent.setRight(null);
                }

            }
            //Case 2: Node only has one child
            //2.1: Node only has one left child
            else if (current.getLeft() != null && current.getRight() == null) {
                if (parent.getLeft().equals(current)) {
                    parent.setLeft(current.getLeft());
                } else {
                    parent.setRight(current.getRight());
                }
            }
            //2.2: Node Only has one right child
            else if (current.getRight() != null && current.getLeft() == null) {
                if (parent.getLeft().equals(current)) {
                    parent.setLeft(current.getLeft());
                } else {
                    parent.setRight(current.getRight());
                }
            }
            //Case 3: Node has two children
            else if (current.getLeft() != null && current.getRight() != null) {
                // Get Succesor --> Smallest node of right subtree
                Employee successor = minimum(current.getRight());

                // Update attributes to replace old node
                current.setId(successor.getId());
                current.setName(successor.getName());
                current.setOffice(successor.getOffice());

                // delete Succesor --> update Connections
                return remove(current, current.getRight(), successor.getId());
            }
        }
        // When we do not find the node
        else if (current.getId().compareTo(ident) < 0) {
            return remove(current, current.getRight(), ident);
        } else if (current.getId().compareTo(ident) > 0) {
            return remove(current, current.getLeft(), ident);
        }
        return current;
    }

    public int weight(){
        return inOrder(root).size();
    }

    @Override
    public String toString(){
        return toString(root);
    }

    private String toString(Employee currentRoot){
        List<Employee> employees = inOrder(root);
        String listEmp = "";
        for(Employee employee : employees){
            listEmp += employee.toString();
        }
        return listEmp;
    }

    public List<Employee> preOrder(Employee currentRoot){
        List<Employee> preOrderEmp = new ArrayList<Employee>();
        if(currentRoot == null){
            return null;
        }
        // Visit node
        preOrderEmp.add(currentRoot);
        // Traverse left subtree
        preOrder(currentRoot.getLeft());
        // Traverse right subtree
        preOrder(currentRoot.getRight());
        return preOrderEmp;

    }

    public List<Employee> inOrder(Employee currentRoot){
	    List<Employee> inOrderEmp = new ArrayList<Employee>();
        if(currentRoot == null){
            return null;
        }
	    // Traverse left subtree
	    inOrder(currentRoot.getLeft());
	    // Visit node
	    inOrderEmp.add(currentRoot);
        // Traverse right subtree
        inOrder(currentRoot.getRight());
        return inOrderEmp;
    }

    public List<Employee> postOrder(Employee currentRoot){
        List<Employee> postOrderEmp = new ArrayList<Employee>();
        if(currentRoot == null){
            return null;
        }
        // Traverse left subtree
        postOrder(currentRoot.getLeft());
        // Traverse right subtree
        postOrder(currentRoot.getRight());
        // Visit node
        postOrderEmp.add(currentRoot);
        return postOrderEmp;
    }

    public String show(int order){
        String orderEmp = "";
        List<Employee> employees;
        if(order==1){
            employees = inOrder(root);
            for (Employee emp: employees) {
                orderEmp += emp.toString() + "\n";
            }
        }
        else if(order==2){
            employees = preOrder(root);
            for (Employee emp: employees) {
                orderEmp += emp.toString() + "\n";
            }
        }
        else if(order==3){
            employees = postOrder(root);
            for (Employee emp: employees) {
                orderEmp += emp.toString() + "\n";
            }
        }
        return orderEmp;
    }
}
