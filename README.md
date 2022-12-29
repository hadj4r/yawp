# yawp

Yet another web project.

## Requirements

* Java 17
* Docker
* Docker Compose

## Getting Started

### Run

```bash
./startup.sh
```

### Stop

```bash
./shutdown.sh
```

## API

### Swagger

[http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

## Databases

### Postgres

[http://localhost:5432/](http://localhost:5432/) connect with `user` user and `postgres` password

### Redis

[http://localhost:6379/](http://localhost:6379/) connect with `supersecret!` password

### TODOs:

* [ ] Add more tests (*some)

### Possible features

* [ ] Add authorization (at least 2 roles)
* [ ] Migration to Webflux
* [ ] Change network to protocol buffers or rsocket
* [ ] Add even more tests
* [ ] Add more features