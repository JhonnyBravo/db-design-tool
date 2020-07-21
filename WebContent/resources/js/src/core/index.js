import "bootstrap/js/dist/modal";
import FieldMaster from "../field_master";
import FldSrcDialog from "../field_source_definition/dialog";
import FldSrcTable from "../field_source_definition/table";
import TblSrcDialog from "../table_source_definition/dialog";
import TblSrcTable from "../table_source_definition/table";

/**
 * FieldMaster プラグインに登録されているメソッドを実行する。
 * @param method 実行するメソッドの名前を指定する。
 * @see FieldMaster
 */
$.fn.fieldMaster = function ( method ) {
    if ( FieldMaster[ method ] ) {
        return FieldMaster[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ) );
    } else {
        $.error( `${ method }: 未定義のメソッドです。` );
    }
};
/**
 * FldSrcDialog プラグインに登録されているメソッドを実行する。
 * @param method 実行するメソッドの名前を指定する。
 * @see FldSrcDialog
 */
$.fn.fldSrcDialog = function ( method ) {
    if ( FldSrcDialog[ method ] ) {
        return FldSrcDialog[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ) );
    } else {
        $.error( `${ method }: 未定義のメソッドです。` );
    }
};
/**
 * FldSrcTable プラグインに登録されているメソッドを実行する。
 * @param method 実行するメソッドの名前を指定する。
 * @see FldSrcTable
 */
$.fn.fldSrcTable = function ( method ) {
    if ( FldSrcTable[ method ] ) {
        return FldSrcTable[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ) );
    } else {
        $.error( `${ method }: 未定義のメソッドです。` );
    }
};
/**
 * TblSrcDialog プラグインに登録されているメソッドを実行する。
 * @param method 実行するメソッドの名前を指定する。
 * @see TblSrcDialog
 */
$.fn.tblSrcDialog = function ( method ) {
    if ( TblSrcDialog[ method ] ) {
        return TblSrcDialog[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ) );
    } else {
        $.error( `${ method }: 未定義のメソッドです。` );
    }
};
/**
 * TblSrcTable プラグインに登録されているメソッドを実行する。
 * @param method 実行するメソッドの名前を指定する。
 * @see TblSrcTable
 */
$.fn.tblSrcTable = function ( method ) {
    if ( TblSrcTable[ method ] ) {
        return TblSrcTable[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ) );
    } else {
        $.error( `${ method }: 未定義のメソッドです。` );
    }
};

export default $;