import "bootstrap";
import TableSourceDefinitionDialog from "../../model/table_source_definition_dialog";

/**
 * 取得元テーブル定義編集ダイアログの操作を管理する。
 */
export default class TableSourceDefinitionDialogService {
    /**
     * @param el 操作対象とする要素を指定する。
     */
    constructor( el ) {
        this.el = el;
    }

    /**
     * DOM 上のフォームコントロールから取得したデータをオブジェクトに変換して返す。
     *
     * @returns tableSourceDefinition
     * {
     *  definitionId: 取得元テーブル定義を識別する為の ID,
     *  no: 取得元テーブル定義の項番,
     *  sourceId: 取得元テーブルの tableId,
     *  sourceName: 取得元テーブルの物理名,
     *  joinCondition: 取得元テーブルの結合条件
     * }
     */
    getData () {
        var dialog = new TableSourceDefinitionDialog( this.el );
        return dialog.getData();
    }

    /**
     * DOM 上のフォームコントロールへデータを出力する。
     *
     * @param tableSourceDefinition
     * {
     *  definitionId: 取得元テーブル定義を識別する為の ID,
     *  no: 取得元テーブル定義の項番,
     *  sourceId: 取得元テーブルの tableId,
     *  sourceName: 取得元テーブルの物理名,
     *  joinCondition: 取得元テーブルの結合条件
     * }
     */
    setData ( tableSourceDefinition ) {
        var dialog = new TableSourceDefinitionDialog( this.el );
        dialog.setData( tableSourceDefinition );
    }

    /**
     * ダイアログのデータをデフォルト値に初期化する。
     */
    initData () {
        $( this.el ).data( "tableSourceDefinition", {
            definitionId: 0,
            no: 0,
            sourceId: parseInt( $( this.el ).find( "select[name='tableSourceDefinition.sourceName'] option:first-child" ).val(), 10 ),
            sourceName: null,
            joinCondition: null
        } );

        var tableSourceDefinition = $( this.el ).data( "tableSourceDefinition" );
        this.setData( tableSourceDefinition );
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
}