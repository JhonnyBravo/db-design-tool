package db_design_tool.domain.repository.table_master;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import db_design_tool.app.table_definition.TableDefinitionTestHelper;
import db_design_tool.domain.model.TableMaster;

@RunWith(Enclosed.class)
public class TableMasterRepositoryTest {
    public static class テーブル定義が存在しない場合 {
        private static SqlSessionFactory factory;
        private TableMasterRepository repository;
        private final TableDefinitionTestHelper helper = new TableDefinitionTestHelper();

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        }

        @Before
        public void setUp() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                repository.deleteAll();
                repository.resetId();
                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                repository.deleteAll();
                repository.resetId();
                session.commit();
            }
        }

        @Test
        public void findAll実行時に空のリストが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), equalTo(0));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            final String[] params = {"table1", "テーブル1"};
            final TableMaster expect = helper.createTableMaster(params);

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                final boolean result = repository.create(expect);
                assertThat(result, equalTo(true));
                session.commit();

                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), equalTo(1));

                final TableMaster actual = repository.findByLatestTableId();
                assertThat(actual.getTableId(), equalTo(1));
                assertThat(actual.getPhysicalName(),
                        equalTo(expect.getPhysicalName()));
                assertThat(actual.getLogicalName(),
                        equalTo(expect.getLogicalName()));
                assertThat(actual.getDeleteFlag(),
                        equalTo(expect.getDeleteFlag()));
            }
        }
    }

    public static class テーブル定義が存在する場合 {
        private static SqlSessionFactory factory;
        private TableMasterRepository repository;
        private final TableDefinitionTestHelper helper = new TableDefinitionTestHelper();

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        }

        @Before
        public void setUp() throws Exception {
            final String[] params1 = {"table1", "テーブル1"};
            final TableMaster record1 = helper.createTableMaster(params1);

            final String[] params2 = {"table2", "テーブル2"};
            final TableMaster record2 = helper.createTableMaster(params2);

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                repository.deleteAll();
                repository.resetId();

                repository.create(record1);
                repository.create(record2);
                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                repository.deleteAll();
                repository.resetId();
                session.commit();
            }
        }

        @Test
        public void findAll実行時に2件のレコードを取得できること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), equalTo(2));
            }
        }

        @Test
        public void create実行時にレコードを追加できてtrueが返されること() throws Exception {
            final String[] params = {"table3", "テーブル3", "1"};
            final TableMaster expect = helper.createTableMaster(params);

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                final boolean result = repository.create(expect);
                assertThat(result, equalTo(true));
                session.commit();

                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), equalTo(3));

                final TableMaster actual = repository.findByLatestTableId();
                assertThat(actual.getTableId(), equalTo(3));
                assertThat(actual.getPhysicalName(),
                        equalTo(expect.getPhysicalName()));
                assertThat(actual.getLogicalName(),
                        equalTo(expect.getLogicalName()));
                assertThat(actual.getDeleteFlag(),
                        equalTo(expect.getDeleteFlag()));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);

                final String[] params = {"updated", "更新", "1"};
                final TableMaster expect = helper.createTableMaster(params);

                final TableMaster original = repository.findByTableId(2);
                original.setPhysicalName(expect.getPhysicalName());
                original.setLogicalName(expect.getLogicalName());
                original.setDeleteFlag(expect.getDeleteFlag());

                final boolean result = repository.update(original);
                assertThat(result, equalTo(true));
                session.commit();

                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), equalTo(2));

                final TableMaster updated = repository.findByTableId(2);
                assertThat(updated.getTableId(), equalTo(2));
                assertThat(updated.getPhysicalName(),
                        equalTo(expect.getPhysicalName()));
                assertThat(updated.getLogicalName(),
                        equalTo(expect.getLogicalName()));
                assertThat(updated.getDeleteFlag(),
                        equalTo(expect.getDeleteFlag()));
            }
        }

        @Test
        public void deleteByTableId実行時にレコードを削除できてTrueが返されること()
                throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);

                boolean result = repository.deleteByTableId(1);
                assertThat(result, equalTo(true));
                session.commit();

                List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), equalTo(1));
                assertThat(recordset.get(0).getTableId(), equalTo(2));
            }
        }
    }
}
