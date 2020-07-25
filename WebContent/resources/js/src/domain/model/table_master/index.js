import $ from "jquery";

/**
 * table_master のレコードを管理する。
 */
export default class TableMaster {
    /**
     * @param el 操作対象とする要素を指定する。
     */
    constructor( el ) {
        this.el = el;
    }

    /**
     * DOM 上のフォームコントロールから取得したデータをオブジェクトに変換して返す。
     * 
     * @returns tableMaster
     * {
     *  tableId: テーブルを識別する為の ID,
     *  physicalName: テーブルの物理名,
     *  logicalName: テーブルの論理名,
     *  deleteFlag: 削除フラグ,
     *  entityType: エンティティタイプ
     * }
     */
    getData () {
        $( this.el ).data( "tableMaster", {
            tableId: parseInt( $( this.el ).find( "input[name='tableMaster.tableId']" ).val(), 10 ),
            physicalName: $( this.el ).find( "input[name='tableMaster.physicalName']" ).val(),
            logicalName: $( this.el ).find( "input[name='tableMaster.logicalName']" ).val(),
            deleteFlag: parseInt( $( this.el ).find( "input[name='tableMaster.deleteFlag']" ).val(), 10 ),
            entityType: parseInt( $( this.el ).find( "input[name='tableMaster.entityType']" ).val(), 10 )
        } );

        return $( this.el ).data( "tableMaster" );
    }

    /**
     * DOM 上のフォームコントロールへデータを出力する。
     *
     * @param tableMaster
     * {
     *  tableId: テーブルを識別する為の ID,
     *  physicalName: テーブルの物理名,
     *  logicalName: テーブルの論理名,
     *  deleteFlag: 削除フラグ,
     *  entityType: エンティティタイプ
     * }
     */
    setData ( tableMaster ) {
        $( this.el ).find( "input[name='tableMaster.tableId']" ).val( tableMaster.tableId );
        $( this.el ).find( "input[name='tableMaster.physicalName']" ).val( tableMaster.physicalName );
        $( this.el ).find( "input[name='tableMaster.logicalName']" ).val( tableMaster.logicalName );
        $( this.el ).find( "input[name='tableMaster.deleteFlag']" ).val( tableMaster.deleteFlag );
        $( this.el ).find( "input[name='tableMaster.entityType']" ).val( tableMaster.entityType );
    }
};