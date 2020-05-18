package db_design_tool.domain.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class FieldSourceDefinition extends FieldMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    private int definitionId;
    @Size(min = 1, max = 255)
    private String sourceDefinition;
    private String sourceDefinitionError;

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

    /**
     * @return sourceDefinitionError データ取得元の定義に関するエラーメッセージを返す。
     */
    public String getSourceDefinitionError() {
        return sourceDefinitionError;
    }

    /**
     * @param sourceDefinitionError
     *            データ取得元の定義に関するエラーメッセージを指定する。
     */
    public void setSourceDefinitionError(String sourceDefinitionError) {
        this.sourceDefinitionError = sourceDefinitionError;
    }
}
