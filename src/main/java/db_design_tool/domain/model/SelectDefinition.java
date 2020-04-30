package db_design_tool.domain.model;

public class SelectDefinition extends TableDefinition {
    private static final long serialVersionUID = 1L;

    private TableSourceDefinition[] tableSourceDefinition;
    private FieldSourceDefinition[] fieldSourceDefinition;

    /**
     * @return tableSourceDefinition TableSourceDefinition を返す。
     */
    public TableSourceDefinition[] getTableSourceDefinition() {
        return tableSourceDefinition;
    }

    /**
     * @param tableSourceDefinition
     *            TableSourceDefinition を指定する。
     */
    public void setTableSourceDefinition(
            TableSourceDefinition[] tableSourceDefinition) {
        this.tableSourceDefinition = tableSourceDefinition;
    }

    /**
     * @return fieldSourceDefinition FieldSourceDefinition を返す。
     */
    public FieldSourceDefinition[] getFieldSourceDefinition() {
        return fieldSourceDefinition;
    }

    /**
     * @param fieldSourceDefinition
     *            FieldSourceDefinition を指定する。
     */
    public void setFieldSourceDefinition(
            FieldSourceDefinition[] fieldSourceDefinition) {
        this.fieldSourceDefinition = fieldSourceDefinition;
    }
}
