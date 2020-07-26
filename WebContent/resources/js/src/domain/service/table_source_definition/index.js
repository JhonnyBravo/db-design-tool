import $ from "jquery";
import TableSourceDefinition from "../../model/table_source_definition";

/**
 * table_source_definition のレコード操作を管理する。
 */
export default class TableSourceDefinitionService {
    /**
     * @param el 操作対象とする要素を指定する。
     * @param template 操作対象とするテンプレート要素を指定する。
     */
    constructor( el, template ) {
        this.el = el;
        this.template = template;
    }

    /**
     * DOM 上のフォームコントロールから取得したデータをオブジェクトに変換して返す。
     * 
     * @param el 操作対象とする要素を指定する。
     * @returns tableSourceDefinition
     * {
     *  definitionId: 取得元テーブル定義を識別する為の ID,
     *  no: 取得元テーブル定義の項番,
     *  sourceId: 取得元テーブルの tableId,
     *  sourceName: 取得元テーブルの物理名,
     *  joinCondition: 取得元テーブルの結合条件
     * }
     */
    getData ( el ) {
        var tableSourceDefinition = new TableSourceDefinition( $( el ).parent().parent() );
        return tableSourceDefinition.getData();
    }

    /**
     * no をキーに tr 要素を検索する。
     * @param no 検索対象とする no を指定する。
     * @returns $tr
     */
    findByNo ( no ) {
        var record = $( this.el ).find( `input[name='tableSourceDefinition.no'][value='${ no }']` ).parent().parent();
        return record;
    }

    /**
     * no 列の自動採番を実行する。
     */
    setNo () {
        $( this.el ).find( "tr" ).each( function ( index ) {
            var tableSourceDefinition = new TableSourceDefinition( $( this ) );
            var data = tableSourceDefinition.getData();
            data.no = index + 1;
            $( this ).find( "th" ).text( data.no ).append( `<input type='hidden' name='tableSourceDefinition.no' value='${ data.no }' />` );
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
     * @param tableSourceDefinition
     * {
     *  definitionId: 取得元テーブル定義を識別する為の ID,
     *  no: 取得元テーブル定義の項番,
     *  sourceId: 取得元テーブルの tableId,
     *  sourceName: 取得元テーブルの物理名,
     *  joinCondition: 取得元テーブルの結合条件
     * }
     */
    create ( tableSourceDefinition ) {
        var template = $( this.template ).clone();

        // sourceId, sourceName
        var sourceName = template.find( "td" ).eq( 0 );
        sourceName.text( tableSourceDefinition.sourceName );
        sourceName.append( `<input type='hidden' name='tableSourceDefinition.sourceId' value='${ tableSourceDefinition.sourceId }' />` );
        sourceName.append( `<input type='hidden' name='tableSourceDefinition.sourceName' value='${ tableSourceDefinition.sourceName }' />` );

        // joinCondition
        var joinCondition = template.find( "td" ).eq( 1 );
        joinCondition.text( tableSourceDefinition.joinCondition );
        joinCondition.append( `<input type='hidden' name='tableSourceDefinition.joinCondition' value='${ tableSourceDefinition.joinCondition }' />` );

        $( this.el ).append( template );
        this.setNo();
    }

    /**
     * レコードを更新する。
     * 
     * @param tableSourceDefinition
     * {
     *  definitionId: 取得元テーブル定義を識別する為の ID,
     *  no: 取得元テーブル定義の項番,
     *  sourceId: 取得元テーブルの tableId,
     *  sourceName: 取得元テーブルの物理名,
     *  joinCondition: 取得元テーブルの結合条件
     * }
     */
    update ( tableSourceDefinition ) {
        var record = this.findByNo( tableSourceDefinition.no );

        // sourceId, sourceName
        var sourceName = record.find( "td" ).eq( 0 );
        sourceName.text( tableSourceDefinition.sourceName );
        sourceName.append( `<input type='hidden' name='tableSourceDefinition.sourceId' value='${ tableSourceDefinition.sourceId }' />` );
        sourceName.append( `<input type='hidden' name='tableSourceDefinition.sourceName' value='${ tableSourceDefinition.sourceName }' />` );

        // joinCondition
        var joinCondition = record.find( "td" ).eq( 1 );
        joinCondition.text( tableSourceDefinition.joinCondition );
        joinCondition.append( `<input type='hidden' name='tableSourceDefinition.joinCondition' value='${ tableSourceDefinition.joinCondition }' />` );
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