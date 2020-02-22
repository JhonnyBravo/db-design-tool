package db_design_tool.app.table_definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

        final String tableId = request.getParameter("tableId");

        if (tableId != null && !tableId.isEmpty()) {
            tableMaster.setTableId(Integer.parseInt(tableId));
        }

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
        final String tableId = request.getParameter("tableId");

        if (definitionMap.containsKey("no")) {
            final List<FieldMaster> fieldMasterList = new ArrayList<>();

            for (int i = 0; i < definitionMap.get("no").length; i++) {
                final FieldMaster fieldMaster = new FieldMaster();

                if (tableId != null && !tableId.isEmpty()) {
                    fieldMaster.setTableId(Integer.parseInt(tableId));

                    final int fieldId = Integer
                            .parseInt(definitionMap.get("fieldId")[i]);
                    fieldMaster.setFieldId(fieldId);
                }

                final int no = Integer.parseInt(definitionMap.get("no")[i]);
                fieldMaster.setNo(no);

                fieldMaster.setPhysicalName(
                        definitionMap.get("physicalFieldName")[i]);

                fieldMaster.setLogicalName(
                        definitionMap.get("logicalFieldName")[i]);

                int dataType = Integer
                        .parseInt(definitionMap.get("dataType")[i]);
                fieldMaster.setDataType(dataType);

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

    /**
     * フォームから送信されたフィールド定義の変更差分を登録済みフィールド定義へ反映する。
     *
     * @param reffArray
     *            登録済みフィールド定義の配列を指定する。
     * @param diffArray
     *            フォームから送信されたフィールド定義の配列を指定する。
     * @return recordset 変更差分を反映したフィールド定義の配列を返す。
     */
    public FieldMaster[] mergeFieldMaster(FieldMaster[] reffArray,
            FieldMaster[] diffArray) {
        List<FieldMaster> mergedList = new ArrayList<>(
                Arrays.asList(diffArray));

        // 削除フラグの設定
        final List<FieldMaster> reffList = Arrays.asList(reffArray);
        final Iterator<FieldMaster> reffIt = reffList.iterator();

        while (reffIt.hasNext()) {
            final FieldMaster reff = reffIt.next();
            boolean isDeleted = true;

            for (final FieldMaster diff : diffArray) {
                if (reff.getFieldId() == diff.getFieldId()) {
                    isDeleted = false;
                }
            }

            if (isDeleted) {
                reff.setDeleteFlag(1);
                mergedList.add(reff);
            }
        }

        final FieldMaster[] mergedArray = mergedList
                .toArray(new FieldMaster[mergedList.size()]);
        return mergedArray;
    }
}
