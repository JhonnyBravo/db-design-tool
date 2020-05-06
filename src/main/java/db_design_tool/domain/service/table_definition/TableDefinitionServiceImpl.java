package db_design_tool.domain.service.table_definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;
import db_design_tool.domain.repository.field_master.FieldMasterRepository;
import db_design_tool.domain.repository.table_master.TableMasterRepository;

public class TableDefinitionServiceImpl implements TableDefinitionService {
    private final SqlSessionFactory factory;
    private TableMasterRepository tableMasterRepository;
    private FieldMasterRepository fieldMasterRepository;

    public TableDefinitionServiceImpl() throws Exception {
        factory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

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
    public List<TableMaster> findTableByEntityType(int entityType)
            throws Exception {
        List<TableMaster> recordset = new ArrayList<>();

        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            recordset = tableMasterRepository.findByEntityType(entityType);
        }

        return recordset;
    }

    @Override
    public TableDefinition findTableDefinitionByTableId(int tableId)
            throws Exception {
        TableMaster tableMaster = new TableMaster();
        List<FieldMaster> fieldMasterList = new ArrayList<>();
        final TableDefinition tableDefinition = new TableDefinition();

        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMaster = tableMasterRepository.findByTableId(tableId);

            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterList = fieldMasterRepository.findByTableId(tableId);
        }

        tableDefinition.setTableMaster(tableMaster);
        tableDefinition.setFieldMaster(fieldMasterList
                .toArray(new FieldMaster[fieldMasterList.size()]));

        return tableDefinition;
    }

    @Override
    public void create(TableDefinition tableDefinition) throws Exception {
        final TableMaster tableMaster = tableDefinition.getTableMaster();
        final FieldMaster[] fieldMasterArray = tableDefinition.getFieldMaster();

        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMaster.setEntityType(1);
            tableMasterRepository.create(tableMaster);

            final TableMaster latest = tableMasterRepository
                    .findByLatestTableId();

            for (final FieldMaster fieldMaster : fieldMasterArray) {
                fieldMaster.setTableId(latest.getTableId());
            }

            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterRepository.create(Arrays.asList(fieldMasterArray));

            session.commit();
        }
    }

    @Override
    public void update(TableDefinition tableDefinition) throws Exception {
        final TableMaster tableMaster = tableDefinition.getTableMaster();
        final FieldMaster[] fieldMasterArray = tableDefinition.getFieldMaster();

        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMasterRepository.update(tableMaster);

            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterRepository.update(Arrays.asList(fieldMasterArray));
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
    public void deleteByEntityType(int entityType) throws Exception {
        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMasterRepository.deleteByEntityType(entityType);
            session.commit();
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try (SqlSession session = factory.openSession()) {
            tableMasterRepository = session
                    .getMapper(TableMasterRepository.class);
            tableMasterRepository.deleteAll();
            tableMasterRepository.resetId();

            fieldMasterRepository = session
                    .getMapper(FieldMasterRepository.class);
            fieldMasterRepository.deleteAll();
            fieldMasterRepository.resetId();

            session.commit();
        }
    }

}
