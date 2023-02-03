## What's Going On Here
In this commit, we've got a Spring application that creates some 
Customer instances, changes the last name on 1L (from "Bauer" to "Black"),
retrieves the customer from the database again, then saves the change.
Along the way it prints out what's going on.  Here's the output:

```text
: Started AccessingDataJpaApplication in 1.638 seconds (JVM running for 1.989)
: Customers found with findAll():
: -------------------------------
: Customer[id=1, firstName='Jack', lastName='Bauer']
: Customer[id=2, firstName='Chloe', lastName='O'Brian']
: Customer[id=3, firstName='Kim', lastName='Bauer']
: Customer[id=4, firstName='David', lastName='Palmer']
: Customer[id=5, firstName='Michelle', lastName='Dessler']
: 
: Customer found with findById(1L):
: --------------------------------
: Customer[id=1, firstName='Jack', lastName='Bauer']
: 
: Customer found with findByLastName('Bauer'):
: --------------------------------------------
: Customer[id=1, firstName='Jack', lastName='Bauer']
: Customer[id=3, firstName='Kim', lastName='Bauer']
:
: Customer found with id(1L):
: --------------------------------------------
: Customer[id=1, firstName='Jack', lastName='Bauer']
: Set lastname('Black'): Customer[id=1, firstName='Jack', lastName='Black']
: Customer[id=1, firstName='Jack', lastName='Black'] == Customer[id=1, firstName='Jack', lastName='Black']? true
: Customer re-fetched with id(1L):
: --------------------------------------------
: Customer[id=1, firstName='Jack', lastName='Black'] == Customer[id=1, firstName='Jack', lastName='Bauer']? false
: Saving lastname
: --------------------------------------------
: Customer[id=1, firstName='Jack', lastName='Black'] == Customer[id=1, firstName='Jack', lastName='Bauer']? false
```
## Conclusion
Using JPA `@Repository` does not automatically save changes. `repository.save(..)` _must_ be called.
In addition, different objects that represent the same row in the database (`1L` in this example)
_do not_ share state - before or after `save` is called.