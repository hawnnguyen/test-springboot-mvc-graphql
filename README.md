# SpringBoot MVC + GraphQL Pattern Service

This DEMO project demonstrates a Spring Boot application using SpringBootMVC and GraphQL to manage design patterns by HawnSolo.  It uses a H2 database for local development.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Application

1. Clean and compile the project:
```bash
mvn clean
```

2. Package the application:
```bash
mvn package
```

## Running the Application

You can run the application using either of these methods:

1. Using Maven:
```bash
mvn spring-boot:run
```

2. Using the JAR file:
```bash
java -jar target/pattern-service-1.0.0.jar
```

The application will start on `http://localhost:8080`

## Testing the Application

The GraphQL endpoint is available at: `http://localhost:8080/graphql`
GraphiQL interface is available at: `http://localhost:8080/graphiql`

### Sample GraphQL Queries

1. Get all patterns:
```graphql
query {
  patterns {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

2. Get pattern by ID:
```graphql
query {
  patternById(id: "1") {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

3. Get patterns by category:
```graphql
query {
  patternsByCategory(category: "Design Patterns") {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

4. Get patterns by phase:
```graphql
query {
  patternsByPhase(phase: "Implementation") {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

Note: The `patternsByPhase` query performs a case-insensitive match on the phase field. If no patterns are found for the given phase, it will return an empty list rather than null. For example:

```json
{
  "data": {
    "patternsByPhase": []
  }
}
```

### Sample GraphQL Mutation

Create a new pattern:
```graphql
mutation {
  createPattern(
    id: "4"
    name: "Factory Method"
    type: "Creational"
    useCases: "Create objects without specifying exact class"
    description: "Defines an interface for creating objects, but lets subclasses decide which class to instantiate"
    category: "Design Patterns"
    phase: "Implementation"
  ) {
    id
    name
    type
    description
  }
}
```

### Using cURL

1. Query all patterns:
```bash
curl -X POST \
  http://localhost:8080/graphql \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "{ patterns { id name type useCases description category phase } }"
  }'
```

2. Query pattern by ID:
```bash
curl -X POST \
  http://localhost:8080/graphql \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "query { patternById(id: \"1\") { id name type useCases description category phase } }"
  }'
```

3. Query patterns by category:
```bash
curl -X POST \
  http://localhost:8080/graphql \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "query { patternsByCategory(category: \"Design Patterns\") { id name type useCases description category phase } }"
  }'
```

4. Query patterns by phase:
```bash
curl -X POST \
  http://localhost:8080/graphql \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "query { patternsByPhase(phase: \"Implementation\") { id name type useCases description category phase } }"
  }'
```

5. Create a new pattern:
```bash
curl -X POST \
  http://localhost:8080/graphql \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "mutation { createPattern(pattern: { id: \"4\", name: \"Factory Method\", type: \"Creational\", useCases: \"Create objects without specifying exact class\", description: \"Defines an interface for creating objects, but lets subclasses decide which class to instantiate\", category: \"Design Patterns\", phase: \"Implementation\" }) { id name type useCases description category phase } }"
  }'
```

## GraphiQL Interface Examples

The GraphiQL interface is available at `http://localhost:8080/graphiql`. Here are complete examples you can copy and paste directly into the interface:

### 1. Query All Patterns

In the GraphiQL interface, paste this query:
```graphql
query {
  patterns {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

Expected response:
```json
{
  "data": {
    "patterns": [
      {
        "id": "1",
        "name": "Singleton",
        "type": "Creational",
        "useCases": "Ensure a class has only one instance",
        "description": "The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance.",
        "category": "Design Patterns",
        "phase": "Implementation"
      },
      {
        "id": "2",
        "name": "Observer",
        "type": "Behavioral",
        "useCases": "Event handling and monitoring",
        "description": "Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.",
        "category": "Design Patterns",
        "phase": "Design"
      }
    ]
  }
}
```

![GraphQL Interface Example](docs/graphql-inteface-pattern.png)

### 2. Query Pattern by ID

Query:
```graphql
query {
  patternById(id: "1") {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

Expected response:
```json
{
  "data": {
    "patternById": {
      "id": "1",
      "name": "Singleton",
      "type": "Creational",
      "useCases": "Ensure a class has only one instance",
      "description": "The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance.",
      "category": "Design Patterns",
      "phase": "Implementation"
    }
  }
}
```

### 3. Query Patterns by Category

Query:
```graphql
query {
  patternsByCategory(category: "Design Patterns") {
    id
    name
    type
    phase
  }
}
```

Expected response:
```json
{
  "data": {
    "patternsByCategory": [
      {
        "id": "1",
        "name": "Singleton",
        "type": "Creational",
        "phase": "Implementation"
      },
      {
        "id": "2",
        "name": "Observer",
        "type": "Behavioral",
        "phase": "Design"
      }
    ]
  }
}
```

### 4. Query Patterns by Phase

The `patternsByPhase` query allows you to find all patterns for a specific implementation phase. The query performs a case-insensitive match on the phase field.

Query:
```graphql
query {
  patternsByPhase(phase: "Implementation") {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

Expected response when patterns are found:
```json
{
  "data": {
    "patternsByPhase": [
      {
        "id": "1",
        "name": "Singleton",
        "type": "Creational",
        "useCases": "Ensure a class has only one instance",
        "description": "The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance.",
        "category": "Design Patterns",
        "phase": "Implementation"
      },
      {
        "id": "4",
        "name": "Factory Method",
        "type": "Creational",
        "useCases": "Create objects without specifying exact class",
        "description": "Defines an interface for creating objects, but lets subclasses decide which class to instantiate",
        "category": "Design Patterns",
        "phase": "Implementation"
      }
    ]
  }
}
```

Expected response when no patterns are found:
```json
{
  "data": {
    "patternsByPhase": []
  }
}
```

### 5. Create New Pattern

Mutation:
```graphql
mutation {
  createPattern(pattern: {
    id: "4"
    name: "Factory Method"
    type: "Creational"
    useCases: "Create objects without specifying exact class"
    description: "Defines an interface for creating objects, but lets subclasses decide which class to instantiate"
    category: "Design Patterns"
    phase: "Implementation"
  }) {
    id
    name
    type
    useCases
    description
    category
    phase
  }
}
```

Expected response:
```json
{
  "data": {
    "createPattern": {
      "id": "4",
      "name": "Factory Method",
      "type": "Creational",
      "useCases": "Create objects without specifying exact class",
      "description": "Defines an interface for creating objects, but lets subclasses decide which class to instantiate",
      "category": "Design Patterns",
      "phase": "Implementation"
    }
  }
}
```

### Tips for Using GraphiQL

1. GraphiQL provides auto-completion: Press Ctrl+Space to see available fields
2. You can format your queries using the "Prettify" button
3. Use the "Docs" explorer on the right side to browse the schema
4. Variables can be defined in the "Query Variables" panel at the bottom

## Sample Response

When querying all patterns, you'll get a response like this:
```json
{
  "data": {
    "patterns": [
      {
        "id": "1",
        "name": "Singleton",
        "type": "Creational",
        "description": "The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance."
      },
      {
        "id": "2",
        "name": "Observer",
        "type": "Behavioral",
        "description": "Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically."
      }
    ]
  }
}
