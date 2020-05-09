package db_design_tool.domain.repository.field_source_definition;

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
import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.repository.field_master.FieldMasterRepository;
import db_design_tool.domain.repository.table_master.TableMasterRepository;

@RunWith(Enclosed.class)
public class FieldSourceDefinitionRepositoryTest {
    public static class クエリ定義が存在しない場合 {
        private static SelectDefinitionTestHelper helper;
        private static SqlSessionFactory factory;
        private FieldSourceDefinitionRepository fieldSourceDefinitionRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            helper = new SelectDefinitionTestHelper();
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            try (SqlSession session = factory.openSession()) {
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);
                tableMasterRepository.deleteAll();
                tableMasterRepository.resetId();

                String[] params1 = {"query1", "クエリ1"};
                TableMaster query1 = helper.createTableMaster(params1);
                tableMasterRepository.create(query1);

                // FieldMaster
                FieldMasterRepository fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.resetId();

                List<FieldMaster> fieldList = new ArrayList<>();
                TableMaster latestTable = tableMasterRepository
                        .findByLatestTableId();

                String[] params2 = {"0", "1", "query1.field1", "クエリ1.フィールド1",
                        "1 つ目のフィールド"};
                FieldMaster field1 = helper.createFieldMaster(params2);
                field1.setTableId(latestTable.getTableId());
                fieldList.add(field1);

                String[] params3 = {"0", "2", "query1.field2", "クエリ1.フィールド2",
                        "2 つ目のフィールド"};
                FieldMaster field2 = helper.createFieldMaster(params3);
                field2.setTableId(latestTable.getTableId());
                fieldList.add(field2);

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

                TableMaster query1 = tableMasterRepository
                        .findByLatestTableId();

                // FieldMaster
                FieldMasterRepository fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                List<FieldMaster> fieldList = fieldMasterRepository
                        .findByTableId(query1.getTableId());

                // FieldSourceDefinition
                List<FieldSourceDefinition> expectDefinitionList = new ArrayList<>();

                FieldSourceDefinition expectDefinition1 = new FieldSourceDefinition();
                expectDefinition1.setSourceDefinition("definition1");
                expectDefinitionList.add(expectDefinition1);

                FieldSourceDefinition expectDefinition2 = new FieldSourceDefinition();
                expectDefinition2.setSourceDefinition("definition2");
                expectDefinitionList.add(expectDefinition2);

                for (int i = 0; i < fieldList.size(); i++) {
                    expectDefinitionList.get(i)
                            .setFieldId(fieldList.get(i).getFieldId());
                }

                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                boolean result = fieldSourceDefinitionRepository
                        .create(expectDefinitionList);
                assertThat(result, is(true));
                session.commit();

                List<FieldSourceDefinition> actualDefinitionList = fieldSourceDefinitionRepository
                        .findAll();
                assertThat(actualDefinitionList.size(), is(2));

                FieldSourceDefinition actualDefinition1 = actualDefinitionList
                        .get(0);
                assertThat(actualDefinition1.getDefinitionId(), is(1));
                assertThat(actualDefinition1.getSourceDefinition(),
                        is(expectDefinition1.getSourceDefinition()));

                FieldSourceDefinition actualDefinition2 = actualDefinitionList
                        .get(1);
                assertThat(actualDefinition2.getDefinitionId(), is(2));
                assertThat(actualDefinition2.getSourceDefinition(),
                        is(expectDefinition2.getSourceDefinition()));
            }
        }
    }

    public static class クエリ定義が存在する場合 {
        private static SelectDefinitionTestHelper helper;
        private static SqlSessionFactory factory;

        private FieldMasterRepository fieldMasterRepository;
        private FieldSourceDefinitionRepository fieldSourceDefinitionRepository;

        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            helper = new SelectDefinitionTestHelper();
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));

            final String[] params1 = {"query1", "クエリ1"};
            final TableMaster query1 = helper.createTableMaster(params1);

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
                TableMaster query1 = tableMasterRepository
                        .findByLatestTableId();

                // FieldMaster
                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.deleteAll();
                fieldMasterRepository.resetId();

                List<FieldMaster> fieldList = new ArrayList<>();

                final String[] params1 = {"0", "1", "query1.field1",
                        "クエリ1.フィールド1", "1 つ目のフィールド"};
                final FieldMaster field1 = helper.createFieldMaster(params1);
                field1.setTableId(query1.getTableId());
                fieldList.add(field1);

                final String[] params2 = {"0", "2", "query1.field2",
                        "クエリ1.フィールド2", "2 つ目のフィールド"};
                final FieldMaster field2 = helper.createFieldMaster(params2);
                field2.setTableId(query1.getTableId());
                fieldList.add(field2);

                fieldMasterRepository.create(fieldList);

                // FieldSourceDefinition
                List<FieldSourceDefinition> definitionList = new ArrayList<>();

                final String[] params3 = {"0", "definition1"};
                final FieldSourceDefinition definition1 = helper
                        .createFieldSourceDefinition(params3);
                definitionList.add(definition1);

                final String[] params4 = {"0", "definition2"};
                final FieldSourceDefinition definition2 = helper
                        .createFieldSourceDefinition(params4);
                definitionList.add(definition2);

                for (int i = 0; i < fieldList.size(); i++) {
                    definitionList.get(i)
                            .setFieldId(fieldList.get(i).getFieldId());
                }

                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                fieldSourceDefinitionRepository.deleteAll();
                fieldSourceDefinitionRepository.resetId();
                fieldSourceDefinitionRepository.create(definitionList);

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
        public void findByTableId実行時に2件のレコードが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);
                TableMaster query1 = tableMasterRepository
                        .findByLatestTableId();

                // FieldSourceDefinition
                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);
                FieldSourceDefinition expectDefinition1 = fieldSourceDefinitionRepository
                        .findByFieldId(1);
                FieldSourceDefinition expectDefinition2 = fieldSourceDefinitionRepository
                        .findByFieldId(2);

                List<FieldSourceDefinition> actualDefinitionList = fieldSourceDefinitionRepository
                        .findByTableId(query1.getTableId());
                assertThat(actualDefinitionList.size(), is(2));

                FieldSourceDefinition actualDefinition1 = actualDefinitionList
                        .get(0);
                assertThat(actualDefinition1.getDefinitionId(),
                        is(expectDefinition1.getDefinitionId()));
                assertThat(actualDefinition1.getSourceDefinition(),
                        is(expectDefinition1.getSourceDefinition()));

                FieldSourceDefinition actualDefinition2 = actualDefinitionList
                        .get(1);
                assertThat(actualDefinition2.getDefinitionId(),
                        is(expectDefinition2.getDefinitionId()));
                assertThat(actualDefinition2.getSourceDefinition(),
                        is(expectDefinition2.getSourceDefinition()));
            }
        }

        @Test
        public void create実行時にレコードを登録できてtrueが返されること() throws Exception {
            try (SqlSession session = factory.openSession()) {
                // TableMaster
                TableMasterRepository tableMasterRepository = session
                        .getMapper(TableMasterRepository.class);
                TableMaster query1 = tableMasterRepository
                        .findByLatestTableId();

                // FieldMaster
                List<FieldMaster> fieldList = new ArrayList<>();

                final String[] params1 = {"0", "3", "query1.field3",
                        "クエリ1.フィールド3", "3 つ目のフィールド"};
                final FieldMaster field3 = helper.createFieldMaster(params1);
                field3.setTableId(query1.getTableId());
                fieldList.add(field3);

                final String[] params2 = {"0", "3", "query1.field4",
                        "クエリ1.フィールド4", "4 つ目のフィールド"};
                final FieldMaster field4 = helper.createFieldMaster(params2);
                field4.setTableId(query1.getTableId());
                fieldList.add(field4);

                fieldMasterRepository = session
                        .getMapper(FieldMasterRepository.class);
                fieldMasterRepository.create(fieldList);

                // FieldSourceDefinition
                List<FieldSourceDefinition> definitionList = new ArrayList<>();

                final String[] params3 = {"0", "definition3"};
                final FieldSourceDefinition definition3 = helper
                        .createFieldSourceDefinition(params3);
                definitionList.add(definition3);

                final String[] params4 = {"0", "definition4"};
                final FieldSourceDefinition definition4 = helper
                        .createFieldSourceDefinition(params4);
                definitionList.add(definition4);

                for (int i = 0; i < fieldList.size(); i++) {
                    definitionList.get(i)
                            .setFieldId(fieldList.get(i).getFieldId());
                }

                fieldSourceDefinitionRepository = session
                        .getMapper(FieldSourceDefinitionRepository.class);

                boolean result = fieldSourceDefinitionRepository
                        .create(definitionList);
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

                List<FieldSourceDefinition> expectList = new ArrayList<>();

                FieldSourceDefinition expect1 = fieldSourceDefinitionRepository
                        .findByFieldId(1);
                expectList.add(expect1);

                FieldSourceDefinition expect2 = fieldSourceDefinitionRepository
                        .findByFieldId(2);
                expect2.setSourceDefinition("update1");
                expectList.add(expect2);

                boolean result = fieldSourceDefinitionRepository
                        .update(expectList);
                assertThat(result, is(true));
                session.commit();

                FieldSourceDefinition actual1 = fieldSourceDefinitionRepository
                        .findByFieldId(1);
                assertThat(actual1.getSourceDefinition(),
                        is(expect1.getSourceDefinition()));

                FieldSourceDefinition actual2 = fieldSourceDefinitionRepository
                        .findByFieldId(2);
                assertThat(actual2.getSourceDefinition(),
                        is(expect2.getSourceDefinition()));
            }
        }
    }
}
