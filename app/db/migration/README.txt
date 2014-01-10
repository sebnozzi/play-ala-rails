
Java/Scala based migrations should NOT use ActiveRecord, 
but instead use JDBC directly (possibly using ScalikeJdbc).

The problem is that ActiveRecord requires that tables exist.

But FlyWay should be the one creating the tables in production.
