# 6. Comply with HTTP Semantics RFC 9110

Date: 2024-12-05

## Status

Accepted

## Context

There exist different versions of the HTTP Semantics specification. 

## Decision

For the avoidance of doubt, this service's API was created initially in compliance with HTTP Semantics RFC 9110. 

Available at https://datatracker.ietf.org/doc/rfc9110/. HTMLized at https://datatracker.ietf.org/doc/html/rfc9110.

At a future date, this version of the document will be updated and/or obseleted. In this case, developers should migrate code to comply with the more recent version and provide an Architecture Decision Record stating such a change.

## Consequences

Explicitly stating the reference used should maintain consistency throughout the codebase.

Endpoints across the codebase will be consistent with each other. This provides an improved client experience.

Increases the effort needed when the specification is updated. At the time of writing, this is a worthwhile tradeoff.
