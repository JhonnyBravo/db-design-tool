import $ from "jquery";

export default {
    /**
     * 取得元フィールド選択ダイアログから渡された fieldDefinition をフィールド定義の一覧へ追加する。
     * @param $template 操作対象とするテンプレート要素
     * @param fieldDefinition 
     * {
     *  tableName: ダイアログ上で選択されているテーブルの物理名,
     *  fieldName: ダイアログ上で選択されているフィールドの物理名
     * }
     */
    addRecord ( $template, fieldDefinition ) {
        var template = $template.clone();
        template.find( "textarea[name='fieldSourceDefinition.sourceDefinition']" ).val( `${ fieldDefinition.tableName }.${ fieldDefinition.fieldName }` );
        $( this ).find( "tbody" ).append( template );
    }
};