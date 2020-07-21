import $ from "jquery";

export default {
    /**
     * @returns fieldDefinition
     * {
     *  tableId: ダイアログ上で選択されているテーブルの tableId,
     *  tableName: ダイアログ上で選択されているテーブルの物理名,
     *  fieldId: ダイアログ上で選択されているフィールドの fieldId,
     *  fieldName: ダイアログ上で選択されているフィールドの物理名
     * }
     */
    getFieldDefinition () {
        var $tableNameSelect = $( this ).find( "select[name='tableName']" );
        var $fieldNameSelect = $( this ).find( "select[name='fieldName']" );

        $( this ).data( "fieldDefinition", {
            tableId: $tableNameSelect.val(),
            tableName: $.trim( $tableNameSelect.find( "option:selected" ).text() ),
            fieldId: $fieldNameSelect.val(),
            fieldName: $.trim( $fieldNameSelect.find( "option:selected" ).text() )
        } );

        var fieldDefinition = $( this ).data( "fieldDefinition" );
        return fieldDefinition;
    },
    /**
     * 取得元フィールド選択ダイアログのテーブル選択セレクトボックスのオプションリストを生成する。
     * 
     * @param tableMasterList 
     * [{
     *  tableId: tableId,
     *  physicalName: テーブルの物理名
     * }]
     */
    setTableList ( tableMasterList ) {
        var $tableNameSelect = $( this ).find( "select[name='tableName']" );
        $tableNameSelect.find( "option" ).remove();
        $tableNameSelect.append( "<option></option>" );

        var $fieldNameSelect = $( this ).find( "select[name='fieldName']" );
        $fieldNameSelect.find( "option" ).remove();
        $fieldNameSelect.append( "<option></option>" );

        $.each( tableMasterList, function ( index, tableMaster ) {
            $tableNameSelect.append( `<option value="${ tableMaster.tableId }">${ tableMaster.physicalName }</option>` );
        } );
    },
    /**
     * 取得元フィールド選択ダイアログのフィールド選択セレクトボックスのオプションリストを生成する。
     * @param tableId フィールド定義取得対象とするテーブルの tableId
     */
    setFieldList ( tableId ) {
        var $fieldNameSelect = $( this ).find( "select[name='fieldName']" );
        $fieldNameSelect.find( "option" ).remove();

        $.get( "/db-design-tool/data/table", {tableId: tableId} )
            .done( function ( data ) {
                $.each( data.fieldMaster, function ( index, value ) {
                    $fieldNameSelect.append( `<option value="${ value.fieldId }">${ value.physicalName }</option>` );
                } );
            } );
    }
};