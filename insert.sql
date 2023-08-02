drop schema if exists quanvanstore;
create schema quanvanstore;
use quanvanstore;

drop table if exists role;
create table role(
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    name varchar(255) not null,
    created_date timestamp null,
    created_by bigint null,
    modified_date timestamp null,
    modified_by bigint null,
    deleted bool null
);

drop table if exists user;
create table user (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    username varchar(255) not null unique,
	password varchar(255) not null,
	fullname varchar(255) not null,
	email varchar(255) not null unique,
    phone varchar(255) null,
    date_of_birth date null,
	role_id bigint null,
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table user add constraint fk_user_role foreign key(role_id) references role(id);

drop table if exists category;
create table category (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    name varchar(255) not null unique,
    parent_id bigint null,
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table category add constraint fk_category_parent_category foreign key(parent_id)
references category(id);

drop table if exists product;
create table product (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    name varchar(255) not null,
    old_price int not null,
    new_price int not null,
    category_id bigint not null,
    description text null,
    thumbnail varchar(255) null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table product add constraint fk_product_category foreign key(category_id) references category(id);

drop table if exists product_media;
create table product_media (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    type varchar(255) null,
    url varchar(255) null,
	product_id bigint not null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table product_media add constraint fk_product_media_product foreign key(product_id)
references product(id);

drop table if exists product_property;
create table product_property (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    name varchar(255) null,
    value varchar(255) null,
	product_id bigint not null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table product_property add constraint fk_product_property_product foreign key(product_id)
references product(id);


drop table if exists product_price;
create table product_price (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    price int,
	product_id bigint not null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table product_price add constraint fk_product_price_product foreign key(product_id)
references product(id);

drop table if exists orders;
create table orders (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    user_id bigint not null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

drop table if exists order_item;
create table order_item (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    quantity int null,
    price int null,
    user_id bigint null,
    product_id bigint not null,
    order_id bigint not null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table order_item add constraint fk_order_item_user foreign key(user_id)
references user(id);

alter table order_item add constraint fk_order_item_product foreign key(product_id)
references product(id);

alter table order_item add constraint fk_order_item_orders foreign key(order_id)
references orders(id);

drop table if exists rating;
create table rating (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    rate int null,
    content varchar(255) null,
	product_id bigint not null,
    user_id bigint not null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table rating add constraint fk_rating_user foreign key(user_id)
references user(id);

alter table rating add constraint fk_rating_product foreign key(product_id)
references product(id);

drop table if exists cart;
create table cart (
	id bigint not null primary key auto_increment,
	code varchar(255) not null,
    user_id bigint not null,
    product_id bigint not null,
    quantity int null,
    
	created_date timestamp null,
	created_by bigint null,
	modified_date timestamp null,
	modified_by bigint null,
	deleted bool null
);

alter table cart add constraint fk_cart_user foreign key(user_id)
references user(id);

alter table cart add constraint fk_cart_product foreign key(product_id)
references product(id);




