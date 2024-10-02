package model;

public class Employee implements Comparable<Employee>{

    private Employee parent;
    private Employee left;
    private Employee right;

    private String id;
    private String name;
    private int office;

    public Employee(String id, String name, int office) {
        this.id = id;
        this.name = name;
        this.office = office;
    }

    public Employee getParent() {
        return parent;
    }

    public Employee getLeft() {
        return left;
    }

    public Employee getRight() {
        return right;
    }

    public void setParent(Employee newParent){
        this.parent = newParent;
    }
    public void setRight(Employee newRight){
        this.right = newRight;
    }
    public void setLeft(Employee newLeft){
        this.left = newLeft;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOffice() {
        return office;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    @Override
    public int compareTo(Employee o) {
        if(this.getId().compareTo(o.getId())==0){
            return 0;
        }
        else if(this.getId().compareTo(o.getId()) < 0){
            return -1;
        }
        else{
            return 1;
        }
    }

    @Override
    public String toString(){
        return "Name: " + this.name + "Id number: " + this.id;
    }


}
