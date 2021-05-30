package db_design_tool.domain.model;

import java.io.Serializable;

/**
 * table_definition のエンティティ。
 */
public class TableDefinition implements Serializable {
  private static final long serialVersionUID = 1L;

  private TableMaster tableMaster;
  private FieldMaster[] fieldMaster;

  /**
   * {@link TableMaster} を返す。
   *
   * @return tableMaster {@link TableMaster}
   */
  public TableMaster getTableMaster() {
    return tableMaster;
  }

  /**
   * {@link TableMaster} を設定する。
   *
   * @param tableMaster {@link TableMaster}
   */
  public void setTableMaster(TableMaster tableMaster) {
    this.tableMaster = tableMaster;
  }

  /**
   * {@link FieldMaster} の配列を返す。
   *
   * @return fieldMaster {@link FieldMaster} の配列
   */
  public FieldMaster[] getFieldMaster() {
    return fieldMaster;
  }

  /**
   * {@link FieldMaster} を設定する。
   *
   * @param fieldMaster {@link FieldMaster} の配列
   */
  public void setFieldMaster(FieldMaster[] fieldMaster) {
    this.fieldMaster = fieldMaster;
  }
}
