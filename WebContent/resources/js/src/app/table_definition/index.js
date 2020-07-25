import $ from "jquery";
import FieldMasterService from "../../domain/service/field_master";
import TableMasterService from "../../domain/service/table_master";

$( function () {
    var tableMasterService = new TableMasterService( "form" );
    var fieldMasterService = new FieldMasterService( "#fieldMaster tbody", "#fieldMasterTemplate tbody tr" );

    // テーブル定義削除処理
    $( "button.btn-danger" ).click( function () {
        tableMasterService.delete();
    } );

    // フィールド定義削除処理
    $( ".removeRecord" ).click( function ( e ) {
        e.preventDefault();
        fieldMasterService.delete( $( this ) );
    } );

    // 一つ上に移動する。
    $( ".dropUpRecord" ).click( function ( e ) {
        e.preventDefault();
        fieldMasterService.dropUp( $( this ) );
    } );

    // 一つ下に移動する。
    $( ".dropDownRecord" ).click( function ( e ) {
        e.preventDefault();
        fieldMasterService.dropDown( $( this ) );
    } );

    // 新規フィールド定義追加処理
    $( ".addRecord" ).click(
        function ( e ) {
            e.preventDefault();
            var fieldMasterService = new FieldMasterService( "#fieldMaster tbody", "#fieldMasterTemplate tbody tr" );
            fieldMasterService.create();

            // フィールド定義削除処理
            $( ".removeRecord" ).click( function ( e ) {
                e.preventDefault();
                fieldMasterService.delete( $( this ) );
            } );

            // 一つ上に移動する。
            $( ".dropUpRecord" ).off( "click" );
            $( ".dropUpRecord" ).click( function ( e ) {
                e.preventDefault();
                fieldMasterService.dropUp( $( this ) );
            } );

            // 一つ下に移動する。
            $( ".dropDownRecord" ).off( "click" );
            $( ".dropDownRecord" ).click( function ( e ) {
                e.preventDefault();
                fieldMasterService.dropDown( $( this ) );
            } );
        } );
} );