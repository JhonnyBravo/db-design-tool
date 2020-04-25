package db_design_tool.domain.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class FieldSourceDefinition implements Serializable {
    private static final long serialVersionUID = 1L;

    private int fieldId;
    private int definitionId;
    @Size(min = 1, max = 255)
    private String sourceDefinition;

    /**
     * @return fieldId 定義が紐づくフィールドの ID を返す。
     */
    public int getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     *            定義が紐づくフィールドの ID を指定する。
     */
    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return definitionId 定義の ID を返す。
     */
    public int getDefinitionId() {
        return definitionId;
    }

    /**
     * @param definitionId
     *            定義の ID を指定する。
     */
    public void setDefinitionId(int definitionId) {
        this.definitionId = definitionId;
    }

    /**
     * @return sourceDefinition データ取得元の定義を返す。
     */
    public String getSourceDefinition() {
        return sourceDefinition;
    }

    /**
     * @param sourceDefinition
     *            データ取得元の定義を指定する。
     */
    public void setSourceDefinition(String sourceDefinition) {
        this.sourceDefinition = sourceDefinition;
    }
}
