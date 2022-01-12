create table PRICES (
    id int not null auto_increment primary key,
    brand_id int not null,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    price_list int not null,
    product_id int not null,
    priority int not null,
    price DECIMAL(20,2) not null,
    curr varchar(10) not null,
    foreign key (brand_id) references brands(id)
);