package db_design_tool.domain.repository.table_source_definition;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import db_design_tool.app.select_definition.SelectDefinitionTestHelper;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.model.TableSourceDefinition;
import db_design_tool.domain.repository.table_master.TableMasterRepository;

@RunWith(Enclosed.class)
public class TableSourceDefinitionRepositoryTest {
    public static class クエリ定義が存在しない場合 {
        private static SelectDefinitionTestHelper helper;
        private static SqlSessionFactory factory;
        private TableSourceDefinitionRepository tableSourceDefinitionRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            helper = new SelectDefinitionTestHelper();

            String[] params1 = {"query1", "クエリ1"};
            TableMaster query1 = helper.createTableMaster(params1);

            String[] params2 = {"table1", "テーブル1"};
            TableMaster table1 = helper.createTableMaster(params2);

            String[] params3 = {"table2", "テーブル2"};
            TableMaster table2 = helper.createTableMaster(params3);

            try (SqlSession session = factory.openSession()) {
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();

                tableMasterRepository.create(query1);
                tableMasterRepository.create(table1);
                tableMasterRepository.create(table2);

                session.commit();
            }
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            try (SqlSession session = factory.openSession()) {
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();

                session.commit();
            }
        }

        @Before
        public void setUp() throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                tableSourceDefinitionRepository.deleteAll();
                tableSourceDefinitionRepository.resetId();

                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                tableSourceDefinitionRepository.deleteAll();
                tableSourceDefinitionRepository.resetId();

                session.commit();
            }
        }

        @Test
        public void findAll実行時に空のリストが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final List<TableSourceDefinition> recordset = tableSourceDefinitionRepository
                        .findAll();
                assertThat(recordset.size(), is(0));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            final List<TableSourceDefinition> expectList = new ArrayList<>();

            final String[] params1 = {"1", "1", "2", "condition1"};
            final TableSourceDefinition expect1 = helper
                    .createTableSourceDefinition(params1);
            expectList.add(expect1);

            final String[] params2 = {"1", "2", "3", "condition2"};
            final TableSourceDefinition expect2 = helper
                    .createTableSourceDefinition(params2);
            expectList.add(expect2);

            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final boolean result = tableSourceDefinitionRepository
                        .create(expectList);
                assertThat(result, is(true));
                session.commit();

                final List<TableSourceDefinition> actualList = tableSourceDefinitionRepository
                        .findAll();
                assertThat(actualList.size(), is(2));

                final TableSourceDefinition actual1 = actualList.get(0);
                assertThat(actual1.getDefinitionId(), is(1));
                assertThat(actual1.getTableId(), is(expect1.getTableId()));
                assertThat(actual1.getNo(), is(expect1.getNo()));
                assertThat(actual1.getSourceId(), is(expect1.getSourceId()));
                assertThat(actual1.getJoinCondition(),
                        is(expect1.getJoinCondition()));
                assertThat(actual1.getDeleteFlag(),
                        is(expect1.getDeleteFlag()));

                final TableSourceDefinition actual2 = actualList.get(1);
                assertThat(actual2.getDefinitionId(), is(2));
                assertThat(actual2.getTableId(), is(expect2.getTableId()));
                assertThat(actual2.getNo(), is(expect2.getNo()));
                assertThat(actual2.getSourceId(), is(expect2.getSourceId()));
                assertThat(actual2.getJoinCondition(),
                        is(expect2.getJoinCondition()));
                assertThat(actual2.getDeleteFlag(),
                        is(expect2.getDeleteFlag()));
            }
        }
    }

    public static class クエリ定義が存在する場合 {
        private static SelectDefinitionTestHelper helper;
        private static SqlSessionFactory factory;
        private TableSourceDefinitionRepository tableSourceDefinitionRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            helper = new SelectDefinitionTestHelper();
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            SelectDefinitionTestHelper helper = new SelectDefinitionTestHelper();

            String[] params1 = {"query1", "クエリ1"};
            TableMaster query1 = helper.createTableMaster(params1);

            String[] params2 = {"query2", "クエリ2"};
            TableMaster query2 = helper.createTableMaster(params2);

            String[] params3 = {"table1", "テーブル1"};
            TableMaster table1 = helper.createTableMaster(params3);

            String[] params4 = {"table2", "テーブル2"};
            TableMaster table2 = helper.createTableMaster(params4);

            try (SqlSession session = factory.openSession()) {
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();

                tableMasterRepository.create(query1);
                tableMasterRepository.create(query2);
                tableMasterRepository.create(table1);
                tableMasterRepository.create(table2);

                session.commit();
            }
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            try (SqlSession session = factory.openSession()) {
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                session.commit();
            }
        }

        @Before
        public void setUp() throws Exception {
            final List<TableSourceDefinition> recordset = new ArrayList<>();
            // クエリ1
            final String[] params1 = {"1", "1", "3", "query1.condition1"};
            final TableSourceDefinition record1 = helper
                    .createTableSourceDefinition(params1);
            recordset.add(record1);

            final String[] params2 = {"1", "2", "4", "query1.condition2", "1"};
            final TableSourceDefinition record2 = helper
                    .createTableSourceDefinition(params2);
            recordset.add(record2);

            // クエリ2
            final String[] params3 = {"2", "1", "3", "query2.condition1"};
            final TableSourceDefinition record3 = helper
                    .createTableSourceDefinition(params3);
            recordset.add(record3);

            final String[] params4 = {"2", "2", "4", "query2.condition2", "1"};
            final TableSourceDefinition record4 = helper
                    .createTableSourceDefinition(params4);
            recordset.add(record4);

            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);
                tableSourceDefinitionRepository.deleteAll();
                tableSourceDefinitionRepository.resetId();
                tableSourceDefinitionRepository.create(recordset);
                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                tableSourceDefinitionRepository.deleteAll();
                tableSourceDefinitionRepository.resetId();
                session.commit();
            }
        }

        @Test
        public void findAll実行時に4件のレコードが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final List<TableSourceDefinition> recordset = tableSourceDefinitionRepository
                        .findAll();
                assertThat(recordset.size(), is(4));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            final List<TableSourceDefinition> expectList = new ArrayList<>();

            final String[] params1 = {"1", "3", "3", "query1.condition3"};
            final TableSourceDefinition expect1 = helper
                    .createTableSourceDefinition(params1);
            expectList.add(expect1);

            final String[] params2 = {"1", "4", "4", "query1.condition4"};
            final TableSourceDefinition expect2 = helper
                    .createTableSourceDefinition(params2);
            expectList.add(expect2);

            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final boolean result = tableSourceDefinitionRepository
                        .create(expectList);
                assertThat(result, is(true));
                session.commit();

                final List<TableSourceDefinition> actualList1 = tableSourceDefinitionRepository
                        .findAll();
                assertThat(actualList1.size(), is(6));

                final List<TableSourceDefinition> actualList2 = tableSourceDefinitionRepository
                        .findByTableId(1);
                assertThat(actualList2.size(), is(4));

                final TableSourceDefinition actual1 = actualList2.get(2);
                assertThat(actual1.getDefinitionId(), is(5));
                assertThat(actual1.getTableId(), is(expect1.getTableId()));
                assertThat(actual1.getNo(), is(expect1.getNo()));
                assertThat(actual1.getSourceId(), is(expect1.getSourceId()));
                assertThat(actual1.getJoinCondition(),
                        is(expect1.getJoinCondition()));
                assertThat(actual1.getDeleteFlag(),
                        is(expect1.getDeleteFlag()));

                final TableSourceDefinition actual2 = actualList2.get(3);
                assertThat(actual2.getDefinitionId(), is(6));
                assertThat(actual2.getTableId(), is(expect2.getTableId()));
                assertThat(actual2.getNo(), is(expect2.getNo()));
                assertThat(actual2.getSourceId(), is(expect2.getSourceId()));
                assertThat(actual2.getJoinCondition(),
                        is(expect2.getJoinCondition()));
                assertThat(actual2.getDeleteFlag(),
                        is(expect2.getDeleteFlag()));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final List<TableSourceDefinition> expectList = tableSourceDefinitionRepository
                        .findByTableId(2);

                TableSourceDefinition expect1 = expectList.get(0);
                expect1.setNo(2);
                expect1.setSourceId(4);
                expect1.setJoinCondition("query2.update1");

                TableSourceDefinition expect2 = expectList.get(1);
                expect2.setNo(1);
                expect2.setSourceId(3);
                expect2.setJoinCondition("query2.update2");

                final boolean result = tableSourceDefinitionRepository
                        .update(expectList);
                assertThat(result, is(true));
                session.commit();

                final List<TableSourceDefinition> actualList = tableSourceDefinitionRepository
                        .findByTableId(2);
                assertThat(actualList.size(), is(2));

                final TableSourceDefinition actual1 = actualList.get(0);
                assertThat(actual1.getNo(), is(expect2.getNo()));
                assertThat(actual1.getSourceId(), is(expect2.getSourceId()));
                assertThat(actual1.getJoinCondition(),
                        is(expect2.getJoinCondition()));

                final TableSourceDefinition actual2 = actualList.get(1);
                assertThat(actual2.getNo(), is(expect1.getNo()));
                assertThat(actual2.getSourceId(), is(expect1.getSourceId()));
                assertThat(actual2.getJoinCondition(),
                        is(expect1.getJoinCondition()));
            }
        }

        @Test
        public void deleteByDeleteFlag実行時にdeleteFlagが1であるレコードを削除できてtrueが返されること()
                throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);
                boolean result = tableSourceDefinitionRepository
                        .deleteByDeleteFlag();
                assertThat(result, is(true));
                session.commit();

                List<TableSourceDefinition> recordset = tableSourceDefinitionRepository
                        .findAll();
                assertThat(recordset.size(), is(2));
            }
        }
    }
}
