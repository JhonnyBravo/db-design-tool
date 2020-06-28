package db_design_tool.domain.model;

import java.io.Serializable;

public class DataTypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String typeName;

    /**
     * @return id id を返す。
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            id を指定する。
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return typeName 型名を返す。
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName
     *            型名を指定する。
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
