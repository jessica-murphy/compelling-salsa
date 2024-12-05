# 4. Use Spring Data Java Persistence API

Date: 2024-11-26

## Status

Accepted

## Context

We desire to reduce the effort needed for developers to setup database interactions and encourage appropriately separated data transfer entities.

## Decision

Use Spring Data Java Persistence API.

## Consequences

Easier initial setup of service.

Spring Data JPA enforces some conventions. This creates less flexibility which at initial stages is a good thing but may not scale well.

One such convention is the lack of a built-in insert option. Instead `repository.save()` performs an upsert. If other behaviour, eg. insert, is desired it must be implemented by the developer.

Spring Data JPA adds a layer of abstraction which can inadvertently impact performance. Developers should familiarise with common pitfalls.

Useful resources:
- https://medium.com/@majbahbuet08/performance-pitfalls-while-using-spring-data-jpa-and-solutions-to-avoid-them-5eb4ee3fe4ea
- https://betterprogramming.pub/hibernate-is-not-so-evil-84ca72b959c3

