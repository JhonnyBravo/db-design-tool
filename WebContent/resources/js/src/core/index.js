import "bootstrap";
import FieldMaster from "../field_master";
import SourceDialog from "../source_dialog";
import SourceMaster from "../source_master";

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
 * SourceDialog プラグインに登録されているメソッドを実行する。
 * @param method 実行するメソッドの名前を指定する。
 * @see SourceDialog
 */
$.fn.sourceDialog = function ( method ) {
    if ( SourceDialog[ method ] ) {
        return SourceDialog[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ) );
    } else {
        $.error( `${ method }: 未定義のメソッドです。` );
    }
};
/**
 * SourceMaster プラグインに登録されているメソッドを実行する。
 * @param method 実行するメソッドの名前を指定する。
 * @see SourceMaster
 */
$.fn.sourceMaster = function ( method ) {
    if ( SourceMaster[ method ] ) {
        return SourceMaster[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ) );
    } else {
        $.error( `${ method }: 未定義のメソッドです。` );
    }
}

export default $;