import $ from "jquery";
import FieldMaster from "../field_master";

var sourceMaster = $.extend( {}, FieldMaster );
sourceMaster = $.extend( sourceMaster, {
    /**
     * No 列の自動採番を実行する。
     */
    resetNo () {
        $( this ).find( "tbody th" ).each(
            function ( index ) {
                $( this ).text( index + 1 ).append( `<input type='hidden' name='tableSourceDefinition.no' value='${ index + 1 }'/>` );
            } );
    },
    /**
     * 結合条件の定義を新規登録する。
     * @param $template テンプレートとして使用する要素を指定する。
     * @param definition 入力値として使用するオブジェクトを指定する。
     * {
     *  tableId: 取得元エンティティの table_id,
     *  tableName: 取得元エンティティの名前,
     *  condition: 結合条件
     * }
     */
    addRecord ( $template, definition ) {
        var template = $template.clone();

        // テーブル名
        var $tableNameInput = $( "<input type='hidden' name='tableSourceDefinition.sourceName'>" );
        $tableNameInput.val( definition.tableName );

        // テーブル ID
        var $tableIdInput = $( "<input type='hidden' name='tableSourceDefinition.sourceId'>" );
        $tableIdInput.val( definition.tableId );

        var $tableName = template.find( "td" ).eq( 0 );
        $tableName.text( definition.tableName );
        $tableName.append( $tableIdInput );
        $tableName.append( $tableNameInput );

        // 結合条件
        var $condition = template.find( "td" ).eq( 1 );
        $condition.text( definition.condition );

        var $conditionInput = $( "<input type='hidden' name='tableSourceDefinition.joinCondition'>" );
        $conditionInput.val( definition.condition );
        $condition.append( $conditionInput );

        $( this ).append( template );
    },
    /**
     * 登録済み結合条件の定義を更新する。
     * @param definition 更新値として使用するオブジェクトを指定する。
     * {
     *  no: no,
     *  tableId: 取得元エンティティの table_id,
     *  tableName: 取得元エンティティの名前,
     *  condition: 結合条件
     * }
     */
    updateRecord ( definition ) {
        var $no = $( this ).find( `input[name='tableSourceDefinition.no'][value='${ definition.no }']` ).parent();

        // テーブル名とテーブル ID の更新
        var $tableName = $no.next();

        var $tableNameInput = $tableName.find( "input[name='tableSourceDefinition.sourceName']" );
        $tableNameInput.val( definition.tableName );

        var $tableIdInput = $tableName.find( "input[name='tableSourceDefinition.sourceId']" );
        $tableIdInput.val( definition.tableId );

        $tableName.text( definition.tableName );
        $tableName.append( $tableIdInput );
        $tableName.append( $tableNameInput );

        // 結合条件の更新
        var $condition = $tableName.next();

        var $conditionInput = $condition.find( "input[name='tableSourceDefinition.joinCondition']" );
        $conditionInput.val( definition.condition );

        $condition.text( definition.condition );
        $condition.append( $conditionInput );
    },
    /**
     * @returns definition 登録済み結合条件の定義を取得し、オブジェクトに変換して返す。
     * {
     *  no: no,
     *  tableId: 取得元エンティティの table_id,
     *  condition: 結合条件
     * }
     */
    getDefinition () {
        var record = $( this ).parent().parent();
        var definition = {};

        definition.no = record.find( "input[name='tableSourceDefinition.no']" ).val();
        definition.tableId = record.find( "input[name='tableSourceDefinition.sourceId']" ).val();
        definition.condition = record.find( "input[name='tableSourceDefinition.joinCondition']" ).val();

        return definition;
    }
} );

export default sourceMaster;