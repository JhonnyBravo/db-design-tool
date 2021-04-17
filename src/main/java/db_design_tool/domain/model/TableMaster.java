package db_design_tool.domain.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Size;

public class TableMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    private int tableId;
    @Size(min = 1, max = 100)
    private String physicalName;
    @Size(min = 1, max = 100)
    private String logicalName;
    @Range(min = 0, max = 1)
    private int deleteFlag;
    private int entityType;
    private String physicalNameError;
    private String logicalNameError;

    /**
     * @return tableId テーブルの ID を返す。
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * @param tableId テーブルの ID を指定する。
     */
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    /**
     * @return physicalName テーブルの物理名を返す。
     */
    public String getPhysicalName() {
        return physicalName;
    }

    /**
     * @param physicalName テーブルの物理名を指定する。
     */
    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    /**
     * @return logicalName テーブルの論理名を返す。
     */
    public String getLogicalName() {
        return logicalName;
    }

    /**
     * @param logicalName テーブルの論理名を指定する。
     */
    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    /**
     * @return deleteFlag 削除フラグを返す。
     */
    public int getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag 削除フラグを指定する。
     *                   <ul>
     *                   <li>0: 削除されていないことを表す。</li>
     *                   <li>1: 削除されていることを表す。</li>
     *                   </ul>
     */
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return entityType エンティティタイプを返す。
     */
    public int getEntityType() {
        return entityType;
    }

    /**
     * @param entityType エンティティタイプを指定する。
     *                   <ul>
     *                   <li>1: テーブル</li>
     *                   <li>2: クエリ</li>
     *                   </ul>
     */
    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    /**
     * @return physicalNameError テーブルの物理名に関するエラーメッセージを返す。
     */
    public String getPhysicalNameError() {
        return physicalNameError;
    }

    /**
     * @param physicalNameError テーブルの物理名に関するエラーメッセージを指定する。
     */
    public void setPhysicalNameError(String physicalNameError) {
        this.physicalNameError = physicalNameError;
    }

    /**
     * @return logicalNameError テーブルの論理名に関するエラーメッセージを返す。
     */
    public String getLogicalNameError() {
        return logicalNameError;
    }

    /**
     * @param logicalNameError テーブルの論理名に関するエラーメッセージを指定する。
     */
    public void setLogicalNameError(String logicalNameError) {
        this.logicalNameError = logicalNameError;
    }
}
