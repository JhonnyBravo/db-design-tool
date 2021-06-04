package db_design_tool.domain.model;

/**
 * select_definition のエンティティ。
 */
public class SelectDefinition extends TableDefinition {
  private static final long serialVersionUID = 1L;

  private TableSourceDefinition[] tableSourceDefinition;
  private FieldSourceDefinition[] fieldSourceDefinition;

  /**
   * {@link TableSourceDefinition} を返す。
   *
   * @return tableSourceDefinition {@link TableSourceDefinition}
   */
  public TableSourceDefinition[] getTableSourceDefinition() {
    return tableSourceDefinition;
  }

  /**
   * {@link TableSourceDefinition} を設定する。
   *
   * @param tableSourceDefinition {@link TableSourceDefinition} を指定する。
   */
  public void setTableSourceDefinition(TableSourceDefinition[] tableSourceDefinition) {
    this.tableSourceDefinition = tableSourceDefinition;
  }

  /**
   * {@link FieldSourceDefinition} を返す。
   *
   * @return fieldSourceDefinition {@link FieldSourceDefinition} を返す。
   */
  public FieldSourceDefinition[] getFieldSourceDefinition() {
    return fieldSourceDefinition;
  }

  /**
   * {@link FieldSourceDefinition} を設定する。
   *
   * @param fieldSourceDefinition {@link FieldSourceDefinition} を指定する。
   */
  public void setFieldSourceDefinition(FieldSourceDefinition[] fieldSourceDefinition) {
    this.fieldSourceDefinition = fieldSourceDefinition;
  }
}
