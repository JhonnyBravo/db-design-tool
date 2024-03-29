package db_design_tool.domain.repository.table_master;

import db_design_tool.domain.model.TableMaster;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * table_master からのデータ入出力を管理する。
 */
@Mapper
public interface TableMasterRepository {
  /**
   * テーブルに登録されている全レコードを取得する。
   *
   * @return recordset {@link TableMaster} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<TableMaster> findAll() throws Exception;

  /**
   * entity_type をキーにレコードを検索する。
   *
   * @param entityType 検索対象とする entity_type を指定する。
   * @return recordset {@link TableMaster} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<TableMaster> findByEntityType(@Param("entityType") int entityType) throws Exception;

  /**
   * table_id をキーにレコードを検索する。
   *
   * @param tableId 検索対象とする table_id を指定する。
   * @return record {@link TableMaster}
   * @throws Exception {@link java.lang.Exception}
   */
  TableMaster findByTableId(@Param("tableId") int tableId) throws Exception;

  /**
   * 最新の table_id を持つレコードを取得する。
   *
   * @return record {@link TableMaster}
   * @throws Exception {@link java.lang.Exception}
   */
  TableMaster findByLatestTableId() throws Exception;

  /**
   * レコードを新規登録する。
   *
   * @param tableMaster 登録対象とする {@link TableMaster} を指定する。
   * @return result
   *         <ul>
   *         <li>true: 登録に成功したことを表す。</li>
   *         <li>false: 登録しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean create(@Param("tableMaster") TableMaster tableMaster) throws Exception;

  /**
   * レコードを更新する。
   *
   * @param tableMaster 更新対象とする {@link TableMaster} を指定する。
   * @return result
   *         <ul>
   *         <li>true: 更新に成功したことを表す。</li>
   *         <li>false: 更新しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean update(@Param("tableMaster") TableMaster tableMaster) throws Exception;

  /**
   * table_id をキーにレコードを削除する。
   *
   * @param tableId 削除対象とするレコードの tableId を指定する。
   * @return result
   *         <ul>
   *         <li>true: 削除に成功したことを表す。</li>
   *         <li>true: 削除しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean deleteByTableId(@Param("tableId") int tableId) throws Exception;

  /**
   * entity_type をキーにレコードを削除する。
   *
   * @param entityType 削除対象とする entityType を指定する。
   * @return result
   *         <ul>
   *         <li>true: 削除に成功したことを表す。</li>
   *         <li>true: 削除しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean deleteByEntityType(@Param("entityType") int entityType) throws Exception;

  /**
   * レコードを全件削除する。
   *
   * @return result
   *         <ul>
   *         <li>true: 削除に成功したことを表す。</li>
   *         <li>false: 削除しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean deleteAll() throws Exception;

  /**
   * table_id の開始値を 1 にリセットする。
   *
   * @return result
   *         <ul>
   *         <li>true: リセットに成功したことを表す。</li>
   *         <li>false: リセットしなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean resetId() throws Exception;
}
