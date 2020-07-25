import $ from "jquery";
import FieldMaster from "../../model/field_master";

/**
 * field_master のレコード操作を管理する。
 */
export default class FieldMasterService {
    /**
     * @param el 操作対象とする要素を指定する。
     * @param template 操作対象とするテンプレート要素を指定する。
     */
    constructor( el, template ) {
        this.el = el;
        this.template = template;
    }

    /**
     * no 列の自動採番を実行する。
     */
    setNo () {
        $( this.el ).find( "tr" ).each( function ( index ) {
            var fieldMaster = new FieldMaster( $( this ) );
            var data = fieldMaster.getData();
            data.no = index + 1;
            $( this ).find( "th" ).text( data.no ).append( `<input type='hidden' name='fieldMaster.no' value='${ data.no }'/>` );
        } );
    }

    /**
     * レコードを一つ上へ移動する。
     * 
     * @param el 操作対象とする要素を指定する。
     */
    dropUp ( el ) {
        var src = $( el ).parent().parent();
        var dst = src.prev();
        src.insertBefore( dst );
        this.setNo();
    }

    /**
     * レコードを一つ下へ移動する。
     * 
     * @param el 操作対象とする要素を指定する。
     */
    dropDown ( el ) {
        var src = $( el ).parent().parent();
        var dst = src.next();
        src.insertAfter( dst );
        this.setNo();
    }

    /**
     * 新規レコードを追加する。
     */
    create () {
        var template = $( this.template ).clone();
        $( this.el ).append( template );
        this.setNo();
    }

    /**
     * レコードを削除する。
     * 
     * @param el 操作対象とする要素を指定する。
     */
    delete ( el ) {
        $( el ).parent().parent().remove();
        this.setNo();
    }
};