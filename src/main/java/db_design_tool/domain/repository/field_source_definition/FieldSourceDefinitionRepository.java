package db_design_tool.domain.repository.field_source_definition;

import db_design_tool.domain.model.FieldSourceDefinition;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * field_source_definition からのデータ入出力を管理する。
 */
@Mapper
public interface FieldSourceDefinitionRepository {
  /**
   * テーブルに登録されている全レコードを取得する。
   *
   * @return recordset {@link FieldSourceDefinition} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<FieldSourceDefinition> findAll() throws Exception;

  /**
   * table_id をキーにレコードを検索する。
   *
   * @param tableId 検索対象とする table_id を指定する。
   * @return recordset {@link FieldSourceDefinition} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  List<FieldSourceDefinition> findByTableId(@Param("tableId") int tableId) throws Exception;

  /**
   * field_id をキーにレコードを検索する。
   *
   * @param fieldId 検索対象とする field_id を指定する。
   * @return recordset {@link FieldSourceDefinition} の {@link List}
   * @throws Exception {@link java.lang.Exception}
   */
  FieldSourceDefinition findByFieldId(@Param("fieldId") int fieldId) throws Exception;

  /**
   * レコードを新規登録する。
   *
   * @param recordset 登録対象とする {@link FieldSourceDefinition} の {@link List} を指定する。
   * @return result
   *         <ul>
   *         <li>true: 登録に成功したことを表す。</li>
   *         <li>false: 登録を実行しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean create(@Param("list") List<FieldSourceDefinition> recordset) throws Exception;

  /**
   * レコードを更新する。
   *
   * @param recordset 更新対象とする {@link FieldSourceDefinition} の {@link List} を指定する。
   * @return result
   *         <ul>
   *         <li>true: 更新に成功したことを表す。</li>
   *         <li>false: 更新を実行しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean update(@Param("list") List<FieldSourceDefinition> recordset) throws Exception;

  /**
   * レコードを全件削除する。
   *
   * @return result
   *         <ul>
   *         <li>true: 削除に成功したことを表す。</li>
   *         <li>false: 削除を実行しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean deleteAll() throws Exception;

  /**
   * field_id の開始値を 1 にリセットする。
   *
   * @return result
   *         <ul>
   *         <li>true: リセットに成功したことを表す。</li>
   *         <li>false: リセットを実行しなかったことを表す。</li>
   *         </ul>
   * @throws Exception {@link java.lang.Exception}
   */
  boolean resetId() throws Exception;
}
