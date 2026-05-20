# Spring IA Backend Java 21

Backend application built with Java 21, Spring Boot, and Spring AI to integrate with OpenAI APIs and ChatGPT.

## 🚀 About the Project

This project is a backend application developed using Java 21 and Spring Boot with Spring AI integration.

The main goal is to provide a practical example of how to connect Java applications with OpenAI models such as ChatGPT.

The application exposes REST APIs that can be consumed by frontend applications like React, mobile apps, or other services.

Features included in the project:

* ChatGPT integration using Spring AI
* REST API development with Spring Boot
* AI prompt processing
* OpenAI API integration
* Support for modern Java 21 features
* Full Stack integration support

## 🛠️ Technologies Used

* Java 21
* Spring Boot
* Spring AI
* OpenAI API
* Maven
* REST APIs
* Jackson
* Lombok

## 📁 Project Structure

```bash
spring-ia-brunojdsz/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
└── README.md
```

## ⚙️ Requirements

Before running the project, make sure you have installed:

* Java 21
* Maven 3.9+
* OpenAI account
* OpenAI API Key

## 🔑 OpenAI Configuration

Configure your API Key in the application properties file.

Example:

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
```

You can also configure it directly:

```properties
spring.ai.openai.api-key=your-api-key
```

## ▶️ Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/brunojdsz/spring-ia-brunojdsz.git
```

### 2. Open the project folder

```bash
cd spring-ia-brunojdsz
```

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The application will start at:

```bash
http://localhost:8080
```

## 🔗 Example API

Example endpoint:

```http
GET /api/ask-ai?prompt=Responda em uma palavra a capital de Marrocos
```

Example request:

```json
{
  "message": "Explain what Spring AI is"
}
```

## 💻 Example Controller

```java
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping
    public String chat(@RequestBody String message) {
        return chatClient.prompt(message)
                .call()
                .content();
    }
}
```

## 📸 Features

* ChatGPT integration with Spring AI
* REST API communication
* Prompt-based AI interactions
* Java 21 modern features
* Simple architecture for learning and scalability
* Frontend integration support

## 📚 Learning Goals

This project was created to explore:

* Generative AI with Java
* Spring AI practical usage
* OpenAI API integration
* Modern backend development with Spring Boot
* Full Stack AI applications
* AI-driven REST APIs

## 🔥 Spring AI Benefits

Spring AI helps developers integrate AI models into Spring applications using a familiar Spring ecosystem.

Benefits include:

* Simplified OpenAI integration
* Standardized AI abstractions
* Easy prompt management
* Better developer experience
* Native Spring Boot support

## 🤝 Contribution

Contributions are welcome.

To contribute:

```bash
# Fork the project
# Create a branch
 git checkout -b feature/my-feature

# Commit changes
 git commit -m "feat: my new feature"

# Push changes
 git push origin feature/my-feature
```

Then open a Pull Request.

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Bruno Jesus da Silva

* GitHub: [https://github.com/brunojdsz](https://github.com/brunojdsz)
* LinkedIn: [www.linkedin.com/in/bruno-silva-b04a62138](www.linkedin.com/in/bruno-silva-b04a62138)
