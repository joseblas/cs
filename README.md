# RFQ Implementation

This projects pretends to solve the RFQ problem (Mandrill Pre Interview v1.5)


# Implementations details

 This project has been developed with Intellij Idea, Maven and Java 8.
 To compile the project:
   mvn clean compile
   
 To run the tests:
   mvn clean test
  
## Libraries used
   Junit and Mockito for testing purposes
   Guava: Immutable list for tests. 
   commons-lang3: for Pairs(tuples) class.
    
## Assumptions     
  1. LiveOrderBoard.ordersFor return orders without any kind of order.
  2. Provided classes cannot be modified


## Optimisations not performed
    There are some optimizations that have not been performed because it is premature.
     1. Implementation of equals and hashcode in the model: Order and Quote.
     2. Builder static classes for Order (useful for tests also)
     3. Utils.buildForQuote should be in Quote class as static builder. Assumption #3
     4. Likewise, some classes would be nice to have such as Currency or price (where encapsulate the price rounds)
     5. I would rather use BigDecimal for price instead of double.