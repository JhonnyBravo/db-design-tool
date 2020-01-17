package db_design_tool.domain.model;

import java.io.Serializable;

public class TableMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    private int tableId;
    private String physicalName;
    private String logicalName;
    private int deleteFlag;

    /**
     * @return tableId テーブルの ID を返す。
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     *            テーブルの ID を指定する。
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
     * @param physicalName
     *            テーブルの物理名を指定する。
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
     * @param logicalName
     *            テーブルの論理名を指定する。
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
     * @param deleteFlag
     *            削除フラグを指定する。
     *            <ul>
     *            <li>0: 削除されていないことを表す。</li>
     *            <li>1: 削除されていることを表す。</li>
     *            </ul>
     */
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
