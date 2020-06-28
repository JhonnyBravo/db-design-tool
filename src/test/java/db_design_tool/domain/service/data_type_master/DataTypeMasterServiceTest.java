package db_design_tool.domain.service.data_type_master;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import db_design_tool.domain.model.DataTypeMaster;

public class DataTypeMasterServiceTest {
    private DataTypeMasterService service;

    @Before
    public void setUp() throws Exception {
        service = new DataTypeMasterServiceImpl();
    }

    @Test
    public void testFindAll() throws Exception {
        List<DataTypeMaster> recordset = service.findAll();
        assertThat(recordset.size(), is(5));
    }

}
