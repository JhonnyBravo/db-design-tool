import $ from "jquery";

export default {
    /**
     * No 列の自動採番を実行する。
     */
    resetNo () {
        var entityType = parseInt( $( "input[name='tableMaster.entityType']" ).val(), 10 );
        var name;

        if ( entityType === 1 ) {
            name = "fieldMaster.no";
        } else if ( entityType === 2 ) {
            name = "fieldSourceDefinition.no";
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
    addRecord ( $template ) {
        $( this ).find( "tbody" ).append( $template.clone() );
    },
    /**
     * レコードを削除する。
     */
    removeRecord () {
        $( this ).parent().parent().remove();
    },
    /**
     * レコードを一つ上に移動する。
     */
    dropUpRecord () {
        var src = $( this ).parent().parent();
        var dst = src.prev();
        src.insertBefore( dst );
    },
    /**
     * レコードを一つ下に移動する。
     */
    dropDownRecord () {
        var src = $( this ).parent().parent();
        var dst = src.next();
        src.insertAfter( dst );
    }
};