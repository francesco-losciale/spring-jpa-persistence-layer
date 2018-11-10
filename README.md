# Simple Repository pattern implementation using Spring JPA

Most important flaws:

1. Excessive use of Java Reflection
2. Soft delete implementation using this version of Spring JPA was possible only thanks to Reflection and a workaround
3. A bunch of Hibernate annotations needed to be used and are spread in the codebase mixing JPA and Hibernate... 

### Prerequisites

You need a MySql empty database to run the test. 

The endpoint would be:

jdbc:mysql://address=(protocol=tcp)(host=localhost)(port=3306)/manager_db?useLegacyDatetimeCode=false&amp&serverTimezone=UTC

You can change it in both these files: AppConfigReading.java AppConfigWriting.java

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
