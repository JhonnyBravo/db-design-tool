package db_design_tool.domain.model;

import java.io.Serializable;

public class FieldMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    private int tableId;
    private int fieldId;
    private int no;
    private String physicalName;
    private String logicalName;
    private String dataType;
    private int dataSize;
    private String description;
    private int deleteFlag;

    /**
     * @return tableId table_id を返す。
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     *            table_id を指定する。
     */
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    /**
     * @return fieldId field_id を返す。
     */
    public int getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     *            field_id を指定する。
     */
    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return no を返す。
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no
     *            no を指定する。
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * @return physicalName フィールドの物理名を返す。
     */
    public String getPhysicalName() {
        return physicalName;
    }

    /**
     * @param physicalName
     *            physicalName フィールドの物理名を指定する。
     */
    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    /**
     * @return logicalName フィールドの論理名を返す。
     */
    public String getLogicalName() {
        return logicalName;
    }

    /**
     * @param logicalName
     *            logicalName フィールドの論理名を指定する。
     */
    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    /**
     * @return dataType データ型を返す。
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     *            dataType データ型を指定する。
     *            <ul>
     *            <li>String</li>
     *            <li>Integer</li>
     *            <li>Long</li>
     *            <li>Single</li>
     *            <li>Double</li>
     *            <li>Boolean</li>
     *            <li>Date</li>
     *            </ul>
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * @return dataSize データの桁数を返す。
     */
    public int getDataSize() {
        return dataSize;
    }

    /**
     * @param dataSize
     *            dataSize データの桁数を指定する。
     */
    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    /**
     * @return description フィールドの説明を返す。
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            description フィールドの説明を指定する。
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return deleteFlag 削除フラグを返す。
     */
    public int getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     *            deleteFlag 削除フラグを指定する。
     *            <ul>
     *            <li>0: フィールドが削除されていないことを表す。</li>
     *            <li>1: フィールドが削除されていることを表す。</li>
     *            </ul>
     */
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
