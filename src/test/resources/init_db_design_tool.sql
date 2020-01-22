create database if not exists db_design_tool;
use db_design_tool;

CREATE TABLE IF NOT EXISTS table_master (
    table_id INT AUTO_INCREMENT,
    physical_name VARCHAR(100) UNIQUE NOT NULL,
    logical_name VARCHAR(100) UNIQUE NOT NULL,
    delete_flag INT NOT NULL,
    PRIMARY KEY (table_id)
);

CREATE TABLE IF NOT EXISTS field_master (
    table_id INT NOT NULL,
    field_id INT AUTO_INCREMENT,
    no INT NOT NULL,
    physical_name VARCHAR(100) NOT NULL,
    logical_name VARCHAR(100) NOT NULL,
    data_type VARCHAR(10) NOT NULL,
    data_size INT,
    description VARCHAR(255),
    delete_flag INT NOT NULL,
    PRIMARY KEY (field_id)
);

-- grant all privileges on db_design_tool.* to user@localhost;