package db_design_tool.domain.repository.field_master;

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

import db_design_tool.app.table_definition.TableDefinitionTestHelper;
import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.repository.table_master.TableMasterRepository;

@RunWith(Enclosed.class)
public class FieldMasterRepositoryTest {
    public static class フィールド定義が存在しない場合 {
        private static TableDefinitionTestHelper helper;
        private static SqlSessionFactory factory;
        private FieldMasterRepository fieldMasterRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            helper = new TableDefinitionTestHelper();
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            final String[] params = {"table1", "テーブル1"};
            final TableMaster table1 = helper.createTableMaster(params);

            try (SqlSession session = factory.openSession()) {
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();
                tableMasterRepository.create(table1);

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
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();
                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();
                session.commit();
            }
        }

        @Test
        public void findAll実行時に空のリストが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                final List<FieldMaster> recordset = fieldMasterRepository
                        .findAll();
                assertThat(recordset.size(), is(0));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            final String[] params1 = {"1", "1", "field1", "フィールド1",
                    "1つ目のフィールド"};
            final FieldMaster expect1 = helper.createFieldMaster(params1);

            final String[] params2 = {"1", "2", "field2", "フィールド2",
                    "2つ目のフィールド"};
            final FieldMaster expect2 = helper.createFieldMaster(params2);

            final List<FieldMaster> expectList = new ArrayList<>();
            expectList.add(expect1);
            expectList.add(expect2);

            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                final boolean result = fieldMasterRepository.create(expectList);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> actualList = fieldMasterRepository
                        .findAll();
                assertThat(actualList.size(), is(expectList.size()));

                final FieldMaster actual1 = fieldMasterRepository
                        .findByTableIdAndNo(expect1.getTableId(),
                                expect1.getNo());

                assertThat(actual1.getFieldId(), is(1));
                assertThat(actual1.getTableId(), is(expect1.getTableId()));
                assertThat(actual1.getNo(), is(expect1.getNo()));
                assertThat(actual1.getPhysicalName(),
                        is(expect1.getPhysicalName()));
                assertThat(actual1.getLogicalName(),
                        is(expect1.getLogicalName()));
                assertThat(actual1.getDescription(),
                        is(expect1.getDescription()));
                assertThat(actual1.getDeleteFlag(),
                        is(expect1.getDeleteFlag()));

                final FieldMaster actual2 = fieldMasterRepository
                        .findByTableIdAndNo(expect2.getTableId(),
                                expect2.getNo());

                assertThat(actual2.getFieldId(), is(2));
                assertThat(actual2.getTableId(), is(expect2.getTableId()));
                assertThat(actual2.getNo(), is(expect2.getNo()));
                assertThat(actual2.getPhysicalName(),
                        is(expect2.getPhysicalName()));
                assertThat(actual2.getLogicalName(),
                        is(expect2.getLogicalName()));
                assertThat(actual2.getDescription(),
                        is(expect2.getDescription()));
                assertThat(actual2.getDeleteFlag(),
                        is(expect2.getDeleteFlag()));
            }
        }
    }

    public static class フィールド定義が存在する場合 {
        private static TableDefinitionTestHelper helper;
        private static SqlSessionFactory factory;
        private FieldMasterRepository fieldMasterRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            helper = new TableDefinitionTestHelper();
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            String[] params1 = {"table1", "テーブル1"};
            TableMaster table1 = helper.createTableMaster(params1);

            String[] params2 = {"table2", "テーブル2"};
            TableMaster table2 = helper.createTableMaster(params2);

            try (SqlSession session = factory.openSession()) {
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();
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
            final List<FieldMaster> fieldList = new ArrayList<>();

            // テーブル1
            final String[] params1 = {"1", "1", "field1", "フィールド1",
                    "table1.field1"};
            final FieldMaster field1 = helper.createFieldMaster(params1);
            fieldList.add(field1);

            final String[] params2 = {"1", "2", "field2", "フィールド2",
                    "table1.field2", "1"};
            final FieldMaster field2 = helper.createFieldMaster(params2);
            fieldList.add(field2);

            // テーブル2
            final String[] params3 = {"2", "1", "field1", "フィールド1",
                    "table2.field1"};
            final FieldMaster field3 = helper.createFieldMaster(params3);
            fieldList.add(field3);

            final String[] params4 = {"2", "2", "field2", "フィールド2",
                    "table2.field2", "1"};
            final FieldMaster field4 = helper.createFieldMaster(params4);
            fieldList.add(field4);

            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();
                fieldMasterRepository.create(fieldList);
                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();
                session.commit();
            }
        }

        @Test
        public void findAll実行時に4件のレコードが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                final List<FieldMaster> recordset = fieldMasterRepository
                        .findAll();
                assertThat(recordset.size(), is(4));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            final List<FieldMaster> expectList = new ArrayList<>();

            final String[] params1 = {"1", "3", "field3", "フィールド3",
                    "table1.field3"};
            final FieldMaster expect1 = helper.createFieldMaster(params1);
            expectList.add(expect1);

            final String[] params2 = {"1", "4", "field4", "フィールド4",
                    "table1.field4"};
            final FieldMaster expect2 = helper.createFieldMaster(params2);
            expectList.add(expect2);

            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                final boolean result = fieldMasterRepository.create(expectList);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> actualList1 = fieldMasterRepository
                        .findAll();
                assertThat(actualList1.size(), is(6));

                final List<FieldMaster> actualList2 = fieldMasterRepository
                        .findByTableId(1);
                assertThat(actualList2.size(), is(4));

                final FieldMaster actual1 = fieldMasterRepository
                        .findByTableIdAndNo(expect1.getTableId(),
                                expect1.getNo());

                assertThat(actual1.getFieldId(), is(5));
                assertThat(actual1.getTableId(), is(expect1.getTableId()));
                assertThat(actual1.getNo(), is(expect1.getNo()));
                assertThat(actual1.getPhysicalName(),
                        is(expect1.getPhysicalName()));
                assertThat(actual1.getLogicalName(),
                        is(expect1.getLogicalName()));
                assertThat(actual1.getDescription(),
                        is(expect1.getDescription()));
                assertThat(actual1.getDeleteFlag(),
                        is(expect1.getDeleteFlag()));

                final FieldMaster actual2 = fieldMasterRepository

                        .findByTableIdAndNo(expect2.getTableId(),
                                expect2.getNo());
                assertThat(actual2.getFieldId(), is(6));
                assertThat(actual2.getTableId(), is(expect2.getTableId()));
                assertThat(actual2.getNo(), is(expect2.getNo()));
                assertThat(actual2.getPhysicalName(),
                        is(expect2.getPhysicalName()));
                assertThat(actual2.getLogicalName(),
                        is(expect2.getLogicalName()));
                assertThat(actual2.getDescription(),
                        is(expect2.getDescription()));
                assertThat(actual2.getDeleteFlag(),
                        is(expect2.getDeleteFlag()));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                List<FieldMaster> expectList = new ArrayList<>();

                final FieldMaster expect1 = fieldMasterRepository
                        .findByTableIdAndNo(2, 2);
                expect1.setPhysicalName("update1");
                expect1.setLogicalName("更新1");
                expect1.setDescription("table2.update1");
                expectList.add(expect1);

                final FieldMaster expect2 = fieldMasterRepository
                        .findByTableIdAndNo(2, 1);
                expect2.setPhysicalName("update2");
                expect2.setLogicalName("更新2");
                expect2.setDescription("table2.update2");
                expectList.add(expect2);

                boolean result = fieldMasterRepository.update(expectList);
                assertThat(result, is(true));
                session.commit();

                List<FieldMaster> actualList = fieldMasterRepository
                        .findByTableId(2);
                assertThat(actualList.size(), is(2));

                final FieldMaster actual1 = fieldMasterRepository
                        .findByTableIdAndNo(expect1.getTableId(),
                                expect1.getNo());

                assertThat(actual1.getNo(), is(expect1.getNo()));
                assertThat(actual1.getPhysicalName(),
                        is(expect1.getPhysicalName()));
                assertThat(actual1.getLogicalName(),
                        is(expect1.getLogicalName()));
                assertThat(actual1.getDescription(),
                        is(expect1.getDescription()));
                assertThat(actual1.getDeleteFlag(),
                        is(expect1.getDeleteFlag()));

                final FieldMaster actual2 = fieldMasterRepository
                        .findByTableIdAndNo(expect2.getTableId(),
                                expect2.getNo());

                assertThat(actual2.getNo(), is(expect2.getNo()));
                assertThat(actual2.getPhysicalName(),
                        is(expect2.getPhysicalName()));
                assertThat(actual2.getLogicalName(),
                        is(expect2.getLogicalName()));
                assertThat(actual2.getDescription(),
                        is(expect2.getDescription()));
                assertThat(actual2.getDeleteFlag(),
                        is(expect2.getDeleteFlag()));
            }
        }

        @Test
        public void deleteByDeleteFlag実行時にdeleteFlagが1であるレコードを削除できてtrueが返されること()
                throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                boolean result = fieldMasterRepository.deleteByDeleteFlag();
                assertThat(result, is(true));
                session.commit();

                List<FieldMaster> recordset = fieldMasterRepository.findAll();
                assertThat(recordset.size(), is(2));
            }
        }
    }
}
