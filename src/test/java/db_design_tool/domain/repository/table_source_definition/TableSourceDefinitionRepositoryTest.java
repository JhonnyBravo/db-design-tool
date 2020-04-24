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

import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.model.TableSourceDefinition;
import db_design_tool.domain.repository.table_master.TableMasterRepository;

@RunWith(Enclosed.class)
public class TableSourceDefinitionRepositoryTest {
    public static class テーブルが空である場合 {
        private static SqlSessionFactory factory;
        private TableSourceDefinitionRepository tableSourceDefinitionRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            TableMaster query1 = new TableMaster();
            query1.setPhysicalName("query1");
            query1.setLogicalName("query1");

            TableMaster table1 = new TableMaster();
            table1.setPhysicalName("table1");
            table1.setLogicalName("テーブル1");

            TableMaster table2 = new TableMaster();
            table2.setPhysicalName("table2");
            table2.setLogicalName("テーブル2");

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
            final List<TableSourceDefinition> recordset = new ArrayList<>();
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(1);
                record.setNo(1);
                record.setSourceId(2);
                record.setJoinCondition("condition1");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(1);
                record.setNo(2);
                record.setSourceId(3);
                record.setJoinCondition("condition2");
                record.setDeleteFlag(0);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final boolean result = tableSourceDefinitionRepository
                        .create(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<TableSourceDefinition> curRecordset = tableSourceDefinitionRepository
                        .findAll();
                assertThat(curRecordset.size(), is(2));

                final TableSourceDefinition record1 = curRecordset.get(0);
                assertThat(record1.getTableId(), is(1));
                assertThat(record1.getDefinitionId(), is(1));
                assertThat(record1.getNo(), is(1));
                assertThat(record1.getSourceId(), is(2));
                assertThat(record1.getJoinCondition(), is("condition1"));
                assertThat(record1.getDeleteFlag(), is(0));

                final TableSourceDefinition record2 = curRecordset.get(1);
                assertThat(record2.getTableId(), is(1));
                assertThat(record2.getDefinitionId(), is(2));
                assertThat(record2.getNo(), is(2));
                assertThat(record2.getSourceId(), is(3));
                assertThat(record2.getJoinCondition(), is("condition2"));
                assertThat(record2.getDeleteFlag(), is(0));
            }
        }
    }

    public static class テーブルが空ではない場合 {
        private static SqlSessionFactory factory;
        private TableSourceDefinitionRepository tableSourceDefinitionRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            TableMaster query1 = new TableMaster();
            query1.setPhysicalName("query1");
            query1.setLogicalName("クエリ1");

            TableMaster query2 = new TableMaster();
            query2.setPhysicalName("query2");
            query2.setLogicalName("クエリ2");

            TableMaster table1 = new TableMaster();
            table1.setPhysicalName("table1");
            table1.setLogicalName("テーブル1");

            TableMaster table2 = new TableMaster();
            table2.setPhysicalName("table2");
            table2.setLogicalName("テーブル2");

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
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(1);
                record.setNo(1);
                record.setSourceId(3);
                record.setJoinCondition("query1.condition1");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(1);
                record.setNo(2);
                record.setSourceId(4);
                record.setJoinCondition("query1.condition2");
                record.setDeleteFlag(1);

                recordset.add(record);
            }
            // クエリ2
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(2);
                record.setNo(1);
                record.setSourceId(3);
                record.setJoinCondition("query2.condition1");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(2);
                record.setNo(2);
                record.setSourceId(4);
                record.setJoinCondition("query2.condition2");
                record.setDeleteFlag(1);

                recordset.add(record);
            }

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
            final List<TableSourceDefinition> recordset = new ArrayList<>();
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(1);
                record.setNo(3);
                record.setSourceId(3);
                record.setJoinCondition("query1.condition3");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final TableSourceDefinition record = new TableSourceDefinition();
                record.setTableId(1);
                record.setNo(4);
                record.setSourceId(4);
                record.setJoinCondition("query1.condition4");
                record.setDeleteFlag(0);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final boolean result = tableSourceDefinitionRepository
                        .create(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<TableSourceDefinition> curRecordset = tableSourceDefinitionRepository
                        .findAll();
                assertThat(curRecordset.size(), is(6));

                final List<TableSourceDefinition> query1 = tableSourceDefinitionRepository
                        .findByTableId(1);
                assertThat(query1.size(), is(4));

                final TableSourceDefinition record3 = query1.get(2);
                assertThat(record3.getTableId(), is(1));
                assertThat(record3.getDefinitionId(), is(5));
                assertThat(record3.getNo(), is(3));
                assertThat(record3.getSourceId(), is(3));
                assertThat(record3.getJoinCondition(), is("query1.condition3"));
                assertThat(record3.getDeleteFlag(), is(0));

                final TableSourceDefinition record4 = query1.get(3);
                assertThat(record4.getTableId(), is(1));
                assertThat(record4.getDefinitionId(), is(6));
                assertThat(record4.getNo(), is(4));
                assertThat(record4.getSourceId(), is(4));
                assertThat(record4.getJoinCondition(), is("query1.condition4"));
                assertThat(record4.getDeleteFlag(), is(0));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                tableSourceDefinitionRepository = session
                        .getMapper(TableSourceDefinitionRepository.class);

                final List<TableSourceDefinition> recordset = tableSourceDefinitionRepository
                        .findByTableId(2);
                assertThat(recordset.size(), is(2));

                recordset.get(0).setNo(2);
                recordset.get(0).setSourceId(4);
                recordset.get(0).setJoinCondition("query2.update1");

                recordset.get(1).setNo(1);
                recordset.get(1).setSourceId(3);
                recordset.get(1).setJoinCondition("query2.update2");

                final boolean result = tableSourceDefinitionRepository
                        .update(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<TableSourceDefinition> curRecordset = tableSourceDefinitionRepository
                        .findByTableId(2);

                final TableSourceDefinition record1 = curRecordset.get(0);
                assertThat(record1.getNo(), is(1));
                assertThat(record1.getSourceId(), is(3));
                assertThat(record1.getJoinCondition(), is("query2.update2"));

                final TableSourceDefinition record2 = curRecordset.get(1);
                assertThat(record2.getNo(), is(2));
                assertThat(record2.getSourceId(), is(4));
                assertThat(record2.getJoinCondition(), is("query2.update1"));
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
