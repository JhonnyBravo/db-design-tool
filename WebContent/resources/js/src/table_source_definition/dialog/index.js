import $ from "jquery";

export default {
    /**
     * 結合条件入力ダイアログの画面表示を初期化する。
     */
    init () {
        $( this ).data( "tableSourceDefinition", {
            no: 0,
            tableId: $( this ).find( "select[name='tableName'] option:first-child" ).val(),
            tableName: null,
            condition: null
        } );

        var definition = $( this ).data( "tableSourceDefinition" );
        $( this ).find( "input[name='no']" ).val( definition.no );
        $( this ).find( "select[name='tableName']" ).val( definition.tableId );
        $( this ).find( "textarea[name='condition']" ).val( definition.condition );
    },
    /**
     *結合条件入力ダイアログへ取得元テーブルの定義を登録する。
     * @param definition
     * {
     *  no: no,
     *  tableId: 取得元エンティティの table_id,
     *  condition: 結合条件
     * }
     */
    setDefinition ( definition ) {
        $( this ).find( "input[name='no']" ).val( definition.no );
        $( this ).find( "select[name='tableName']" ).val( definition.tableId );
        $( this ).find( "textarea[name='condition']" ).val( definition.condition );
    },
    /**
     * @returns definition 結合条件入力ダイアログから入力値を取得し、オブジェクトに変換して返す。
     * {
     *  no: no,
     *  tableId: 取得元エンティティの table_id,
     *  tableName: 取得元エンティティの名前,
     *  condition: 結合条件
     * }
     */
    getDefinition () {
        $( this ).data( "tableSourceDefinition", {
            no: $( this ).find( "input[name='no']" ).val(),
            tableId: $( this ).find( "select[name='tableName']" ).val(),
            tableName: $( this ).find( "select[name='tableName']" ).find( "option:selected" ).text(),
            condition: $( this ).find( "textarea[name='condition']" ).val()
        } );

        return $( this ).data( "tableSourceDefinition" );
    }
};