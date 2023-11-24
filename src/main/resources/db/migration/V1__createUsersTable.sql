create table users (
     id bigint not null auto_increment,
     login varchar(100) not null,
     senha varchar(255) not null,
     matricula INT,
     tipo varchar(100) not null,


     primary key (id)
 )