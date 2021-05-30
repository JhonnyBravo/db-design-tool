package db_design_tool.domain.model;

/**
 * select_definition のエンティティ。
 */
public class SelectDefinition extends TableDefinition {
  private static final long serialVersionUID = 1L;

  private TableSourceDefinition[] tableSourceDefinition;
  private FieldSourceDefinition[] fieldSourceDefinition;

  /**
   * 参照元テーブルの定義を返す。
   *
   * @return tableSourceDefinition 参照元テーブルの定義
   */
  public TableSourceDefinition[] getTableSourceDefinition() {
    return tableSourceDefinition;
  }

  /**
   * 参照元テーブルの定義を設定する。
   *
   * @param tableSourceDefinition 参照元テーブルの定義を指定する。
   */
  public void setTableSourceDefinition(TableSourceDefinition[] tableSourceDefinition) {
    this.tableSourceDefinition = tableSourceDefinition;
  }

  /**
   * 参照元フィールドの定義を返す。
   *
   * @return fieldSourceDefinition 参照元フィールドの定義
   */
  public FieldSourceDefinition[] getFieldSourceDefinition() {
    return fieldSourceDefinition;
  }

  /**
   * 参照元フィールドの定義を設定する。
   *
   * @param fieldSourceDefinition 参照元フィールドの定義を指定する。
   */
  public void setFieldSourceDefinition(FieldSourceDefinition[] fieldSourceDefinition) {
    this.fieldSourceDefinition = fieldSourceDefinition;
  }
}
