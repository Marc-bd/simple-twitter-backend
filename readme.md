# Twitter Simulator - Backend Application

## Project Description

This project is a backend application that simulates a simple Twitter platform. Built with Spring Boot, Spring Security 6, JWT, OAUTH 2, and MySQL, it allows users to perform CRUD operations on user profiles and tweets, as well as providing authentication functionality.

## Prerequisites

- Java 11 or higher
- MySQL
- Docker and Docker Compose (optional)

## Generating RSA Keys

To set up authentication with JWT, you will need to generate an RSA key pair. Follow the steps below:

1. **Generate a private key and a public key in PEM format.** You should safeguard the private key and never share it, not even with Auth0:

    ```bash
    openssl genrsa -out test_key.pem 2048
    ```

2. **Extract the public key in PEM format using the following command.** This command extracts the public key details so it can be safely shared without revealing the details of the private key:

    ```bash
    openssl rsa -in test_key.pem -outform PEM -pubout -out test_key.pem.pub
    ```

3. **Verify that the created keys have the same names as `jwt.private.key` and `jwt.public.key` in `application.properties`.** If they do not match, rename the files to correspond.

## Setting Up the Database

After generating the keys, start the Docker container that contains the MySQL database. Navigate to the Docker folder and run the following command:

```bash
docker-compose up
```

## Starting the Project
Once the database is set up, you can start the Spring Boot project normally. Execute the following command at the root of your project:
```bash
./mvnw spring-boot:run
```

## Admin User
The project has an admin user that is created at the time of database initialization. The admin user credentials are as follows:
 * **Username**: admin
 * **Password**: admin
 * **Email**: admin@mail.com

## Common User Routes

- **POST** `/auth/register` - Create a new user. Required fields in the JSON for creation:
    - `username`: string
    - `password`: string
    - `email`: string

## Admin User Routes

- **GET** `/users` - List all users.

## Login Route

- **POST** `/login` - User login. Must send:
    - `email`: string
    - `password`: string

## Tweet Routes

- **POST** `/tweets` - Create a tweet. Must send:
    - `content`: string (the content of the tweet)
- **GET** `/tweets` - Retrieve all tweets.
- **GET** `/mytweets` - Retrieve all tweets of the authenticated user.
- **DELETE** `/tweets/{id}` - Delete the user's tweet.
- **GET** `/feed` - Retrieve the tweet feed.

## Contributing

Feel free to contribute improvements or fixes!

## License

This project is licensed under the [MIT License](LICENSE).

