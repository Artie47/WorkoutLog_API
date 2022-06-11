CREATE TABLE IF NOT EXISTS sportkinds(
    id int(11) primary key auto_increment,
    name varchar(50)
);

CREATE TABLE IF NOT EXISTS trainers(
    id int(11) primary key auto_increment,
    name varchar(100),
    email varchar(100),
    password varchar(50),
    sportkind_id int(11),
    foreign key (sportkind_id) references sportkinds (id)
);

CREATE TABLE IF NOT EXISTS groups(
    id int(11) primary key auto_increment,
    name varchar(50),
    trainer_id int(11),
    foreign key (trainer_id) references trainers (id)
);

CREATE TABLE IF NOT EXISTS users(
    id int(11) primary key auto_increment,
    name varchar(100),
    email varchar(100),
    password varchar(50),
    date_of_reg datetime,
    group_id int(11),
    foreign key (group_id) references groups (id)
);

CREATE TABLE IF NOT EXISTS schedule(
    id int(11) primary key auto_increment,
    date_of_start datetime,
    date_of_finish datetime,
    group_id int(11),
    trainer_id int(11),
    sportkind_id int(11),
    foreign key (group_id) references groups (id),
    foreign key (trainer_id) references trainers (id),
    foreign key (sportkind_id) references sportkinds (id)
);