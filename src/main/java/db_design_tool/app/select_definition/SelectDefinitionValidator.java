package db_design_tool.app.select_definition;

import db_design_tool.app.table_definition.TableDefinitionValidator;
import db_design_tool.domain.model.FieldSourceDefinition;
import db_design_tool.domain.model.TableSourceDefinition;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

/**
 * クエリ定義入力画面のバリデーションチェックを管理する。
 */
public class SelectDefinitionValidator extends TableDefinitionValidator {
  /**
   * TableSourceDefinition のバリデーションチェックを実行する。
   *
   * @param tableSourceDefinition チェック対象とする TableSourceDefinition を指定する。
   * @return hasError エラーの有無を真偽値で返す。
   *         <ul>
   *         <li>true: エラーが有ることを表す。</li>
   *         <li>false: エラーが無いことを表す。</li>
   *         </ul>
   */
  public boolean validateTableSourceDefinition(TableSourceDefinition tableSourceDefinition) {
    boolean hasError = false;
    final Set<ConstraintViolation<TableSourceDefinition>> tableSourceDefinitionViolation =
        validator.validate(tableSourceDefinition);

    if (tableSourceDefinitionViolation.size() > 0) {
      hasError = true;

      tableSourceDefinitionViolation.stream().forEach(e -> {
        final String path = e.getPropertyPath().toString();

        if ("joinCondition".equals(path)) {
          tableSourceDefinition.setJoinConditionError(e.getMessage());
        }
      });
    }

    return hasError;
  }

  /**
   * FieldMaster のバリデーションチェックを実行する。
   *
   * @param fieldSourceDefinition チェック対象とする FieldMaster を指定する。
   * @return hasError エラーの有無を真偽値で返す。
   *         <ul>
   *         <li>true: エラーが有ることを表す。</li>
   *         <li>false: エラーが無いことを表す。</li>
   *         </ul>
   */
  public boolean validateFieldSourceDefinition(FieldSourceDefinition fieldSourceDefinition) {
    boolean hasError = false;
    final Set<ConstraintViolation<FieldSourceDefinition>> fieldSourceDefinitionViolation =
        validator.validate(fieldSourceDefinition);

    if (fieldSourceDefinitionViolation.size() > 0) {
      hasError = true;

      fieldSourceDefinitionViolation.stream().forEach(e -> {
        final String path = e.getPropertyPath().toString();

        if ("physicalName".equals(path)) {
          fieldSourceDefinition.setPhysicalNameError(e.getMessage());
        } else if ("logicalName".equals(path)) {
          fieldSourceDefinition.setLogicalNameError(e.getMessage());
        } else if ("description".equals(path)) {
          fieldSourceDefinition.setDescriptionError(e.getMessage());
        } else if ("sourceDefinition".equals(path)) {
          fieldSourceDefinition.setSourceDefinitionError(e.getMessage());
        }
      });

      if (fieldSourceDefinition.getDataSizeError() != null) {
        hasError = true;
      }
    }

    return hasError;
  }
}
