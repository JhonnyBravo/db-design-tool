package db_design_tool.app.select_definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        final String tableId = request.getParameter("tableId");

        if (definitionMap.containsKey("joinNo")) {
            final List<TableSourceDefinition> tableSourceDefinitionList = new ArrayList<>();

            for (int i = 0; i < definitionMap.get("joinNo").length; i++) {
                final TableSourceDefinition tableSourceDefinition = new TableSourceDefinition();

                if (tableId != null && !tableId.isEmpty()) {
                    tableSourceDefinition.setTableId(Integer.parseInt(tableId));
                    tableSourceDefinition.setDefinitionId(Integer.parseInt(
                            definitionMap.get("joinDefinitionId")[i]));
                }

                final int no = Integer.parseInt(definitionMap.get("joinNo")[i]);
                tableSourceDefinition.setNo(no);

                final int sourceId = Integer
                        .parseInt(definitionMap.get("joinTableId")[i]);
                tableSourceDefinition.setSourceId(sourceId);

                tableSourceDefinition.setJoinCondition(
                        definitionMap.get("joinCondition")[i]);

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
        final String tableId = request.getParameter("tableId");

        if (definitionMap.containsKey("no")) {
            final List<FieldSourceDefinition> fieldSourceDefinitionList = new ArrayList<>();

            for (int i = 0; i < definitionMap.get("no").length; i++) {
                final FieldSourceDefinition fieldSourceDefinition = new FieldSourceDefinition();

                if (tableId != null && !tableId.isEmpty()) {
                    int fieldId = Integer
                            .parseInt(definitionMap.get("fieldId")[i]);
                    fieldSourceDefinition.setFieldId(fieldId);

                    int definitionId = Integer.parseInt(
                            definitionMap.get("fieldDefinitionId")[i]);
                    fieldSourceDefinition.setDefinitionId(definitionId);
                }

                fieldSourceDefinition.setSourceDefinition(
                        definitionMap.get("recordSource")[i]);

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
        List<TableSourceDefinition> mergedList = new ArrayList<>(
                Arrays.asList(diffArray));

        // 削除フラグの設定
        final List<TableSourceDefinition> reffList = Arrays.asList(reffArray);
        final Iterator<TableSourceDefinition> reffIt = reffList.iterator();

        while (reffIt.hasNext()) {
            final TableSourceDefinition reff = reffIt.next();
            boolean isDeleted = true;

            for (final TableSourceDefinition diff : diffArray) {
                if (reff.getDefinitionId() == diff.getDefinitionId()) {
                    isDeleted = false;
                }
            }

            if (isDeleted) {
                reff.setDeleteFlag(1);
                mergedList.add(reff);
            }
        }

        final TableSourceDefinition[] mergedArray = mergedList
                .toArray(new TableSourceDefinition[mergedList.size()]);
        return mergedArray;
    }
}
