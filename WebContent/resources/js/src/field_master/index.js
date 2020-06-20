import $ from "jquery";

export default {
    /**
     * No 列の自動採番を実行する。
     */
    resetNo: function () {
        var entityType = $( "input[name='tableMaster.entityType']" ).val();

        if ( entityType == 1 ) {
            var name = "fieldMaster.no";
        } else if ( entityType == 2 ) {
            var name = "fieldSourceDefinition.no";
        }

        $( this ).find( "tbody th" ).each(
            function ( index ) {
                $( this ).text( index + 1 ).append( `<input type='hidden' name='${ name }' value='${ index + 1 }'/>` );
            } );
    },
    /**
     * 新規レコードをテーブルへ追加する。
     * @param $template コピー対象とするテンプレートを指定する。
     */
    addRecord: function ( $template ) {
        $( this ).find( "tbody" ).append( $template.clone() );
    },
    /**
     * レコードを削除する。
     */
    removeRecord: function () {
        $( this ).parent().parent().remove();
    },
    /**
     * レコードを一つ上に移動する。
     */
    dropUpRecord: function () {
        var src = $( this ).parent().parent();
        var dst = src.prev();
        src.insertBefore( dst );
    },
    /**
     * レコードを一つ下に移動する。
     */
    dropDownRecord: function () {
        var src = $( this ).parent().parent();
        var dst = src.next();
        src.insertAfter( dst );
    }
}