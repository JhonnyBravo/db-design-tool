package db_design_tool.app.table_definition;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableMaster;

public class TableDefinitionTestHelper {
    /**
     * @param params
     *            {phisicalName, logicalName, deleteFlag}
     * @return tableMaster
     */
    public TableMaster createTableMaster(String[] params) {
        TableMaster tableMaster = new TableMaster();

        tableMaster.setPhysicalName(params[0]);
        tableMaster.setLogicalName(params[1]);

        if (params.length == 3) {
            tableMaster.setDeleteFlag(Integer.parseInt(params[2]));
        }

        return tableMaster;
    }

    /**
     * @param params
     *            {tableId, no, physicalName, logicalName, description,
     *            deleteFlag}
     * @return fieldMaster
     */
    public FieldMaster createFieldMaster(String[] params) {
        FieldMaster fieldMaster = new FieldMaster();

        fieldMaster.setTableId(Integer.parseInt(params[0]));
        fieldMaster.setNo(Integer.parseInt(params[1]));
        fieldMaster.setPhysicalName(params[2]);
        fieldMaster.setLogicalName(params[3]);
        fieldMaster.setDescription(params[4]);
        fieldMaster.setDataType(1);

        if (params.length == 6) {
            fieldMaster.setDeleteFlag(Integer.parseInt(params[5]));
        }

        return fieldMaster;
    }
}
