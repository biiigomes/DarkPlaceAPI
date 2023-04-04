CREATE TABLE tb_blog_writers (
    writer_id bigint primary key auto_increment,
    name varchar(100) not null,
    fullname varchar(100) not null,
    username varchar(100) not null,
    email varchar(100)
);

CREATE TABLE tb_cases_without_solution (
    cases_without_solution_id bigint primary key auto_increment,
    title varchar(50) not null,
    history varchar(5000) not null,
    writer_id bigint not null,
    foreign key (writer_id) references tb_blog_writers(writer_id)
);

CREATE TABLE tb_legends (
    tb_legends bigint primary ket auto_increment,
    title varchar(50) not null,
    history varchar(5000) not null,
    writer_id bigint not null,
    foreign key (writer_id) references tb_blog_writers(writer_id)
);