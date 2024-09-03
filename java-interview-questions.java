1. What is ClassNotFondException and when do we get this
Ans:
If we try to load a class dynamically ie run time and class is not present in specified classPath
eg.
class HelloWorld {
    public static void main(String[] args) {
      try {
            // Attempt to load a class that doesn't exist
            Class<?> myClass = Class.forName("com.example.NonExistentClass");
            System.out.println("Class found: " + myClass.getName());
        } catch (ClassNotFoundException e) {
            // Handle the exception when the class is not found
            System.out.println("Class not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
a) Class.forName is static member function of Class class to load class dynamically


2. final, finally and finalise keyword (https://www.javatpoint.com/difference-between-final-finally-and-finalize)
a) final is an access modifier, finally is the block in Exception Handling and finalize is the method of object class.
b) final can be used with class, method and variables
c) Once declared, final variable becomes constant and cannot be modified.
d) final method cannot be overridden by sub class.
e) final class cannot be inherited.
f) finally block runs the important code even if exception occurs or not.(use in exception handling)
g) finally block cleans up all the resources used in try block
h) finalize method performs the cleaning activities with respect to the object before its destruction.

public class FinalizeExample {    
     public static void main(String[] args)     
    {     
        FinalizeExample obj = new FinalizeExample();        
        // printing the hashcode   
        System.out.println("Hashcode is: " + obj.hashCode());           
        obj = null;    
        // calling the garbage collector using gc()   
        System.gc();     
        System.out.println("End of the garbage collection");     
    }     
   // defining the finalize method   
    protected void finalize()     
    {     
        System.out.println("Called the finalize() method");     
    }     
} 

3. Difference between Exception and Error with example (https://www.javatpoint.com/exception-vs-error-in-java)
a) both are subclasses of the Java Throwable class
b) Exception can be happen at compile or runtime and can be handlled using try/cach or throw eg. ClassNotFound
c) Exception can happen at runtime only and can't be handled eg. Java.lang.StackOverFlow, java.lang.OutOfMemoryError

public class ErrorExample   {  
    public static void main(String args[])  {  
        recursiveDemo(10);  
    }  
    public static void recursiveDemo(int i)  {  
        recursiveDemo(i+1); 
    } 
}


