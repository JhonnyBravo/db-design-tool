package db_design_tool.domain.service.data_type_master;

import java.util.List;

import db_design_tool.domain.model.DataTypeMaster;

/**
 * DataTypeMaster のレコードを管理する。
 */
public interface DataTypeMasterService {
    /**
     * @return recordset DataTypeMaster に登録されているレコードを全件取得する。
     */
    public List<DataTypeMaster> findAll() throws Exception;
}
