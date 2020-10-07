# Licensed Tennis Matches

Restful service to display details of licensed tennis matches for a customer.


## Running

    ./gradlew bootRun

The application exposes two endpoints:

1. `/customers` with a list of customer IDs
2. `/customers/{customerId}` with a list of purchased matches

Sample data are automatically pre-loaded during initialisation.
