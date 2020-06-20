import $ from "jquery";
export default {
    /**
     * 結合条件入力ダイアログの画面表示を初期化する。
     * @param definition ダイアログへ渡す初期値を設定したオブジェクトを指定する。
     * {
     *  no: no,
     *  tableId: 取得元エンティティの table_id,
     *  condition: 結合条件
     * }
     */
    init: function ( definition ) {
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
    getDefinition: function () {
        var definition = {};

        definition.no = $( this ).find( "input[name='no']" ).val();
        definition.tableId = $( this ).find( "select[name='tableName']" ).val();
        definition.tableName = $( this ).find( "select[name='tableName']" )
            .find( "option:selected" ).text();
        definition.condition = $( this ).find( "textarea[name='condition']" )
            .val();

        return definition;
    },
    /**
     * @returns definition 結合条件入力ダイアログのデフォルト設定値を設定したオブジェクトを返す。
     * {
     *  no: no,
     *  tableId: 取得元エンティティの table_id,
     *  condition: 結合条件
     * }
     */
    createDefinition: function () {
        var definition = {};

        definition.no = 0;
        definition.tableId = $( this ).find(
            "select[name='tableName'] option:first-child" ).val();
        definition.condition = null;

        return definition;
    }
}