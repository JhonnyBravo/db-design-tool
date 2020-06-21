import "bootstrap";

export default {
    /**
     * モーダルダイアログを開く。
     * @param $dialog 操作対象とする要素を指定する。
     */
    open: function ( $dialog ) {
        $( $dialog ).modal( "show" );
    },
    /**
     * モーダルダイアログを閉じる。
     * @param $dialog 操作対象とする要素を指定する。
     */
    close: function ( $dialog ) {
        $( $dialog ).modal( "hide" );
    }
}