https://www.youtube.com/watch?v=LcmL5ee8XGc&list=PL3NrzZBjk6m-nYX072dSaGfyCJ59Q5TEi&index=4

Annotation link
https://javatechonline-com.cdn.ampproject.org/c/s/javatechonline.com/spring-boot-annotations-with-examples/amp/

1. What is IOC?

IOC means inversion of control.
Spring has IOC container which creats objects for us called beans.

2. Dependency Injection

All variables of a class is called dependency. because that particular class depends on these properties.
Injecting these values in the object is called dependency injection.

3. How IOC container creats objects.

DemoApplication.java

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = context.getBean("student",Student.class);
        
beans.xml

        <bean id = "student" class = "com.example.demo.Student"/>
        
4. What is constructor Injection

Creating objects with help of setter injection

Student.java
    
    class Student {
        int id;
        String name;
        
        Student(int id,String name){
            this.id = id;
            this.name = name;
        }
    }

beans.xml
         
         <bean id = "deepak" class = "com.example.demo.Student">
                <constructor-arg name = "id" value="2"/>
                <constructor-arg name = "name" value="Deepak" type="java.lang.String"/>
        </bean>

DemoApplication.java

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student deepak = context.getBean("deepak",Student.class);
                
5. What is Setter Injection

Creating objects with help of setters

Student.java
    
    class Student {
        int id;
        String name;
        
        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

beans.xml
         
    <bean id = "ramesh" class = "com.example.demo.Student">
        <property name="id" value = "3"/>
        <property name="name" value="Ramesh"/>
    </bean>

DemoApplication.java

        Student ramesh = context.getBean("ramesh",Student.class);
        
6. How to inject object type dependency


Student.java
    
    class Student {
        Address address;

        public void setAddress(String address) {
            this.address = address;
        }
    }

beans.xml
         
    <bean id = "gopal" class = "com.example.demo.Student">
        <property name="address">
            <bean class="com.example.demo.Address"/>
        </property>
    </bean>
OR

    <bean id = "address" class="com.example.demo.Address"/>

    <bean id = "gopal" class = "com.example.demo.Student">
        <property name="address" ref="address"/>
    </bean>

DemoApplication.java

        Student gopal  = context.getBean("gopal",Student.class);
        
7. What is autowired?
Spring automatically inject dependency for an object, called autowired.
We need not to define <property> or <constructor-arg> tag explicitly.

DemoApplicatio.java
        public class DemoApplication {

            public static void main(String[] args) {
                //SpringApplication.run(DemoApplication.class, args);
                ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
                Human human = context.getBean("human",Human.class);
                human.startPumping();
            }
        }
        class Human{

            private Heart heart;

            public Human(Heart heart) {
                this.heart = heart;
            }
            public void setHeart(Heart heart) {
                this.heart = heart;
            }

            public void startPumping(){
                heart.pump();
            }

        }
        class Heart{
            public void pump(){
                System.out.println("Heart is pumping : Alive");
            }
        }

beans.xml

        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

            <bean id = "heart" class = "com.example.demo.Heart"/>
            <bean id = "human" class = "com.example.demo.Human" autowire="constructor"/>

        </beans>
        
8. Types of autowired.
        a) byName
        object ref name and id shound match
        <bean id = "human" class = "com.example.demo.Human" autowire="byName"/>
        b) byType
        class Name should match
        <bean id = "human" class = "com.example.demo.Human" autowire="byType"/>
        c) constructor
        <bean id = "human" class = "com.example.demo.Human" autowire="constructor"/>
        
9. what is @Autowired
        We can do use autowired concept from java class as well.
        By default auwired is off, we need to enable it before using in java
        add following line in beans.mxl
        <context:annotation-config></context:annotation-config>
        
        Now we can use this annotation
        
        @Autowired
        public void setHeart(Heart heart) {
        this.heart = heart;
        }
        Note :  this annotation first try to make autowiring using "byType" if not possible[Multiple object of same class] then try "byName".
        if both fails we need to use @Qualifier
        
        beans.xml
            <bean id = "humanHeart" class = "com.example.demo.Heart"/>
            <bean id = "octopusHeart" class = "com.example.demo.Heart"/>
            <bean id = "human" class = "com.example.demo.Human"/>
            
            @Autowired
            @Qualifier("humanHeart")
            public void setHeart(Heart heart) {
                this.heart = heart;
            }
        
10. What happens if we use @Autowired before dependency
        We need not to create setter/constructor at all
        
        class Human{
            @Autowired
            @Qualifier("humanHeart")
            private Heart heart;

            public void startPumping(){
                heart.pump();
            }
        }
11. how to load literal values from .properties file
student-info.properties
        student.name = Deepak
        student.course = java
        
beans.xml
    <context:property-placeholder location="classpath:student-info.properties"/>
    <bean id = "student" class = "com.example.demo.Student">
        <property name="name" value="${student.name}"/>
        <property name="course" value="${student.course}"/>
    </bean>

DemoApplication.java
public class DemoApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = context.getBean("student",Student.class);
        System.out.println(student);
    }
}
class Student{
    private String name;
    private String course;

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}

11. What is @Value annotation
It is used to inject literal values 

    <context:annotation-config></context:annotation-config>
    <context:property-placeholder location="classpath:student-info.properties"/>
    <bean id = "student" class = "com.example.demo.Student"/>
    
class Student{
    private String name;
    private String course;

    @Value("${student.name}")
    public void setName(String name) {
        this.name = name;
    }
    @Value("${student.course}")
    public void setCourse(String course) {
        this.course = course;
    }
}

We can use this annotation before variable as well. in that case we need not to create setter method at all.

    @Value("${student.name}")
    private String name;
    @Value("${student.course}")
    private String course;

12. What is @Required annotaion
It is used to make some variable as required, ie if we don't inject its value, we can't create object of that class
   @Required
    public void setName(String name) {
        this.name = name;
    }
We can use this annotation before setter only not before variable

13. What is @Component annotations
It is used to creat objects called beans. Which earlier we are doing using <bean> tag in xml file.

beans.xml
        <context:component-scan base-package="com.example.demo"/>
DemoApplication.java
        public class DemoApplication {

            public static void main(String[] args) {
                //SpringApplication.run(DemoApplication.class, args);
                ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
                Student student = context.getBean("student1",Student.class);
                System.out.println(student);
            }
        }
        
        @Component("student1")
        class Student{

        }
Note : 
        1. If we do not mention bean id(ex, student1) here. By default class name with small letter is considered as bean id.
        2. In xml file we need to mention component scan package. Which scan complete package to find @Component annotaions and if found
        will create object for this.
        3. We can mention component scan in java as well.
        
14. How to use java class as config file or What is @Configuration and @ComponentScan annotations
        public class DemoApplication {
            public static void main(String[] args) {
                ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
                Student student = context.getBean("student",Student.class);
                System.out.println(student);
            }
        }
        @Component
        class Student{
        }
        @Configuration
        @ComponentScan(basePackages = "com.example.demo")
        class Config{
        }
15. What is @Bean Annotation
        It is used to create beans in java file
        public class DemoApplication {

            public static void main(String[] args) {
                ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
                Student student = context.getBean("student",Student.class);
                System.out.println(student);
            }
        }
        @Component
        class Student{
        }

        @Configuration
        //@ComponentScan(basePackages = "com.example.demo")
        class Config{
            @Bean(name="student")
            public Student studentBean(){
                return new Student();
            }
        }
       Note : 
       1. by default bean id will be method name
       2. We can mention more than name as well
       @Bean(name={"student","student1"})
       
16. How to do dependency injection using java class as config file  
        We can either use setter or constructor injection
        
        public class DemoApplication {
            public static void main(String[] args) {
                ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
                Student student = context.getBean("studentBean",Student.class);
                System.out.println(student);
            }
        }
        @Component
        class Student{
            Address address;
            public Student(Address address) {
                this.address = address;
            }
        }
        class Address{
        }
        @Configuration
        //@ComponentScan(basePackages = "com.example.demo")
        class Config{
            @Bean
            public Address addressBean(){
                return new Address();
            }
            @Bean
            public Student studentBean(){
                return new Student(addressBean());
            }
        }
17. What is @PropertySource annotation
        When even we need to fetch value from .properties file we need to mention it path before using.
        public class DemoApplication {
            public static void main(String[] args) {
                ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
                Student student = context.getBean("student",Student.class);
                System.out.println(student);
            }
        }
        @Component
        class Student{
            @Value("${student.name}")
            String name;
        }

        @Configuration
        @ComponentScan(basePackages = "com.example.demo")
        @PropertySource("classpath:student-info.properties")
        class Config{
        }
18. What is @Primary annotation
if we have many implementaion classes and by default we need to inject dependency of some class then we have to use this annotation.
        @Component
        class Student{
            @Autowired
            private Book book;
        }
        interface Book{
        }
        @Component
        @Primary
        class MathBook implements Book{

        }
        @Component
        class ScienceBook implements Book{

        }
        @Configuration
        @ComponentScan(basePackages = "com.example.demo")
        class Config{
        }
Note : Here by default MathBook beans will be injected but if we want to inject some other class dependency we need to use @Qualifier
        @Component
        class Student{
            @Autowired
            @Qualifier("scienceBook")
            private Book book;
        } 
19. Bean lifecycle
Method 1:
@PostConstruct this annotation is used before init method. This methood is called after bean is created.
@PreDestroy this annottion is used before destroy method, This method is called before bean is removed from IOC container ie, once we call context.close()

public class DemoApplication {
    public static void main(String[] args) {
        //ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = context.getBean("student",Student.class);
        System.out.println(student);
        context.close();
    }
}

class Student{
    @PostConstruct
    public void init(){
        System.out.println("Insdide init");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Insdide destroy");
    }
}

beans.xml
      <context:annotation-config/>
      <bean id = "student" class = "com.example.demo.Student"/>
Note : We can use context.registerShutdownHook(); instead of context.close() both does same work. But registerShutdownHook() method is called before main thread 
destroyed. 

Method 2:
we can do this from xml file as well ie, without using annotation
      <context:annotation-config/>
      <bean id = "student" class = "com.example.demo.Student" init-method="init" destroy-method="destroy"/>
      
Note : If we have many classes and each have int and destriy method we can as follows
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd"
      default-init-method="init"
       default-destroy-method="destroy">

      <context:annotation-config/>
      <bean id = "student" class = "com.example.demo.Student"/>
</beans>
        
Method 3:
By implementing interfaces. But it is not recommended way
class Student implements InitializingBean, DisposableBean {
    
    @Override
    public void afterPropertiesSet() throws Exception {
        
    }

    @Override
    public void destroy() throws Exception {
        
    }
}
