**Technology stack**：Spring Boot + MyBatis-Plus + MySQL + Redis + HTML  
**development tool**：IDEA、Maven、Postman、VSCode  
**project description**：  
- The one-stop management system designed for the hospital outpatient service supports core business processes such as patient registration, doctor diagnosis, and pharmacy drug distribution.  
- To achieve department management, patient information query, authority control and other functions, optimize the response speed of registration process.
 
**Back-end development**：  
   - Build project framework based on Spring Boot, generate DAO layer code through MyBatis Plus reverse engineering, reduce 30% repetitive development work.  
   - Integrated Redis storage login verification code, using Token+MD5 encryption to achieve security authentication.  
   - Design RESTful API interfaces and generate interface documents combined with Swagger to improve the efficiency of front-end and back-end collaboration.

**system optimize**：  
   - Use interceptor and route guard to complete forced login and ensure system security.
