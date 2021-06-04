package db_design_tool.app.table_definition;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableMaster;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;

/**
 * テーブル定義入力画面から送信されたデータのバリデーションチェックを管理する。
 */
public class TableDefinitionValidator {
  protected final Validator validator;

  /**
   * 初期化処理を実行する。
   */
  public TableDefinitionValidator() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  /**
   * TableMaster のバリデーションチェックを実行する。
   *
   * @param tableMaster チェック対象とする TableMaster を指定する。
   * @return hasError エラーの有無を真偽値で返す。
   *         <ul>
   *         <li>true: エラーが有ることを表す。</li>
   *         <li>false: エラーが無いことを表す。</li>
   *         </ul>
   */
  public boolean validateTableMaster(TableMaster tableMaster) {
    boolean hasError = false;
    final Set<ConstraintViolation<TableMaster>> tableMasterViolation =
        validator.validate(tableMaster);

    if (tableMasterViolation.size() > 0) {
      hasError = true;

      tableMasterViolation.stream().forEach(e -> {
        final String path = e.getPropertyPath().toString();

        if ("physicalName".equals(path)) {
          tableMaster.setPhysicalNameError(e.getMessage());
        } else if ("logicalName".equals(path)) {
          tableMaster.setLogicalNameError(e.getMessage());
        }
      });
    }

    return hasError;
  }

  /**
   * FieldMaster のバリデーションチェックを実行する。
   *
   * @param fieldMaster チェック対象とする FieldMaster を指定する。
   * @return hasError エラーの有無を真偽値で返す。
   *         <ul>
   *         <li>true: エラーが有ることを表す。</li>
   *         <li>false: エラーが無いことを表す。</li>
   *         </ul>
   */
  public boolean validateFieldMaster(FieldMaster fieldMaster) {
    boolean hasError = false;
    final Set<ConstraintViolation<FieldMaster>> fieldMasterViolation =
        validator.validate(fieldMaster);

    if (fieldMasterViolation.size() > 0) {
      hasError = true;

      fieldMasterViolation.stream().forEach(e -> {
        final String path = e.getPropertyPath().toString();

        if ("physicalName".equals(path)) {
          fieldMaster.setPhysicalNameError(e.getMessage());
        } else if ("logicalName".equals(path)) {
          fieldMaster.setLogicalNameError(e.getMessage());
        } else if ("description".equals(path)) {
          fieldMaster.setDescriptionError(e.getMessage());
        }
      });

      if (fieldMaster.getDataSizeError() != null) {
        hasError = true;
      }
    }

    return hasError;
  }
}
