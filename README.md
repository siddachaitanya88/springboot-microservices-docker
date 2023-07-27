This project is about microservices communication and deploying them using a docker container and kubernetese.
 API Gateways can route incoming requests to the appropriate microservices  based on predefined rules. They can also aggregate multiple API requests into a single request, reducing the number of round-trips between the client and the server.
 Load Balancing: API Gateways can distribute incoming requests across multiple instances of a service, ensuring even distribution of load and improving system scalability and performance.Kafka is used for Asynchrounous communication.
All micro services are monitored by a promotheus and Graphana,ZIPKIN is used for tracing .
Spring Security is added  for authorozing the tokens using JWT Keycloak server.
Webclinet is used for inter service communication and for Resilence Resilience4J CircuitBreaker has been added.
