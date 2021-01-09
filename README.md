# CRM
Customer Relationship Management microservices is the Service & API Design Models implementation based on the RESTful API Design Model and using Java Service Design Model with Spring Boot (Graddle).


### Requirements:

1. Have installed Java 8 or higher on the computer or laptop:
    https://www.java.com/pt-BR/download/manual.jsp

### Instructions:

1. Clone the project by running the command in a folder of your choice:
   git clone https://github.com/jamilsolutions/crm.git

2. Enter the directory where the project was cloned and open a command propt with Git Bash:
   Note: 
   ** If you don't have git installed, use this link to install: https://git-scm.com/downloads 
   ** Git tutorial at https://git-scm.com/book/pt-br/v2

3. Run the command in the <PROJECT PATH>/crm project with the command:
   ./gradlew bootJar  
   
4. Run the project with the command:
   cd /build/libs/
   java -jar crm-0.0.1-SNAPSHOT.jar
   
5. Open Postman and import the crm.postman_collection.json collection file:
    Postman -> File -> Import -> Select folder or Upload the file -> Confirm import.
    
6. Run the collection crm -> Run Collection Runner -> Run crm.

7. Open the url in the browser: 
   http://localhost:8080/h2
   
8. Update de JDBC URL with:
   jdbc:h2:mem:crm   

9. Click in Test Connection button to appear "Test successful" message in the screen and finally click in the "Connect".

10. Ready!