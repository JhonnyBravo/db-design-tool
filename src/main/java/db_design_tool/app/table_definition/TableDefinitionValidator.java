package db_design_tool.app.table_definition;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import db_design_tool.domain.model.FieldMaster;
import db_design_tool.domain.model.TableMaster;

public class TableDefinitionValidator {
    private final Validator validator;

    public TableDefinitionValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * TableMaster のバリデーションチェックを実行する。
     *
     * @param tableMaster
     *            チェック対象とする TableMaster を指定する。
     * @return hasError エラーの有無を真偽値で返す。
     *         <ul>
     *         <li>true: エラーが有ることを表す。</li>
     *         <li>false: エラーが無いことを表す。</li>
     *         </ul>
     */
    public boolean validateTableMaster(TableMaster tableMaster) {
        boolean hasError = false;
        final Set<ConstraintViolation<TableMaster>> tableMasterViolation = validator
                .validate(tableMaster);

        if (tableMasterViolation.size() > 0) {
            hasError = true;

            tableMasterViolation.stream().forEach(e -> {
                final String path = e.getPropertyPath().toString();

                if (path.equals("physicalName")) {
                    tableMaster.setPhysicalNameError(e.getMessage());
                } else if (path.equals("logicalName")) {
                    tableMaster.setLogicalNameError(e.getMessage());
                }
            });
        }

        return hasError;
    }

    /**
     * FieldMaster のバリデーションチェックを実行する。
     *
     * @param fieldMaster
     *            チェック対象とする FieldMaster を指定する。
     * @return hasError エラーの有無を真偽値で返す。
     *         <ul>
     *         <li>true: エラーが有ることを表す。</li>
     *         <li>false: エラーが無いことを表す。</li>
     *         </ul>
     */
    public boolean validateFieldMaster(FieldMaster fieldMaster) {
        boolean hasError = false;
        final Set<ConstraintViolation<FieldMaster>> fieldMasterViolation = validator
                .validate(fieldMaster);

        if (fieldMasterViolation.size() > 0) {
            hasError = true;

            fieldMasterViolation.stream().forEach(e -> {
                final String path = e.getPropertyPath().toString();

                if (path.equals("physicalName")) {
                    fieldMaster.setPhysicalNameError(e.getMessage());
                } else if (path.equals("logicalName")) {
                    fieldMaster.setLogicalNameError(e.getMessage());
                } else if (path.equals("description")) {
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
