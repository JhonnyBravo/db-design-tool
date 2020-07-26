import $ from "jquery";

/**
 * 取得元テーブル定義編集ダイアログのデータを管理する。
 */
export default class TableSourceDefinitionDialog {
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
        $( this.el ).data( "tableSourceDefinition", {
            definitionId: parseInt( $( this.el ).find( "input[name='tableSourceDefinition.definitionId']" ).val(), 10 ),
            no: parseInt( $( this.el ).find( "input[name='tableSourceDefinition.no']" ).val(), 10 ),
            sourceId: parseInt( $( this.el ).find( "select[name='tableSourceDefinition.sourceName']" ).val(), 10 ),
            sourceName: $.trim( $( this.el ).find( "select[name='tableSourceDefinition.sourceName']" ).find( "option:selected" ).text() ),
            joinCondition: $.trim( $( this.el ).find( "textarea[name='tableSourceDefinition.joinCondition']" ).val() )
        } );

        return $( this.el ).data( "tableSourceDefinition" );
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
        $( this.el ).find( "input[name='tableSourceDefinition.definitionId']" ).val( tableSourceDefinition.definitionId );
        $( this.el ).find( "input[name='tableSourceDefinition.no']" ).val( tableSourceDefinition.no );
        $( this.el ).find( "select[name='tableSourceDefinition.sourceName']" ).val( tableSourceDefinition.sourceId );
        $( this.el ).find( "textarea[name='tableSourceDefinition.joinCondition']" ).val( tableSourceDefinition.joinCondition );
    }
};