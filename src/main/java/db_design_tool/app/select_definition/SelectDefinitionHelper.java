package db_design_tool.app.select_definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import db_design_tool.app.table_definition.TableDefinitionHelper;
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.TableSourceDefinition;

public class SelectDefinitionHelper extends TableDefinitionHelper {
    /**
     * フォームから受け取った入力値を TableSourceDefinition の配列へ変換する。
     *
     * @param request
     *            フォームから受け取ったリクエストを指定する。
     * @return tableSourceDefinition 変換された TableSourceDefinition の配列を返す。
     */
    public TableSourceDefinition[] convertToTableSourceDefinition(
            HttpServletRequest request) {
        final Map<String, String[]> definitionMap = request.getParameterMap();
        TableSourceDefinition[] tableSourceDefinitionArray = null;
        final String tableId = request.getParameter("tableMaster.tableId");

        if (definitionMap.containsKey("tableSourceDefinition.no")) {
            final List<TableSourceDefinition> tableSourceDefinitionList = new ArrayList<>();

            for (int i = 0; i < definitionMap
                    .get("tableSourceDefinition.no").length; i++) {
                final TableSourceDefinition tableSourceDefinition = new TableSourceDefinition();

                if (tableId != null && !tableId.isEmpty()) {
                    tableSourceDefinition.setTableId(Integer.parseInt(tableId));
                    tableSourceDefinition
                            .setDefinitionId(Integer.parseInt(definitionMap.get(
                                    "tableSourceDefinition.definitionId")[i]));
                }

                final int no = Integer.parseInt(
                        definitionMap.get("tableSourceDefinition.no")[i]);
                tableSourceDefinition.setNo(no);

                final int sourceId = Integer.parseInt(
                        definitionMap.get("tableSourceDefinition.sourceId")[i]);
                tableSourceDefinition.setSourceId(sourceId);

                tableSourceDefinition.setSourceName(definitionMap
                        .get("tableSourceDefinition.sourceName")[i]);

                tableSourceDefinition.setJoinCondition(definitionMap
                        .get("tableSourceDefinition.joinCondition")[i]);

                tableSourceDefinitionList.add(tableSourceDefinition);
            }

            tableSourceDefinitionArray = tableSourceDefinitionList
                    .toArray(new TableSourceDefinition[tableSourceDefinitionList
                            .size()]);
        }

        return tableSourceDefinitionArray;
    }

    /**
     * フォームから受け取った入力値を FieldSourceDefinition の配列へ変換する。
     *
     * @param request
     *            フォームから受け取ったリクエストを指定する。
     * @return fieldSourceDefinition 変換された FieldSourceDefinition の配列を返す。
     */
    public FieldSourceDefinition[] convertToFieldSourceDefinition(
            HttpServletRequest request) {
        final Map<String, String[]> definitionMap = request.getParameterMap();
        FieldSourceDefinition[] fieldSourceDefinitionArray = null;
        final String tableId = request.getParameter("tableMaster.tableId");

        if (definitionMap.containsKey("fieldSourceDefinition.no")) {
            final List<FieldSourceDefinition> fieldSourceDefinitionList = new ArrayList<>();

            for (int i = 0; i < definitionMap
                    .get("fieldSourceDefinition.no").length; i++) {
                final FieldSourceDefinition fieldSourceDefinition = new FieldSourceDefinition();

                if (tableId != null && !tableId.isEmpty()) {
                    fieldSourceDefinition.setTableId(Integer.parseInt(tableId));

                    final int fieldId = Integer.parseInt(definitionMap
                            .get("fieldSourceDefinition.fieldId")[i]);
                    fieldSourceDefinition.setFieldId(fieldId);

                    final int definitionId = Integer.parseInt(definitionMap
                            .get("fieldSourceDefinition.definitionId")[i]);
                    fieldSourceDefinition.setDefinitionId(definitionId);
                }

                fieldSourceDefinition.setSourceDefinition(definitionMap
                        .get("fieldSourceDefinition.sourceDefinition")[i]);

                final int no = Integer.parseInt(
                        definitionMap.get("fieldSourceDefinition.no")[i]);
                fieldSourceDefinition.setNo(no);

                fieldSourceDefinition.setPhysicalName(definitionMap
                        .get("fieldSourceDefinition.physicalName")[i]);

                fieldSourceDefinition.setLogicalName(definitionMap
                        .get("fieldSourceDefinition.logicalName")[i]);

                int dataType = Integer.parseInt(
                        definitionMap.get("fieldSourceDefinition.dataType")[i]);
                fieldSourceDefinition.setDataType(dataType);

                if (!definitionMap.get("fieldSourceDefinition.dataSize")[i]
                        .isEmpty()) {
                    if (!definitionMap.get("fieldSourceDefinition.dataSize")[i]
                            .matches("[0-9]*")) {
                        fieldSourceDefinition.setDataSizeError("数値を指定してください。");
                    } else {
                        final int dataSize = Integer.parseInt(definitionMap
                                .get("fieldSourceDefinition.dataSize")[i]);
                        fieldSourceDefinition.setDataSize(dataSize);
                    }
                }

                fieldSourceDefinition.setDescription(definitionMap
                        .get("fieldSourceDefinition.description")[i]);

                fieldSourceDefinitionList.add(fieldSourceDefinition);
            }

            fieldSourceDefinitionArray = fieldSourceDefinitionList
                    .toArray(new FieldSourceDefinition[fieldSourceDefinitionList
                            .size()]);
        }

        return fieldSourceDefinitionArray;
    }

    /**
     * フォームから送信されたテーブル結合条件の変更差分を登録済み定義へ反映する。
     *
     * @param reffArray
     *            登録済みテーブル結合条件の配列を指定する。
     * @param diffArray
     *            フォームから送信されたテーブル結合条件の配列を指定する。
     * @return recordset 変更差分を反映したテーブル結合条件の配列を返す。
     */
    public TableSourceDefinition[] mergeTableSourceDefinition(
            TableSourceDefinition[] reffArray,
            TableSourceDefinition[] diffArray) {
        List<TableSourceDefinition> reffList = new ArrayList<>();
        reffList.addAll(Arrays.asList(reffArray));

        // 削除フラグの設定
        reffList.forEach(reff -> {
            boolean isDeleted = true;

            for (TableSourceDefinition diff : diffArray) {
                if (reff.getDefinitionId() == diff.getDefinitionId()) {
                    isDeleted = false;
                }
            }

            if (isDeleted) {
                reff.setDeleteFlag(1);
            }
        });

        // 削除されたレコードの抽出
        List<TableSourceDefinition> deletedList = reffList.stream()
                .filter(tableSourceDefinition -> (tableSourceDefinition
                        .getDeleteFlag() == 1))
                .collect(Collectors.toList());

        // マージ処理
        List<TableSourceDefinition> mergedList = new ArrayList<>();
        mergedList.addAll(Arrays.asList(diffArray));
        mergedList.addAll(deletedList);

        final TableSourceDefinition[] mergedArray = mergedList
                .toArray(new TableSourceDefinition[mergedList.size()]);
        return mergedArray;
    }

    /**
     * フォームから送信されたクエリ定義の変更差分を登録済みクエリ定義へ反映する。
     *
     * @param reffArray
     *            登録済みクエリ定義の配列を指定する。
     * @param diffArray
     *            フォームから送信されたクエリ定義の配列を指定する。
     * @return recordset 変更差分を反映したクエリ定義の配列を返す。
     */
    public FieldSourceDefinition[] mergeFieldSourceDefinition(
            FieldSourceDefinition[] reffArray,
            FieldSourceDefinition[] diffArray) {
        List<FieldSourceDefinition> reffList = new ArrayList<>();
        reffList.addAll(Arrays.asList(reffArray));

        // 削除フラグの設定
        reffList.forEach(reff -> {
            boolean isDeleted = true;

            for (FieldSourceDefinition diff : diffArray) {
                if (reff.getDefinitionId() == diff.getDefinitionId()) {
                    isDeleted = false;
                }
            }

            if (isDeleted) {
                reff.setDeleteFlag(1);
            }
        });

        // 削除されたレコードの抽出
        List<FieldSourceDefinition> deletedList = reffList.stream()
                .filter(fieldSourceDefinition -> (fieldSourceDefinition
                        .getDeleteFlag() == 1))
                .collect(Collectors.toList());

        // マージ処理
        List<FieldSourceDefinition> mergedList = new ArrayList<>();
        mergedList.addAll(Arrays.asList(diffArray));
        mergedList.addAll(deletedList);

        final FieldSourceDefinition[] mergedArray = mergedList
                .toArray(new FieldSourceDefinition[mergedList.size()]);
        return mergedArray;
    }

}
