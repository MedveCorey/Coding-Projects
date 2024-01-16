
package firstexam;

 public class Instructor {
    private int age;
    private String name;
    public Instructor(){
       
    }
    public Instructor(int age, String name){
        this.age = age;
        this.name = name;
    }
    public Instructor(Instructor instructor){
        
    }
    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean equals(Instructor instructor){
        if(instructor.name == name && instructor.age == age){
            return true;
 
        }
        else{
            return false;
        }
    }
}
