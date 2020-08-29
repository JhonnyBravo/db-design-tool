package db_design_tool.app.table_definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        final String tableId = request.getParameter("tableMaster.tableId");

        if (tableId != null && !tableId.isEmpty()) {
            tableMaster.setTableId(Integer.parseInt(tableId));
        }

        tableMaster.setPhysicalName(
                request.getParameter("tableMaster.physicalName"));
        tableMaster.setLogicalName(
                request.getParameter("tableMaster.logicalName"));

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
        final String tableId = request.getParameter("tableMaster.tableId");

        if (definitionMap.containsKey("fieldMaster.no")) {
            final List<FieldMaster> fieldMasterList = new ArrayList<>();

            for (int i = 0; i < definitionMap
                    .get("fieldMaster.no").length; i++) {
                final FieldMaster fieldMaster = new FieldMaster();

                if (tableId != null && !tableId.isEmpty()) {
                    fieldMaster.setTableId(Integer.parseInt(tableId));

                    final int fieldId = Integer.parseInt(
                            definitionMap.get("fieldMaster.fieldId")[i]);
                    fieldMaster.setFieldId(fieldId);
                }

                final int no = Integer
                        .parseInt(definitionMap.get("fieldMaster.no")[i]);
                fieldMaster.setNo(no);

                fieldMaster.setPhysicalName(
                        definitionMap.get("fieldMaster.physicalName")[i]);

                fieldMaster.setLogicalName(
                        definitionMap.get("fieldMaster.logicalName")[i]);

                int dataType = Integer
                        .parseInt(definitionMap.get("fieldMaster.dataType")[i]);
                fieldMaster.setDataType(dataType);

                if (!definitionMap.get("fieldMaster.dataSize")[i].isEmpty()) {
                    if (!definitionMap.get("fieldMaster.dataSize")[i]
                            .matches("[0-9]*")) {
                        fieldMaster.setDataSizeError("数値を指定してください。");
                    } else {
                        final int dataSize = Integer.parseInt(
                                definitionMap.get("fieldMaster.dataSize")[i]);
                        fieldMaster.setDataSize(dataSize);
                    }
                }

                fieldMaster.setDescription(
                        definitionMap.get("fieldMaster.description")[i]);

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

        List<FieldMaster> reffList = new ArrayList<>();
        reffList.addAll(Arrays.asList(reffArray));

        // 削除フラグの設定
        reffList.forEach(reff -> {
            boolean isDeleted = true;

            for (FieldMaster diff : diffArray) {
                if (reff.getFieldId() == diff.getFieldId()) {
                    isDeleted = false;
                }
            }

            if (isDeleted) {
                reff.setDeleteFlag(1);
            }
        });

        // 削除されたレコードの抽出
        List<FieldMaster> deletedList = reffList.stream()
                .filter(fieldMaster -> (fieldMaster.getDeleteFlag() == 1))
                .collect(Collectors.toList());

        // マージ処理
        List<FieldMaster> mergedList = new ArrayList<>();
        mergedList.addAll(Arrays.asList(diffArray));
        mergedList.addAll(deletedList);

        final FieldMaster[] mergedArray = mergedList
                .toArray(new FieldMaster[mergedList.size()]);
        return mergedArray;
    }
}
