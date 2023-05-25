create table customer (customerId integer not null, accountNumber varchar(255), name varchar(255),
                       primary key (customerId)) engine=InnoDB;
create table customer_seq (next_val bigint) engine=InnoDB;
insert into customer_seq values ( 1 );