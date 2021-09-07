# Idea Rating API

Idea Rating Tool REST API using Springboot and MySQL

Deployed at [**Heroku**](https://heroku.com) using [**CircleCI**](https://circleci.com) for the CI/CD procedure

[Frontend application repo](https://github.com/victorgrubio/idea-rating-frontend)

See the application (NOT WORKING IN PROD) [**here!**](https://idea-rating-frontend.vercel.app/)

## Installation

### Source

**Recommended tools**

- Docker
- Docker Compose

Clone this repository

```bash
git clone https://github.com/victorgrubio/idea-rating-api.git
```

Run docker-compose. Here we have two options:

- Build it from code using: `docker-compose up --build`
- Pull image from docker: `docker-compose up`

**MySQL**

A MySQL database will be deployed automatically using Docker before creating the API. Default credentials are defined
within `docker-compose.yaml`. They can be customized in a `.env` file or in the mentioned YAML.

### Docker

Just download the docker image from Dockerhub

```bash
docker pull victorgarciarubio/idea-rating-api:1.0.0
```

## Docs

OpenAPI v3 documentation is available in `/api-docs` path.

The Swagger UI that represents the documentation will be accesible at `/swagger-ui.html` .

You can check the Swagger UI Rendered Version in
my [Swaggerhub Link](https://app.swaggerhub.com/apis/victorgarciar/idea-rating_api/v0.1.0)

## CI / CD

I have created a continuos integration / deployment pipeline using CircleCI, Docker and Heroku.

The CircleCI pipeline is available [**here**](https://app.circleci.com/pipelines/github/victorgrubio/idea-rating-api)

### Circle CI

Created [.circleci/config.yaml](.circleci/config.yaml) to build the application and perform the test. If the tests are
passed, the Docker image is built and uploaded to Dockerhub.

### Heroku

Automatic deployments using CI/CD at **CircleCI** allows the SpringBoot app to be deployed in Heroku. A more detailed
explanation about the deployment process is available at [.circleci/config.yaml](.circleci/config.yaml)
