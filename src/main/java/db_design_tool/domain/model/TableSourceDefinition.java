package db_design_tool.domain.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class TableSourceDefinition implements Serializable {
    private static final long serialVersionUID = 1L;

    private int tableId;
    private int definitionId;
    private int no;
    private int sourceId;
    @Size(min = 0, max = 255)
    private String joinCondition;
    @Range(min = 0, max = 1)
    private int deleteFlag;

    /**
     * @return tableId 定義が紐づくテーブルの ID を返す。
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     *            定義が紐づくテーブルの ID を指定する。
     */
    public void setTableId(int tableId) {
        this.tableId = tableId;
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
     * @return sourceId 取得元テーブルの ID を返す。
     */
    public int getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId
     *            取得元テーブルの ID を指定する。
     */
    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return no no を返す。
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no
     *            no を指定する。
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * @return joinCondition 結合条件を返す。
     */
    public String getJoinCondition() {
        return joinCondition;
    }

    /**
     * @param joinCondition
     *            結合条件を指定する。
     */
    public void setJoinCondition(String joinCondition) {
        this.joinCondition = joinCondition;
    }

    /**
     * @return deleteFlag 削除フラグを返す。
     */
    public int getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @param deleteFlag
     *            削除フラグを指定する。
     *            <ul>
     *            <li>0: 定義が削除されていないことを表す。</li>
     *            <li>1: 定義が削除されていることを表す。</li>
     *            </ul>
     */
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
