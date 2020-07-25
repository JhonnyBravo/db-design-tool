import $ from "jquery";

/**
 * field_master のレコードを管理する。
 */
export default class FieldMaster {
    /**
     * @param el 操作対象とする要素を指定する。
     */
    constructor( el ) {
        this.el = el;
    }

    /**
     * DOM 上のフォームコントロールから取得したデータをオブジェクトに変換して返す。
     * 
     * @returns fieldMaster
     * {
     *  fieldId: フィールドを識別する為の ID,
     *  no: フィールドの項番,
     *  physicalName: フィールドの物理名,
     *  logicalName: フィールドの論理名,
     *  dataType: データ型,
     *  dataSize: データサイズ,
     *  description: フィールドの説明
     * }
     */
    getData () {
        $( this.el ).data( "fieldMaster", {
            fieldId: parseInt( $( this.el ).find( "input[name='fieldMaster.fieldId']" ).val(), 10 ),
            no: parseInt( $( this.el ).find( "input[name='fieldMaster.no']" ).val(), 10 ),
            physicalName: $( this.el ).find( "input[name='fieldMaster.physicalName']" ).val(),
            logicalName: $( this.el ).find( "input[name='fieldMaster.logicalName']" ).val(),
            dataType: parseInt( $( this.el ).find( "select[name='fieldMaster.dataType']" ).val(), 10 ),
            dataSize: parseInt( $( this.el ).find( "input[name='fieldMaster.dataSize']" ).val(), 10 ),
            description: $.trim( $( this.el ).find( "textarea[name='fieldMaster.description']" ).val() )
        } );

        return $( this.el ).data( "fieldMaster" );
    }

    /**
     * DOM 上のフォームコントロールへデータを出力する。
     *
     * @param fieldMaster
     * {
     *  fieldId: フィールドを識別する為の ID,
     *  no: フィールドの項番,
     *  physicalName: フィールドの物理名,
     *  logicalName: フィールドの論理名,
     *  dataType: データ型,
     *  dataSize: データサイズ,
     *  description: フィールドの説明
     * }
     */
    setData ( fieldMaster ) {
        $( this.el ).find( "input[name='fieldMaster.fieldId']" ).val( fieldMaster.fieldId );
        $( this.el ).find( "input[name='fieldMaster.no']" ).val( fieldMaster.no );
        $( this.el ).find( "input[name='fieldMaster.physicalName']" ).val( fieldMaster.physicalName );
        $( this.el ).find( "input[name='fieldMaster.logicalName']" ).val( fieldMaster.logicalName );
        $( this.el ).find( "select[name='fieldMaster.dataType']" ).val( fieldMaster.dataType );
        $( this.el ).find( "input[name='fieldMaster.dataSize']" ).val( fieldMaster.dataSize );
        $( this.el ).find( "textarea[name='fieldMaster.description']" ).val( fieldMaster.description );
    }
};