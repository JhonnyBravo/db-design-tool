import $ from "jquery";

/**
 * 取得元フィールド定義編集ダイアログのデータを管理する。
 */
export default class FieldSourceDefinitionDialog {
    /**
     * @param el 操作対象とする要素を指定する。
     */
    constructor( el ) {
        this.el = el;
    }

    /**
     * DOM 上のフォームコントロールからデータを取得する。
     * 
     * @returns tableMaster
     * {
     *  tableId: テーブルを識別する為の ID,
     *  physicalName: テーブルの物理名
     * }
     */
    getTableMaster () {
        $( this.el ).data( "tableMaster", {
            tableId: parseInt( $( this.el ).find( "select[name='tableMaster.physicalName']" ).val(), 10 ),
            physicalName: $.trim( $( this.el ).find( "select[name='tableMaster.physicalName'] option:selected" ).text() )
        } );

        return $( this.el ).data( "tableMaster" );
    }

    /**
     * DOM 上のフォームコントロールへデータを出力する。
     *
     * @param tableMaster
     * {
     *  tableId: テーブルを識別する為の ID,
     *  physicalName: テーブルの物理名
     * }
     */
    setTableMaster ( tableMaster ) {
        $( this.el ).find( "select[name='tableMaster.physicalName']" ).val( tableMaster.tableId );
    }

    /**
     * DOM 上のフォームコントロールからデータを取得する。
     * 
     * @returns fieldMaster
     * {
     *  fieldId: フィールドを識別する為の ID,
     *  physicalName: フィールドの物理名
     * }
     */
    getFieldMaster () {
        $( this.el ).data( "fieldMaster", {
            fieldId: parseInt( $( this.el ).find( "select[name='fieldMaster.physicalName']" ).val(), 10 ),
            physicalName: $.trim( $( this.el ).find( "select[name='fieldMaster.physicalName'] option:selected" ).text() )
        } );

        return $( this.el ).data( "fieldMaster" );
    }

    /**
     * DOM 上のフォームコントロールへデータを出力する。
     *
     * @param fieldMaster
     * {
     *  fieldId: フィールドを識別する為の ID,
     *  physicalName: フィールドの物理名
     * }
     */
    setFieldMaster ( fieldMaster ) {
        $( this.el ).find( "select[name='fieldMaster.physicalName']" ).val( fieldMaster.fieldId );
    }
};