package db_design_tool.domain.service.select_definition;

import java.util.List;

import db_design_tool.domain.model.SelectDefinition;
import db_design_tool.domain.model.TableMaster;

/**
 * 選択クエリの定義を管理する。
 */
public interface SelectDefinitionService {
    /**
     * @return recordset TableMaster に登録されている全レコードを取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public List<TableMaster> findTableAll() throws Exception;

    /**
     * table_id をキーにクエリ定義を検索する。
     *
     * @param tableId
     *            検索対象とする table_id を指定する。
     * @return record table_id に紐づくテーブル定義を返す。削除フラグ 1 が設定されているレコードは除外する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public SelectDefinition findSelectDefinitionByTableId(int tableId)
            throws Exception;

    /**
     * クエリ定義を新規登録する。
     *
     * @param selectDefinition
     *            登録対象とするクエリ定義を指定する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void create(SelectDefinition selectDefinition) throws Exception;

    /**
     * クエリ定義を更新する。
     *
     * @param selectDefinition
     *            更新対象とするクエリ定義を指定する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void update(SelectDefinition selectDefinition) throws Exception;

    /**
     * table_id をキーにクエリ定義を削除する。
     *
     * @param tableId
     *            削除対象とするクエリ定義の table_id を指定する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void deleteByTableId(int tableId) throws Exception;

    /**
     * テーブル定義を全件削除する。
     *
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public void deleteAll() throws Exception;
}
