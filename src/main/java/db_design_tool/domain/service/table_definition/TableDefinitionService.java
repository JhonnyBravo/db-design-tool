package db_design_tool.domain.service.table_definition;

import java.util.List;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableDefinition;
import db_design_tool.domain.model.TableMaster;

/**
 * テーブル定義を管理する。
 */
public interface TableDefinitionService {
    /**
     * @return recordset TableMaster に登録されている全レコードを取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public List<TableMaster> findTableAll() throws Exception;

    /**
     * field_id をキーにレコードを検索する。
     *
     * @param fieldId
     *            検索対象とする field_id を指定する。
     * @return record 条件に合致するレコードを取得する。
     */
    public FieldMaster findFieldByFieldId(int fieldId) throws Exception;

    /**
     * entity_type をキーにレコードを検索する。
     *
     * @param entityType
     *            検索対象とする entity_type を指定する。
     * @return recordset 条件に合致するレコードを取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public List<TableMaster> findTableByEntityType(int entityType)
            throws Exception;

    /**
     * table_id をキーにテーブル定義を検索する。
     *
     * @param tableId
     *            検索対象とする table_id を指定する。
     * @return record table_id に紐づくテーブル定義を返す。削除フラグ 1 が設定されているレコードは除外する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public TableDefinition findTableDefinitionByTableId(int tableId)
            throws Exception;

    /**
     * テーブル定義を新規登録する。
     *
     * @param tableDefinition
     *            登録対象とするテーブル定義を指定する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void create(TableDefinition tableDefinition) throws Exception;

    /**
     * テーブル定義を更新する。
     *
     * @param tableDefinition
     *            更新対象とするテーブル定義を指定する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void update(TableDefinition tableDefinition) throws Exception;

    /**
     * table_id をキーにテーブル定義を削除する。
     *
     * @param tableId
     *            削除対象とするテーブル定義の table_id を指定する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void deleteByTableId(int tableId) throws Exception;

    /**
     * entity_type をキーにテーブル定義を削除する。
     *
     * @param entityType
     *            削除対象とするテーブル定義の entity_type を指定する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void deleteByEntityType(int entityType) throws Exception;

    /**
     * テーブル定義を全件削除する。
     *
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void deleteAll() throws Exception;
}
