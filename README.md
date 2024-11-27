# Content Management Application Service

## Purpose

Content Management Application backend intended to let users design, create, modify and remove content from a website without the need for Hypertext Markup Language (HTML) knowledge.

The intended use case for this service is to enable an e-commerce product display page.

## Tech Stack
|                                                                                                                               |                                                                                                                                                                       | |
|:-----------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------:|
| <img height="100" alt="Github" src="https://static-00.iconduck.com/assets.00/github-icon-512x500-i14wp164.png"> <p>GitHub</p> |                 <img height="100" alt="Java 21 icon" src="https://static-00.iconduck.com/assets.00/java-icon-1511x2048-6ikx8301.png"> <p>Java 21</p>                  |<img height="100" alt="Gradle icon" src="https://static-00.iconduck.com/assets.00/file-type-light-gradle-icon-512x377-slv3rykw.png"> <p>Gradle</p>|

## Local Setup
### Test
- `./gradlew check`

No standalone container needed due to testcontainers.

### Deploy
- `docker pull postgres`
- `docker run --name local-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres`
- `docker container logs local-postgres`
- `./gradlew bootRun`

Service available at http://localhost:8080/.

## Architecture Decision Records
See [.adr-dir](.adr-dir).
