#  Keycloak Authentication using Quarkus Framework

##  Overview

This project shows how to secure a Java (Quarkus) backend using **Keycloak** for authentication and **MSSQL** for data storage.  
Users can log in through Keycloak, and based on their roles (`admin` or `user`), they can access different API endpoints.

---

##  Technologies Used

- **Java** – Backend programming language  
- **Quarkus** – Java framework for building REST APIs  
- **Keycloak** – For authentication and role-based access control  
- **MSSQL** – Database to store application data  
- **Docker** – Containerization for Keycloak and MSSQL  

---

## API Endpoints

- **GET** `http://localhost:8085/api/v1/wsstatus`  
  → Allows access to both **admin** and **user** roles  
  → Used to fetch all status details

- **PUT** `http://localhost:8085/api/v1/wsstatus?requestUUID={UUID}`  
  → Allows access to **admin** role only  
  → Used to update a specific status by passing the `requestUUID` as a query parameter

---

 **Note:**  
All API requests must include an **authentication token** from Keycloak in the request headers:

