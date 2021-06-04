package db_design_tool.domain.service.table_definition;

import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;
import java.util.List;

/**
 * テーブル定義を管理する。
 */
public interface TableDefinitionService {
  /**
   * {@link TableMaster} に登録されている全レコードを取得する。
   *
   * @return recordset {@link TableMaster} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<TableMaster> findTableAll() throws Exception;

  /**
   * エンティティ種別をキーにレコードを検索する。
   *
   * @param entityType 検索対象とする entity_type を指定する。
   * @return recordset {@link TableMaster} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<TableMaster> findTableByEntityType(int entityType) throws Exception;

  /**
   * table_id をキーにテーブル定義を検索する。 削除フラグ 1 が設定されているレコードは除外する。
   *
   * @param tableId 検索対象とする table_id を指定する。
   * @return record {@link TableDefinition}
   * @throws Exception {@link java.lang.Exception}
   */
  TableDefinition findTableDefinitionByTableId(int tableId) throws Exception;

  /**
   * テーブル定義を新規登録する。
   *
   * @param tableDefinition 登録対象とする {@link TableDefinition} を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  void create(TableDefinition tableDefinition) throws Exception;

  /**
   * テーブル定義を更新する。
   *
   * @param tableDefinition 更新対象とする {@link TableDefinition} を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  void update(TableDefinition tableDefinition) throws Exception;

  /**
   * table_id をキーにテーブル定義を削除する。
   *
   * @param tableId 削除対象とするテーブル定義の table_id を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  void deleteByTableId(int tableId) throws Exception;

  /**
   * entity_type をキーにテーブル定義を削除する。
   *
   * @param entityType 削除対象とするテーブル定義のエンティティ種別を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  void deleteByEntityType(int entityType) throws Exception;

  /**
   * テーブル定義を全件削除する。
   *
   * @throws Exception {@link java.lang.Exception}
   */
  void deleteAll() throws Exception;
}
