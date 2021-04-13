use db_design_tool;

CREATE TABLE IF NOT EXISTS data_type_master (
    id INT AUTO_INCREMENT,
    name VARCHAR(10),
    PRIMARY KEY (id)
);

insert into data_type_master(name)values("INT");
insert into data_type_master(name)values("DOUBLE");
insert into data_type_master(name)values("VARCHAR");
insert into data_type_master(name)values("DATETIME");
insert into data_type_master(name)values("BOOL");

CREATE TABLE IF NOT EXISTS table_master (
    table_id INT AUTO_INCREMENT,
    physical_name VARCHAR(100) UNIQUE NOT NULL,
    logical_name VARCHAR(100) UNIQUE NOT NULL,
    delete_flag INT NOT NULL,
    entity_type INT NOT NULL,
    PRIMARY KEY (table_id)
);

CREATE TABLE IF NOT EXISTS field_master (
    table_id INT NOT NULL,
    field_id INT AUTO_INCREMENT,
    no INT NOT NULL,
    physical_name VARCHAR(100) NOT NULL,
    logical_name VARCHAR(100) NOT NULL,
    data_type INT,
    data_size INT,
    description VARCHAR(255),
    delete_flag INT NOT NULL,
    PRIMARY KEY (field_id),
    INDEX (table_id , data_type),
    FOREIGN KEY (table_id)
        REFERENCES table_master (table_id)
        ON DELETE CASCADE,
    FOREIGN KEY (data_type)
        REFERENCES data_type_master (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS table_source_definition (
    table_id INT NOT NULL,
    definition_id INT AUTO_INCREMENT,
    source_id INT NOT NULL,
    no INT NOT NULL,
    join_condition VARCHAR(255),
    delete_flag INT NOT NULL,
    PRIMARY KEY (definition_id),
    INDEX (table_id),
    FOREIGN KEY (table_id)
        REFERENCES table_master (table_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS field_source_definition (
    field_id INT NOT NULL,
    definition_id INT AUTO_INCREMENT,
    source_definition VARCHAR(255),
    PRIMARY KEY (definition_id),
    INDEX (field_id),
    FOREIGN KEY (field_id)
        REFERENCES field_master (field_id)
        ON DELETE CASCADE
);
