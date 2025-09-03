# 📝 Todo Management Application  

A full-stack **Spring Boot Todo App** built using **Spring MVC, JPA, Spring Security, H2 Database, and JSP views**.  
This project started as a simple demo but grew into a complete learning journey where I explored **Spring MVC flow, authentication, persistence, and debugging**.  

---

## 🚀 Features
- User authentication with **Spring Security**  
- CRUD operations on Todos (Add, Update, Delete, List)  
- **H2 in-memory database** with preloaded data (`data.sql`)  
- **JSP-based frontend** using Spring form tags & JSTL  
- Session handling and displaying logged-in username  
- Migration from **in-memory list** → **JPA repository**  

---

## 📖 My Journey & Key Learnings

### 1. Spring MVC Setup
- Started with a simple `WELCOMEController` returning `welcome.jsp`.  
- Configured view resolver in `application.properties`:
  ```properties
  spring.mvc.view.prefix=/WEB-INF/jsp/
  spring.mvc.view.suffix=.jsp
  ```
  🔑 **Why:** Without this, Spring didn’t know where to find JSPs → *Whitelabel Error Page*.  
  Learned how **ViewResolver** maps controller return values to JSP files.  

---

### 2. Handling Forms & Binding
- Faced error:  
  ```
  Neither BindingResult nor plain target object for bean name 'todo' available
  ```
- Cause → `<form:form modelAttribute="todo">` requires a `Todo` object in the model.  
- Fix → Added:
  ```java
  model.put("todo", new Todo(...));
  ```
  🔑 **Why:** This taught me how Spring MVC **binds form fields to model attributes**.  

---

### 3. Session Management
- Initially hardcoded username (`in28minutes`).  
- Later replaced with:
  ```java
  Authentication authentication = 
      SecurityContextHolder.getContext().getAuthentication();
  String user = authentication.getName();
  ```
  🔑 **Why:** Learned how **Spring SecurityContext** manages the logged-in user session.  

---

### 4. Spring Security Integration
- Added Spring Security dependency → app auto-generated a password.  
- Configured `SpringSecurityConfig` to allow authenticated access.  
- 🔑 **Learning:** My welcome page was blocked at first because by default, **Spring blocks all endpoints** unless configured.  

---

### 5. In-Memory Storage → JPA Migration
- Started with static in-memory list in `TodoService`.  
- Migrated to **JPA Repository + H2 Database**:  
  ```java
  public interface TodoRepo extends JpaRepository<Todo, Integer> {
      List<Todo> findByUsername(String username);
  }
  ```
- Added `data.sql` with sample todos:  
  ```sql
  insert into todo (ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
  values(10001,'USER', 'Get AWS Certified', CURRENT_DATE(), false);
  ```
  🔑 **Why:** Moving from in-memory to JPA taught me **real persistence** and how Spring Boot automatically executes `data.sql`.  

---

### 6. Debugging H2 Console
- Initial issue: Data not showing in `/list-todos`.  
- Cause → Controller still used `TodoService` (static list).  
- Fix → Switched to `todoRepo.findByUsername(user)`.  
- 🔑 **Why:** Learned the importance of **consistency when switching storage layers**.  

---

## ⚙️ Tech Stack
- **Backend**: Spring Boot (MVC, Security, JPA)  
- **Database**: H2 (in-memory, auto-seeded with `data.sql`)  
- **Frontend**: JSP + JSTL + Spring Form Tags  
- **Build Tool**: Maven  
- **Language**: Java 17  

---

## ▶️ Running the App
```bash
mvn spring-boot:run
```
- App → `http://localhost:8080`  
- H2 Console → `http://localhost:8080/h2-console`  
  - JDBC URL: `jdbc:h2:mem:testdb`  

---

## 🔑 Default Credentials
- Username: `user`  
- Password: printed in console at startup  

---

## 📸 Screenshots 
<img width="1920" height="1080" alt="Screenshot 2025-09-03 123248" src="https://github.com/user-attachments/assets/94c80018-8bdc-4117-9f97-a18ec18870b4" />
<img width="1920" height="1080" alt="Screenshot 2025-09-03 123318" src="https://github.com/user-attachments/assets/69f47ec3-bf10-4b7c-804f-3f03f52788a3" />
<img width="1920" height="1080" alt="Screenshot 2025-09-03 123331" src="https://github.com/user-attachments/assets/bed455b5-abf7-4e7a-bc21-4e37c59f8914" />
<img width="1920" height="1080" alt="Screenshot 2025-09-03 123440" src="https://github.com/user-attachments/assets/4116a165-453e-45b9-a21f-ba2fed329f55" />
<img width="1920" height="1080" alt="Screenshot 2025-09-03 123501" src="https://github.com/user-attachments/assets/cdaf321e-9bfb-4f16-a980-f0507b0541f8" />
<img width="1920" height="1080" alt="Screenshot 2025-09-03 123544" src="https://github.com/user-attachments/assets/c80ac9ef-e655-471c-842d-98d5a719765b" />


 

---

## 🌟 Why This Project Matters
- Debugged **binding issues**, **view resolver configs**, **form submissions**  
- Understood **Spring Security authentication flow**  
- Migrated from **in-memory service** → **JPA/H2 persistence**  
- Learned **separation of concerns**: Controller → Service → Repo → Entity → View  

---

## 🚀 Next Steps
- Switch from H2 → **MySQL/PostgreSQL**  
- Add REST APIs + React/Angular frontend  
- Deploy to **Heroku / AWS**  

---

⚡ This project isn’t just a todo app — it’s a **step-by-step Spring Boot learning log**.  
Every bug I fixed here made me more confident as a Java developer 🚀  
