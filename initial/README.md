## What's New
Adding in a relationship. A `Portfolio` has many `Customers` (and a name).
The application was updated to set the `portfolio` on each `customer`, then save the `customer`.
Then, fetch the `portfolio` and print out each customer. SQL statements are logged. There's a lot of sql. 
The relevant queries are at the bottom - when fetching the Portfolio, there are 2 queries (not n+1):

To be clear, here is the interesting bit:

```text
: Fetch the portfolio
: --------------------------------------------
: select portfolio0_.id as id1_1_, portfolio0_.name as name2_1_ from portfolio portfolio0_ where portfolio0_.name=?
: binding parameter [1] as [VARCHAR] - [24]
: select customers0_.portfolio_id as portfoli4_0_0_, customers0_.id as id1_0_0_, customers0_.id as id1_0_1_, customers0_.first_name as first_na2_0_1_, customers0_.last_name as last_nam3_0_1_, customers0_.portfolio_id as portfoli4_0_1_ from customer customers0_ where customers0_.portfolio_id=?
: binding parameter [1] as [INTEGER] - [6]
: Portfolio{id=6, name='24', customer count=5}
: 
: Portfolio's customers
: Customer[id=1, firstName='Jack', lastName='Black', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=2, firstName='Chloe', lastName='O'Brian', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=3, firstName='Kim', lastName='Bauer', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=4, firstName='David', lastName='Palmer', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=5, firstName='Michelle', lastName='Dessler', portfolio='Portfolio{id=6, name='24', customer count=5}']
```

**Full logs:**

```text
: Initialized JPA EntityManagerFactory for persistence unit 'default'
: spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
: Tomcat started on port(s): 8080 (http) with context path ''
: Started AccessingDataJpaApplication in 1.554 seconds (JVM running for 1.759)
: select next_val as id_val from hibernate_sequence for update
: update hibernate_sequence set next_val= ? where next_val=?
: insert into customer (first_name, last_name, portfolio_id, id) values (?, ?, ?, ?)
: binding parameter [1] as [VARCHAR] - [Jack]
: binding parameter [2] as [VARCHAR] - [Bauer]
: binding parameter [3] as [INTEGER] - [null]
: binding parameter [4] as [BIGINT] - [1]
: select next_val as id_val from hibernate_sequence for update
: update hibernate_sequence set next_val= ? where next_val=?
: insert into customer (first_name, last_name, portfolio_id, id) values (?, ?, ?, ?)
: binding parameter [1] as [VARCHAR] - [Chloe]
: binding parameter [2] as [VARCHAR] - [O'Brian]
: binding parameter [3] as [INTEGER] - [null]
: binding parameter [4] as [BIGINT] - [2]
: select next_val as id_val from hibernate_sequence for update
: update hibernate_sequence set next_val= ? where next_val=?
: insert into customer (first_name, last_name, portfolio_id, id) values (?, ?, ?, ?)
: binding parameter [1] as [VARCHAR] - [Kim]
: binding parameter [2] as [VARCHAR] - [Bauer]
: binding parameter [3] as [INTEGER] - [null]
: binding parameter [4] as [BIGINT] - [3]
: select next_val as id_val from hibernate_sequence for update
: update hibernate_sequence set next_val= ? where next_val=?
: insert into customer (first_name, last_name, portfolio_id, id) values (?, ?, ?, ?)
: binding parameter [1] as [VARCHAR] - [David]
: binding parameter [2] as [VARCHAR] - [Palmer]
: binding parameter [3] as [INTEGER] - [null]
: binding parameter [4] as [BIGINT] - [4]
: select next_val as id_val from hibernate_sequence for update
: update hibernate_sequence set next_val= ? where next_val=?
: insert into customer (first_name, last_name, portfolio_id, id) values (?, ?, ?, ?)
: binding parameter [1] as [VARCHAR] - [Michelle]
: binding parameter [2] as [VARCHAR] - [Dessler]
: binding parameter [3] as [INTEGER] - [null]
: binding parameter [4] as [BIGINT] - [5]
: Customers found with findAll():
: -------------------------------
: select customer0_.id as id1_0_, customer0_.first_name as first_na2_0_, customer0_.last_name as last_nam3_0_, customer0_.portfolio_id as portfoli4_0_ from customer customer0_
: Customer[id=1, firstName='Jack', lastName='Bauer', portfolio='null']
: Customer[id=2, firstName='Chloe', lastName='O'Brian', portfolio='null']
: Customer[id=3, firstName='Kim', lastName='Bauer', portfolio='null']
: Customer[id=4, firstName='David', lastName='Palmer', portfolio='null']
: Customer[id=5, firstName='Michelle', lastName='Dessler', portfolio='null']
: 
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_, portfolio1_.id as id1_1_1_, portfolio1_.name as name2_1_1_ from customer customer0_ left outer join portfolio portfolio1_ on customer0_.portfolio_id=portfolio1_.id where customer0_.id=?
: binding parameter [1] as [BIGINT] - [1]
: Customer found with findById(1L):
: --------------------------------
: Customer[id=1, firstName='Jack', lastName='Bauer', portfolio='null']
: 
: Customer found with findByLastName('Bauer'):
: --------------------------------------------
: select customer0_.id as id1_0_, customer0_.first_name as first_na2_0_, customer0_.last_name as last_nam3_0_, customer0_.portfolio_id as portfoli4_0_ from customer customer0_ where customer0_.last_name=?
: binding parameter [1] as [VARCHAR] - [Bauer]
: Customer[id=1, firstName='Jack', lastName='Bauer', portfolio='null']
: Customer[id=3, firstName='Kim', lastName='Bauer', portfolio='null']
: Customer found with id(1L):
: --------------------------------------------
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_, portfolio1_.id as id1_1_1_, portfolio1_.name as name2_1_1_ from customer customer0_ left outer join portfolio portfolio1_ on customer0_.portfolio_id=portfolio1_.id where customer0_.id=?
: binding parameter [1] as [BIGINT] - [1]
: Customer[id=1, firstName='Jack', lastName='Bauer', portfolio='null']
: Set lastname('Black'): Customer[id=1, firstName='Jack', lastName='Black', portfolio='null']
: Customer[id=1, firstName='Jack', lastName='Black', portfolio='null'] == Customer[id=1, firstName='Jack', lastName='Black', portfolio='null']? true
: Customer re-fetched with id(1L):
: --------------------------------------------
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_, portfolio1_.id as id1_1_1_, portfolio1_.name as name2_1_1_ from customer customer0_ left outer join portfolio portfolio1_ on customer0_.portfolio_id=portfolio1_.id where customer0_.id=?
: binding parameter [1] as [BIGINT] - [1]
: Customer[id=1, firstName='Jack', lastName='Black', portfolio='null'] == Customer[id=1, firstName='Jack', lastName='Bauer', portfolio='null']? false
: Saving lastname
: --------------------------------------------
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_ from customer customer0_ where customer0_.id=?
: binding parameter [1] as [BIGINT] - [1]
: update customer set first_name=?, last_name=?, portfolio_id=? where id=?
: binding parameter [1] as [VARCHAR] - [Jack]
: binding parameter [2] as [VARCHAR] - [Black]
: binding parameter [3] as [INTEGER] - [null]
: binding parameter [4] as [BIGINT] - [1]
: Customer[id=1, firstName='Jack', lastName='Black', portfolio='null'] == Customer[id=1, firstName='Jack', lastName='Bauer', portfolio='null']
: 
: Create a portfolio named '24'
: --------------------------------------------
: select next_val as id_val from hibernate_sequence for update
: update hibernate_sequence set next_val= ? where next_val=?
: insert into portfolio (name, id) values (?, ?)
: binding parameter [1] as [VARCHAR] - [24]
: binding parameter [2] as [INTEGER] - [6]
: Portfolio{id=6, name='24', customer count=0}
: 
: Add customers to portfolio
: --------------------------------------------
: select customer0_.id as id1_0_, customer0_.first_name as first_na2_0_, customer0_.last_name as last_nam3_0_, customer0_.portfolio_id as portfoli4_0_ from customer customer0_
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_ from customer customer0_ where customer0_.id=?
: binding parameter [1] as [BIGINT] - [1]
: select portfolio0_.id as id1_1_0_, portfolio0_.name as name2_1_0_, customers1_.portfolio_id as portfoli4_0_1_, customers1_.id as id1_0_1_, customers1_.id as id1_0_2_, customers1_.first_name as first_na2_0_2_, customers1_.last_name as last_nam3_0_2_, customers1_.portfolio_id as portfoli4_0_2_ from portfolio portfolio0_ left outer join customer customers1_ on portfolio0_.id=customers1_.portfolio_id where portfolio0_.id=?
: binding parameter [1] as [INTEGER] - [6]
: update customer set first_name=?, last_name=?, portfolio_id=? where id=?
: binding parameter [1] as [VARCHAR] - [Jack]
: binding parameter [2] as [VARCHAR] - [Black]
: binding parameter [3] as [INTEGER] - [6]
: binding parameter [4] as [BIGINT] - [1]
: Customer[id=1, firstName='Jack', lastName='Black', portfolio='Portfolio{id=6, name='24', customer count=0}']
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_ from customer customer0_ where customer0_.id=?
: binding parameter [1] as [BIGINT] - [2]
: select portfolio0_.id as id1_1_0_, portfolio0_.name as name2_1_0_, customers1_.portfolio_id as portfoli4_0_1_, customers1_.id as id1_0_1_, customers1_.id as id1_0_2_, customers1_.first_name as first_na2_0_2_, customers1_.last_name as last_nam3_0_2_, customers1_.portfolio_id as portfoli4_0_2_ from portfolio portfolio0_ left outer join customer customers1_ on portfolio0_.id=customers1_.portfolio_id where portfolio0_.id=?
: binding parameter [1] as [INTEGER] - [6]
: update customer set first_name=?, last_name=?, portfolio_id=? where id=?
: binding parameter [1] as [VARCHAR] - [Chloe]
: binding parameter [2] as [VARCHAR] - [O'Brian]
: binding parameter [3] as [INTEGER] - [6]
: binding parameter [4] as [BIGINT] - [2]
: Customer[id=2, firstName='Chloe', lastName='O'Brian', portfolio='Portfolio{id=6, name='24', customer count=0}']
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_ from customer customer0_ where customer0_.id=?
: binding parameter [1] as [BIGINT] - [3]
: select portfolio0_.id as id1_1_0_, portfolio0_.name as name2_1_0_, customers1_.portfolio_id as portfoli4_0_1_, customers1_.id as id1_0_1_, customers1_.id as id1_0_2_, customers1_.first_name as first_na2_0_2_, customers1_.last_name as last_nam3_0_2_, customers1_.portfolio_id as portfoli4_0_2_ from portfolio portfolio0_ left outer join customer customers1_ on portfolio0_.id=customers1_.portfolio_id where portfolio0_.id=?
: binding parameter [1] as [INTEGER] - [6]
: update customer set first_name=?, last_name=?, portfolio_id=? where id=?
: binding parameter [1] as [VARCHAR] - [Kim]
: binding parameter [2] as [VARCHAR] - [Bauer]
: binding parameter [3] as [INTEGER] - [6]
: binding parameter [4] as [BIGINT] - [3]
: Customer[id=3, firstName='Kim', lastName='Bauer', portfolio='Portfolio{id=6, name='24', customer count=0}']
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_ from customer customer0_ where customer0_.id=?
: binding parameter [1] as [BIGINT] - [4]
: select portfolio0_.id as id1_1_0_, portfolio0_.name as name2_1_0_, customers1_.portfolio_id as portfoli4_0_1_, customers1_.id as id1_0_1_, customers1_.id as id1_0_2_, customers1_.first_name as first_na2_0_2_, customers1_.last_name as last_nam3_0_2_, customers1_.portfolio_id as portfoli4_0_2_ from portfolio portfolio0_ left outer join customer customers1_ on portfolio0_.id=customers1_.portfolio_id where portfolio0_.id=?
: binding parameter [1] as [INTEGER] - [6]
: update customer set first_name=?, last_name=?, portfolio_id=? where id=?
: binding parameter [1] as [VARCHAR] - [David]
: binding parameter [2] as [VARCHAR] - [Palmer]
: binding parameter [3] as [INTEGER] - [6]
: binding parameter [4] as [BIGINT] - [4]
: Customer[id=4, firstName='David', lastName='Palmer', portfolio='Portfolio{id=6, name='24', customer count=0}']
: select customer0_.id as id1_0_0_, customer0_.first_name as first_na2_0_0_, customer0_.last_name as last_nam3_0_0_, customer0_.portfolio_id as portfoli4_0_0_ from customer customer0_ where customer0_.id=?
: binding parameter [1] as [BIGINT] - [5]
: select portfolio0_.id as id1_1_0_, portfolio0_.name as name2_1_0_, customers1_.portfolio_id as portfoli4_0_1_, customers1_.id as id1_0_1_, customers1_.id as id1_0_2_, customers1_.first_name as first_na2_0_2_, customers1_.last_name as last_nam3_0_2_, customers1_.portfolio_id as portfoli4_0_2_ from portfolio portfolio0_ left outer join customer customers1_ on portfolio0_.id=customers1_.portfolio_id where portfolio0_.id=?
: binding parameter [1] as [INTEGER] - [6]
: update customer set first_name=?, last_name=?, portfolio_id=? where id=?
: binding parameter [1] as [VARCHAR] - [Michelle]
: binding parameter [2] as [VARCHAR] - [Dessler]
: binding parameter [3] as [INTEGER] - [6]
: binding parameter [4] as [BIGINT] - [5]
: Customer[id=5, firstName='Michelle', lastName='Dessler', portfolio='Portfolio{id=6, name='24', customer count=0}']
: Is the portfolio updated?
: --------------------------------------------
: Portfolio{id=6, name='24', customer count=0}
: 
: Fetch the portfolio
: --------------------------------------------
: select portfolio0_.id as id1_1_, portfolio0_.name as name2_1_ from portfolio portfolio0_ where portfolio0_.name=?
: binding parameter [1] as [VARCHAR] - [24]
: select customers0_.portfolio_id as portfoli4_0_0_, customers0_.id as id1_0_0_, customers0_.id as id1_0_1_, customers0_.first_name as first_na2_0_1_, customers0_.last_name as last_nam3_0_1_, customers0_.portfolio_id as portfoli4_0_1_ from customer customers0_ where customers0_.portfolio_id=?
: binding parameter [1] as [INTEGER] - [6]
: Portfolio{id=6, name='24', customer count=5}
: 
: Portfolio's customers
: Customer[id=1, firstName='Jack', lastName='Black', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=2, firstName='Chloe', lastName='O'Brian', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=3, firstName='Kim', lastName='Bauer', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=4, firstName='David', lastName='Palmer', portfolio='Portfolio{id=6, name='24', customer count=5}']
: Customer[id=5, firstName='Michelle', lastName='Dessler', portfolio='Portfolio{id=6, name='24', customer count=5}']
```

### Conclusion
Introducing a `@ManyToOne` and `@OneToMany(fetch = FetchType.EAGER)` didn't introduce an n+1 query.


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