import $ from "jquery";
import TableMaster from "../../model/table_master";

/**
 * table_master のレコード操作を管理する。
 */
export default class TableMasterService {
    /**
     * @param el 操作対象とする要素を指定する。
     */
    constructor( el ) {
        this.el = el;
    }

    /**
     * レコードを削除する。
     */
    delete () {
        var tableMaster = new TableMaster( this.el );
        var data = tableMaster.getData();
        data.deleteFlag = 1;
        tableMaster.setData( data );
        $( this.el ).submit();
    }
};