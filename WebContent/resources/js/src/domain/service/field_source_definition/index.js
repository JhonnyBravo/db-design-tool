import $ from "jquery";
import FieldSourceDefinition from "../../model/field_source_definition";

/**
 * field_source_definition のレコードを管理する。
 */
export default class FieldSourceDefinitionService {
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
            var fieldSourceDefinition = new FieldSourceDefinition( $( this ) );
            var data = fieldSourceDefinition.getData();
            data.no = index + 1;
            $( this ).find( "th" ).text( data.no ).append( `<input type='hidden' name='fieldSourceDefinition.no' value='${ data.no }' />` );
        } );
    }

    /**
     * レコードを一つ上に移動する。
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
     * レコードを一つ下に移動する。
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
     * 
     * @param fieldSourceDefinition
     * {
     *  tableMaster: {
     *      tableId: テーブルを識別する為の ID,
     *      physicalName: テーブルの物理名
     *  },
     *  fieldMaster: {
     *      fieldId: フィールドを識別する為の ID,
     *      physicalName: フィールドの物理名
     *  }
     * }
     */
    create ( fieldSourceDefinition ) {
        var template = $( this.template ).clone();
        var tableMaster = fieldSourceDefinition.tableMaster;
        var fieldMaster = fieldSourceDefinition.fieldMaster;

        if ( tableMaster.physicalName != "" && fieldMaster.physicalName != "" ) {
            template.find( "textarea[name='fieldSourceDefinition.sourceDefinition']" ).val( `${ tableMaster.physicalName }.${ fieldMaster.physicalName }` );
        }

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