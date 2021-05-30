package db_design_tool.domain.service.data_type_master;

import db_design_tool.domain.model.DataTypeMaster;
import java.util.List;

/**
 * data_type_master からのデータ入出力を管理する。
 */
public interface DataTypeMasterService {
  /**
   * テーブルに登録されている全レコードを取得する。
   *
   * @return recordset {@link DataTypeMaster} の {@link List}
   */
  List<DataTypeMaster> findAll() throws Exception;
}
