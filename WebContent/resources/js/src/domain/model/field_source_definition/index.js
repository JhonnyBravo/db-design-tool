import $ from "jquery";

/**
 * field_source_definition のレコードを管理する。
 */
export default class FieldSourceDefinition {
    /**
     * @param el 操作対象とする要素を指定する。
     */
    constructor( el ) {
        this.el = el;
    }

    /**
     * DOM 上のフォームコントロールから取得したデータをオブジェクトに変換して返す。
     * 
     * @returns fieldSourceDefinition
     * {
     *  fieldId: フィールドを識別する為の ID,
     *  no: フィールドの項番,
     *  physicalName: フィールドの物理名,
     *  logicalName: フィールドの論理名,
     *  dataType: データ型,
     *  dataSize: データサイズ,
     *  definitionId: 取得元フィールド定義を識別する為の ID,
     *  sourceDefinition: 取得元フィールドの定義,
     *  description: フィールドの説明
     * }
     */
    getData () {
        $( this.el ).data( "fieldSourceDefinition", {
            fieldId: parseInt( $( this.el ).find( "input[name='fieldSourceDefinition.fieldId']" ).val(), 10 ),
            no: parseInt( $( this.el ).find( "input[name='fieldSourceDefinition.no']" ).val(), 10 ),
            physicalName: $( this.el ).find( "input[name='fieldSourceDefinition.physicalName']" ).val(),
            logicalName: $( this.el ).find( "input[name='fieldSourceDefinition.logicalName']" ).val(),
            dataType: parseInt( $( this.el ).find( "select[name='fieldSourceDefinition.dataType']" ).val(), 10 ),
            dataSize: parseInt( $( this.el ).find( "input[name='fieldSourceDefinition.dataSize']" ).val(), 10 ),
            definitionId: parseInt( $( this.el ).find( "input[name='fieldSourceDefinition.definitionId']" ).val(), 10 ),
            sourceDefinition: $.trim( $( this.el ).find( "textarea[name='fieldSourceDefinition.sourceDefinition']" ).val() ),
            description: $.trim( $( this.el ).find( "textarea[name='fieldSourceDefinition.description']" ).val() )
        } );

        return $( this.el ).data( "fieldSourceDefinition" );
    }

    /**
     * DOM 上のフォームコントロールへデータを出力する。
     *
     * @param fieldSourceDefinition
     * {
     *  fieldId: フィールドを識別する為の ID,
     *  no: フィールドの項番,
     *  physicalName: フィールドの物理名,
     *  logicalName: フィールドの論理名,
     *  dataType: データ型,
     *  dataSize: データサイズ,
     *  definitionId: 取得元フィールド定義を識別する為の ID,
     *  sourceDefinition: 取得元フィールドの定義,
     *  description: フィールドの説明
     * }
     */
    setData ( fieldSourceDefinition ) {
        $( this.el ).find( "input[name='fieldSourceDefinition.fieldId']" ).val( fieldSourceDefinition.fieldId );
        $( this.el ).find( "input[name='fieldSourceDefinition.no']" ).val( fieldSourceDefinition.no );
        $( this.el ).find( "input[name='fieldSourceDefinition.physicalName']" ).val( fieldSourceDefinition.physicalName );
        $( this.el ).find( "input[name='fieldSourceDefinition.logicalName']" ).val( fieldSourceDefinition.logicalName );
        $( this.el ).find( "select[name='fieldSourceDefinition.dataType']" ).val( fieldSourceDefinition.dataType );
        $( this.el ).find( "input[name='fieldSourceDefinition.dataSize']" ).val( fieldSourceDefinition.dataSize );
        $( this.el ).find( "input[name='fieldSourceDefinition.definitionId']" ).val( fieldSourceDefinition.definitionId );
        $( this.el ).find( "textarea[name='fieldSourceDefinition.sourceDefinition']" ).val( fieldSourceDefinition.sourceDefinition );
        $( this.el ).find( "textarea[name='fieldSourceDefinition.description']" ).val( fieldSourceDefinition.description );
    }
};