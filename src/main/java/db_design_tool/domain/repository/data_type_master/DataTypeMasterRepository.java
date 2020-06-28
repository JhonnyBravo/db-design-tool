package db_design_tool.domain.repository.data_type_master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import db_design_tool.domain.model.DataTypeMaster;

/**
 * DataTypeMaster のレコードを管理する。
 */
@Mapper
public interface DataTypeMasterRepository {
    /**
     * @return recordset 登録されているレコードを全件取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public List<DataTypeMaster> findAll() throws Exception;
}
