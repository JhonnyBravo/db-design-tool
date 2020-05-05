package db_design_tool.domain.service.select_definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.SelectDefinition;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.model.TableSourceDefinition;
import db_design_tool.domain.repository.field_master.FieldMasterRepository;
import db_design_tool.domain.repository.field_source_definition.FieldSourceDefinitionRepository;
import db_design_tool.domain.repository.table_master.TableMasterRepository;
import db_design_tool.domain.repository.table_source_definition.TableSourceDefinitionRepository;

public class SelectDefinitionServiceImpl implements SelectDefinitionService {
    private final SqlSessionFactory factory;
    private TableMasterRepository tableMasterRepository;
    private FieldMasterRepository fieldMasterRepository;
    private TableSourceDefinitionRepository tableSourceDefinitionRepository;
    private FieldSourceDefinitionRepository fieldSourceDefinitionRepository;

    public SelectDefinitionServiceImpl() throws Exception {
        factory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

    // TODO クエリのみを取得するように修正する。
    @Override
    public List<TableMaster> findTableAll() throws Exception {
        List<TableMaster> recordset = new ArrayList<>();

        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            recordset = tableMasterRepository.findAll();
        }

        return recordset;
    }

    @Override
    public SelectDefinition findSelectDefinitionByTableId(int tableId)
            throws Exception {
        TableMaster tableMaster = new TableMaster();
        List<FieldMaster> fieldMasterList = new ArrayList<>();
        List<TableSourceDefinition> tableSourceDefinitionList = new ArrayList<>();
        List<FieldSourceDefinition> fieldSourceDefinitionList = new ArrayList<>();
        final SelectDefinition selectDefinition = new SelectDefinition();

        try (SqlSession session = factory.openSession()) {
            // TableMaster
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMaster = tableMasterRepository.findByTableId(tableId);
            selectDefinition.setTableMaster(tableMaster);

            // FieldMaster
            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterList = fieldMasterRepository.findByTableId(tableId);
            selectDefinition.setFieldMaster(fieldMasterList
                    .toArray(new FieldMaster[fieldMasterList.size()]));

            // TableSourceDefinition
            tableSourceDefinitionRepository = session
                    .getMapper(TableSourceDefinitionRepository.class);
            tableSourceDefinitionList = tableSourceDefinitionRepository
                    .findByTableId(tableId);
            selectDefinition.setTableSourceDefinition(tableSourceDefinitionList
                    .toArray(new TableSourceDefinition[tableSourceDefinitionList
                            .size()]));

            // FieldSourceDefinition
            fieldSourceDefinitionRepository = session
                    .getMapper(FieldSourceDefinitionRepository.class);

            // View 用意するか取得系クエリの SQL を書き直すかした方が良さそう。
            for (FieldMaster fieldMaster : fieldMasterList) {
                FieldSourceDefinition fieldSourceDefinition = fieldSourceDefinitionRepository
                        .findByFieldId(fieldMaster.getFieldId());
                fieldSourceDefinitionList.add(fieldSourceDefinition);
            }

            selectDefinition.setFieldSourceDefinition(fieldSourceDefinitionList
                    .toArray(new FieldSourceDefinition[fieldSourceDefinitionList
                            .size()]));
        }

        return selectDefinition;
    }

    @Override
    public void create(SelectDefinition selectDefinition) throws Exception {
        final TableMaster tableMaster = selectDefinition.getTableMaster();
        final FieldMaster[] fieldMasterArray = selectDefinition
                .getFieldMaster();
        final TableSourceDefinition[] tableSourceDefinitionArray = selectDefinition
                .getTableSourceDefinition();
        final FieldSourceDefinition[] fieldSourceDefinitionArray = selectDefinition
                .getFieldSourceDefinition();

        try (SqlSession session = factory.openSession()) {
            // TableMaster
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMaster.setEntityType(2);
            tableMasterRepository.create(tableMaster);

            final TableMaster latestTable = tableMasterRepository
                    .findByLatestTableId();

            // TableSourceDefinition
            for (final TableSourceDefinition tableSourceDefinition : tableSourceDefinitionArray) {
                tableSourceDefinition.setTableId(latestTable.getTableId());
            }

            tableSourceDefinitionRepository = session
                    .getMapper(TableSourceDefinitionRepository.class);
            tableSourceDefinitionRepository
                    .create(Arrays.asList(tableSourceDefinitionArray));

            // FieldMaster
            for (final FieldMaster fieldMaster : fieldMasterArray) {
                fieldMaster.setTableId(latestTable.getTableId());
            }

            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterRepository.create(Arrays.asList(fieldMasterArray));

            List<FieldMaster> fieldMasterList = fieldMasterRepository
                    .findByTableId(latestTable.getTableId());

            // FieldSourceDefinition
            for (int i = 0; i < fieldMasterList.size(); i++) {
                fieldSourceDefinitionArray[i]
                        .setFieldId(fieldMasterList.get(i).getFieldId());
            }

            fieldSourceDefinitionRepository = session
                    .getMapper(FieldSourceDefinitionRepository.class);
            fieldSourceDefinitionRepository
                    .create(Arrays.asList(fieldSourceDefinitionArray));

            session.commit();
        }
    }

    @Override
    public void update(SelectDefinition selectDefinition) throws Exception {
        final TableMaster tableMaster = selectDefinition.getTableMaster();
        final FieldMaster[] fieldMasterArray = selectDefinition
                .getFieldMaster();
        final TableSourceDefinition[] tableSourceDefinitionArray = selectDefinition
                .getTableSourceDefinition();
        final FieldSourceDefinition[] fieldSourceDefinitionArray = selectDefinition
                .getFieldSourceDefinition();

        try (SqlSession session = factory.openSession()) {
            // TableMaster
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMasterRepository.update(tableMaster);

            // TableSourceDefinition
            tableSourceDefinitionRepository = session
                    .getMapper(TableSourceDefinitionRepository.class);
            tableSourceDefinitionRepository
                    .update(Arrays.asList(tableSourceDefinitionArray));

            // FieldMaster
            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterRepository.update(Arrays.asList(fieldMasterArray));

            // FieldSourceDefinition
            fieldSourceDefinitionRepository = session
                    .getMapper(FieldSourceDefinitionRepository.class);
            fieldSourceDefinitionRepository
                    .update(Arrays.asList(fieldSourceDefinitionArray));

            fieldMasterRepository.deleteByDeleteFlag();

            session.commit();
        }
    }

    @Override
    public void deleteByTableId(int tableId) throws Exception {
        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMasterRepository.deleteByTableId(tableId);
            session.commit();
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try (SqlSession session = factory.openSession()) {
            // TableMaster
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMasterRepository.deleteAll();
            tableMasterRepository.resetId();

            // FieldMaster
            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterRepository.deleteAll();
            fieldMasterRepository.resetId();

            // TableSourceDefinition
            tableSourceDefinitionRepository = session
                    .getMapper(TableSourceDefinitionRepository.class);
            tableSourceDefinitionRepository.deleteAll();
            tableSourceDefinitionRepository.resetId();

            // FieldSourceDefinition
            fieldSourceDefinitionRepository = session
                    .getMapper(FieldSourceDefinitionRepository.class);
            fieldSourceDefinitionRepository.deleteAll();
            fieldSourceDefinitionRepository.resetId();
        }
    }

}
