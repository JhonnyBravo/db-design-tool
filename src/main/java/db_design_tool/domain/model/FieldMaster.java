package db_design_tool.domain.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class FieldMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    private int tableId;
    private int fieldId;
    private int no;
    @Size(min = 1, max = 100)
    private String physicalName;
    @Size(min = 1, max = 100)
    private String logicalName;
    @Range(min = 1, max = 7)
    private int dataType;
    private int dataSize;
    @Size(min = 0, max = 255)
    private String description;
    @Range(min = 0, max = 1)
    private int deleteFlag;
    private String physicalNameError;
    private String logicalNameError;
    private String dataSizeError;
    private String descriptionError;

    /**
     * @return tableId フィールド定義が紐づくテーブルの ID を返す。
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     *            フィールド定義が紐づくテーブルの ID を指定する。
     */
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    /**
     * @return fieldId フィールド定義の ID を返す。
     */
    public int getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     *            フィールド定義の ID を指定する。
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
    public int getDataType() {
        return dataType;
    }

    /**
     * @param dataType
     *            dataType データ型を指定する。
     *            <ol>
     *            <li>String</li>
     *            <li>Integer</li>
     *            <li>Long</li>
     *            <li>Single</li>
     *            <li>Double</li>
     *            <li>Boolean</li>
     *            <li>Date</li>
     *            </ol>
     */
    public void setDataType(int dataType) {
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
     *            削除フラグを指定する。
     *            <ul>
     *            <li>0: フィールドが削除されていないことを表す。</li>
     *            <li>1: フィールドが削除されていることを表す。</li>
     *            </ul>
     */
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @return physicalNameError フィールドの物理名に関するエラーメッセージを返す。
     */
    public String getPhysicalNameError() {
        return physicalNameError;
    }

    /**
     * @param physicalNameError
     *            フィールドの物理名に関するエラーメッセージを指定する。
     */
    public void setPhysicalNameError(String physicalNameError) {
        this.physicalNameError = physicalNameError;
    }

    /**
     * @return logicalNameError フィールドの論理名に関するエラーメッセージを返す。
     */
    public String getLogicalNameError() {
        return logicalNameError;
    }

    /**
     * @param logicalNameError
     *            フィールドの論理名に関するエラーメッセージを指定する。
     */
    public void setLogicalNameError(String logicalNameError) {
        this.logicalNameError = logicalNameError;
    }

    /**
     * @return dataSizeError フィールドのデータサイズに関するエラーメッセージを返す。
     */
    public String getDataSizeError() {
        return dataSizeError;
    }

    /**
     * @param dataSizeError
     *            フィールドのデータサイズに関するエラーメッセージを指定する。
     */
    public void setDataSizeError(String dataSizeError) {
        this.dataSizeError = dataSizeError;
    }

    /**
     * @return descriptionError フィールドの説明に関するエラーメッセージを返す。
     */
    public String getDescriptionError() {
        return descriptionError;
    }

    /**
     * @param descriptionError
     *            フィールドの説明に関するエラーメッセージを指定する。
     */
    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }
}
