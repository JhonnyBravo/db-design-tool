package db_design_tool.domain.repository.data_type_master;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import db_design_tool.domain.model.DataTypeMaster;

public class DataTypeMasterRepositoryTest {
    private static SqlSessionFactory factory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        factory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

    @Test
    public void testFindAll() throws Exception {
        try (SqlSession session = factory.openSession()) {
            DataTypeMasterRepository repository = session
                    .getMapper(DataTypeMasterRepository.class);

            List<DataTypeMaster> recordset = repository.findAll();
            assertThat(recordset.size(), is(5));
        }
    }

}
