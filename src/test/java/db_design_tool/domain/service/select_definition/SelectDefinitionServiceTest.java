package db_design_tool.domain.service.select_definition;

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
        private TableDefinitionService tableDefinitionService;
        private SelectDefinitionService selectDefinitionService;

        @Before
        public void setUp() throws Exception {
            selectDefinitionService = new SelectDefinitionServiceImpl();
            selectDefinitionService.deleteAll();

            // ベーステーブル
            TableDefinition baseDefinition = new TableDefinition();

            TableMaster baseTable = new TableMaster();
            baseTable.setPhysicalName("base_table");
            baseTable.setLogicalName("ベーステーブル");

            baseDefinition.setTableMaster(baseTable);

            List<FieldMaster> fieldMasterList1 = new ArrayList<>();

            {
                FieldMaster field1 = new FieldMaster();
                field1.setNo(1);
                field1.setPhysicalName("field1_1");
                field1.setLogicalName("フィールド1-1");
                fieldMasterList1.add(field1);

                FieldMaster field2 = new FieldMaster();
                field2.setNo(2);
                field2.setPhysicalName("field1_2");
                field2.setLogicalName("フィールド1-2");
                fieldMasterList1.add(field2);
            }

            baseDefinition.setFieldMaster(fieldMasterList1
                    .toArray(new FieldMaster[fieldMasterList1.size()]));

            tableDefinitionService = new TableDefinitionServiceImpl();
            tableDefinitionService.create(baseDefinition);

            // 結合用テーブル
            TableDefinition joinDefinition = new TableDefinition();

            TableMaster joinTable = new TableMaster();
            joinTable.setPhysicalName("join_table");
            joinTable.setLogicalName("結合用テーブル");

            joinDefinition.setTableMaster(joinTable);

            List<FieldMaster> fieldMasterList2 = new ArrayList<>();

            {
                FieldMaster field1 = new FieldMaster();
                field1.setNo(1);
                field1.setPhysicalName("field2_1");
                field1.setLogicalName("フィールド2-1");
                fieldMasterList2.add(field1);

                FieldMaster field2 = new FieldMaster();
                field2.setNo(2);
                field2.setPhysicalName("field2_2");
                field2.setLogicalName("フィールド2-2");
                fieldMasterList2.add(field2);
            }

            joinDefinition.setFieldMaster(fieldMasterList2
                    .toArray(new FieldMaster[fieldMasterList2.size()]));

            tableDefinitionService.create(joinDefinition);
        }

        @After
        public void tearDown() throws Exception {
            selectDefinitionService.deleteAll();
        }

        @Test
        public void findTableAll実行時に2件のレコードが取得されること() throws Exception {
            List<TableMaster> tableMasterList = selectDefinitionService
                    .findTableAll();
            assertThat(tableMasterList.size(), is(2));
        }

        @Test
        public void create実行時にレコードの登録ができること() throws Exception {
            SelectDefinition selectDefinition = new SelectDefinition();

            // クエリ名の定義
            TableMaster query1 = new TableMaster();
            query1.setPhysicalName("query1");
            query1.setLogicalName("クエリ1");

            selectDefinition.setTableMaster(query1);

            // 取得元テーブルの定義
            List<TableSourceDefinition> tableSourceDefinitionList = new ArrayList<>();

            TableSourceDefinition baseTable = new TableSourceDefinition();
            baseTable.setNo(1);
            baseTable.setSourceId(1);
            tableSourceDefinitionList.add(baseTable);

            TableSourceDefinition joinTable = new TableSourceDefinition();
            joinTable.setNo(2);
            joinTable.setSourceId(2);
            joinTable.setJoinCondition(
                    "base_table.field1_1=join_table.field_2_1");
            tableSourceDefinitionList.add(joinTable);

            selectDefinition.setTableSourceDefinition(tableSourceDefinitionList
                    .toArray(new TableSourceDefinition[tableSourceDefinitionList
                            .size()]));

            // フィールド名の定義
            List<FieldMaster> fieldList = new ArrayList<>();

            FieldMaster field1 = new FieldMaster();
            field1.setNo(1);
            field1.setPhysicalName("field3_1");
            field1.setLogicalName("フィールド3_1");
            fieldList.add(field1);

            FieldMaster field2 = new FieldMaster();
            field2.setNo(2);
            field2.setPhysicalName("field3_2");
            field2.setLogicalName("フィールド3_2");
            fieldList.add(field2);

            selectDefinition.setFieldMaster(
                    fieldList.toArray(new FieldMaster[fieldList.size()]));

            // 取得元フィールドの定義
            List<FieldSourceDefinition> fieldSourceList = new ArrayList<>();

            FieldSourceDefinition fieldSource1 = new FieldSourceDefinition();
            fieldSource1.setSourceDefinition("base_table.field1_1");
            fieldSourceList.add(fieldSource1);

            FieldSourceDefinition fieldSource2 = new FieldSourceDefinition();
            fieldSource2.setSourceDefinition("join_table.field2_2");
            fieldSourceList.add(fieldSource2);

            selectDefinition.setFieldSourceDefinition(fieldSourceList.toArray(
                    new FieldSourceDefinition[fieldSourceList.size()]));

            selectDefinitionService.create(selectDefinition);

            SelectDefinition definition = selectDefinitionService
                    .findSelectDefinitionByTableId(3);

            // クエリ名が登録できること
            TableMaster tableMaster = definition.getTableMaster();
            assertThat(tableMaster.getPhysicalName(), is("query1"));
            assertThat(tableMaster.getLogicalName(), is("クエリ1"));

            // クエリのフィールド定義が登録できること
            FieldMaster[] fieldMasterArray = definition.getFieldMaster();
            assertThat(fieldMasterArray.length, is(2));

            assertThat(fieldMasterArray[0].getNo(), is(1));
            assertThat(fieldMasterArray[0].getPhysicalName(), is("field3_1"));
            assertThat(fieldMasterArray[0].getLogicalName(), is("フィールド3_1"));

            assertThat(fieldMasterArray[1].getNo(), is(2));
            assertThat(fieldMasterArray[1].getPhysicalName(), is("field3_2"));
            assertThat(fieldMasterArray[1].getLogicalName(), is("フィールド3_2"));

            // 取得元テーブルの定義が登録できること
            TableSourceDefinition[] tableSourceArray = definition
                    .getTableSourceDefinition();
            assertThat(tableSourceArray.length, is(2));

            assertThat(tableSourceArray[0].getNo(), is(1));
            assertThat(tableSourceArray[0].getSourceId(), is(1));

            assertThat(tableSourceArray[1].getNo(), is(2));
            assertThat(tableSourceArray[1].getSourceId(), is(2));
            assertThat(tableSourceArray[1].getJoinCondition(),
                    is("base_table.field1_1=join_table.field_2_1"));

            // 取得元フィールドの定義が登録できること
            FieldSourceDefinition[] fieldSourceArray = selectDefinition
                    .getFieldSourceDefinition();
            assertThat(fieldSourceArray.length, is(2));

            assertThat(fieldSourceArray[0].getSourceDefinition(),
                    is("base_table.field1_1"));
            assertThat(fieldSourceArray[0].getFieldId(),
                    is(fieldMasterArray[0].getFieldId()));

            assertThat(fieldSourceArray[1].getSourceDefinition(),
                    is("join_table.field2_2"));
            assertThat(fieldSourceArray[1].getFieldId(),
                    is(fieldMasterArray[1].getFieldId()));
        }
    }

    public static class クエリ定義が存在する場合 {
        private TableDefinitionService tableDefinitionService;
        private SelectDefinitionService selectDefinitionService;

        @Before
        public void setUp() throws Exception {
            selectDefinitionService = new SelectDefinitionServiceImpl();
            selectDefinitionService.deleteAll();

            // ベーステーブル
            TableDefinition baseDefinition = new TableDefinition();

            {
                TableMaster baseTable = new TableMaster();
                baseTable.setPhysicalName("base_table");
                baseTable.setLogicalName("ベーステーブル");

                baseDefinition.setTableMaster(baseTable);

                List<FieldMaster> fieldMasterList1 = new ArrayList<>();

                FieldMaster field1 = new FieldMaster();
                field1.setNo(1);
                field1.setPhysicalName("field1_1");
                field1.setLogicalName("フィールド1-1");
                fieldMasterList1.add(field1);

                FieldMaster field2 = new FieldMaster();
                field2.setNo(2);
                field2.setPhysicalName("field1_2");
                field2.setLogicalName("フィールド1-2");
                fieldMasterList1.add(field2);

                baseDefinition.setFieldMaster(fieldMasterList1
                        .toArray(new FieldMaster[fieldMasterList1.size()]));
            }

            tableDefinitionService = new TableDefinitionServiceImpl();
            tableDefinitionService.create(baseDefinition);

            // 結合用テーブル
            TableDefinition joinDefinition = new TableDefinition();

            {
                TableMaster joinTable = new TableMaster();
                joinTable.setPhysicalName("join_table");
                joinTable.setLogicalName("結合用テーブル");

                joinDefinition.setTableMaster(joinTable);

                List<FieldMaster> fieldMasterList2 = new ArrayList<>();

                FieldMaster field1 = new FieldMaster();
                field1.setNo(1);
                field1.setPhysicalName("field2_1");
                field1.setLogicalName("フィールド2-1");
                fieldMasterList2.add(field1);

                FieldMaster field2 = new FieldMaster();
                field2.setNo(2);
                field2.setPhysicalName("field2_2");
                field2.setLogicalName("フィールド2-2");
                fieldMasterList2.add(field2);

                joinDefinition.setFieldMaster(fieldMasterList2
                        .toArray(new FieldMaster[fieldMasterList2.size()]));
            }

            tableDefinitionService.create(joinDefinition);

            // クエリ定義
            SelectDefinition selectDefinition = new SelectDefinition();

            // クエリ名の定義
            {
                TableMaster query1 = new TableMaster();
                query1.setPhysicalName("query1");
                query1.setLogicalName("クエリ1");

                selectDefinition.setTableMaster(query1);

                // 取得元テーブルの定義
                List<TableSourceDefinition> tableSourceDefinitionList = new ArrayList<>();

                TableSourceDefinition baseTable = new TableSourceDefinition();
                baseTable.setNo(1);
                baseTable.setSourceId(1);
                tableSourceDefinitionList.add(baseTable);

                TableSourceDefinition joinTable = new TableSourceDefinition();
                joinTable.setNo(2);
                joinTable.setSourceId(2);
                joinTable.setJoinCondition(
                        "base_table.field1_1=join_table.field_2_1");
                tableSourceDefinitionList.add(joinTable);

                selectDefinition.setTableSourceDefinition(
                        tableSourceDefinitionList.toArray(
                                new TableSourceDefinition[tableSourceDefinitionList
                                        .size()]));

                // フィールド名の定義
                List<FieldMaster> fieldList = new ArrayList<>();

                FieldMaster field1 = new FieldMaster();
                field1.setNo(1);
                field1.setPhysicalName("field3_1");
                field1.setLogicalName("フィールド3_1");
                fieldList.add(field1);

                FieldMaster field2 = new FieldMaster();
                field2.setNo(2);
                field2.setPhysicalName("field3_2");
                field2.setLogicalName("フィールド3_2");
                fieldList.add(field2);

                selectDefinition.setFieldMaster(
                        fieldList.toArray(new FieldMaster[fieldList.size()]));

                // 取得元フィールドの定義
                List<FieldSourceDefinition> fieldSourceList = new ArrayList<>();

                FieldSourceDefinition fieldSource1 = new FieldSourceDefinition();
                fieldSource1.setSourceDefinition("base_table.field1_1");
                fieldSourceList.add(fieldSource1);

                FieldSourceDefinition fieldSource2 = new FieldSourceDefinition();
                fieldSource2.setSourceDefinition("join_table.field2_2");
                fieldSourceList.add(fieldSource2);

                selectDefinition.setFieldSourceDefinition(fieldSourceList
                        .toArray(new FieldSourceDefinition[fieldSourceList
                                .size()]));
            }

            selectDefinitionService.create(selectDefinition);
        }

        @After
        public void tearDown() throws Exception {
            selectDefinitionService.deleteAll();
        }

        @Test
        public void findTableAll実行時に3件のレコードが取得されること() throws Exception {
            List<TableMaster> tableMasterList = selectDefinitionService
                    .findTableAll();
            assertThat(tableMasterList.size(), is(3));
        }

        @Test
        public void create実行時にレコードの登録ができること() throws Exception {
            SelectDefinition selectDefinition = new SelectDefinition();

            // クエリ名の定義
            TableMaster query2 = new TableMaster();
            query2.setPhysicalName("query2");
            query2.setLogicalName("クエリ2");

            selectDefinition.setTableMaster(query2);

            // 取得元テーブルの定義
            List<TableSourceDefinition> tableSourceDefinitionList = new ArrayList<>();

            TableSourceDefinition baseQuery = new TableSourceDefinition();
            baseQuery.setNo(1);
            baseQuery.setSourceId(3);
            tableSourceDefinitionList.add(baseQuery);

            selectDefinition.setTableSourceDefinition(tableSourceDefinitionList
                    .toArray(new TableSourceDefinition[tableSourceDefinitionList
                            .size()]));

            // フィールド名の定義
            List<FieldMaster> fieldList = new ArrayList<>();

            FieldMaster field1 = new FieldMaster();
            field1.setNo(1);
            field1.setPhysicalName("field4_1");
            field1.setLogicalName("フィールド4_1");
            fieldList.add(field1);

            FieldMaster field2 = new FieldMaster();
            field2.setNo(2);
            field2.setPhysicalName("field4_2");
            field2.setLogicalName("フィールド4_2");
            fieldList.add(field2);

            selectDefinition.setFieldMaster(
                    fieldList.toArray(new FieldMaster[fieldList.size()]));

            // 取得元フィールドの定義
            List<FieldSourceDefinition> fieldSourceList = new ArrayList<>();

            FieldSourceDefinition fieldSource1 = new FieldSourceDefinition();
            fieldSource1.setSourceDefinition("query1.field3_1");
            fieldSourceList.add(fieldSource1);

            FieldSourceDefinition fieldSource2 = new FieldSourceDefinition();
            fieldSource2.setSourceDefinition("query1.field3_2");
            fieldSourceList.add(fieldSource2);

            selectDefinition.setFieldSourceDefinition(fieldSourceList.toArray(
                    new FieldSourceDefinition[fieldSourceList.size()]));

            selectDefinitionService.create(selectDefinition);

            List<TableMaster> tableList = selectDefinitionService
                    .findTableAll();
            assertThat(tableList.size(), is(4));
        }

        @Test
        public void update実行時にレコードの更新ができること() throws Exception {
            // 更新前のクエリ定義
            SelectDefinition selectDefinition1 = selectDefinitionService
                    .findSelectDefinitionByTableId(3);

            TableSourceDefinition[] tableSource1 = selectDefinition1
                    .getTableSourceDefinition();
            tableSource1[1].setJoinCondition("updated condition");

            selectDefinition1.setTableSourceDefinition(tableSource1);

            FieldSourceDefinition[] fieldSource1 = selectDefinition1
                    .getFieldSourceDefinition();
            fieldSource1[1].setSourceDefinition("updated field source");

            selectDefinition1.setFieldSourceDefinition(fieldSource1);

            selectDefinitionService.update(selectDefinition1);

            // 更新後のクエリ定義
            SelectDefinition selectDefinition2 = selectDefinitionService
                    .findSelectDefinitionByTableId(3);

            TableSourceDefinition[] tableSource2 = selectDefinition2
                    .getTableSourceDefinition();

            assertThat(tableSource2[1].getNo(), is(tableSource1[1].getNo()));
            assertThat(tableSource2[1].getSourceId(),
                    is(tableSource1[1].getSourceId()));
            assertThat(tableSource2[1].getJoinCondition(),
                    is(tableSource1[1].getJoinCondition()));

            FieldSourceDefinition[] fieldSource2 = selectDefinition2
                    .getFieldSourceDefinition();

            assertThat(fieldSource2[1].getFieldId(),
                    is(fieldSource1[1].getFieldId()));
            assertThat(fieldSource2[1].getSourceDefinition(),
                    is(fieldSource1[1].getSourceDefinition()));
        }
    }
}
