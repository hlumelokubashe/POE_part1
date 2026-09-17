# POE Part 1 - Submission

POE Part 1 - Application Development & Version Control Report
1. Project Overview
This project forms Part 1 of the Portfolio of Evidence (PoE) for the Application Development module. The primary objective was to build a Java-based user authentication system that enforces strict validation rules for user registration and login functionality. The project also demonstrates software quality assurance through JUnit unit testing and disciplined version control using Git and GitHub Desktop.

2. System Features & Architecture
The application consists of two main Java source files and a dedicated test suite:

Login.java: Contains the core business logic and validation routines.

checkUserName(): Uses regular expressions to ensure usernames contain an underscore (_) and do not exceed 5 characters in length.

checkPasswordComplexity(): Validates that passwords are at least 8 characters long, contain a capital letter, a number, and a special character.

registerUser() & loginUser(): Processes credentials and returns structured status messages based on authentication success or failure.

POEpart1.java: Serves as the main entry point for the application. It runs the console interface, accepts user input for account creation and login, and displays output feedback.

LoginTest.java: The JUnit test suite designed to automatically test edge cases, including valid and invalid usernames, password complexity rules, and login authentication results.

3. Unit Testing & Quality Assurance
To guarantee reliability, automated unit tests were created in LoginTest.java. The test suite covers:

Validation of correct and incorrect username formatting.

Password strength verification across various combinations of missing characters.

Verification of login status checks to ensure authorized access is granted and unauthorized access is blocked.

All 8 test cases were executed in NetBeans, achieving a 100% pass rate.

4. Version Control & Git Commit History
Version control was managed using GitHub Desktop. The project features a structured commit history tracking incremental development milestones across the repository:

Initial Commit: Project structure setup in NetBeans.

Added Login logic: Implemented core registration and validation methods.

Added Unit Tests: Created LoginTest.java with JUnit assertion cases.

Updated POEpart1 main method comments: Refined documentation in the main entry point.

Added documentation comments to LoginTest: Cleaned up code comments within the test suite.

Created README file for submission: Created initial README layout and documentation.

5. Video Presentation
A live screen-recording demonstration was conducted to walk through the codebase, execute interactive console login flows, run the JUnit test suite, and present the GitHub commit history.
