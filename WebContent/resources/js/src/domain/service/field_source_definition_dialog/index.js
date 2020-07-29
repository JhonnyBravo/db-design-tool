import "bootstrap";
import jq from "jquery";
import FieldSourceDefinitionDialog from "../../model/field_source_definition_dialog";

/**
 * 取得元フィールド定義編集ダイアログのデータ操作を管理する。
 */
export default class FieldSourceDefinitionDialogService {
    /**
     * @param el 操作対象とする要素を指定する。
     * @param recordSource 取得元テーブル定義一覧の取得元を指定する。
     */
    constructor( el, recordSource ) {
        this.el = el;
        this.recordSource = recordSource;
    }

    /**
     * 取得元フィールド定義編集ダイアログのセレクトボックスを初期化する。
     */
    initData () {
        // 取得元テーブル定義の一覧を取得する。
        var tableMasterList = [];

        $( this.recordSource ).each( function ( index ) {
            $( this ).data( "tableMaster", {
                tableId: $( this ).find( "input[name='tableSourceDefinition.sourceId']" ).val(),
                physicalName: $( this ).find( "input[name='tableSourceDefinition.sourceName']" ).val()
            } );

            var tableMaster = $( this ).data( "tableMaster" );
            tableMasterList.push( tableMaster );
        } );

        // ダイアログのセレクトボックスを初期化する。
        this.deleteTableMasterAll();
        this.deleteFieldMasterAll();
        this.addTableMaster( tableMasterList );
    }

    /**
     * @returns fieldSourceDefinition
     * {
     *  tableMaster: {
     *      tableId: テーブルを識別する為の ID,
     *      physicalName: テーブルの物理名
     *  },
     *  fieldMaster: {
     *      fieldId: フィールドを識別する為の ID,
     *      physicalName: フィールドの物理名
     *  }
     * }
     */
    getData () {
        var dialog = new FieldSourceDefinitionDialog( this.el );
        var tableMaster = dialog.getTableMaster();
        var fieldMaster = dialog.getFieldMaster();
        var fieldSourceDefinition = {tableMaster: tableMaster, fieldMaster: fieldMaster};
        return fieldSourceDefinition;
    }

    /**
     * ダイアログのテーブルセレクトボックスへオプションリストを登録する。
     * 
     * @param tableMasterList 登録対象とする table_master の一覧を指定する。
     */
    addTableMaster ( tableMasterList ) {
        var physicalName = $( this.el ).find( "select[name='tableMaster.physicalName']" );

        $.each( tableMasterList, function ( index, tableMaster ) {
            physicalName.append( `<option value="${ tableMaster.tableId }">${ tableMaster.physicalName }</option>` );
        } );
    }

    /**
     * ダイアログのテーブルセレクトボックスのオプションリストを削除する。
     */
    deleteTableMasterAll () {
        var tableMaster = $( this.el ).find( "select[name='tableMaster.physicalName']" );
        tableMaster.find( "option" ).remove();
        tableMaster.append( "<option></option>" );
    }

    /**
     * ダイアログのフィールドセレクトボックスへオプションリストを登録する。
     */
    addFieldMaster () {
        // ダイアログのフィールドセレクトボックスのオプションリストを削除する。
        var physicalName = $( this.el ).find( "select[name='fieldMaster.physicalName']" );
        physicalName.find( "option" ).remove();

        // 現在選択されているテーブルに紐づくフィールド定義の一覧を取得し、セレクトボックスのオプションリストとして登録する。
        var tableId = $( this.el ).find( "select[name='tableMaster.physicalName']" ).val();

        jq.get( "/db-design-tool/data/table", {tableId: tableId} ).done( function ( data ) {
            $.each( data.fieldMaster, function ( index, fieldMaster ) {
                physicalName.append( `<option value="${ fieldMaster.fieldId }">${ fieldMaster.physicalName }</option>` );
            } );
        } );
    }

    /**
     * ダイアログのフィールドセレクトボックスのオプションリストを削除する。
     */
    deleteFieldMasterAll () {
        var fieldMaster = $( this.el ).find( "select[name='fieldMaster.physicalName']" );
        fieldMaster.find( "option" ).remove();
        fieldMaster.append( "<option></option>" );
    }

    /**
     * ダイアログを開く。
     */
    open () {
        $( this.el ).modal( "show" );
    }

    /**
     * ダイアログを閉じる。
     */
    close () {
        $( this.el ).modal( "hide" );
    }
};