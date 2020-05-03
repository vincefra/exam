# Exam

Contains a server, client and database. The server is written in Java SpringBoot while client is Html & JavaScript. 

Both client and server is sharing the same tomcat-server. Database is right now using Hibernate as it is easier to
develop but the goal is to setup PostgresSQL with flyway to execute and version management.

## Setup

To run this project, follow the steps below.

1. Clone the project by using git clone and link to this repo.
2. Download NetBeans or whatever that suits you and can open Java SpringBoot project.
3. Your IDE will problably report about missing depencies, let your IDE download these.
4. Run the project and enjoy!

## Good to know

DbInit contains the information of the database, you can either register your own account or use one from that file.

If the project still runs on Hibernate, every time you restart the project all the changes in the database will be
gone, it will go back to standard again!
