(function ($) {
    var FieldMaster = {
        /*
         * No 列の自動採番を実行する。
         */
        resetNo : function () {
            $(this).find("tbody th").each(
                    function (index) {
                        $(this).text(index + 1).append(
                                "<input type='hidden' name='no' value='"
                                        + (index + 1) + "'/>");
                    });
        },
        /*
         * 新規レコードをテーブルへ追加する。
         * 
         * @param $template コピー対象とするテンプレートを指定する。
         */
        addRecord : function ($template) {
            $(this).find("tbody").append($template.clone());
        },
        /*
         * レコードを削除する。
         */
        removeRecord : function () {
            $(this).parent().parent().remove();
        },
        /*
         * レコードを一つ上に移動する。
         */
        dropUpRecord : function () {
            var src = $(this).parent().parent();
            var dst = src.prev();
            src.insertBefore(dst);
        },
        /*
         * レコードを一つ下に移動する。
         * 
         * @param $td 操作対象とする要素を指定する。
         */
        dropDownRecord : function () {
            var src = $(this).parent().parent();
            var dst = src.next();
            src.insertAfter(dst);
        }
    };

    /**
     * fieldMaster プラグインに登録されているメソッドを実行する。
     * @param method 実行するメソッドの名前を指定する。
     */
    $.fn.fieldMaster = function (method) {
        if (FieldMaster[method]) {
            return FieldMaster[method].apply(this, Array.prototype.slice.call(
                    arguments, 1));
        } else {
            $.error(method + ": 未定義のメソッドです。");
        }
    }
})(jQuery);