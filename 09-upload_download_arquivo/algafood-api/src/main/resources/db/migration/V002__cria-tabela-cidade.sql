CREATE TABLE tb_city(
	id BIGINT NOT NULL AUTO_INCREMENT,
    name_city VARCHAR(80) NOT NULL,
    name_state VARCHAR(80) NOT NULL,
    
    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;