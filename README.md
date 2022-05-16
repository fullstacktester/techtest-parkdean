# Parkdean Resorts Tech Test: Tom Binns

## Getting Started
This project is written in Java and the tests can be run via the command:

    ./mvnw clean verify

This requires a working java environment locally. The tests have been written to work with Java 8 to keep things simple as no modern Java features were required.

## Frameworks Used
The main framework used is Serenity BDD (https://github.com/serenity-bdd/serenity-core)

This provides some assistance with Selenium, but its main feature is it's reporting capability.
Using, Serenity BDD, the tests are split into **features** which call **steps** which perform the actual work.

The report can be found [here](./report/index.html) and includes the tests plus screenshots.