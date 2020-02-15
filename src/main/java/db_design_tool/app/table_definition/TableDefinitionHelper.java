package db_design_tool.app.table_definition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableMaster;

public class TableDefinitionHelper {
    /**
     * フォームから受け取った入力値を TableMaster へ変換する。
     *
     * @param request
     *            フォームから受け取ったリクエストを指定する。
     * @return tableMaster 変換された TableMaster を返す。
     */
    public TableMaster convertToTableMaster(HttpServletRequest request) {
        final TableMaster tableMaster = new TableMaster();

        tableMaster.setPhysicalName(request.getParameter("physicalTableName"));
        tableMaster.setLogicalName(request.getParameter("logicalTableName"));

        return tableMaster;
    }

    /**
     * フォームから受け取った入力値を FieldMaster の配列へ変換する。
     *
     * @param request
     *            フォームから受け取ったリクエストを指定する。
     * @return fieldMaster 変換された FieldMaster の配列を返す。
     */
    public FieldMaster[] convertToFieldMaster(HttpServletRequest request) {
        final Map<String, String[]> definitionMap = request.getParameterMap();
        FieldMaster[] fieldMasterArray = null;

        if (definitionMap.containsKey("no")) {
            final List<FieldMaster> fieldMasterList = new ArrayList<>();

            for (int i = 0; i < definitionMap.get("no").length; i++) {
                final FieldMaster fieldMaster = new FieldMaster();
                final int no = Integer.parseInt(definitionMap.get("no")[i]);

                fieldMaster.setNo(no);
                fieldMaster.setPhysicalName(
                        definitionMap.get("physicalFieldName")[i]);
                fieldMaster.setLogicalName(
                        definitionMap.get("logicalFieldName")[i]);
                fieldMaster.setDataType(definitionMap.get("dataType")[i]);

                if (!definitionMap.get("dataSize")[i].isEmpty()) {
                    if (!definitionMap.get("dataSize")[i].matches("[0-9]*")) {
                        fieldMaster.setDataSizeError("数値を指定してください。");
                    } else {
                        final int dataSize = Integer
                                .parseInt(definitionMap.get("dataSize")[i]);
                        fieldMaster.setDataSize(dataSize);
                    }
                }

                fieldMaster.setDescription(definitionMap.get("description")[i]);

                fieldMasterList.add(fieldMaster);
            }

            fieldMasterArray = fieldMasterList
                    .toArray(new FieldMaster[fieldMasterList.size()]);
        }

        return fieldMasterArray;
    }
}
