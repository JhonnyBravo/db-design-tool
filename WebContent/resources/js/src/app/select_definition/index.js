import $ from "jquery";
import TableMasterService from "../../domain/service/table_master";
import TableSourceDefinitionDialogService from "../../domain/service/table_source_definition_dialog";
import TableSourceDefinitionService from "../../domain/service/table_source_definition";
import FieldSourceDefinitionDialogService from "../../domain/service/field_source_definition_dialog";
import FieldSourceDefinitionService from "../../domain/service/field_source_definition";

$( function () {
    var tableMasterService = new TableMasterService( "form" );

    // クエリ定義削除処理
    $( "button.btn-danger" ).click( function () {
        tableMasterService.delete();
    } );

    // 取得元フィールド
    var fldSrcService = new FieldSourceDefinitionService( "#fieldSourceDefinition tbody", "#fieldSourceDefinitionTemplate tbody tr" );
    var fldSrcDialogService = new FieldSourceDefinitionDialogService( "#fieldDefinitionModal", "#tableSourceDefinition tbody tr" );

    // 取得元フィールド選択ダイアログの起動処理
    $( "#fieldSourceDefinition .addRecord" ).click( function ( e ) {
        e.preventDefault();
        fldSrcDialogService.initData();
        fldSrcDialogService.open();
    } );

    // 取得元フィールド選択ダイアログのフィールド一覧更新処理
    $( "#fieldDefinitionModal" ).find( "select[name='tableMaster.physicalName']" ).change( function () {
        fldSrcDialogService.addFieldMaster();
    } );

    $( "#fieldDefinitionModal button.btn-primary" ).click( function ( e ) {
        e.preventDefault();
        // 新規フィールド定義追加処理
        var definition = fldSrcDialogService.getData();
        fldSrcService.create( definition );
        fldSrcDialogService.close();

        // フィールド定義削除処理
        $( "#fieldSourceDefinition .removeRecord" ).click( function ( e ) {
            e.preventDefault();
            fldSrcService.delete( $( this ) );
        } );

        // 一つ上に移動する。
        $( "#fieldSourceDefinition .dropUpRecord" ).off( "click" );
        $( "#fieldSourceDefinition .dropUpRecord" ).click( function ( e ) {
            e.preventDefault();
            fldSrcService.dropUp( $( this ) );
        } );

        // 一つ下に移動する。
        $( "#fieldSourceDefinition .dropDownRecord" ).off( "click" );
        $( "#fieldSourceDefinition .dropDownRecord" ).click( function ( e ) {
            e.preventDefault();
            fldSrcService.dropDown( $( this ) );
        } );
    } );

    // フィールド定義削除処理
    $( "#fieldSourceDefinition .removeRecord" ).click( function ( e ) {
        e.preventDefault();
        fldSrcService.delete( $( this ) );
    } );

    // 一つ上に移動する。
    $( "#fieldSourceDefinition .dropUpRecord" ).click( function ( e ) {
        e.preventDefault();
        fldSrcService.dropUp( $( this ) );
    } );

    // 一つ下に移動する。
    $( "#fieldSourceDefinition .dropDownRecord" ).click( function ( e ) {
        e.preventDefault();
        fldSrcService.dropDown( $( this ) );
    } );

    // 取得元テーブル
    var tblSrcDialogService = new TableSourceDefinitionDialogService( "#joinDefinitionModal" );
    var tblSrcService = new TableSourceDefinitionService( "#tableSourceDefinition tbody", "#tableSourceDefinitionTemplate tbody tr" );

    $( "#tableSourceDefinition .addRecord" ).click(
        function ( e ) {
            e.preventDefault();
            // ダイアログを新規登録モードで起動する。
            tblSrcDialogService.initData();
            tblSrcDialogService.open();
        } );

    $( "#joinDefinitionModal button.btn-primary" ).click(
        function ( e ) {
            e.preventDefault();
            var definition = tblSrcDialogService.getData();

            if ( parseInt( definition.no, 10 ) !== 0 ) {
                // 取得元テーブル更新処理
                tblSrcService.update( definition );
                tblSrcDialogService.close();
                return;
            }

            // 取得元テーブル追加処理
            tblSrcService.create( definition );

            // 取得元テーブル削除処理
            $( "#tableSourceDefinition .removeRecord" ).click( function ( e ) {
                e.preventDefault();
                tblSrcService.delete( $( this ) );
            } );

            // ダイアログを更新モードで起動する。
            $( "#tableSourceDefinition .updateRecord" ).off( "click" );
            $( "#tableSourceDefinition .updateRecord" ).click( function ( e ) {
                e.preventDefault();
                var definition = tblSrcService.getData( $( this ) );
                tblSrcDialogService.setData( definition );
                tblSrcDialogService.open();
            } );

            // 一つ上に移動する。
            $( "#tableSourceDefinition .dropUpRecord" ).off( "click" );
            $( "#tableSourceDefinition .dropUpRecord" ).click( function ( e ) {
                e.preventDefault();
                tblSrcService.dropUp( $( this ) );
            } );

            // 一つ下に移動する。
            $( "#tableSourceDefinition .dropDownRecord" ).off( "click" );
            $( "#tableSourceDefinition .dropDownRecord" ).click( function ( e ) {
                e.preventDefault();
                tblSrcService.dropDown( $( this ) );
            } );

            tblSrcDialogService.close();
        } );

    // 取得元テーブル削除処理
    $( "#tableSourceDefinition .removeRecord" ).click( function ( e ) {
        e.preventDefault();
        tblSrcService.delete( $( this ) );
    } );

    // 取得元テーブル更新処理
    $( "#tableSourceDefinition .updateRecord" ).click( function ( e ) {
        e.preventDefault();
        var definition = tblSrcService.getData( $( this ) );
        tblSrcDialogService.setData( definition );
        tblSrcDialogService.open();
    } );

    // 一つ上に移動する。
    $( "#tableSourceDefinition .dropUpRecord" ).off( "click" );
    $( "#tableSourceDefinition .dropUpRecord" ).click( function ( e ) {
        e.preventDefault();
        tblSrcService.dropUp( $( this ) );
    } );

    // 一つ下に移動する。
    $( "#tableSourceDefinition .dropDownRecord" ).off( "click" );
    $( "#tableSourceDefinition .dropDownRecord" ).click( function ( e ) {
        e.preventDefault();
        tblSrcService.dropDown( $( this ) );
    } );
} );