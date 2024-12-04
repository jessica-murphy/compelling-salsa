# 5. Use Liquibase to Version Database Schema

Date: 2024-12-04

## Status

Accepted

## Context

Desire to version the database schema to enable schema rollbacks and consistent database schema across environments.

## Decision

Use Spring Boot integrated Liquibase to version database schema. See spring.liquibase.change-log property for file location. 

## Consequences

Learning curve and extra files when creating a new endpoint/entity. 

Not required for small projects but good practise in case developer team size increases in future.
