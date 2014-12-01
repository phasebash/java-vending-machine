java-vending-machine
====================

A simple exercise in implementing a vending machine with Java and TDD.

## Project Overview ##
* Java 8 implementation.
* Groovy 2.3.x tests.
* Interfaces used to decouple domain concepts (and annoyingly, to avoid CGLIB target class proxy issues in GMock).
* GMock for mocking (as it is vastly less redundant that Mockito).
* Gradle project with static analysis.  Overkill for this project, but given to communicate my development preferences.
* More notes to come...

## How to build ##
Clients of this project will need Java 8u25 or higher.  To build the project, simply run:
`./gradlew clean build` from the root of the project.

Upon completion, the vending machine demo can be exercised using:
`./gradlew clean run`

## Current Status ##
[![Build Status](https://travis-ci.org/phasebash/java-vending-machine.png)](https://travis-ci.org/phasebash/java-vending-machine)
