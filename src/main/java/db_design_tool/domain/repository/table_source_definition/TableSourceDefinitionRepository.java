package db_design_tool.domain.repository.table_source_definition;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import db_design_tool.domain.model.TableSourceDefinition;

/**
 * TableSourceDefinition のレコードを管理する。
 */
@Mapper
public interface TableSourceDefinitionRepository {
    /**
     * @return recordset 登録されているレコードを全件取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public List<TableSourceDefinition> findAll() throws Exception;

    /**
     * table_id をキーにレコードを検索する。削除フラグ 1 が設定されているレコードは除外する。
     *
     * @param tableId
     *            検索対象とする table_id を指定する。
     * @return recordset 条件に合致するレコードを取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public List<TableSourceDefinition> findByTableId(
            @Param("tableId") int tableId) throws Exception;

    /**
     * レコードを新規登録する。
     *
     * @param recordset
     *            登録対象とするレコードセットを指定する。
     * @return result
     *         <ul>
     *         <li>true: 登録に成功したことを表す。</li>
     *         <li>false: 登録を実行しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean create(@Param("list") List<TableSourceDefinition> recordset)
            throws Exception;

    /**
     * レコードを更新する。
     *
     * @param recordset
     *            更新対象とするレコードセットを指定する。
     * @return result
     *         <ul>
     *         <li>true: 更新に成功したことを表す。</li>
     *         <li>false: 更新を実行しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean update(@Param("list") List<TableSourceDefinition> recordset)
            throws Exception;

    /**
     * delete_flag に 1 が設定されているレコードを削除する。
     *
     * @return result
     *         <ul>
     *         <li>true: 削除に成功したことを表す。</li>
     *         <li>false: 削除を実行しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean deleteByDeleteFlag() throws Exception;

    /**
     * レコードを全件削除する。
     *
     * @return result
     *         <ul>
     *         <li>true: 削除に成功したことを表す。</li>
     *         <li>false: 削除を実行しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean deleteAll() throws Exception;

    /**
     * field_id の開始値を 1 にリセットする。
     *
     * @return result
     *         <ul>
     *         <li>true: リセットに成功したことを表す。</li>
     *         <li>false: リセットを実行しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean resetId() throws Exception;
}
