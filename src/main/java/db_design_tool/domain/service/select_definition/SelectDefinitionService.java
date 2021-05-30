package db_design_tool.domain.service.select_definition;

import db_design_tool.domain.model.SelectDefinition;
import db_design_tool.domain.model.TableMaster;
import java.util.List;

/**
 * 選択クエリの定義を管理する。
 */
public interface SelectDefinitionService {
  /**
   * table_master に登録されているテーブル・クエリを全て取得する。
   *
   * @return recordset {@link TableMaster} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<TableMaster> findEntityAll() throws Exception;

  /**
   * table_master に登録されている選択クエリを全て取得する。
   *
   * @return recordset {@link TableMaster} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<TableMaster> findQueryAll() throws Exception;

  /**
   * table_id をキーにクエリ定義を検索する。
   *
   * @param tableId 検索対象とする table_id を指定する。
   * @return record table_id に紐づくテーブル定義を返す。削除フラグ 1 が設定されているレコードは除外する。
   * @throws Exception {@link java.lang.Exception}
   */
  SelectDefinition findSelectDefinitionByTableId(int tableId) throws Exception;

  /**
   * クエリ定義を新規登録する。
   *
   * @param selectDefinition 登録対象とするクエリ定義を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  void create(SelectDefinition selectDefinition) throws Exception;

  /**
   * クエリ定義を更新する。
   *
   * @param selectDefinition 更新対象とするクエリ定義を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  void update(SelectDefinition selectDefinition) throws Exception;

  /**
   * table_id をキーにクエリ定義を削除する。
   *
   * @param tableId 削除対象とするクエリ定義の table_id を指定する。
   * @throws Exception {@link java.lang.Exception}
   */
  void deleteByTableId(int tableId) throws Exception;

  /**
   * テーブル定義を全件削除する。
   *
   * @throws Exception {@link java.lang.Exception}
   */
  void deleteAll() throws Exception;
}
