import $ from "../core";

$( function () {
    // テーブル定義削除処理
    $( "button.btn-danger" ).click( function () {
        $( "input[name='tableMaster.deleteFlag']" ).val( 1 );
        $( "form" ).submit();
    } );
    // 新規フィールド定義追加処理
    $( "#fieldSourceDefinition .addRecord" ).click(
        function ( e ) {
            e.preventDefault();
            $( "#fieldSourceDefinition" ).fieldMaster( "addRecord",
                $( "#fieldSourceDefinitionTemplate tbody tr" ) );
            $( "#fieldSourceDefinition" ).fieldMaster( "resetNo" );

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
            var definition = $( "#joinDefinitionModal" ).sourceDialog(
                "createDefinition" );
            $( "#joinDefinitionModal" ).sourceDialog( "init", definition );
            $( "#joinDefinitionModal" ).modal( "show" );
        } );

    $( "#joinDefinitionModal button.btn-primary" ).click(
        function ( e ) {
            e.preventDefault();
            // 結合テーブル追加処理
            var definition = $( "#joinDefinitionModal" ).sourceDialog(
                "getDefinition" );

            if ( parseInt( definition.no, 10 ) !== 0 ) {
                $( "#tableSourceDefinition tbody" ).sourceMaster(
                    "updateRecord", definition );
                $( "#joinDefinitionModal" ).modal( "hide" );
                return;
            }

            $( "#tableSourceDefinition tbody" ).sourceMaster( "addRecord",
                $( "#tableSourceDefinitionTemplate tbody tr" ),
                definition );
            $( "#tableSourceDefinition" ).sourceMaster( "resetNo" );

            // 結合テーブル削除処理
            $( "#tableSourceDefinition .removeRecord" ).click( function ( e ) {
                e.preventDefault();
                $( this ).sourceMaster( "removeRecord" );
                $( "#tableSourceDefinition" ).sourceMaster( "resetNo" );
            } );

            // 結合テーブル編集ダイアログ起動処理
            $( "#tableSourceDefinition .updateRecord" ).off( "click" );
            $( "#tableSourceDefinition .updateRecord" ).click( function ( e ) {
                e.preventDefault();
                var definition = $( this ).sourceMaster( "getDefinition" );
                $( "#joinDefinitionModal" ).sourceDialog( "init", definition );
                $( "#joinDefinitionModal" ).modal( "show" );
            } );

            // 一つ上に移動する。
            $( "#tableSourceDefinition .dropUpRecord" ).off( "click" );
            $( "#tableSourceDefinition .dropUpRecord" ).click( function ( e ) {
                e.preventDefault();
                $( this ).sourceMaster( "dropUpRecord" );
                $( "#tableSourceDefinition" ).sourceMaster( "resetNo" );
            } );

            // 一つ下に移動する。
            $( "#tableSourceDefinition .dropDownRecord" ).off( "click" );
            $( "#tableSourceDefinition .dropDownRecord" ).click( function ( e ) {
                e.preventDefault();
                $( this ).sourceMaster( "dropDownRecord" );
                $( "#tableSourceDefinition" ).sourceMaster( "resetNo" );
            } );

            $( "#joinDefinitionModal" ).modal( "hide" );
        } );
    // 結合テーブル削除処理
    $( "#tableSourceDefinition .removeRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).sourceMaster( "removeRecord" );
        $( "#tableSourceDefinition" ).sourceMaster( "resetNo" );
    } );

    $( "#tableSourceDefinition .updateRecord" ).click( function ( e ) {
        e.preventDefault();
        var definition = $( this ).sourceMaster( "getDefinition" );
        $( "#joinDefinitionModal" ).sourceDialog( "init", definition );
        $( "#joinDefinitionModal" ).modal( "show" );
    } );
    // 一つ上に移動する。
    $( "#tableSourceDefinition .dropUpRecord" ).off( "click" );
    $( "#tableSourceDefinition .dropUpRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).sourceMaster( "dropUpRecord" );
        $( "#tableSourceDefinition" ).sourceMaster( "resetNo" );
    } );
    // 一つ下に移動する。
    $( "#tableSourceDefinition .dropDownRecord" ).off( "click" );
    $( "#tableSourceDefinition .dropDownRecord" ).click( function ( e ) {
        e.preventDefault();
        $( this ).sourceMaster( "dropDownRecord" );
        $( "#tableSourceDefinition" ).sourceMaster( "resetNo" );
    } );
} );