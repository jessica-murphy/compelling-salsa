# 3. Testing

Date: 2024-11-25

## Status

Accepted

## Context

We desire a standard test structure to avoid miscommunication and simplify code review.

## Decision

### Unit Test
- Do not spin up Spring context.
- Make use of mocks when appropriate.
- Use {class under test}_Tests_ naming convention.

### Integration Test
- Do spin up Spring context.
- Avoid use of mocks.
- Use {class under test}_IntegrationTests_ naming convention.
- Clean up dataset between tests using appropriate setUp and tearDown methods.

## Consequences

Compliance must be actively checked for during code review as does not form a part of checkstyle automated checks.

Clear standard makes it easier to ensure adherence.

