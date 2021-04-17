package db_design_tool.domain.service.select_definition;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import db_design_tool.app.select_definition.SelectDefinitionTestHelper;
import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.SelectDefinition;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.model.TableSourceDefinition;
import db_design_tool.domain.service.table_definition.TableDefinitionService;
import db_design_tool.domain.service.table_definition.TableDefinitionServiceImpl;

@RunWith(Enclosed.class)
public class SelectDefinitionServiceTest {
    public static class クエリ定義が存在しない場合 {
        private static SelectDefinitionTestHelper helper;
        private static TableDefinitionService tableDefinitionService;
        private SelectDefinitionService selectDefinitionService;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            helper = new SelectDefinitionTestHelper();
            tableDefinitionService = new TableDefinitionServiceImpl();
            tableDefinitionService.deleteAll();

            // ベーステーブル
            final TableDefinition baseDefinition = new TableDefinition();

            final String[] params1 = { "base_table", "ベーステーブル" };
            final TableMaster baseTable = helper.createTableMaster(params1);
            baseDefinition.setTableMaster(baseTable);

            final List<FieldMaster> fieldList1 = new ArrayList<>();

            {
                final String[] params2 = { "0", "1", "base_table.field1", "ベーステーブル.フィールド1", "1 つ目のフィールド" };
                final FieldMaster field1 = helper.createFieldMaster(params2);
                fieldList1.add(field1);

                final String[] params3 = { "0", "2", "base_table.field2", "ベーステーブル.フィールド2", "2 つ目のフィールド" };
                final FieldMaster field2 = helper.createFieldMaster(params3);
                fieldList1.add(field2);
            }

            baseDefinition.setFieldMaster(fieldList1.toArray(new FieldMaster[fieldList1.size()]));

            tableDefinitionService.create(baseDefinition);

            // 結合用テーブル
            final TableDefinition joinDefinition = new TableDefinition();

            final String[] params4 = { "join_table", "結合用テーブル" };
            final TableMaster joinTable = helper.createTableMaster(params4);
            joinDefinition.setTableMaster(joinTable);

            final List<FieldMaster> fieldList2 = new ArrayList<>();

            {
                final String[] params5 = { "0", "1", "join_table.field1", "結合用テーブル.フィールド1", "1 つ目のフィールド" };
                final FieldMaster field1 = helper.createFieldMaster(params5);
                fieldList2.add(field1);

                final String[] params6 = { "0", "2", "join_table.field2", "結合用テーブル.フィールド2", "2 つ目のフィールド" };
                final FieldMaster field2 = helper.createFieldMaster(params6);
                fieldList2.add(field2);
            }

            joinDefinition.setFieldMaster(fieldList2.toArray(new FieldMaster[fieldList2.size()]));

            tableDefinitionService.create(joinDefinition);
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            tableDefinitionService.deleteAll();
        }

        @Before
        public void setUp() throws Exception {
            selectDefinitionService = new SelectDefinitionServiceImpl();
        }

        @After
        public void tearDown() throws Exception {
            tableDefinitionService.deleteByEntityType(2);
        }

        @Test
        public void findQueryAll実行時に空のリストが返されること() throws Exception {
            final List<TableMaster> tableMasterList = selectDefinitionService.findQueryAll();
            assertThat(tableMasterList.size(), is(0));
        }

        @Test
        public void create実行時にレコードの登録ができること() throws Exception {
            final SelectDefinition expectDefinition = new SelectDefinition();

            // クエリ名の定義
            final String[] params1 = { "query1", "クエリ1" };
            final TableMaster expectTable = helper.createTableMaster(params1);
            expectDefinition.setTableMaster(expectTable);

            // 取得元テーブルの定義
            final List<TableSourceDefinition> expectTableSourceList = new ArrayList<>();

            final String[] params2 = { "0", "1", "1", "" };
            final TableSourceDefinition expectTableSource1 = helper.createTableSourceDefinition(params2);
            expectTableSourceList.add(expectTableSource1);

            final String[] params3 = { "0", "2", "2", "base_table.field1 = join_table.field1" };
            final TableSourceDefinition expectTableSource2 = helper.createTableSourceDefinition(params3);
            expectTableSourceList.add(expectTableSource2);

            expectDefinition.setTableSourceDefinition(
                    expectTableSourceList.toArray(new TableSourceDefinition[expectTableSourceList.size()]));

            // フィールド名の定義
            final List<FieldMaster> expectFieldList = new ArrayList<>();

            final String[] params4 = { "0", "1", "query1.field1", "クエリ1.フィールド1", "1 つ目のフィールド" };
            final FieldMaster expectField1 = helper.createFieldMaster(params4);
            expectFieldList.add(expectField1);

            final String[] params5 = { "0", "2", "query1.field2", "クエリ1.フィールド2", "2 つ目のフィールド" };
            final FieldMaster expectField2 = helper.createFieldMaster(params5);
            expectFieldList.add(expectField2);

            expectDefinition.setFieldMaster(expectFieldList.toArray(new FieldMaster[expectFieldList.size()]));

            // 取得元フィールドの定義
            final List<FieldSourceDefinition> expectFieldSourceList = new ArrayList<>();

            final String[] params6 = { "0", "base_table.field1" };
            final FieldSourceDefinition expectFieldSource1 = helper.createFieldSourceDefinition(params6);
            expectFieldSourceList.add(expectFieldSource1);

            final String[] params7 = { "0", "join_table.field2" };
            final FieldSourceDefinition expectFieldSource2 = helper.createFieldSourceDefinition(params7);
            expectFieldSourceList.add(expectFieldSource2);

            expectDefinition.setFieldSourceDefinition(
                    expectFieldSourceList.toArray(new FieldSourceDefinition[expectFieldSourceList.size()]));

            selectDefinitionService.create(expectDefinition);

            // Assertion
            final TableMaster latestQuery = selectDefinitionService.findQueryAll().get(0);
            final SelectDefinition actualDefinition = selectDefinitionService
                    .findSelectDefinitionByTableId(latestQuery.getTableId());

            // クエリ名が登録できること
            final TableMaster actualTable = actualDefinition.getTableMaster();
            assertThat(actualTable.getEntityType(), is(2));
            assertThat(actualTable.getPhysicalName(), is(expectTable.getPhysicalName()));
            assertThat(actualTable.getLogicalName(), is(expectTable.getLogicalName()));

            // クエリのフィールド定義が登録できること
            final FieldMaster[] actualFieldArray = actualDefinition.getFieldMaster();
            assertThat(actualFieldArray.length, is(2));

            final FieldMaster actualField1 = actualFieldArray[0];
            assertThat(actualField1.getNo(), is(expectField1.getNo()));
            assertThat(actualField1.getPhysicalName(), is(expectField1.getPhysicalName()));
            assertThat(actualField1.getLogicalName(), is(expectField1.getLogicalName()));

            final FieldMaster actualField2 = actualFieldArray[1];
            assertThat(actualField2.getNo(), is(expectField2.getNo()));
            assertThat(actualField2.getPhysicalName(), is(expectField2.getPhysicalName()));
            assertThat(actualField2.getLogicalName(), is(expectField2.getLogicalName()));

            // 取得元テーブルの定義が登録できること
            final TableSourceDefinition[] actualTableSourceArray = actualDefinition.getTableSourceDefinition();
            assertThat(actualTableSourceArray.length, is(2));

            final TableSourceDefinition actualTableSource1 = actualTableSourceArray[0];
            assertThat(actualTableSource1.getNo(), is(expectTableSource1.getNo()));
            assertThat(actualTableSource1.getSourceId(), is(expectTableSource1.getSourceId()));
            assertThat(actualTableSource1.getJoinCondition(), is(expectTableSource1.getJoinCondition()));

            final TableSourceDefinition actualTableSource2 = actualTableSourceArray[1];
            assertThat(actualTableSource2.getNo(), is(expectTableSource2.getNo()));
            assertThat(actualTableSource2.getSourceId(), is(expectTableSource2.getSourceId()));
            assertThat(actualTableSource2.getJoinCondition(), is(expectTableSource2.getJoinCondition()));

            // 取得元フィールドの定義が登録できること
            final FieldSourceDefinition[] actualFieldSourceArray = expectDefinition.getFieldSourceDefinition();
            assertThat(actualFieldSourceArray.length, is(2));

            final FieldSourceDefinition actualFieldSource1 = actualFieldSourceArray[0];
            assertThat(actualFieldSource1.getFieldId(), is(actualField1.getFieldId()));
            assertThat(actualFieldSource1.getSourceDefinition(), is(expectFieldSource1.getSourceDefinition()));

            final FieldSourceDefinition actualFieldSource2 = actualFieldSourceArray[1];
            assertThat(actualFieldSource2.getFieldId(), is(actualField2.getFieldId()));
            assertThat(actualFieldSource2.getSourceDefinition(), is(expectFieldSource2.getSourceDefinition()));
        }
    }

    public static class クエリ定義が存在する場合 {
        private static SelectDefinitionTestHelper helper;
        private static TableDefinitionService tableDefinitionService;
        private SelectDefinitionService selectDefinitionService;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            helper = new SelectDefinitionTestHelper();
            tableDefinitionService = new TableDefinitionServiceImpl();
            tableDefinitionService.deleteAll();

            // ベーステーブル
            final TableDefinition baseDefinition = new TableDefinition();

            final String[] params1 = { "base_table", "ベーステーブル" };
            final TableMaster baseTable = helper.createTableMaster(params1);
            baseDefinition.setTableMaster(baseTable);

            final List<FieldMaster> fieldList1 = new ArrayList<>();

            {
                final String[] params2 = { "0", "1", "base_table.field1", "ベーステーブル.フィールド1", "1 つ目のフィールド" };
                final FieldMaster field1 = helper.createFieldMaster(params2);
                fieldList1.add(field1);

                final String[] params3 = { "0", "2", "base_table.field2", "ベーステーブル.フィールド2", "2 つ目のフィールド" };
                final FieldMaster field2 = helper.createFieldMaster(params3);
                fieldList1.add(field2);
            }

            baseDefinition.setFieldMaster(fieldList1.toArray(new FieldMaster[fieldList1.size()]));

            tableDefinitionService.create(baseDefinition);

            // 結合用テーブル
            final TableDefinition joinDefinition = new TableDefinition();

            final String[] params4 = { "join_table", "結合用テーブル" };
            final TableMaster joinTable = helper.createTableMaster(params4);
            joinDefinition.setTableMaster(joinTable);

            final List<FieldMaster> fieldList2 = new ArrayList<>();

            {
                final String[] params5 = { "0", "1", "join_table.field1", "結合用テーブル.フィールド1", "1 つ目のフィールド" };
                final FieldMaster field1 = helper.createFieldMaster(params5);
                fieldList2.add(field1);

                final String[] params6 = { "0", "2", "join_table.field2", "結合用テーブル.フィールド2", "2 つ目のフィールド" };
                final FieldMaster field2 = helper.createFieldMaster(params6);
                fieldList2.add(field2);
            }

            joinDefinition.setFieldMaster(fieldList2.toArray(new FieldMaster[fieldList2.size()]));

            tableDefinitionService.create(joinDefinition);
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            tableDefinitionService.deleteAll();
        }

        @Before
        public void setUp() throws Exception {
            // クエリ定義
            final SelectDefinition selectDefinition = new SelectDefinition();

            // クエリ名の定義
            final String[] params1 = { "query1", "クエリ1" };
            final TableMaster query1 = helper.createTableMaster(params1);
            selectDefinition.setTableMaster(query1);

            // 取得元テーブルの定義
            final List<TableSourceDefinition> tableSourceList = new ArrayList<>();

            final String[] params2 = { "0", "1", "1", "" };
            final TableSourceDefinition tableSource1 = helper.createTableSourceDefinition(params2);
            tableSourceList.add(tableSource1);

            final String[] params3 = { "0", "2", "2", "base_table.field1 = join_table.field1" };
            final TableSourceDefinition tableSource2 = helper.createTableSourceDefinition(params3);
            tableSourceList.add(tableSource2);

            selectDefinition.setTableSourceDefinition(
                    tableSourceList.toArray(new TableSourceDefinition[tableSourceList.size()]));

            // フィールド名の定義
            final List<FieldMaster> fieldList = new ArrayList<>();

            final String[] params4 = { "0", "1", "query1.field1", "クエリ1.フィールド1", "1 つ目のフィールド" };
            final FieldMaster field1 = helper.createFieldMaster(params4);
            fieldList.add(field1);

            final String[] params5 = { "0", "2", "query1.field2", "クエリ1.フィールド2", "2 つ目のフィールド" };
            final FieldMaster field2 = helper.createFieldMaster(params5);
            fieldList.add(field2);

            selectDefinition.setFieldMaster(fieldList.toArray(new FieldMaster[fieldList.size()]));

            // 取得元フィールドの定義
            final List<FieldSourceDefinition> fieldSourceList = new ArrayList<>();

            final String[] params6 = { "0", "base_table.field1" };
            final FieldSourceDefinition fieldSource1 = helper.createFieldSourceDefinition(params6);
            fieldSourceList.add(fieldSource1);

            final String[] params7 = { "0", "join_table.field2" };
            final FieldSourceDefinition fieldSource2 = helper.createFieldSourceDefinition(params7);
            fieldSourceList.add(fieldSource2);

            selectDefinition.setFieldSourceDefinition(
                    fieldSourceList.toArray(new FieldSourceDefinition[fieldSourceList.size()]));

            selectDefinitionService = new SelectDefinitionServiceImpl();
            selectDefinitionService.create(selectDefinition);
        }

        @After
        public void tearDown() throws Exception {
            tableDefinitionService.deleteByEntityType(2);
        }

        @Test
        public void findQueryAll実行時に1件のレコードが返されること() throws Exception {
            final List<TableMaster> tableMasterList = selectDefinitionService.findQueryAll();
            assertThat(tableMasterList.size(), is(1));
        }

        @Test
        public void create実行時にレコードの登録ができること() throws Exception {
            final SelectDefinition expectDefinition = new SelectDefinition();

            // クエリ名の定義
            final String[] params1 = { "query2", "クエリ2" };
            final TableMaster expectTable = helper.createTableMaster(params1);
            expectDefinition.setTableMaster(expectTable);

            // 取得元テーブルの定義
            final List<TableSourceDefinition> expectTableSourceList = new ArrayList<>();

            final String[] params2 = { "0", "1", "1", "" };
            final TableSourceDefinition expectTableSource1 = helper.createTableSourceDefinition(params2);
            expectTableSourceList.add(expectTableSource1);

            expectDefinition.setTableSourceDefinition(
                    expectTableSourceList.toArray(new TableSourceDefinition[expectTableSourceList.size()]));

            // フィールド名の定義
            final List<FieldMaster> expectFieldList = new ArrayList<>();

            final String[] params3 = { "0", "1", "query2.field1", "クエリ2.フィールド1", "1 つ目のフィールド" };
            final FieldMaster expectField1 = helper.createFieldMaster(params3);
            expectFieldList.add(expectField1);

            final String[] params4 = { "0", "2", "query2.field2", "クエリ2.フィールド2", "2 つ目のフィールド" };
            final FieldMaster expectField2 = helper.createFieldMaster(params4);
            expectFieldList.add(expectField2);

            expectDefinition.setFieldMaster(expectFieldList.toArray(new FieldMaster[expectFieldList.size()]));

            // 取得元フィールドの定義
            final List<FieldSourceDefinition> expectFieldSourceList = new ArrayList<>();

            final String[] params5 = { "0", "base_table.field1" };
            final FieldSourceDefinition expectFieldSource1 = helper.createFieldSourceDefinition(params5);
            expectFieldSourceList.add(expectFieldSource1);

            final String[] params6 = { "0", "base_table.field2" };
            final FieldSourceDefinition expectFieldSource2 = helper.createFieldSourceDefinition(params6);
            expectFieldSourceList.add(expectFieldSource2);

            expectDefinition.setFieldSourceDefinition(
                    expectFieldSourceList.toArray(new FieldSourceDefinition[expectFieldSourceList.size()]));

            selectDefinitionService.create(expectDefinition);

            // Assertion
            final List<TableMaster> actualTableList = selectDefinitionService.findQueryAll();
            assertThat(actualTableList.size(), is(2));
        }

        @Test
        public void update実行時にレコードの更新ができること() throws Exception {
            final TableMaster latestQuery = selectDefinitionService.findQueryAll().get(0);

            // 更新前のクエリ定義
            final SelectDefinition expectDefinition = selectDefinitionService
                    .findSelectDefinitionByTableId(latestQuery.getTableId());

            final TableSourceDefinition[] expectTableSourceArray = expectDefinition.getTableSourceDefinition();

            final TableSourceDefinition expectTableSource = expectTableSourceArray[1];
            expectTableSource.setJoinCondition("updated condition");

            final FieldSourceDefinition[] expectFieldSourceArray = expectDefinition.getFieldSourceDefinition();
            final FieldSourceDefinition expectFieldSource = expectFieldSourceArray[1];
            expectFieldSource.setSourceDefinition("updated field source");

            selectDefinitionService.update(expectDefinition);

            // 更新後のクエリ定義
            final SelectDefinition actualDefinition = selectDefinitionService
                    .findSelectDefinitionByTableId(latestQuery.getTableId());

            final TableSourceDefinition[] actualTableSourceArray = actualDefinition.getTableSourceDefinition();

            final TableSourceDefinition actualTableSource = actualTableSourceArray[1];
            assertThat(actualTableSource.getNo(), is(expectTableSource.getNo()));
            assertThat(actualTableSource.getSourceId(), is(expectTableSource.getSourceId()));
            assertThat(actualTableSource.getJoinCondition(), is(expectTableSource.getJoinCondition()));

            final FieldSourceDefinition[] actualFieldSourceArray = actualDefinition.getFieldSourceDefinition();

            final FieldSourceDefinition actualFieldSource = actualFieldSourceArray[1];
            assertThat(actualFieldSource.getFieldId(), is(expectFieldSource.getFieldId()));
            assertThat(actualFieldSource.getSourceDefinition(), is(expectFieldSource.getSourceDefinition()));
        }
    }
}
