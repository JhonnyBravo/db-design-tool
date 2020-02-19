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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import db_design_tool.domain.model.FieldMaster;

@RunWith(Enclosed.class)
public class FieldMasterRepositoryTest {
    public static class テーブルが空である場合 {
        private static SqlSessionFactory factory;
        private FieldMasterRepository repository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        }

        @Before
        public void setUp() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);
                repository.deleteAll();
                repository.resetId();
                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);
                repository.deleteAll();
                repository.resetId();
                session.commit();
            }
        }

        @Test
        public void findAll実行時に空のリストが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);
                final List<FieldMaster> recordset = repository.findAll();
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
                record.setDataType("String");
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
                record.setDataType("Boolean");
                record.setDescription("2つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);

                final boolean result = repository.create(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> curRecordset = repository.findAll();
                assertThat(curRecordset.size(), is(2));

                final FieldMaster record1 = curRecordset.get(0);
                assertThat(record1.getTableId(), is(1));
                assertThat(record1.getFieldId(), is(1));
                assertThat(record1.getNo(), is(1));
                assertThat(record1.getPhysicalName(), is("field1"));
                assertThat(record1.getLogicalName(), is("フィールド1"));
                assertThat(record1.getDataType(), is("String"));
                assertThat(record1.getDataSize(), is(10));
                assertThat(record1.getDeleteFlag(), is(0));
                assertThat(record1.getDescription(), is("1つ目のフィールド"));

                final FieldMaster record2 = curRecordset.get(1);
                assertThat(record2.getTableId(), is(1));
                assertThat(record2.getFieldId(), is(2));
                assertThat(record2.getNo(), is(2));
                assertThat(record2.getPhysicalName(), is("field2"));
                assertThat(record2.getLogicalName(), is("フィールド2"));
                assertThat(record2.getDataType(), is("Boolean"));
                assertThat(record2.getDataSize(), is(0));
                assertThat(record2.getDeleteFlag(), is(0));
                assertThat(record2.getDescription(), is("2つ目のフィールド"));
            }
        }
    }

    public static class テーブルが空ではない場合 {
        private static SqlSessionFactory factory;
        private FieldMasterRepository repository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
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
                record.setDataType("String");
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
                record.setDataType("Boolean");
                record.setDescription("2つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            // テーブル2
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(2);
                record.setNo(1);
                record.setPhysicalName("field1");
                record.setLogicalName("フィールド1");
                record.setDataType("String");
                record.setDataSize(20);
                record.setDescription("1つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(2);
                record.setNo(2);
                record.setPhysicalName("field2");
                record.setLogicalName("フィールド2");
                record.setDataType("Boolean");
                record.setDescription("2つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);
                repository.deleteAll();
                repository.resetId();
                repository.create(recordset);
                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);
                repository.deleteAll();
                repository.resetId();
                session.commit();
            }
        }

        @Test
        public void findAll実行時に4件のレコードが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);
                final List<FieldMaster> recordset = repository.findAll();
                assertThat(recordset.size(), is(4));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            final List<FieldMaster> recordset = new ArrayList<>();
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(3);
                record.setNo(1);
                record.setPhysicalName("field1");
                record.setLogicalName("フィールド1");
                record.setDataType("String");
                record.setDataSize(30);
                record.setDescription("1つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }
            {
                final FieldMaster record = new FieldMaster();
                record.setTableId(3);
                record.setNo(2);
                record.setPhysicalName("field2");
                record.setLogicalName("フィールド2");
                record.setDataType("Boolean");
                record.setDescription("2つ目のフィールド");
                record.setDeleteFlag(0);

                recordset.add(record);
            }

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);

                final boolean result = repository.create(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> curRecordset = repository.findAll();
                assertThat(curRecordset.size(), is(6));

                final List<FieldMaster> table3 = repository.findByTableId(3);
                assertThat(table3.size(), is(2));

                final FieldMaster record1 = table3.get(0);
                assertThat(record1.getTableId(), is(3));
                assertThat(record1.getFieldId(), is(5));
                assertThat(record1.getNo(), is(1));
                assertThat(record1.getPhysicalName(), is("field1"));
                assertThat(record1.getLogicalName(), is("フィールド1"));
                assertThat(record1.getDataType(), is("String"));
                assertThat(record1.getDataSize(), is(30));
                assertThat(record1.getDeleteFlag(), is(0));
                assertThat(record1.getDescription(), is("1つ目のフィールド"));

                final FieldMaster record2 = table3.get(1);
                assertThat(record2.getTableId(), is(3));
                assertThat(record2.getFieldId(), is(6));
                assertThat(record2.getNo(), is(2));
                assertThat(record2.getPhysicalName(), is("field2"));
                assertThat(record2.getLogicalName(), is("フィールド2"));
                assertThat(record2.getDataType(), is("Boolean"));
                assertThat(record2.getDataSize(), is(0));
                assertThat(record2.getDeleteFlag(), is(0));
                assertThat(record2.getDescription(), is("2つ目のフィールド"));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(FieldMasterRepository.class);

                final List<FieldMaster> recordset = repository.findByTableId(2);
                assertThat(recordset.size(), is(2));

                recordset.get(0).setNo(2);
                recordset.get(0).setPhysicalName("update1");
                recordset.get(0).setLogicalName("更新1");
                recordset.get(0).setDataType("Integer");
                recordset.get(0).setDataSize(0);
                recordset.get(0).setDescription("1つ目の更新");

                recordset.get(1).setNo(1);
                recordset.get(1).setPhysicalName("update2");
                recordset.get(1).setLogicalName("更新2");
                recordset.get(1).setDataType("String");
                recordset.get(1).setDataSize(30);
                recordset.get(1).setDescription("2つ目の更新");

                final boolean result = repository.update(recordset);
                assertThat(result, is(true));
                session.commit();

                final List<FieldMaster> curRecordset = repository
                        .findByTableId(2);

                final FieldMaster record1 = curRecordset.get(0);
                assertThat(record1.getNo(), is(1));
                assertThat(record1.getPhysicalName(), is("update2"));
                assertThat(record1.getLogicalName(), is("更新2"));
                assertThat(record1.getDataType(), is("String"));
                assertThat(record1.getDataSize(), is(30));
                assertThat(record1.getDescription(), is("2つ目の更新"));
                assertThat(record1.getDeleteFlag(), is(0));

                final FieldMaster record2 = curRecordset.get(1);
                assertThat(record2.getNo(), is(2));
                assertThat(record2.getPhysicalName(), is("update1"));
                assertThat(record2.getLogicalName(), is("更新1"));
                assertThat(record2.getDataType(), is("Integer"));
                assertThat(record2.getDataSize(), is(0));
                assertThat(record2.getDescription(), is("1つ目の更新"));
                assertThat(record2.getDeleteFlag(), is(0));

            }
        }
    }
}
