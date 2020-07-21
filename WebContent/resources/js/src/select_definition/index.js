import $ from "../core";

$( function () {
    // テーブル定義削除処理
    $( "button.btn-danger" ).click( function () {
        $( "input[name='tableMaster.deleteFlag']" ).val( 1 );
        $( "form" ).submit();
    } );

    $( "#fieldSourceDefinition .addRecord" ).click(
        function ( e ) {
            e.preventDefault();
            // 取得元フィールド選択ダイアログの起動処理
            var tableMasterList = [];

            $( "#tableSourceDefinition tbody tr" ).each( function () {
                $( this ).data( "tableMaster", {
                    tableId: $( this ).find( "input[name='tableSourceDefinition.sourceId']" ).val(),
                    physicalName: $( this ).find( "input[name='tableSourceDefinition.sourceName']" ).val()
                } );

                tableMasterList.push( $( this ).data( "tableMaster" ) );
            } );

            $( "#fieldDefinitionModal" ).fldSrcDialog( "setTableList", tableMasterList );
            $( "#fieldDefinitionModal" ).modal( "show" );
        } );
    // 取得元フィールド選択ダイアログのフィールド一覧更新処理
    $( "#fieldDefinitionModal" ).find( "select[name='tableName']" ).change( function () {
        var tableId = $( this ).val();
        $( "#fieldDefinitionModal" ).fldSrcDialog( "setFieldList", tableId );
    } );

    $( "#fieldDefinitionModal button.btn-primary" ).click( function ( e ) {
        e.preventDefault();
        // 新規フィールド定義追加処理
        var fieldDefinition = $( "#fieldDefinitionModal" ).fldSrcDialog( "getFieldDefinition" );
        $( "#fieldSourceDefinition" ).fldSrcTable( "addRecord", $( "#fieldSourceDefinitionTemplate tbody tr" ), fieldDefinition );
        $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );
        $( "#fieldDefinitionModal" ).modal( "hide" );

        // フィールド定義削除処理
        $( "#fieldSourceDefinition .removeRecord" ).click( function ( e ) {
            e.preventDefault();
            $( this ).fieldMaster( "removeRecord" );
            $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );
        } );

        // 一つ上に移動する。
        $( "#fieldSourceDefinition .dropUpRecord" ).off( "click" );
        $( "#fieldSourceDefinition .dropUpRecord" ).click( function ( e ) {
            e.preventDefault();
            $( this ).fieldMaster( "dropUpRecord" );
            $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );
        } );

        // 一つ下に移動する。
        $( "#fieldSourceDefinition .dropDownRecord" ).off( "click" );
        $( "#fieldSourceDefinition .dropDownRecord" ).click( function ( e ) {
            e.preventDefault();
            $( this ).fieldMaster( "dropDownRecord" );
            $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );
        } );
    } );
    // フィールド定義削除処理
    $( "#fieldSourceDefinition .removeRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).fieldMaster( "removeRecord" );
        $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );
    } );
    // 一つ上に移動する。
    $( "#fieldSourceDefinition .dropUpRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).fieldMaster( "dropUpRecord" );
        $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );
    } );
    // 一つ下に移動する。
    $( "#fieldSourceDefinition .dropDownRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).fieldMaster( "dropDownRecord" );
        $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );
    } );
    // 新規結合テーブル編集用ダイアログを起動する。
    $( "#tableSourceDefinition .addRecord" ).click(
        function ( e ) {
            e.preventDefault();
            $( "#joinDefinitionModal" ).tblSrcDialog( "init" );
            $( "#joinDefinitionModal" ).modal( "show" );
        } );

    $( "#joinDefinitionModal button.btn-primary" ).click(
        function ( e ) {
            e.preventDefault();
            // 結合テーブル追加処理
            var definition = $( "#joinDefinitionModal" ).tblSrcDialog(
                "getDefinition" );

            if ( parseInt( definition.no, 10 ) !== 0 ) {
                $( "#tableSourceDefinition tbody" ).tblSrcTable(
                    "updateRecord", definition );
                $( "#joinDefinitionModal" ).modal( "hide" );
                return;
            }

            $( "#tableSourceDefinition tbody" ).tblSrcTable( "addRecord",
                $( "#tableSourceDefinitionTemplate tbody tr" ),
                definition );
            $( "#tableSourceDefinition" ).tblSrcTable( "resetNo" );

            // 結合テーブル削除処理
            $( "#tableSourceDefinition .removeRecord" ).click( function ( e ) {
                e.preventDefault();
                $( this ).tblSrcTable( "removeRecord" );
                $( "#tableSourceDefinition" ).tblSrcTable( "resetNo" );
            } );

            // 結合テーブル編集ダイアログ起動処理
            $( "#tableSourceDefinition .updateRecord" ).off( "click" );
            $( "#tableSourceDefinition .updateRecord" ).click( function ( e ) {
                e.preventDefault();
                var definition = $( this ).tblSrcTable( "getDefinition" );
                $( "#joinDefinitionModal" ).tblSrcDialog( "setDefinition", definition );
                $( "#joinDefinitionModal" ).modal( "show" );
            } );

            // 一つ上に移動する。
            $( "#tableSourceDefinition .dropUpRecord" ).off( "click" );
            $( "#tableSourceDefinition .dropUpRecord" ).click( function ( e ) {
                e.preventDefault();
                $( this ).tblSrcTable( "dropUpRecord" );
                $( "#tableSourceDefinition" ).tblSrcTable( "resetNo" );
            } );

            // 一つ下に移動する。
            $( "#tableSourceDefinition .dropDownRecord" ).off( "click" );
            $( "#tableSourceDefinition .dropDownRecord" ).click( function ( e ) {
                e.preventDefault();
                $( this ).tblSrcTable( "dropDownRecord" );
                $( "#tableSourceDefinition" ).tblSrcTable( "resetNo" );
            } );

            $( "#joinDefinitionModal" ).modal( "hide" );
        } );
    // 結合テーブル削除処理
    $( "#tableSourceDefinition .removeRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).tblSrcTable( "removeRecord" );
        $( "#tableSourceDefinition" ).tblSrcTable( "resetNo" );
    } );

    $( "#tableSourceDefinition .updateRecord" ).click( function ( e ) {
        e.preventDefault();
        var definition = $( this ).tblSrcTable( "getDefinition" );
        $( "#joinDefinitionModal" ).tblSrcDialog( "setDefinition", definition );
        $( "#joinDefinitionModal" ).modal( "show" );
    } );
    // 一つ上に移動する。
    $( "#tableSourceDefinition .dropUpRecord" ).off( "click" );
    $( "#tableSourceDefinition .dropUpRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).tblSrcTable( "dropUpRecord" );
        $( "#tableSourceDefinition" ).tblSrcTable( "resetNo" );
    } );
    // 一つ下に移動する。
    $( "#tableSourceDefinition .dropDownRecord" ).off( "click" );
    $( "#tableSourceDefinition .dropDownRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).tblSrcTable( "dropDownRecord" );
        $( "#tableSourceDefinition" ).tblSrcTable( "resetNo" );
    } );
} );