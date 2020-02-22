package db_design_tool.domain.repository.table_master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import db_design_tool.domain.model.TableMaster;

/**
 * TableMaster のレコードを管理する。
 */
@Mapper
public interface TableMasterRepository {
    /**
     * @return recordset 登録されているレコードを全件取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public List<TableMaster> findAll() throws Exception;

    /**
     * table_id をキーにレコードを検索する。
     *
     * @param tableId
     *            検索対象とする table_id を指定する。
     * @return record 条件に合致するレコードを取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public TableMaster findByTableId(@Param("tableId") int tableId)
            throws Exception;

    /**
     * @return record 最新の table_id を持つレコードを取得する。
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public TableMaster findByLatestTableId() throws Exception;

    /**
     * レコードを新規登録する。
     *
     * @param tableMaster
     *            登録対象とするレコードを指定する。
     * @return result
     *         <ul>
     *         <li>true: 登録に成功したことを表す。</li>
     *         <li>false: 登録しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean create(@Param("tableMaster") TableMaster tableMaster)
            throws Exception;

    /**
     * レコードを更新する。
     *
     * @param tableMaster
     *            更新対象とするレコードを指定する。
     * @return result
     *         <ul>
     *         <li>true: 更新に成功したことを表す。</li>
     *         <li>false: 更新しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean update(@Param("tableMaster") TableMaster tableMaster)
            throws Exception;

    /**
     * table_id をキーにレコードを削除する。
     *
     * @param tableId
     *            削除対象とするレコードの tableId を指定する。
     * @return result
     *         <ul>
     *         <li>true: 削除に成功したことを表す。</li>
     *         <li>true: 削除しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean deleteByTableId(@Param("tableId") int tableId)
            throws Exception;

    /**
     * レコードを全件削除する。
     *
     * @return result
     *         <ul>
     *         <li>true: 削除に成功したことを表す。</li>
     *         <li>false: 削除しなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean deleteAll() throws Exception;

    /**
     * table_id の開始値を 1 にリセットする。
     *
     * @return result
     *         <ul>
     *         <li>true: リセットに成功したことを表す。</li>
     *         <li>false: リセットしなかったことを表す。</li>
     *         </ul>
     * @throws Exception
     *             {@link java.lang.Exception}
     */
    public boolean resetId() throws Exception;
}
