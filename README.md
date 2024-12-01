# User Service

This is a Spring-based microservice for managing user profiles and authentication. The service uses PostgreSQL as its database and is fully containerized for easy deployment. Designed for flexibility, it can be easily cloned, configured, and extended for various use cases.

---

## Features

- User management: Create, read, update, and delete user profiles.
- PostgreSQL integration with Dockerized setup.
- Simple environment variable configuration for customization.
- Ready-to-use endpoints for user management.

---

## Prerequisites

To run this service, ensure you have:

1. [Docker](https://www.docker.com/) installed.
2. [Docker Compose](https://docs.docker.com/compose/) installed.
3. Git (for cloning the repository).
4. A text editor to customize environment variables (e.g., VS Code or Vim).

---

## Setup and Configuration

### 1. Clone the Repository

```bash
git clone https://github.com/Victor-M16/userservice.git
cd userservice
```

### 2. Customize Environment Variables

Modify the `docker-compose.yml` file to set your custom configuration. Pay special attention to the following fields:

- **App Service Environment Variables:**
  - `SPRING_DATASOURCE_URL`: The JDBC URL for your PostgreSQL instance.
  - `SPRING_DATASOURCE_USERNAME`: Your PostgreSQL username.
  - `SPRING_DATASOURCE_PASSWORD`: Your PostgreSQL password.

- **PostgreSQL Service Environment Variables:**
  - `POSTGRES_DB`: Name of the database to be created.
  - `POSTGRES_USER`: PostgreSQL admin username.
  - `POSTGRES_PASSWORD`: PostgreSQL admin password.

Example:
```yaml
services:
  app:
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/my_custom_db
      SPRING_DATASOURCE_USERNAME: my_user
      SPRING_DATASOURCE_PASSWORD: my_secure_password
  postgres:
    environment:
      POSTGRES_DB: my_custom_db
      POSTGRES_USER: my_user
      POSTGRES_PASSWORD: my_secure_password
```

---

## Running the Service

### Using Docker Compose

1. Build the Docker image:
   ```bash
   docker-compose build
   ```

2. Start the containers:
   ```bash
   docker-compose up -d
   ```

The application will be accessible at `http://localhost:8080`.

---

## Customization

This service is designed to be flexible for any use case. To adapt it for your needs:

1. **Endpoints**: Modify the existing controllers or add new ones in the `src/main/java/com/example/userservice/controller` package.
2. **Database Schema**: Update the JPA entities in `src/main/java/com/example/userservice/model` to fit your domain requirements.
3. **Business Logic**: Add or modify services in the `src/main/java/com/example/userservice/service` package.

---

## Database Persistence

The PostgreSQL container uses a volume (`postgres_data`) to persist data across container restarts. If you'd like to start fresh or remove old data, run:

```bash
docker-compose down -v
```

---

## Endpoints

The following API endpoints are available by default:

| HTTP Method | Endpoint           | Description              |
|-------------|--------------------|--------------------------|
| `GET`       | `/users`           | Get all users            |
| `POST`      | `/users`           | Create a new user        |
| `GET`       | `/users/{id}`      | Get a user by ID         |
| `PUT`       | `/users/{id}`      | Update a user's details  |
| `DELETE`    | `/users/{id}`      | Delete a user            |

To test the API, you can use tools like [Postman](https://www.postman.com/) or `curl`.

---

## Deploying to Production

For production use, consider:

1. **Database Backups**: Use tools like `pg_dump` or managed PostgreSQL services.
2. **Environment File**: Replace inline environment variables with a `.env` file for better security:
   ```bash
   SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/your_db
   SPRING_DATASOURCE_USERNAME=your_user
   SPRING_DATASOURCE_PASSWORD=your_password
   ```
   Update `docker-compose.yml`:
   ```yaml
   environment:
     - SPRING_DATASOURCE_URL
     - SPRING_DATASOURCE_USERNAME
     - SPRING_DATASOURCE_PASSWORD
   ```

3. **Reverse Proxy**: Use NGINX or Apache to serve the app securely.
4. **SSL**: Configure HTTPS for secure communication.

---

## Troubleshooting

1. **Container Fails to Start**:
   - Ensure ports `8080` and `5432` are not in use.
   - Check logs using:
     ```bash
     docker-compose logs app
     docker-compose logs postgres
     ```

2. **Database Connection Errors**:
   - Confirm PostgreSQL credentials match in both `app` and `postgres` services.

---

## Contributing

Contributions are welcome! Fork the repository, create a feature branch, and submit a pull request.

---

## License

This project is licensed under the [MIT License](LICENSE).

