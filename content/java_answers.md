#### Q .what paradigm does the language belong to ?
    with respect to programming paradigm, memory handling, data typing.

**A.** Java is a completely object oriented language and supports all the features of object oriented programming.

###### Encapsulation

Java lets you define constructs called classes, that can be used to group data and the methods that act on that data together.
A class also provides a mechanism to control access to the data and methods that are the members of the class.

###### Abstraction

Abstraction is supported using the constructs like interfaces and abstract classes which let you define ‘what’ a piece of code should do(declarative) and not how it should do it (actual logic).

###### Inheritance

Inheritance is class based. A class can inherit other classes or interfaces, whereas an interface can only inherit other interfaces to extend the functionalities provided by the parent.

###### Polymorphism

Polymorphism also comes in two flavours.
Runtime polymorphism : Here the compiler doesn’t know what lines of code will be executed. It gets resolved only when the program is run

```java
  Class Bird {
      public void makeSound(){
          System.out.println("blah");
      }
  }

  Class Duck extends Bird {
      public void makeSound(){
          System.out.println("quack");
      }
  }

  Bird originalBird = new Bird();
  Bird bird = new Duck();

  originalBird.makeSound() // prints "blah"
  bird.makeSound()  // prints "quack"
```

###### Compile time polymorphism

Compiler knows what code will be executed. Compile time polymorphism is supported by method overloading. However operator overloading is not supported.

###### Type safe

 Java is a type safe or memory safe language and also provides automatic memory management.

###### Statically typed

Its mostly a statically typed language
Write once run anywhere: Code written in java gets compiled to a platform independent format called byte code. And hence can be executed on any platform that has Java Runtime (JRE) installed.

#### Q. How is code processed?

Is the code compiled, interpretted, etc..

**A.** All the code written in java has a file extension of .java. You need to compile the ‘.java’ files in order to execute them.
To achieve platform independence all the java code is compiled to an intermediary format called bytecode. When you execute your program using the Java Runtime (JRE) , this bytecode is either interpreted and executed line by line or compiled to native format by the java just-in-time(JIT) compiler and executed  by the JVM. But the JIT compilation is an optimisation step that is completely transparent to the user.

#### Q. what is the runnable unit? How is it executed?

 What is the format of an executable in this language? And how do I execute an application written in this language?

**A.** A java application can be in any of the formats mentioned below. All of them are binary formats.

###### .class files

You can have a java application which is folder consisting of .class files, one for every class defined in your .java files.
Here’s how you would execute it:

`java -cp C:\Users\John\workspace\HelloWorld\bin HelloWorld`

where :

  - -cp is an option used to specify the class path which in this case is
  'C:\Users\John\workspace\HelloWorld\bin’. This is the root folder containing all your class files.

  - ‘HelloWorld’ is the name of the class that contains the main method. This is required since the execution of every java program begins with the main method.

###### .jar file

JAR stands for Java ARchive which is usually just a zip file of the folder containing all your .class files, along with some occasional metadata.

This is the most common executable format of a java application.
To execute a .jar run -

`java -cp '.:/home/ubuntu/test/libs/*.jar' flights`

where

  - -cp '.:/home/ubuntu/test/libs/*.jar’ // points to your jar file or files
  - flights  // is the name of the class containing the main method

###### .war file

WAR stands for War ARchive and is a web application. This requires a web server like tomcat, jboss etc to be run. Usually to run this war you would start your web server and deploy(copy) the war to a web root folder of the server.

To deploy a war to tomcat ...

To deploy a war to jboss ...

#### Q. How do I build and bundle my program in this language?

 How do I create an artefact that can run on its own(executable) or be used by other programs(library)?

A.
           - manual
               - javac , specify all the dependencies, specify main method
                    - compile to executable jar
                    - leave it in folder format
               - run the executable jar
               - run the class folder
          - build tools
               - maven

### Basic programming

#### Q. What does a basic program look like?

**A.**

```java
  // SortEmUp.java
  import java.lang.Integer;
  import java.lang.System;
  import java.util.ArrayList;

  public class SortEmUp{

      public static void main(String[] args) {
          ArrayList<Integer> numbers = new ArrayList<>();
          numbers.add(123);
          numbers.add(6);
          numbers.add(524);
          numbers.add(735);
          numbers.add(23);

          System.out.println("Unsorted numbers:");
          for (Integer number : numbers) {
              System.out.println(number);
          }

          SortingStrategy sortingStrategy = new SortingStrategy();
          sortingStrategy.sort(numbers);

          System.out.println("All sorted out:");
          for (Integer number : numbers) {
              System.out.println(number);
          }
      }
  }

  // SortingStrategy.java
  import java.lang.Integer;
  import java.util.ArrayList;

  class SortingStrategy{
      public ArrayList<Integer> sort(ArrayList<Integer> numbers){
          for (int i = 0; i < numbers.size(); i++) {
              for (int j = i+1; j < numbers.size(); j++) {
                  if(numbers.get(i)>numbers.get(j)){
                      Integer temp = numbers.get(i);
                      numbers.set(i,numbers.get(j));
                      numbers.set(j,temp);
                  }
              }
          }
          return numbers;
      }
  }
```

**Q. How do i create and use variables?**

      - local variables, instance variables, static variables
      - variables of different data types
      - need to be in a class

Basic syntax of declaring a variable in java is :

`[access specifier] [static] [final] <data type> <name of the variable>;`

 Optionally you can also assign a default value to the variable.

**local variable:** variable that is local to a block of code such as method, if condition etc.

```java
  private int add(int[] numbers){   // adds an array of integers
     int sum = 0; // define a variable and optionally assign a value to it.
                         // But you have to assign a value to this variable before you use it in the following lines.
     for(int number : numbers){
         sum = sum + number;   // usage
     }
     return sum;
  }
```

**instance variable :** These are the variables that are available within the context of an instance of the class. You need an instance of the class to access them.

```java
    class Rectangle {
        // since the length and breadth of a Rectangle are fixed,
        // we can make them instance variables
        private int length;  // instance variables
        private int breadth;

        public Rectangle(int length,int breadth){
            // using the 'this' pointer to make it explicit that
            // we are referring to the instance variable in the LHS
            // since the argument name is same as the instance variable name
            this.length = length;  
            this.breadth = breadth;
        }

        public int area(){
            return length * breadth; // using "this" pointer is not required here. uses the instance variables
        }
    }


```












 - How do I create and use constants?
 - What are the datatypes supported by the language?
 - How do i create and use arrays?
 - How do I create and use strings?
 - How do create and use functions?
 - Pass by value or pass by reference?
 - How do i create and use user defined objects or similar structures?
 - How do I create looping constructs?
 - How do i execute code conditionally?
 - What is the scope of variables ?
 - How i import a module?
 - How do i export my program as a module or library?
 - How do i write code that executes in multiple threads?
 - How do i write unit tests?
 - How do can reuse existing code and build on top of it? (inheritance, composition, aggregation, etc)
 - How do i read from a file?
 - How do i write to a file?
 - How do I read from and write to console?
 - How do i write code that executes asynchronously?
 - How do I perform string operations?
 - How do I perform operations on collections?

Building applications

 - How do i modularize my application?

 - how do i use other modules?
 - How do I write a barebones web application using only language features? Not using any frameworks.
 - what frameworks can i use to create a web application?
 - what frameworks can i use to create restful web services?
 - What are some of the popular frameworks  for building applications?
 - How do i connect to a database?
 - How do I achieve dependency injection?
 - How do i build a basic UI ?
 - How do i write an http client ?
