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

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.repository.table_master.TableMasterRepository;

@RunWith(Enclosed.class)
public class FieldMasterRepositoryTest {
    public static class テーブルが空である場合 {
        private static SqlSessionFactory factory;
        private FieldMasterRepository fieldMasterRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            TableMaster table1 = new TableMaster();
            table1.setPhysicalName("table1");
            table1.setLogicalName("テーブル1");

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
            final List<FieldMaster> recordset = new ArrayList<>();
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(1);
                record.setNo(1);
                record.setPhysicalName("field1");
                record.setLogicalName("フィールド1");
                record.setDataType(1);
                record.setDataSize(10);
                record.setDescription("1つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(1);
                record.setNo(2);
                record.setPhysicalName("field2");
                record.setLogicalName("フィールド2");
                record.setDataType(6);
                record.setDescription("2つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                final boolean result = fieldMasterRepository.create(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> curRecordset = fieldMasterRepository
                        .findAll();
                assertThat(curRecordset.size(), is(2));

                final FieldMaster record1 = curRecordset.get(0);
                assertThat(record1.getTableId(), is(1));
                assertThat(record1.getFieldId(), is(1));
                assertThat(record1.getNo(), is(1));
                assertThat(record1.getPhysicalName(), is("field1"));
                assertThat(record1.getLogicalName(), is("フィールド1"));
                assertThat(record1.getDataType(), is(1));
                assertThat(record1.getDataSize(), is(10));
                assertThat(record1.getDeleteFlag(), is(0));
                assertThat(record1.getDescription(), is("1つ目のフィールド"));

                final FieldMaster record2 = curRecordset.get(1);
                assertThat(record2.getTableId(), is(1));
                assertThat(record2.getFieldId(), is(2));
                assertThat(record2.getNo(), is(2));
                assertThat(record2.getPhysicalName(), is("field2"));
                assertThat(record2.getLogicalName(), is("フィールド2"));
                assertThat(record2.getDataType(), is(6));
                assertThat(record2.getDataSize(), is(0));
                assertThat(record2.getDeleteFlag(), is(0));
                assertThat(record2.getDescription(), is("2つ目のフィールド"));
            }
        }
    }

    public static class テーブルが空ではない場合 {
        private static SqlSessionFactory factory;
        private FieldMasterRepository fieldMasterRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

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
            final List<FieldMaster> recordset = new ArrayList<>();
            // テーブル1
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(1);
                record.setNo(1);
                record.setPhysicalName("field1");
                record.setLogicalName("フィールド1");
                record.setDataType(1);
                record.setDataSize(10);
                record.setDescription("table1.field1");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(1);
                record.setNo(2);
                record.setPhysicalName("field2");
                record.setLogicalName("フィールド2");
                record.setDataType(6);
                record.setDescription("table1.field2");
                record.setDeleteFlag(1);

                recordset.add(record);
            }
            // テーブル2
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(2);
                record.setNo(1);
                record.setPhysicalName("field1");
                record.setLogicalName("フィールド1");
                record.setDataType(1);
                record.setDataSize(20);
                record.setDescription("table2.field1");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(2);
                record.setNo(2);
                record.setPhysicalName("field2");
                record.setLogicalName("フィールド2");
                record.setDataType(6);
                record.setDescription("table2.field2");
                record.setDeleteFlag(1);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();
                fieldMasterRepository.create(recordset);
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
            final List<FieldMaster> recordset = new ArrayList<>();
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(1);
                record.setNo(3);
                record.setPhysicalName("field3");
                record.setLogicalName("フィールド3");
                record.setDataType(1);
                record.setDataSize(30);
                record.setDescription("table1.field3");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(1);
                record.setNo(4);
                record.setPhysicalName("field4");
                record.setLogicalName("フィールド4");
                record.setDataType(6);
                record.setDescription("table1.field4");
                record.setDeleteFlag(0);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                final boolean result = fieldMasterRepository.create(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> curRecordset = fieldMasterRepository
                        .findAll();
                assertThat(curRecordset.size(), is(6));

                final List<FieldMaster> table1 = fieldMasterRepository
                        .findByTableId(1);
                assertThat(table1.size(), is(4));

                final FieldMaster record3 = table1.get(2);
                assertThat(record3.getTableId(), is(1));
                assertThat(record3.getFieldId(), is(5));
                assertThat(record3.getNo(), is(3));
                assertThat(record3.getPhysicalName(), is("field3"));
                assertThat(record3.getLogicalName(), is("フィールド3"));
                assertThat(record3.getDataType(), is(1));
                assertThat(record3.getDataSize(), is(30));
                assertThat(record3.getDeleteFlag(), is(0));
                assertThat(record3.getDescription(), is("table1.field3"));

                final FieldMaster record4 = table1.get(3);
                assertThat(record4.getTableId(), is(1));
                assertThat(record4.getFieldId(), is(6));
                assertThat(record4.getNo(), is(4));
                assertThat(record4.getPhysicalName(), is("field4"));
                assertThat(record4.getLogicalName(), is("フィールド4"));
                assertThat(record4.getDataType(), is(6));
                assertThat(record4.getDataSize(), is(0));
                assertThat(record4.getDeleteFlag(), is(0));
                assertThat(record4.getDescription(), is("table1.field4"));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                final List<FieldMaster> recordset = fieldMasterRepository
                        .findByTableId(2);
                assertThat(recordset.size(), is(2));

                recordset.get(0).setNo(2);
                recordset.get(0).setPhysicalName("update1");
                recordset.get(0).setLogicalName("更新1");
                recordset.get(0).setDataType(2);
                recordset.get(0).setDataSize(0);
                recordset.get(0).setDescription("table2.update1");

                recordset.get(1).setNo(1);
                recordset.get(1).setPhysicalName("update2");
                recordset.get(1).setLogicalName("更新2");
                recordset.get(1).setDataType(1);
                recordset.get(1).setDataSize(30);
                recordset.get(1).setDescription("table2.update2");

                final boolean result = fieldMasterRepository.update(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> curRecordset = fieldMasterRepository
                        .findByTableId(2);

                final FieldMaster record1 = curRecordset.get(0);
                assertThat(record1.getNo(), is(1));
                assertThat(record1.getPhysicalName(), is("update2"));
                assertThat(record1.getLogicalName(), is("更新2"));
                assertThat(record1.getDataType(), is(1));
                assertThat(record1.getDataSize(), is(30));
                assertThat(record1.getDescription(), is("table2.update2"));
                assertThat(record1.getDeleteFlag(), is(1));

                final FieldMaster record2 = curRecordset.get(1);
                assertThat(record2.getNo(), is(2));
                assertThat(record2.getPhysicalName(), is("update1"));
                assertThat(record2.getLogicalName(), is("更新1"));
                assertThat(record2.getDataType(), is(2));
                assertThat(record2.getDataSize(), is(0));
                assertThat(record2.getDescription(), is("table2.update1"));
                assertThat(record2.getDeleteFlag(), is(0));
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
