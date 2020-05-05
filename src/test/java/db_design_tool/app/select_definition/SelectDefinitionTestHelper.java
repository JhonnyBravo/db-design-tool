package db_design_tool.app.select_definition;

import db_design_tool.app.table_definition.TableDefinitionTestHelper;
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.TableSourceDefinition;

public class SelectDefinitionTestHelper extends TableDefinitionTestHelper {
    /**
     * @param params
     *            {tableId, no, sourceId, joinCondition, deleteFlag}
     * @return tableSourceDefinition
     */
    public TableSourceDefinition createTableSourceDefinition(String[] params) {
        TableSourceDefinition tableSourceDefinition = new TableSourceDefinition();

        tableSourceDefinition.setTableId(Integer.parseInt(params[0]));
        tableSourceDefinition.setNo(Integer.parseInt(params[1]));
        tableSourceDefinition.setSourceId(Integer.parseInt(params[2]));
        tableSourceDefinition.setJoinCondition(params[3]);

        if (params.length == 5) {
            tableSourceDefinition.setDeleteFlag(Integer.parseInt(params[4]));
        }

        return tableSourceDefinition;
    }

    /**
     * @param params
     *            {fieldId, sourceDefinition}
     * @return fieldSourceDefinition
     */
    public FieldSourceDefinition createFieldSourceDefinition(String[] params) {
        FieldSourceDefinition fieldSourceDefinition = new FieldSourceDefinition();

        fieldSourceDefinition.setFieldId(Integer.parseInt(params[0]));
        fieldSourceDefinition.setSourceDefinition(params[1]);

        return fieldSourceDefinition;
    }
}
