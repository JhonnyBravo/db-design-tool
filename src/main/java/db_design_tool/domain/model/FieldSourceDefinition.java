package db_design_tool.domain.model;

import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * table_source_definition のエンティティ。
 */
public class FieldSourceDefinition extends FieldMaster implements Serializable {
  private static final long serialVersionUID = 1L;

  private int definitionId;
  @Size(min = 1, max = 255)
  private String sourceDefinition;
  private String sourceDefinitionError;

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
   * データ取得元の定義を返す。
   *
   * @return sourceDefinition データ取得元の定義を返す。
   */
  public String getSourceDefinition() {
    return sourceDefinition;
  }

  /**
   * データ取得元の定義を設定する。
   *
   * @param sourceDefinition データ取得元の定義として設定する文字列を指定する。
   */
  public void setSourceDefinition(String sourceDefinition) {
    this.sourceDefinition = sourceDefinition;
  }

  /**
   * データ取得元の定義に関するエラーメッセージを返す。
   *
   * @return sourceDefinitionError データ取得元の定義に関するエラーメッセージ
   */
  public String getSourceDefinitionError() {
    return sourceDefinitionError;
  }

  /**
   * データ取得元の定義に関するエラーメッセージを設定する。
   *
   * @param sourceDefinitionError エラーメッセージとして設定する文字列を指定する。
   */
  public void setSourceDefinitionError(String sourceDefinitionError) {
    this.sourceDefinitionError = sourceDefinitionError;
  }
}
