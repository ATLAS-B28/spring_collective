Hello, everyone.
This is part 2 of the series to create an application using Spring and MySQL
Following up on Part 1 of the Spring Boot application was made as part of the Wells Fargo Forage Engineering Journey. 
I have expanded upon it in Part 2 by adding a database changing from H2 to more familiar MySQL, improving Entity Classes for each real-world 
entity of advisors, clients, security, and additionally adding portfolios entity, and adding DTOs and a repository layer.

Here are some of the code snippets and associated explanations of the code -

1. Let's look at the entity classes first
   Entity classes are collections of attributes of the same entity for example car will have name, model, type,
   and owner as attributes which are then used as the meta-data for the tables in the database. For this project, we take the
   advisor class out of the 4 such classes. The entity of the advisor is responsible for creating and managing users and securities.
   To auto-generate the IDs of the objects in the database using the “@GeneratedValue” annotation.
   Therefore we make a one-to-many relation between itself and the user as a separate table. The important annotations
   used here are “@OneToMany “- Which specifies a many-valued association with one-to-many multiplicity and “@JoinTable”
   with fields name, joinColumns, and an inversveJoinColumns to join with the user as a Hashed Set representation.
    
    ```java
     
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Setter
    @Getter
    @Table(name = "advisor")
    public class Advisor {
    
        @Id
        @Column(name = "adv_id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long adv_id;
    
        @Column(name = "first_name")
        private String firstName;
    
        @Column(name = "last_name")
        private String lastName;
    
        @OneToMany
        @JoinTable(
                name = "adv_user",
                joinColumns = @JoinColumn(name = "adv_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
        )
        private Set<User> users = new LinkedHashSet<>();
    }
    
    ```
    
2. Adding necessary packages in pom.xml file
   To integrate MySQL with Spring and generate entities as tables in the data we use three dependencies -
   MySQL connector, Spring Data JPA, and Lombok for additional annotations, this could be added using Spring
   initializer or Maven central in XML format in a pom.xml file at the root of the project.
    
    ```XML
        <dependency>
    			<groupId>com.mysql</groupId>
    			<artifactId>mysql-connector-j</artifactId>
    			<scope>runtime</scope>
    		</dependency>
    		<dependency>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-data-jpa</artifactId>
    		</dependency>
    		<dependency>
    			<groupId>org.projectlombok</groupId>
    			<artifactId>lombok</artifactId>
    			<optional>true</optional>
    		</dependency>
    ```
    
3. Now, for the database setup process-
   To set up MySQL, head over to MySQL Workbench  and initiate with the following SQL command in the Workbenches
   editor and refresh the schema tab to see the newly created- 

   ```SQL
   create database db_name;

   ```

4. Database Configuration for a Spring Project- 

    ```
    Here we use the spring data source library.  
    Where we access the URL, username, password, and user
    credentials - and from JPA's hibernate library to use the 
    Hibernate underneath it where we access the dialect, and 
    ddl.
    spring.datasource.url=jdbc:mysql://localhost:3306/db_name
    spring.datasource.username=username
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    logging.level.com.example=debug

    ```

5. What is the Repository Layer in an application and which are the repository we implemented?
   Now let's create the Repository Layer that acts as an interface between the service layer and
   database layer of the application, it is an interface that extends the “JpaRepository” interface that automatically generates methods for
   querying and interacting with the database based on the type of entity passed and the ID’s data type. We get some default ones like “getById” for
   retrieving the object by ID, but we can have more custom-based querying methods- 

   ```java
   import com.example.wells_forage.entity.Advisor;
   import org.springframework.data.jpa.repository.JpaRepository;

   public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
   }
   ```

7. What is DTO and creating DTOs for the project?
   DTO stands for Data Transfer Object, and it transfers data between the client and service layer to not expose the entities directly,
   it is used to abstract away the entity class from the client side for safety reasons. Our project has 4 DTOs - advisor, security, user,
   and portfolio. An example, of DTO, is given below for the security- 

   ```java
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class SecurityDTO {
 
     private int sec_id;
     private String sec_name;
     private String category;
     private double purchasePrice;
     private Date purchaseDate;
     private PortfolioDTO portfolio;
     //we can refer to other DTO classes like here we 
     //refer to portfolio dto
     }
    ```

That’s how we added 3 of 5 layers of a REST Backend in Spring.
The remaining 2 are the Controller and Service layer where the core logic of the application exists, it will be covered in the next part.
Also will try adding Frontend in the next upcoming post itself using either HTMX or REACT.JS.
