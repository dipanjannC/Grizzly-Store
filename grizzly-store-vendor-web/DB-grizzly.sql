create database grizzly_storeDB;

use grizzly_storeDB;

create table user_details(user_name varchar(30) not null ,
							user_password varchar(30) not null,
							user_role varchar(30) not null,
                            user_status varchar(30) default 'active');


insert into user_details(user_name,user_password,user_role) values ('dipanjan','paradox','admin');

insert into user_details(user_name,user_password,user_role) values ('paulo','alchemist','admin');

insert into user_details(user_name,user_password,user_role) values ('conan','sherlocked','admin');

insert into user_details(user_name,user_password,user_role) values ('google','firebase','vendor');

insert into user_details(user_name,user_password,user_role) values ('facebook','cassandra','vendor');

insert into user_details(user_name,user_password,user_role) values ('amazon','dynamo','vendor');

select * from user_details;

create table product_details(product_id int not null auto_increment primary key,
							product_name varchar(255) not null,
                            product_brand varchar(255) not null,
                            product_category varchar(255) not null,
                            product_description varchar(255) default null,
                            product_rating double,
                            product_price double not null);

alter table product_details auto_increment=10001;



insert into product_details(product_name ,product_brand,product_category,product_description,product_rating,
							product_price) values ('all stars',
                            'addidas','footware','Felxible Comfortable Overpriced Shoes',5.0,7500);
insert into product_details(product_name ,product_brand,product_category,product_description,product_rating,
							product_price) values ('Polo',
                            'levis','tshirt','Standard Branded Smart Casuals',4.7,1500);
                            
insert into product_details(product_name ,product_brand,product_category,product_description,product_rating,
							product_price) values ('Trimmer',
                            'Philips','Personal Care','Electronic Appliance with 2 years of warranty',4.9,1300);

insert into product_details(product_name ,product_brand,product_category,product_description,product_rating,
							product_price) values ('Macbook Pro',
                            'Apple','Gadgets','Overpriced BenchMark Laptops by Apple ',4.9,150000);

update user_details set user_status='active' where user_name='dipanjan';
update user_details set user_status='active' where user_status='inactive';

select * from product_details;


create table inventory(product_id int  ,inventory_buffer int default 0,inventory_stock int default 0,foreign key(product_id) references product_details(product_id) on delete cascade);



insert into inventory(product_id,
						inventory_buffer,
						inventory_stock) values (10001,7000,200);


insert into inventory(product_id,
						inventory_buffer,
						inventory_stock) values (10002,1567,168);


insert into inventory(product_id,
						inventory_buffer,
						inventory_stock) values (10003,400,207);


insert into inventory(product_id,
						inventory_buffer,
						inventory_stock) values (10004,160,140);

select * from inventory;



select product_details.product_id,
	   product_details.product_name,
		product_details.product_brand,
        product_details.product_category,
        product_details.product_rating,
        product_details.product_price,
		inventory.inventory_stock,
        inventory.inventory_buffer from inventory 
        join product_details on product_details.product_id=inventory.product_id;

select * from product_details;


 
