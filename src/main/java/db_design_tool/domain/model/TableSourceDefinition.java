package db_design_tool.domain.model;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.validator.constraints.Range;

/**
 * table_source_definition のエンティティ。
 */
public class TableSourceDefinition implements Serializable {
  private static final long serialVersionUID = 1L;

  private int tableId;
  private int definitionId;
  private int no;
  private int sourceId;
  private String sourceName;
  @Size(min = 0, max = 255)
  private String joinCondition;
  @Range(min = 0, max = 1)
  private int deleteFlag;
  private String joinConditionError;

  /**
   * 定義が紐づくテーブルの ID を返す。
   *
   * @return tableId 定義が紐づくテーブルの ID
   */
  public int getTableId() {
    return tableId;
  }

  /**
   * 定義が紐づくテーブルの ID を設定する。
   *
   * @param tableId テーブルの ID として設定する数値を指定する。
   */
  public void setTableId(int tableId) {
    this.tableId = tableId;
  }

  /**
   * 定義の ID を返す。
   *
   * @return definitionId 定義の ID
   */
  public int getDefinitionId() {
    return definitionId;
  }

  /**
   * 定義の ID を設定する。
   *
   * @param definitionId 定義の ID として設定する数値を指定する。
   */
  public void setDefinitionId(int definitionId) {
    this.definitionId = definitionId;
  }

  /**
   * 取得元テーブルの ID を返す。
   *
   * @return sourceId 取得元テーブルの ID
   */
  public int getSourceId() {
    return sourceId;
  }

  /**
   * 取得元テーブルの ID を設定する。
   *
   * @param sourceId 取得元テーブルの ID として設定する数値を指定する。
   */
  public void setSourceId(int sourceId) {
    this.sourceId = sourceId;
  }

  /**
   * 取得元テーブルの物理名を返す。
   *
   * @return sourceName 取得元テーブルの物理名
   */
  public String getSourceName() {
    return sourceName;
  }

  /**
   * 取得元テーブルの物理名を設定する。
   *
   * @param sourceName 取得元テーブルの物理名として設定する文字列を指定する。
   */
  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  /**
   * No を返す。
   *
   * @return no
   */
  public int getNo() {
    return no;
  }

  /**
   * No を設定する。
   *
   * @param no no として設定する数値を指定する。
   */
  public void setNo(int no) {
    this.no = no;
  }

  /**
   * 結合条件を返す。
   *
   * @return joinCondition 結合条件
   */
  public String getJoinCondition() {
    return joinCondition;
  }

  /**
   * 結合条件を設定する。
   *
   * @param joinCondition 結合条件として設定する文字列を指定する。
   */
  public void setJoinCondition(String joinCondition) {
    this.joinCondition = joinCondition;
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
   *        <li>0: 定義が削除されていないことを表す。</li>
   *        <li>1: 定義が削除されていることを表す。</li>
   *        </ul>
   */
  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  /**
   * 結合条件に関するエラーメッセージを返す。
   *
   * @return joinConditionError 結合条件に関するエラーメッセージ
   */
  public String getJoinConditionError() {
    return joinConditionError;
  }

  /**
   * 結合条件に関するエラーメッセージを設定する。
   *
   * @param joinConditionError エラーメッセージとして設定する文字列を指定する。
   */
  public void setJoinConditionError(String joinConditionError) {
    this.joinConditionError = joinConditionError;
  }
}
