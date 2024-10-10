# Proof of Concept

## About
A CMS has two components: a content management application (CMA) and a content delivery application (CDA).
- The CMA is a graphical user interface that lets users design, create, modify and remove content from a website without the need for Hypertext Markup Language (HTML) knowledge.
- The CDA component provides the back-end services that support management and delivery of the content once a user creates it in the CMA.

Features may vary depending on the system application but will typically include:
- Intuitive indexing, search, and retrieval features index all data for easy access through search functions and allow users to search by attributes such as publication dates, keywords or author.
- Format management facilitates turning scanned paper documents and legacy electronic documents into HTML or PDF documents.
- Revision features allow content to be updated and edited after initial publication. Revision control also tracks any changes made to files by individuals.
- Publishing functionality allows individuals to use a template or a set of templates approved by the organization, as well as wizards and other tools to create or modify content.

Other CMS features include the following:
- URLs designed to help search engine optimization (SEO-friendly URLs).
- Integrated and online help, including discussion boards.
- Tools for team-based collaboration among content creators and for document management.
- Group-based permission systems and security.
- Workflows for content creator, editor and administrative roles to streamline security and permissions.
- Full template support and customizable templates.
- Easy wizard-based installation and versioning procedures.
- Admin panel with support for multiple languages.
- Minimal server requirements.
- Integrated file managers.
- Integrated audit logs.

[_https://www.techtarget.com/searchcontentmanagement/definition/content-management-system-CMS_]

## MVP I

- Backend Content Management Application
    - As an enterprise user, I can create a minimal product.
    - As an enterprise user, I can create content against a product.
    - As an enterprise user, I can create a product relationship.
    - As an enterprise user, I can search for current content against a product.
    - As an enterprise user, I can view version history for content against a product.
    - As a developer, I can view the authorized user responsible for a previous version.
    - As an enterprise user, when someone else updates the same product content at the same time, then this is handled gracefully and the user is aware of the result. -> use git parallel working for inspiration
    - As an enterprise user, when the service restarts during a save, then this is handled gracefully and the user is aware of the result.
- Backend Content Delivery Application
- Frontend Content Management Application
- Frontend Content Delivery Application

## MVP II

- Backend Content Management Application
    - As an enterprise user, I can roll back to a previous version of content against a product.
    - As an enterprise user, I can view the authorized user responsible for a previous version.
    - As an enterprise user, I can create content against multiple products in a single action.
        - Perhaps via CSV upload? But then pass as JSON
    - As an enterprise user, when the service restarts during a bulk save, then this is handled gracefully and the user is aware of the result.
    - Roll-based authentication (who is the user?) and authorization (do they have permission to update this?)

## Extension

- Backend Content Management Application
    - Templates approved by organization
    - Help pages, how to use documentation
