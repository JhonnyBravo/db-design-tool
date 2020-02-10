package db_design_tool.domain.model;

import java.io.Serializable;

public class TableDefinition implements Serializable {
    private static final long serialVersionUID = 1L;

    private TableMaster tableMaster;
    private FieldMaster[] fieldMaster;

    /**
     * @return tableMaster TableMaster を返す。
     */
    public TableMaster getTableMaster() {
        return tableMaster;
    }

    /**
     * @param tableMaster
     *            TableMaster を指定する。
     */
    public void setTableMaster(TableMaster tableMaster) {
        this.tableMaster = tableMaster;
    }

    /**
     * @return fieldMaster FieldMaster の配列を返す。
     */
    public FieldMaster[] getFieldMaster() {
        return fieldMaster;
    }

    /**
     * @param fieldMaster
     *            FieldMaster の配列を指定する。
     */
    public void setFieldMaster(FieldMaster[] fieldMaster) {
        this.fieldMaster = fieldMaster;
    }
}
