package db_design_tool.domain.repository.table_master;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import db_design_tool.domain.model.TableMaster;

@RunWith(Enclosed.class)
public class TableMasterRepositoryTest {
    public static class テーブルが空である場合 {
        private static SqlSessionFactory factory;
        private TableMasterRepository repository;

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
                assertThat(recordset.size(), is(0));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            final TableMaster record = new TableMaster();
            record.setPhysicalName("table1");
            record.setLogicalName("テーブル1");
            record.setDeleteFlag(0);

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                final boolean result = repository.create(record);
                assertThat(result, is(true));
                session.commit();

                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), is(1));

                final TableMaster latest = repository.findByLatestTableId();
                assertThat(latest.getTableId(), is(1));
                assertThat(latest.getPhysicalName(), is("table1"));
                assertThat(latest.getLogicalName(), is("テーブル1"));
                assertThat(latest.getDeleteFlag(), is(0));
            }
        }
    }

    public static class テーブルが空ではない場合 {
        private static SqlSessionFactory factory;
        private TableMasterRepository repository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        }

        @Before
        public void setUp() throws Exception {
            final TableMaster record1 = new TableMaster();
            record1.setPhysicalName("table1");
            record1.setLogicalName("テーブル1");
            record1.setDeleteFlag(0);

            final TableMaster record2 = new TableMaster();
            record2.setPhysicalName("table2");
            record2.setLogicalName("テーブル2");
            record2.setDeleteFlag(0);

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
                assertThat(recordset.size(), is(2));
            }
        }

        @Test
        public void create実行時にレコードを追加できてtrueが返されること() throws Exception {
            final TableMaster record = new TableMaster();
            record.setPhysicalName("table3");
            record.setLogicalName("テーブル3");
            record.setDeleteFlag(1);

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);
                final boolean result = repository.create(record);
                assertThat(result, is(true));
                session.commit();

                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), is(3));

                final TableMaster record3 = repository.findByLatestTableId();
                assertThat(record3.getTableId(), is(3));
                assertThat(record3.getPhysicalName(), is("table3"));
                assertThat(record3.getLogicalName(), is("テーブル3"));
                assertThat(record3.getDeleteFlag(), is(1));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);

                final TableMaster record = repository.findByTableId(2);

                record.setPhysicalName("update1");
                record.setLogicalName("更新1");
                record.setDeleteFlag(1);

                final boolean result = repository.update(record);
                assertThat(result, is(true));
                session.commit();

                final List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), is(2));

                final TableMaster record2 = repository.findByTableId(2);
                assertThat(record2.getTableId(), is(2));
                assertThat(record2.getPhysicalName(), is("update1"));
                assertThat(record2.getLogicalName(), is("更新1"));
                assertThat(record2.getDeleteFlag(), is(1));
            }
        }

        @Test
        public void deleteByTableId実行時にレコードを削除できてTrueが返されること()
                throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(TableMasterRepository.class);

                boolean result = repository.deleteByTableId(1);
                assertThat(result, is(true));
                session.commit();

                List<TableMaster> recordset = repository.findAll();
                assertThat(recordset.size(), is(1));
                assertThat(recordset.get(0).getTableId(), is(2));
            }
        }
    }
}
