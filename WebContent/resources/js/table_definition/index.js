/**
 * db-design-tool/table_definition で使用する処理を定義する。
 */
(function ($) {
    $.tableDefinition = {};

    /*
     * No 列の自動採番を実行する。
     * 
     * @param $table 操作対象とするテーブルを指定する。
     */
    $.tableDefinition.resetNo = function ($table) {
        $table.find("tbody th").each(function (index) {
            $(this).text(index + 1);
        });
    };

    /*
     * 新規レコードをテーブルへ追加する。
     * 
     * @param $template コピー対象とするテンプレートを指定する。
     * 
     * @param $target レコードの挿入先を指定する。
     */
    $.tableDefinition.addRecord = function ($template, $target) {
        var template = $template.clone();
        $target.append(template);
    };

    /*
     * レコードを削除する。
     * 
     * @param $td 削除対象とする要素を指定する。
     */
    $.tableDefinition.removeRecord = function ($td) {
        $td.parent().parent().remove();
    };

    /*
     * レコードを一つ上に移動する。
     * 
     * @param $td 操作対象とする要素を指定する。
     */
    $.tableDefinition.dropUpRecord = function ($td) {
        var src = $td.parent().parent();
        var dst = src.prev();

        src.insertBefore(dst);
    };

    /*
     * レコードを一つ下に移動する。
     * 
     * @param $td 操作対象とする要素を指定する。
     */
    $.tableDefinition.dropDownRecord = function ($td) {
        var src = $td.parent().parent();
        var dst = src.next();

        src.insertAfter(dst);
    };
}(jQuery));

$(function () {
    // 新規レコード追加処理
    $(".addRecord").click(
            function (e) {
                e.preventDefault();
                $.tableDefinition.addRecord($("#definitionTemplate tbody tr"),
                        $("#tableDefinition tbody"));
                $.tableDefinition.resetNo($("#tableDefinition"));

                // レコード削除処理
                $(".removeRecord").click(function (e) {
                    e.preventDefault();
                    $.tableDefinition.removeRecord($(this));
                    $.tableDefinition.resetNo($("#tableDefinition"));
                });

                // 一つ上に移動する。
                $(".dropUpRecord").off("click");
                $(".dropUpRecord").click(function (e) {
                    e.preventDefault();
                    $.tableDefinition.dropUpRecord($(this));
                    $.tableDefinition.resetNo($("#tableDefinition"));
                });

                // 一つ下に移動する。
                $(".dropDownRecord").off("click");
                $(".dropDownRecord").click(function (e) {
                    e.preventDefault();
                    $.tableDefinition.dropDownRecord($(this));
                    $.tableDefinition.resetNo($("#tableDefinition"));
                });
            });

    // レコード削除処理
    $(".removeRecord").click(function (e) {
        e.preventDefault();
        $.tableDefinition.removeRecord($(this));
        $.tableDefinition.resetNo($("#tableDefinition"));
    });

    // 一つ上に移動する。
    $(".dropUpRecord").click(function (e) {
        e.preventDefault();
        $.tableDefinition.dropUpRecord($(this));
        $.tableDefinition.resetNo($("#tableDefinition"));
    });

    // 一つ下に移動する。
    $(".dropDownRecord").click(function (e) {
        e.preventDefault();
        $.tableDefinition.dropDownRecord($(this));
        $.tableDefinition.resetNo($("#tableDefinition"));
    });
});