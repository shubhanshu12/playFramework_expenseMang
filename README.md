# playFramework_expenseMang

This is a basic expense management system built using the play framework. It has capabilitites like CRUD operations over clinet and expense. 

Tech Stack:
1. Play framework using JAVA
2. Hibernate as ORM
3. Postgres DB

Pre-requisite:
1. Install SBT
2. Install PostgresDB

Import project to eclipse:
1. Inside /playFramework_expenseMang/expensetracking, run sbt eclipse command
2. Import the project in eclipse, Import->Exisiting projects into workspace

Build project:
1. Inside /playFramework_expenseMang/expensetracking, run sbt compile command

Run project
1. Configure your db settings in conf/application.conf
2. Inside /playFramework_expenseMang/expensetracking, run sbt run command

After database connection is established hibernate will generate schema automatically.
