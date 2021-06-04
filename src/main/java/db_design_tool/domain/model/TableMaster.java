package db_design_tool.domain.model;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.validator.constraints.Range;

/**
 * table_master のエンティティ。
 */
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
   * テーブルの ID を返す。
   *
   * @return tableId テーブルの ID
   */
  public int getTableId() {
    return tableId;
  }

  /**
   * テーブルの ID を設定する。
   *
   * @param tableId テーブルの ID として設定する数値を指定する。
   */
  public void setTableId(int tableId) {
    this.tableId = tableId;
  }

  /**
   * テーブルの物理名を返す。
   *
   * @return physicalName テーブルの物理名
   */
  public String getPhysicalName() {
    return physicalName;
  }

  /**
   * テーブルの物理名を設定する。
   *
   * @param physicalName テーブルの物理名として設定する文字列を指定する。
   */
  public void setPhysicalName(String physicalName) {
    this.physicalName = physicalName;
  }

  /**
   * テーブルの論理名を返す。
   *
   * @return logicalName テーブルの論理名
   */
  public String getLogicalName() {
    return logicalName;
  }

  /**
   * テーブルの論理名を設定する。
   *
   * @param logicalName テーブルの論理名として設定する文字列を指定する。
   */
  public void setLogicalName(String logicalName) {
    this.logicalName = logicalName;
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
   *        <li>0: 削除されていないことを表す。</li>
   *        <li>1: 削除されていることを表す。</li>
   *        </ul>
   */
  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  /**
   * エンティティ種別を返す。
   *
   * @return entityType エンティティ種別
   */
  public int getEntityType() {
    return entityType;
  }

  /**
   * エンティティ種別を設定する。
   *
   * @param entityType エンティティ種別として設定する数値を指定する。
   *        <ul>
   *        <li>1: テーブル</li>
   *        <li>2: クエリ</li>
   *        </ul>
   */
  public void setEntityType(int entityType) {
    this.entityType = entityType;
  }

  /**
   * テーブルの物理名に関するエラーメッセージを返す。
   *
   * @return physicalNameError テーブルの物理名に関するエラーメッセージ
   */
  public String getPhysicalNameError() {
    return physicalNameError;
  }

  /**
   * テーブルの物理名に関するエラーメッセージを設定する。
   *
   * @param physicalNameError エラーメッセージとして設定する文字列を指定する。
   */
  public void setPhysicalNameError(String physicalNameError) {
    this.physicalNameError = physicalNameError;
  }

  /**
   * テーブルの論理名に関するエラーメッセージを返す。
   *
   * @return logicalNameError テーブルの論理名に関するエラーメッセージ
   */
  public String getLogicalNameError() {
    return logicalNameError;
  }

  /**
   * テーブルの論理名に関するエラーメッセージを設定する。
   *
   * @param logicalNameError エラーメッセージとして設定する文字列を指定する。
   */
  public void setLogicalNameError(String logicalNameError) {
    this.logicalNameError = logicalNameError;
  }
}
