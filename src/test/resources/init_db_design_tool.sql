create database if not exists db_design_tool;
use db_design_tool;

CREATE TABLE IF NOT EXISTS table_master (
    table_id INT AUTO_INCREMENT,
    physical_name VARCHAR(100) UNIQUE NOT NULL,
    logical_name VARCHAR(100) UNIQUE NOT NULL,
    delete_flag INT NOT NULL,
    PRIMARY KEY (table_id)
);

--grant all privileges on db_design_tool.* to user@localhost;