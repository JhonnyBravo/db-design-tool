package db_design_tool.domain.model;

import java.io.Serializable;

/**
 * data_type_master のエンティティ。
 */
public class DataTypeMaster implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String typeName;

  /**
   * データ型の ID を返す。
   *
   * @return id データ型の ID
   */
  public int getId() {
    return id;
  }

  /**
   * データ型の ID を設定する。
   *
   * @param id データ型の ID として設定する数値を指定する。
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * 型名を返す。
   *
   * @return typeName 型名
   */
  public String getTypeName() {
    return typeName;
  }

  /**
   * 型名を設定する。
   *
   * @param typeName 型名として設定する文字列を指定する。
   */
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
}
