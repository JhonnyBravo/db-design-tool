package db_design_tool.domain.model;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.validator.constraints.Range;

/**
 * field_master のエンティティ。
 */
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
   * フィールド定義が紐づくテーブルのテーブルの ID を返す。
   *
   * @return tableId テーブルの ID
   */
  public int getTableId() {
    return tableId;
  }

  /**
   * フィールド定義が紐づくテーブルの ID を設定する。
   *
   * @param tableId テーブルの ID として設定する数値を指定する。
   */
  public void setTableId(int tableId) {
    this.tableId = tableId;
  }

  /**
   * フィールド定義の ID を返す。
   *
   * @return fieldId フィールド定義の ID
   */
  public int getFieldId() {
    return fieldId;
  }

  /**
   * フィールド定義の ID を設定する。
   *
   * @param fieldId フィールド定義の ID として設定する数値を指定する。
   */
  public void setFieldId(int fieldId) {
    this.fieldId = fieldId;
  }

  /**
   * フィールド No を返す。
   *
   * @return no フィールド No
   */
  public int getNo() {
    return no;
  }

  /**
   * フィールド No を設定する。
   *
   * @param no フィールド No として設定する数値を指定する。
   */
  public void setNo(int no) {
    this.no = no;
  }

  /**
   * フィールドの物理名を返す。
   *
   * @return physicalName フィールドの物理名
   */
  public String getPhysicalName() {
    return physicalName;
  }

  /**
   * フィールドの物理名を設定する。
   *
   * @param physicalName physicalName フィールドの物理名として設定する文字列を指定する。
   */
  public void setPhysicalName(String physicalName) {
    this.physicalName = physicalName;
  }

  /**
   * フィールドの論理名を返す。
   *
   * @return logicalName フィールドの論理名
   */
  public String getLogicalName() {
    return logicalName;
  }

  /**
   * フィールドの論理名を設定する。
   *
   * @param logicalName logicalName フィールドの論理名として設定する文字列を指定する。
   */
  public void setLogicalName(String logicalName) {
    this.logicalName = logicalName;
  }

  /**
   * データ型の ID を返す。
   *
   * @return dataType データ型の ID
   */
  public int getDataType() {
    return dataType;
  }

  /**
   * データ型の ID を設定する。
   *
   * @param dataType dataType データ型の ID として設定する数値を指定する。
   *        <ol>
   *        <li>String</li>
   *        <li>Integer</li>
   *        <li>Long</li>
   *        <li>Single</li>
   *        <li>Double</li>
   *        <li>Boolean</li>
   *        <li>Date</li>
   *        </ol>
   */
  public void setDataType(int dataType) {
    this.dataType = dataType;
  }

  /**
   * データの桁数を返す。
   *
   * @return dataSize データの桁数
   */
  public int getDataSize() {
    return dataSize;
  }

  /**
   * データの桁数を設定する。
   *
   * @param dataSize dataSize データの桁数として設定する数値を指定する。
   */
  public void setDataSize(int dataSize) {
    this.dataSize = dataSize;
  }

  /**
   * フィールドの説明を返す。
   *
   * @return description フィールドの説明
   */
  public String getDescription() {
    return description;
  }

  /**
   * フィールドの説明を設定する。
   *
   * @param description description フィールドの説明として設定する文字列を指定する。
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * 削除フラグを返す。
   *
   * @return deleteFlag 削除フラグ
   */
  public int getDeleteFlag() {
    return deleteFlag;
  }

  /**
   * 削除フラグを設定する。
   *
   * @param deleteFlag 削除フラグとして設定する数値を指定する。
   *        <ul>
   *        <li>0: フィールドが削除されていないことを表す。</li>
   *        <li>1: フィールドが削除されていることを表す。</li>
   *        </ul>
   */
  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  /**
   * フィールドの物理名に関するエラーメッセージを返す。
   *
   * @return physicalNameError フィールドの物理名に関するエラーメッセージ
   */
  public String getPhysicalNameError() {
    return physicalNameError;
  }

  /**
   * フィールドの物理名に関するエラーメッセージを設定する。
   *
   * @param physicalNameError エラーメッセージとして設定する文字列を指定する。
   */
  public void setPhysicalNameError(String physicalNameError) {
    this.physicalNameError = physicalNameError;
  }

  /**
   * フィールドの論理名に関するエラーメッセージを返す。
   *
   * @return logicalNameError フィールドの論理名に関するエラーメッセージ
   */
  public String getLogicalNameError() {
    return logicalNameError;
  }

  /**
   * フィールドの論理名に関するエラーメッセージを設定する。
   *
   * @param logicalNameError エラーメッセージとして設定する文字列を指定する。
   */
  public void setLogicalNameError(String logicalNameError) {
    this.logicalNameError = logicalNameError;
  }

  /**
   * フィールドのデータ桁数に関するエラーメッセージを返す。
   *
   * @return dataSizeError フィールドのデータ桁数に関するエラーメッセージ
   */
  public String getDataSizeError() {
    return dataSizeError;
  }

  /**
   * フィールドのデータ桁数に関するエラーメッセージを設定する。
   *
   * @param dataSizeError エラーメッセージとして設定する文字列を指定する。
   */
  public void setDataSizeError(String dataSizeError) {
    this.dataSizeError = dataSizeError;
  }

  /**
   * フィールドの説明に関するエラーメッセージを返す。
   *
   * @return descriptionError フィールドの説明に関するエラーメッセージ
   */
  public String getDescriptionError() {
    return descriptionError;
  }

  /**
   * フィールドの説明に関するエラーメッセージを設定する。
   *
   * @param descriptionError エラーメッセージとして設定する文字列を指定する。
   */
  public void setDescriptionError(String descriptionError) {
    this.descriptionError = descriptionError;
  }
}
