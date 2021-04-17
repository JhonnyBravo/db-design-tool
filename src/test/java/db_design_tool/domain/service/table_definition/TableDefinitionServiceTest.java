package db_design_tool.domain.service.table_definition;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import db_design_tool.app.table_definition.TableDefinitionTestHelper;
import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;

@RunWith(Enclosed.class)
public class TableDefinitionServiceTest {
    public static class テーブル定義が存在しない場合 {
        private TableDefinitionService service;
        private TableDefinitionTestHelper helper;

        @Before
        public void setUp() throws Exception {
            helper = new TableDefinitionTestHelper();
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
            final TableDefinition expectDefinition = new TableDefinition();

            // TableMaster
            final String[] params1 = { "table1", "テーブル1" };
            final TableMaster expectTable = helper.createTableMaster(params1);
            expectDefinition.setTableMaster(expectTable);

            // FieldMaster
            final List<FieldMaster> expectFieldList = new ArrayList<>();

            final String[] params2 = { "0", "1", "table1.field1", "テーブル1.フィールド1", "1 つ目のフィールド" };
            final FieldMaster expectField1 = helper.createFieldMaster(params2);
            expectFieldList.add(expectField1);

            final String[] params3 = { "0", "2", "table1.field2", "テーブル1.フィールド2", "2 つ目のフィールド" };
            final FieldMaster expectField2 = helper.createFieldMaster(params3);
            expectFieldList.add(expectField2);

            expectDefinition.setFieldMaster(expectFieldList.toArray(new FieldMaster[expectFieldList.size()]));

            service.create(expectDefinition);

            // Assertion
            final TableDefinition actualDefinition = service.findTableDefinitionByTableId(1);

            final TableMaster actualTable = actualDefinition.getTableMaster();
            assertThat(actualTable.getTableId(), is(1));
            assertThat(actualTable.getEntityType(), is(1));
            assertThat(actualTable.getPhysicalName(), is(expectTable.getPhysicalName()));
            assertThat(actualTable.getLogicalName(), is(expectTable.getLogicalName()));
            assertThat(actualTable.getDeleteFlag(), is(expectTable.getDeleteFlag()));

            final FieldMaster[] actualFieldArray = actualDefinition.getFieldMaster();

            final FieldMaster actualField1 = actualFieldArray[0];
            assertThat(actualField1.getFieldId(), is(1));
            assertThat(actualField1.getTableId(), is(1));
            assertThat(actualField1.getNo(), is(expectField1.getNo()));
            assertThat(actualField1.getPhysicalName(), is(expectField1.getPhysicalName()));
            assertThat(actualField1.getLogicalName(), is(expectField1.getLogicalName()));
            assertThat(actualField1.getDescription(), is(expectField1.getDescription()));
            assertThat(actualField1.getDeleteFlag(), is(expectField1.getDeleteFlag()));

            final FieldMaster actualField2 = actualFieldArray[1];
            assertThat(actualField2.getFieldId(), is(2));
            assertThat(actualField2.getTableId(), is(1));
            assertThat(actualField2.getNo(), is(expectField2.getNo()));
            assertThat(actualField2.getPhysicalName(), is(expectField2.getPhysicalName()));
            assertThat(actualField2.getLogicalName(), is(expectField2.getLogicalName()));
            assertThat(actualField2.getDescription(), is(expectField2.getDescription()));
            assertThat(actualField2.getDeleteFlag(), is(expectField2.getDeleteFlag()));
        }
    }

    public static class テーブル定義が存在する場合 {
        private TableDefinitionService service;
        private TableDefinitionTestHelper helper;

        @Before
        public void setUp() throws Exception {
            helper = new TableDefinitionTestHelper();
            service = new TableDefinitionServiceImpl();
            service.deleteAll();

            final TableDefinition tableDefinition = new TableDefinition();

            // TableMaster
            final String[] params1 = { "table1", "テーブル1" };
            final TableMaster table1 = helper.createTableMaster(params1);
            tableDefinition.setTableMaster(table1);

            // FieldMaster
            final List<FieldMaster> fieldList = new ArrayList<>();

            final String[] params2 = { "0", "1", "table1.field1", "テーブル1.フィールド1", "1 つ目のフィールド" };
            final FieldMaster field1 = helper.createFieldMaster(params2);
            fieldList.add(field1);

            final String[] params3 = { "0", "2", "table1.field2", "テーブル1.フィールド2", "2 つ目のフィールド" };
            final FieldMaster field2 = helper.createFieldMaster(params3);
            fieldList.add(field2);

            tableDefinition.setFieldMaster(fieldList.toArray(new FieldMaster[fieldList.size()]));

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
            final TableDefinition tableDefinition = new TableDefinition();

            // TableMaster
            final String[] params1 = { "table2", "テーブル2" };
            final TableMaster expectTable = helper.createTableMaster(params1);
            tableDefinition.setTableMaster(expectTable);

            // FieldMaster
            final List<FieldMaster> expectFieldList = new ArrayList<>();

            final String[] params2 = { "0", "1", "table2.field1", "テーブル2.フィールド1", "1 つ目のフィールド" };
            final FieldMaster expectField1 = helper.createFieldMaster(params2);
            expectFieldList.add(expectField1);

            final String[] params3 = { "0", "2", "table2.field2", "テーブル2.field2", "2 つ目のフィールド" };
            final FieldMaster expectField2 = helper.createFieldMaster(params3);
            expectFieldList.add(expectField2);

            tableDefinition.setFieldMaster(expectFieldList.toArray(new FieldMaster[expectFieldList.size()]));

            service.create(tableDefinition);

            // Assertion
            final List<TableMaster> tables = service.findTableAll();
            assertThat(tables.size(), is(2));

            final TableDefinition actualDefinition = service.findTableDefinitionByTableId(2);

            final TableMaster actualTable = actualDefinition.getTableMaster();
            assertThat(actualTable.getTableId(), is(2));
            assertThat(actualTable.getEntityType(), is(1));
            assertThat(actualTable.getPhysicalName(), is(expectTable.getPhysicalName()));
            assertThat(actualTable.getLogicalName(), is(expectTable.getLogicalName()));
            assertThat(actualTable.getDeleteFlag(), is(expectTable.getDeleteFlag()));

            final FieldMaster[] actualFieldArray = actualDefinition.getFieldMaster();

            final FieldMaster actualField1 = actualFieldArray[0];
            assertThat(actualField1.getFieldId(), is(3));
            assertThat(actualField1.getTableId(), is(2));
            assertThat(actualField1.getNo(), is(expectField1.getNo()));
            assertThat(actualField1.getPhysicalName(), is(expectField1.getPhysicalName()));
            assertThat(actualField1.getLogicalName(), is(expectField1.getLogicalName()));
            assertThat(actualField1.getDescription(), is(expectField1.getDescription()));
            assertThat(actualField1.getDeleteFlag(), is(expectField1.getDeleteFlag()));

            final FieldMaster actualField2 = actualFieldArray[1];
            assertThat(actualField2.getFieldId(), is(4));
            assertThat(actualField2.getTableId(), is(2));
            assertThat(actualField2.getNo(), is(expectField2.getNo()));
            assertThat(actualField2.getPhysicalName(), is(expectField2.getPhysicalName()));
            assertThat(actualField2.getLogicalName(), is(expectField2.getLogicalName()));
            assertThat(actualField2.getDescription(), is(expectField2.getDescription()));
            assertThat(actualField2.getDeleteFlag(), is(expectField2.getDeleteFlag()));
        }

        @Test
        public void update実行時にレコードの更新ができること() throws Exception {
            final TableDefinition expectDefinition = service.findTableDefinitionByTableId(1);

            // TableMaster
            final TableMaster expectTable = expectDefinition.getTableMaster();
            expectTable.setPhysicalName("table1_modified");
            expectTable.setLogicalName("テーブル1_更新");

            // FieldMaster
            final FieldMaster[] expectFieldArray = expectDefinition.getFieldMaster();

            final FieldMaster expectField1 = expectFieldArray[0];
            expectField1.setNo(2);
            expectField1.setDeleteFlag(1);
            expectField1.setDescription("廃止");

            final FieldMaster expectField2 = expectFieldArray[1];
            expectField2.setNo(1);
            expectField2.setPhysicalName("table1_modified.field2");
            expectField2.setLogicalName("テーブル1_更新.フィールド2");
            expectField2.setDescription("No とフィールド名を更新");

            service.update(expectDefinition);

            // Assertion
            final TableDefinition actualDefinition = service.findTableDefinitionByTableId(1);

            final TableMaster actualTable = actualDefinition.getTableMaster();
            assertThat(actualTable.getTableId(), is(expectTable.getTableId()));
            assertThat(actualTable.getPhysicalName(), is(expectTable.getPhysicalName()));
            assertThat(actualTable.getLogicalName(), is(expectTable.getLogicalName()));
            assertThat(actualTable.getDeleteFlag(), is(expectTable.getDeleteFlag()));

            final FieldMaster[] actualFieldArray = actualDefinition.getFieldMaster();
            assertThat(actualFieldArray.length, is(1));

            final FieldMaster actualField2 = actualFieldArray[0];
            assertThat(actualField2.getFieldId(), is(expectField2.getFieldId()));
            assertThat(actualField2.getNo(), is(expectField2.getNo()));
            assertThat(actualField2.getPhysicalName(), is(expectField2.getPhysicalName()));
            assertThat(actualField2.getLogicalName(), is(expectField2.getLogicalName()));
            assertThat(actualField2.getDescription(), is(expectField2.getDescription()));
        }
    }
}
