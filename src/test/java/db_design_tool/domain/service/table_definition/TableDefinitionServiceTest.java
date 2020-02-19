package db_design_tool.domain.service.table_definition;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;

@RunWith(Enclosed.class)
public class TableDefinitionServiceTest {
    public static class テーブルが空である場合 {
        private TableDefinitionService service;

        @Before
        public void setUp() throws Exception {
            service = new TableDefinitionServiceImpl();
            service.deleteAll();
        }

        @After
        public void tearDown() throws Exception {
            service.deleteAll();
        }

        @Test
        public void findTableAll実行時に空のリストが返されること() throws Exception {
            final List<TableMaster> result = service.findTableAll();
            assertThat(result.size(), is(0));
        }

        @Test
        public void create実行時にレコードの登録ができること() throws Exception {
            final TableMaster tableMaster = new TableMaster();
            tableMaster.setPhysicalName("table1");
            tableMaster.setLogicalName("テーブル1");
            tableMaster.setDeleteFlag(0);

            final List<FieldMaster> fieldMasterList = new ArrayList<>();
            {
                final FieldMaster fieldMaster = new FieldMaster();

                fieldMaster.setNo(1);
                fieldMaster.setPhysicalName("field1");
                fieldMaster.setLogicalName("フィールド1");
                fieldMaster.setDeleteFlag(0);
                fieldMaster.setDataType("String");
                fieldMaster.setDataSize(10);
                fieldMaster.setDescription("1つ目のフィールド");

                fieldMasterList.add(fieldMaster);
            }
            {
                final FieldMaster fieldMaster = new FieldMaster();

                fieldMaster.setNo(2);
                fieldMaster.setPhysicalName("field2");
                fieldMaster.setLogicalName("フィールド2");
                fieldMaster.setDeleteFlag(0);
                fieldMaster.setDataType("Integer");
                fieldMaster.setDescription("2つ目のフィールド");

                fieldMasterList.add(fieldMaster);
            }

            final TableDefinition tableDefinition = new TableDefinition();
            tableDefinition.setTableMaster(tableMaster);
            tableDefinition.setFieldMaster(fieldMasterList
                    .toArray(new FieldMaster[fieldMasterList.size()]));

            service.create(tableDefinition);

            final TableDefinition definition1 = service
                    .findTableDefinitionByTableId(1);

            final TableMaster table1 = definition1.getTableMaster();
            assertThat(table1.getTableId(), is(1));
            assertThat(table1.getPhysicalName(), is("table1"));
            assertThat(table1.getLogicalName(), is("テーブル1"));
            assertThat(table1.getDeleteFlag(), is(0));

            final FieldMaster[] fields = definition1.getFieldMaster();

            final FieldMaster field1 = fields[0];
            assertThat(field1.getNo(), is(1));
            assertThat(field1.getFieldId(), is(1));
            assertThat(field1.getTableId(), is(1));
            assertThat(field1.getDeleteFlag(), is(0));
            assertThat(field1.getPhysicalName(), is("field1"));
            assertThat(field1.getLogicalName(), is("フィールド1"));
            assertThat(field1.getDataType(), is("String"));
            assertThat(field1.getDataSize(), is(10));
            assertThat(field1.getDescription(), is("1つ目のフィールド"));

            final FieldMaster field2 = fields[1];
            assertThat(field2.getNo(), is(2));
            assertThat(field2.getFieldId(), is(2));
            assertThat(field2.getTableId(), is(1));
            assertThat(field2.getDeleteFlag(), is(0));
            assertThat(field2.getPhysicalName(), is("field2"));
            assertThat(field2.getLogicalName(), is("フィールド2"));
            assertThat(field2.getDataType(), is("Integer"));
            assertThat(field2.getDescription(), is("2つ目のフィールド"));
        }
    }

    public static class テーブルが空ではない場合 {
        private TableDefinitionService service;

        @Before
        public void setUp() throws Exception {
            service = new TableDefinitionServiceImpl();
            service.deleteAll();

            final TableMaster tableMaster = new TableMaster();
            tableMaster.setPhysicalName("table1");
            tableMaster.setLogicalName("テーブル1");
            tableMaster.setDeleteFlag(0);

            final List<FieldMaster> fieldMasterList = new ArrayList<>();
            {
                final FieldMaster fieldMaster = new FieldMaster();

                fieldMaster.setNo(1);
                fieldMaster.setPhysicalName("field1");
                fieldMaster.setLogicalName("フィールド1");
                fieldMaster.setDeleteFlag(0);
                fieldMaster.setDataType("String");
                fieldMaster.setDataSize(10);
                fieldMaster.setDescription("1つ目のフィールド");

                fieldMasterList.add(fieldMaster);
            }
            {
                final FieldMaster fieldMaster = new FieldMaster();

                fieldMaster.setNo(2);
                fieldMaster.setPhysicalName("field2");
                fieldMaster.setLogicalName("フィールド2");
                fieldMaster.setDeleteFlag(0);
                fieldMaster.setDataType("Integer");
                fieldMaster.setDescription("2つ目のフィールド");

                fieldMasterList.add(fieldMaster);
            }

            final TableDefinition tableDefinition = new TableDefinition();
            tableDefinition.setTableMaster(tableMaster);
            tableDefinition.setFieldMaster(fieldMasterList
                    .toArray(new FieldMaster[fieldMasterList.size()]));

            service.create(tableDefinition);
        }

        @After
        public void tearDown() throws Exception {
            service.deleteAll();
        }

        @Test
        public void findTableAll実行時に1件のレコードが返されること() throws Exception {
            final List<TableMaster> result = service.findTableAll();
            assertThat(result.size(), is(1));
        }

        @Test
        public void create実行時にレコードの登録ができること() throws Exception {
            final TableMaster tableMaster = new TableMaster();
            tableMaster.setPhysicalName("table2");
            tableMaster.setLogicalName("テーブル2");
            tableMaster.setDeleteFlag(0);

            final List<FieldMaster> fieldMasterList = new ArrayList<>();
            {
                final FieldMaster fieldMaster = new FieldMaster();

                fieldMaster.setNo(1);
                fieldMaster.setPhysicalName("field1");
                fieldMaster.setLogicalName("フィールド1");
                fieldMaster.setDeleteFlag(0);
                fieldMaster.setDataType("String");
                fieldMaster.setDataSize(10);
                fieldMaster.setDescription("1つ目のフィールド");

                fieldMasterList.add(fieldMaster);
            }
            {
                final FieldMaster fieldMaster = new FieldMaster();

                fieldMaster.setNo(2);
                fieldMaster.setPhysicalName("field2");
                fieldMaster.setLogicalName("フィールド2");
                fieldMaster.setDeleteFlag(0);
                fieldMaster.setDataType("Integer");
                fieldMaster.setDescription("2つ目のフィールド");

                fieldMasterList.add(fieldMaster);
            }

            final TableDefinition tableDefinition = new TableDefinition();
            tableDefinition.setTableMaster(tableMaster);
            tableDefinition.setFieldMaster(fieldMasterList
                    .toArray(new FieldMaster[fieldMasterList.size()]));

            service.create(tableDefinition);

            final List<TableMaster> tables = service.findTableAll();
            assertThat(tables.size(), is(2));

            final TableDefinition definition2 = service
                    .findTableDefinitionByTableId(2);

            final TableMaster table2 = definition2.getTableMaster();
            assertThat(table2.getTableId(), is(2));
            assertThat(table2.getPhysicalName(), is("table2"));
            assertThat(table2.getLogicalName(), is("テーブル2"));
            assertThat(table2.getDeleteFlag(), is(0));

            final FieldMaster[] fields = definition2.getFieldMaster();

            final FieldMaster field1 = fields[0];
            assertThat(field1.getNo(), is(1));
            assertThat(field1.getFieldId(), is(3));
            assertThat(field1.getTableId(), is(2));
            assertThat(field1.getDeleteFlag(), is(0));
            assertThat(field1.getPhysicalName(), is("field1"));
            assertThat(field1.getLogicalName(), is("フィールド1"));
            assertThat(field1.getDataType(), is("String"));
            assertThat(field1.getDataSize(), is(10));
            assertThat(field1.getDescription(), is("1つ目のフィールド"));

            final FieldMaster field2 = fields[1];
            assertThat(field2.getNo(), is(2));
            assertThat(field2.getFieldId(), is(4));
            assertThat(field2.getTableId(), is(2));
            assertThat(field2.getDeleteFlag(), is(0));
            assertThat(field2.getPhysicalName(), is("field2"));
            assertThat(field2.getLogicalName(), is("フィールド2"));
            assertThat(field2.getDataType(), is("Integer"));
            assertThat(field2.getDescription(), is("2つ目のフィールド"));
        }

        @Test
        public void update実行時にレコードの更新ができること() throws Exception {
            final TableDefinition definition1 = service
                    .findTableDefinitionByTableId(1);

            final TableMaster table1 = definition1.getTableMaster();
            table1.setPhysicalName("tblModified");
            table1.setLogicalName("更新テーブル");
            table1.setDeleteFlag(1);

            final FieldMaster[] fields = definition1.getFieldMaster();
            final FieldMaster field1 = fields[0];
            field1.setNo(2);
            field1.setDeleteFlag(1);
            field1.setDescription("廃止");

            final FieldMaster field2 = fields[1];
            field2.setNo(1);

            service.update(definition1);

            final TableDefinition curDefinition = service
                    .findTableDefinitionByTableId(1);
            final TableMaster curTable = curDefinition.getTableMaster();
            assertThat(curTable.getPhysicalName(), is("tblModified"));
            assertThat(curTable.getLogicalName(), is("更新テーブル"));
            assertThat(curTable.getDeleteFlag(), is(1));

            final FieldMaster[] curFields = curDefinition.getFieldMaster();
            final FieldMaster curField1 = curFields[0];
            assertThat(curField1.getNo(), is(1));
            assertThat(curField1.getPhysicalName(), is("field2"));
            assertThat(curField1.getLogicalName(), is("フィールド2"));
        }
    }

}
