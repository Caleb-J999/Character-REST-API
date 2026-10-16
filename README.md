# Pokemon Character REST API

A RESTful CRUD API for Pokemon from the animated series and video games.
Users can create, view, update, and delete Pokemon, filter by type, and
search by name.

Built with Java 25, Spring Boot, Spring Data JPA, and PostgreSQL.
The database is hosted on Neon, and the application is deployed on Render
using a multi-stage Docker build.

## Deployed API

https://pokemon-character-api.onrender.com/api/characters

The free Render service sleeps when idle. The first request after inactivity
may take up to a minute while the application starts.

This application provides API endpoints rather than a website homepage.
Use `/api/characters`; the root URL `/` returns 404.

## Entity Attributes

Entity class: `PokemonCharacter`  
Database table: `characters`

| Attribute | Java Type | Rules |
|---|---|---|
| characterId | long | Unique, automatically generated primary key |
| name | String | Required; cannot be null, empty, or whitespace |
| description | String | Required; cannot be null, empty, or whitespace |
| pokemonType | String | Optional Pokemon type, such as Electric or Fire |
| region | String | Optional region, such as Kanto or Johto |

## API Endpoints

All paths below are relative to:
`https://pokemon-character-api.onrender.com`

| Method | Path | Description | Success Status |
|---|---|---|---|
| GET | /api/characters | Get all Pokemon | 200 OK |
| GET | /api/characters/{id} | Get one Pokemon by ID | 200 OK |
| POST | /api/characters | Create a Pokemon | 201 Created |
| PUT | /api/characters/{id} | Update an existing Pokemon | 200 OK |
| DELETE | /api/characters/{id} | Delete a Pokemon | 204 No Content |
| GET | /api/characters?pokemonType=Electric | Filter by type, ignoring case | 200 OK |
| GET | /api/characters?name=pika | Search by partial name, ignoring case | 200 OK |

If both `name` and `pokemonType` are supplied, name search takes priority.
Filters and searches return a JSON list, which may be empty.

## POST Example

POST `/api/characters`

Content-Type: `application/json`

```json
{
  "name": "Pikachu",
  "description": "An Electric-type Pokemon known for storing electricity in its cheeks.",
  "pokemonType": "Electric",
  "region": "Kanto"
}
```

The response includes the automatically generated `characterId`.
Do not include an ID in the creation request.

## PUT Example

PUT `/api/characters/{id}`

Replace `{id}` with an existing Pokemon's characterId.
Send all fields you want to retain.

Content-Type: `application/json`

```json
{
  "name": "Pikachu",
  "description": "An Electric-type Pokemon and Ash's famous partner in the animated series.",
  "pokemonType": "Electric",
  "region": "Kanto"
}
```

## Project Structure

- Entity: Defines the Pokemon attributes and validation rules.
- Repository: Provides database operations, type filtering, and name searching.
- Service: Handles CRUD operations and missing-ID errors.
- Controller: Exposes HTTP endpoints and validates incoming request bodies.

The controller calls the service, and the service calls the repository.

