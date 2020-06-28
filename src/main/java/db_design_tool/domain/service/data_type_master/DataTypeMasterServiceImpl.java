package db_design_tool.domain.service.data_type_master;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import db_design_tool.domain.model.DataTypeMaster;
import db_design_tool.domain.repository.data_type_master.DataTypeMasterRepository;

public class DataTypeMasterServiceImpl implements DataTypeMasterService {
    private final SqlSessionFactory factory;
    private DataTypeMasterRepository repository;

    public DataTypeMasterServiceImpl() throws Exception {
        factory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

    @Override
    public List<DataTypeMaster> findAll() throws Exception {
        List<DataTypeMaster> recordset = new ArrayList<>();

        try (SqlSession session = factory.openSession()) {
            repository = session.getMapper(DataTypeMasterRepository.class);
            recordset = repository.findAll();
        }

        return recordset;
    }

}
