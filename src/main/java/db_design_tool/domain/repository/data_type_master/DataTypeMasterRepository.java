package db_design_tool.domain.repository.data_type_master;

import db_design_tool.domain.model.DataTypeMaster;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * data_type_master からのデータ入出力を管理する。
 */
@Mapper
public interface DataTypeMasterRepository {
  /**
   * テーブルに登録されている全レコードを取得する。
   *
   * @return recordset {@link DataTypeMaster} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<DataTypeMaster> findAll() throws Exception;
}
