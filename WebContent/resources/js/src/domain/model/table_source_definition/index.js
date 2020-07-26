import "jquery";

/**
 * table_source_definition のレコードを管理する。
 */
export default class TableSourceDefinition {
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
            sourceId: parseInt( $( this.el ).find( "input[name='tableSourceDefinition.sourceId']" ).val(), 10 ),
            sourceName: $( this.el ).find( "input[name='tableSourceDefinition.sourceName']" ).val(),
            joinCondition: $( this.el ).find( "input[name='tableSourceDefinition.joinCondition']" ).val()
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
        $( this.el ).find( "input[name='tableSourceDefinition.sourceId']" ).val( tableSourceDefinition.sourceId );
        $( this.el ).find( "input[name='tableSourceDefinition.sourceName']" ).val( tableSourceDefinition.sourceName );
        $( this.el ).find( "input[name='tableSourceDefinition.joinCondition']" ).val( tableSourceDefinition.joinCondition );
    }
};