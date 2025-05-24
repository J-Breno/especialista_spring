CREATE TABLE tb_state(
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    
    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;

SELECT distinct name_state FROM tb_city;

ALTER TABLE tb_city ADD COLUMN state_id BIGINT NOT NULL;

ALTER TABLE tb_city ADD CONSTRAINT fk_city_state FOREIGN KEY(state_id) REFERENCES tb_state(id);

ALTER TABLE tb_city DROP COLUMN name_state;

ALTER TABLE tb_city CHANGE name_city name VARCHAR(80) NOT NULL;