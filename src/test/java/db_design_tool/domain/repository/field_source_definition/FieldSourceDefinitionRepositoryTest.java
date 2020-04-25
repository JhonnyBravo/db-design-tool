package db_design_tool.domain.repository.field_source_definition;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
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
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.repository.field_master.FieldMasterRepository;
import db_design_tool.domain.repository.table_master.TableMasterRepository;

@RunWith(Enclosed.class)
public class FieldSourceDefinitionRepositoryTest {
    public static class テーブルが空である場合 {
        private static SqlSessionFactory factory;
        private FieldSourceDefinitionRepository fieldSourceDefinitionRepository;

        private List<FieldSourceDefinition> createDefinitionList(
                List<FieldMaster> fieldList,
                List<FieldSourceDefinition> definitionList) {
            FieldMaster[] fieldArray = fieldList
                    .toArray(new FieldMaster[fieldList.size()]);
            FieldSourceDefinition[] definitionArray = definitionList
                    .toArray(new FieldSourceDefinition[definitionList.size()]);

            for (int i = 0; i < fieldArray.length; i++) {
                definitionArray[i].setFieldId(fieldArray[i].getFieldId());
            }

            List<FieldSourceDefinition> result = Arrays.asList(definitionArray);
            return result;
        }

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            // TableMaster
            TableMaster query1 = new TableMaster();
            query1.setPhysicalName("query1");
            query1.setLogicalName("query1");

            try (SqlSession session = factory.openSession()) {
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();
                tableMasterRepository.create(query1);

                // FieldMaster
                List<FieldMaster> fieldList = new ArrayList<>();
                TableMaster curQuery = tableMasterRepository
                        .findByLatestTableId();

                {
                    FieldMaster field1 = new FieldMaster();

                    field1.setTableId(curQuery.getTableId());
                    field1.setNo(1);
                    field1.setPhysicalName("field1");
                    field1.setLogicalName("field1");

                    fieldList.add(field1);
                }
                {
                    FieldMaster field2 = new FieldMaster();

                    field2.setTableId(curQuery.getTableId());
                    field2.setNo(2);
                    field2.setPhysicalName("field2");
                    field2.setLogicalName("field2");

                    fieldList.add(field2);
                }

                FieldMasterRepository fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();
                fieldMasterRepository.create(fieldList);

                session.commit();
            }
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            try (SqlSession session = factory.openSession()) {
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();

                // FieldMaster
                FieldMasterRepository fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();

                session.commit();
            }
        }

        @Before
        public void setUp() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                fieldSourceDefinitionRepository.deleteAll();
                fieldSourceDefinitionRepository.resetId();

                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                fieldSourceDefinitionRepository.deleteAll();
                fieldSourceDefinitionRepository.resetId();

                session.commit();
            }
        }

        @Test
        public void findAll実行時に空のリストが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                List<FieldSourceDefinition> definitionList = fieldSourceDefinitionRepository
                        .findAll();
                assertThat(definitionList.size(), is(0));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                TableMaster curQuery = tableMasterRepository
                        .findByLatestTableId();

                // FieldMaster
                FieldMasterRepository fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                List<FieldMaster> fieldList = fieldMasterRepository
                        .findByTableId(curQuery.getTableId());

                // FieldSourceDefinition
                List<FieldSourceDefinition> definitionList = new ArrayList<>();

                {
                    FieldSourceDefinition definition1 = new FieldSourceDefinition();
                    definition1.setSourceDefinition("definition1");
                    definitionList.add(definition1);
                }
                {
                    FieldSourceDefinition definition2 = new FieldSourceDefinition();
                    definition2.setSourceDefinition("definition2");
                    definitionList.add(definition2);
                }

                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                boolean result = fieldSourceDefinitionRepository.create(
                        createDefinitionList(fieldList, definitionList));
                assertThat(result, is(true));
                session.commit();

                List<FieldSourceDefinition> recordset = fieldSourceDefinitionRepository
                        .findAll();
                assertThat(recordset.size(), is(2));

                FieldSourceDefinition record1 = fieldSourceDefinitionRepository
                        .findByFieldId(1);
                assertThat(record1.getDefinitionId(), is(1));
                assertThat(record1.getSourceDefinition(), is("definition1"));

                FieldSourceDefinition record2 = fieldSourceDefinitionRepository
                        .findByFieldId(2);
                assertThat(record2.getDefinitionId(), is(2));
                assertThat(record2.getSourceDefinition(), is("definition2"));
            }
        }
    }

    public static class テーブルが空ではない場合 {
        private static SqlSessionFactory factory;
        private FieldMasterRepository fieldMasterRepository;
        private FieldSourceDefinitionRepository fieldSourceDefinitionRepository;

        private List<FieldSourceDefinition> createDefinitionList(
                List<FieldMaster> fieldList,
                List<FieldSourceDefinition> definitionList) {
            FieldMaster[] fieldArray = fieldList
                    .toArray(new FieldMaster[fieldList.size()]);
            FieldSourceDefinition[] definitionArray = definitionList
                    .toArray(new FieldSourceDefinition[definitionList.size()]);

            for (int i = 0; i < fieldArray.length; i++) {
                definitionArray[i].setFieldId(fieldArray[i].getFieldId());
            }

            List<FieldSourceDefinition> result = Arrays.asList(definitionArray);
            return result;
        }

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            TableMaster query1 = new TableMaster();
            query1.setPhysicalName("query1");
            query1.setLogicalName("query1");

            try (SqlSession session = factory.openSession()) {
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);

                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();
                tableMasterRepository.create(query1);

                session.commit();
            }
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            try (SqlSession session = factory.openSession()) {
                // TableMaster
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
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);
                TableMaster curQuery = tableMasterRepository
                        .findByLatestTableId();

                // FieldMaster
                List<FieldMaster> fieldList = new ArrayList<>();

                {
                    FieldMaster field1 = new FieldMaster();

                    field1.setTableId(curQuery.getTableId());
                    field1.setNo(1);
                    field1.setPhysicalName("field1");
                    field1.setLogicalName("field1");

                    fieldList.add(field1);
                }
                {
                    FieldMaster field2 = new FieldMaster();

                    field2.setTableId(curQuery.getTableId());
                    field2.setNo(2);
                    field2.setPhysicalName("field2");
                    field2.setLogicalName("field2");

                    fieldList.add(field2);
                }

                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();
                fieldMasterRepository.create(fieldList);

                // FieldSourceDefinition
                List<FieldSourceDefinition> definitionList = new ArrayList<>();

                {
                    FieldSourceDefinition definition1 = new FieldSourceDefinition();
                    definition1.setSourceDefinition("definition1");
                    definitionList.add(definition1);
                }
                {
                    FieldSourceDefinition definition2 = new FieldSourceDefinition();
                    definition2.setSourceDefinition("definition2");
                    definitionList.add(definition2);
                }

                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                fieldSourceDefinitionRepository.deleteAll();
                fieldSourceDefinitionRepository.resetId();
                fieldSourceDefinitionRepository.create(
                        createDefinitionList(fieldList, definitionList));

                session.commit();
            }
        }

        @After
        public void tearDown() throws Exception {
            try (SqlSession session = factory.openSession()) {
                // FieldMaster
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);

                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();

                // FieldSourceDefinition
                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                fieldSourceDefinitionRepository.deleteAll();
                fieldSourceDefinitionRepository.resetId();

                session.commit();
            }
        }

        @Test
        public void findAll実行時に2件のレコードが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                List<FieldSourceDefinition> definitionList = fieldSourceDefinitionRepository
                        .findAll();
                assertThat(definitionList.size(), is(2));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);
                TableMaster curQuery = tableMasterRepository
                        .findByLatestTableId();

                // FieldMaster
                List<FieldMaster> fieldList = new ArrayList<>();

                {
                    FieldMaster field3 = new FieldMaster();

                    field3.setTableId(curQuery.getTableId());
                    field3.setNo(3);
                    field3.setPhysicalName("field3");
                    field3.setLogicalName("field3");

                    fieldList.add(field3);
                }
                {
                    FieldMaster field4 = new FieldMaster();

                    field4.setTableId(curQuery.getTableId());
                    field4.setNo(4);
                    field4.setPhysicalName("field4");
                    field4.setLogicalName("field4");

                    fieldList.add(field4);
                }

                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.create(fieldList);

                // FieldSourceDefinition
                List<FieldSourceDefinition> definitionList = new ArrayList<>();

                {
                    FieldSourceDefinition definition3 = new FieldSourceDefinition();
                    definition3.setSourceDefinition("definition3");
                    definitionList.add(definition3);
                }
                {
                    FieldSourceDefinition definition4 = new FieldSourceDefinition();
                    definition4.setSourceDefinition("definition4");
                    definitionList.add(definition4);
                }

                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                boolean result = fieldSourceDefinitionRepository.create(
                        createDefinitionList(fieldList, definitionList));
                assertThat(result, is(true));
                session.commit();

                List<FieldSourceDefinition> recordset = fieldSourceDefinitionRepository
                        .findAll();
                assertThat(recordset.size(), is(4));
            }
        }

        @Test
        public void update実行時にレコードを更新できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);
                List<FieldSourceDefinition> recordset = fieldSourceDefinitionRepository
                        .findAll();
                recordset.get(1).setSourceDefinition("updated");

                boolean result = fieldSourceDefinitionRepository
                        .update(recordset);
                assertThat(result, is(true));
                session.commit();

                FieldSourceDefinition definition1 = fieldSourceDefinitionRepository
                        .findByFieldId(1);
                assertThat(definition1.getSourceDefinition(),
                        is("definition1"));

                FieldSourceDefinition definition2 = fieldSourceDefinitionRepository
                        .findByFieldId(2);
                assertThat(definition2.getSourceDefinition(), is("updated"));
            }
        }
    }
}
