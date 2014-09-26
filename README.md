##Sample Java EE 7 Project with a generic DAO implementation

Read the blog post:
- http://rodrigouchoa.wordpress.com/2014/09/26/generic-dao-example/ (english version)
- http://rodrigouchoa.wordpress.com/2014/09/26/exemplo-dao-generico/ (versão em português)

This is just some code you can play with. I've tested this against JBoss WildFly 8.1, but
it should run in any Java EE 7 compliant server with minor maven libs adjusments.

The application comes with its own embedded H2 database, which is started up and shutdown
automatically. Each time you run the app, the database is initialized from scratch.

To get this running you need:
- JDK 8
- JBoss WildFly 8.1.0
- An IDE of your choice (or none)
- If you intend to use a server different than JBoss 8.1 (WildFly), 
  please remember you probably you'll need to register the H2 Database JDBC Driver.
- Remember to use **UTF-8 encoding**!
- Deploy the app, run http://localhost:8080/generic-dao and you should get the main page that
contains a simple button to trigger the tests. Browse the code to get a feeling for what it does.


*Please note that running JDK 8 and JBoss WildFly in Eclipse might need some tweaks.*

