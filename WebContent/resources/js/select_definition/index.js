/**
 * db-design-tool/select_definition で使用する処理を定義する。
 */
(function ($) {
    $.tableDefinition = {};

    /*
     * No 列の自動採番を実行する。
     * 
     * @param $table 操作対象とするテーブルを指定する。
     */
    $.tableDefinition.resetNo = function ($table) {
        $table.find("tbody th").each(
                function (index) {
                    $(this).text(index + 1).append(
                            "<input type='hidden' name='no' value='"
                                    + (index + 1) + "'/>");
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

    $.joinDefinition = $.extend({}, $.tableDefinition);
    $.joinDefinition.getDefinition = function ($dialog) {
        var joinDefinition = {};
        joinDefinition.no = $dialog.find("input[name='no']").val();
        joinDefinition.tableId = $dialog.find("select[name='tableName']").find(
                "option:selected").val();
        joinDefinition.tableName = $dialog.find("select[name='tableName']")
                .find("option:selected").text();
        joinDefinition.condition = $dialog.find("textarea[name='condition']")
                .val();
        return joinDefinition;
    };
    $.joinDefinition.setDefinition = function (definition, $target) {
        // 定義を hidden input へ格納する。
        var $tableId = $("<input type='hidden' name='joinTableId'>");
        $tableId.val(definition.tableId);

        var $condition = $("<input type='hidden' name='joinCondition'>");
        $condition.val(definition.condition);

        // テンプレートを本体へ挿入
        var $template = $("#joinDefinitionTemplate").find("tbody tr").clone();

        var $tableName = $template.find("td").eq(0);
        $tableName.text(definition.tableName);
        $tableName.append($tableId);

        var $joinCondition = $template.find("td").eq(1);
        $joinCondition.text(definition.condition);
        $joinCondition.append($condition);

        $target.append($template);
    };

    $.joinDefinition.updateDefinition = function (definition, $target) {
        var $no = $target.find(
                "input[name='joinNo'][value='" + definition.no + "']").parent();

        $tableName = $no.next();
        $tableId = $tableName.find("input[name='joinTableId']");
        $tableId.val(definition.tableId);
        $tableName.text(definition.tableName);
        $tableName.append($tableId);

        $joinCondition = $tableName.next();
        $condition = $joinCondition.find("input[name='joinCondition']");
        $condition.val(definition.condition);
        $joinCondition.text(definition.condition);
        $joinCondition.append($condition);
    };

    $.joinDefinition.resetNo = function ($table) {
        $table.find("tbody th").each(
                function (index) {
                    $(this).text(index + 1).append(
                            "<input type='hidden' name='joinNo' value='"
                                    + (index + 1) + "'/>");
                });
    };
}(jQuery));

$(function () {
    // テーブル定義削除処理
    $("button.btn-danger").click(function () {
        $("input[name='deleteFlag']").val(1);
        $("form").submit();
    });

    // 新規フィールド定義追加処理
    $("#tableDefinition .addRecord").click(
            function (e) {
                e.preventDefault();
                $.tableDefinition.addRecord($("#definitionTemplate tbody tr"),
                        $("#tableDefinition tbody"));
                $.tableDefinition.resetNo($("#tableDefinition"));

                // フィールド定義削除処理
                $("#tableDefinition .removeRecord").click(function (e) {
                    e.preventDefault();
                    $.tableDefinition.removeRecord($(this));
                    $.tableDefinition.resetNo($("#tableDefinition"));
                });

                // 一つ上に移動する。
                $("#tableDefinition .dropUpRecord").off("click");
                $("#tableDefinition .dropUpRecord").click(function (e) {
                    e.preventDefault();
                    $.tableDefinition.dropUpRecord($(this));
                    $.tableDefinition.resetNo($("#tableDefinition"));
                });

                // 一つ下に移動する。
                $("#tableDefinition .dropDownRecord").off("click");
                $("#tableDefinition .dropDownRecord").click(function (e) {
                    e.preventDefault();
                    $.tableDefinition.dropDownRecord($(this));
                    $.tableDefinition.resetNo($("#tableDefinition"));
                });
            });

    // フィールド定義削除処理
    $("#tableDefinition .removeRecord").click(function (e) {
        e.preventDefault();
        $.tableDefinition.removeRecord($(this));
        $.tableDefinition.resetNo($("#tableDefinition"));
    });

    // 一つ上に移動する。
    $("#tableDefinition .dropUpRecord").click(function (e) {
        e.preventDefault();
        $.tableDefinition.dropUpRecord($(this));
        $.tableDefinition.resetNo($("#tableDefinition"));
    });

    // 一つ下に移動する。
    $("#tableDefinition .dropDownRecord").click(function (e) {
        e.preventDefault();
        $.tableDefinition.dropDownRecord($(this));
        $.tableDefinition.resetNo($("#tableDefinition"));
    });

    // 新規結合テーブル編集用ダイアログを起動する。
    $("#joinDefinition .addRecord").click(
            function (e) {
                e.preventDefault();
                var $dialog = $("#joinDefinitionModal");

                var definition = {};
                definition.no = 0;
                definition.tableId = $dialog.find(
                        "select[name='tableName'] option:first-child").val();
                definition.condition = null;

                $dialog.find("input[name='no']").val(definition.no);
                $dialog.find("select[name='tableName']")
                        .val(definition.tableId)
                $dialog.find("textarea[name='condition']").val(
                        definition.condition);
                $dialog.modal("show");
            });

    $("#joinDefinitionModal button.btn-primary").click(
            function (e) {
                e.preventDefault();
                // 結合テーブル追加処理
                var joinDefinition = $.joinDefinition
                        .getDefinition($("#joinDefinitionModal"));

                if (joinDefinition.no != "0") {
                    $.joinDefinition.updateDefinition(joinDefinition,
                            $("#joinDefinition tbody"));
                    $("#joinDefinitionModal").modal("hide");
                    return;
                }

                $.joinDefinition.setDefinition(joinDefinition,
                        $("#joinDefinition tbody"));
                $.joinDefinition.resetNo($("#joinDefinition"));

                // 結合テーブル削除処理
                $("#joinDefinition .removeRecord").click(function (e) {
                    e.preventDefault();
                    $.joinDefinition.removeRecord($(this));
                    $.joinDefinition.resetNo($("#joinDefinition"));
                });

                // 結合テーブル編集ダイアログ起動処理
                $("#joinDefinition .updateRecord").off("click");
                $("#joinDefinition .updateRecord").click(
                        function (e) {
                            e.preventDefault();
                            var $record = $(this).parent().parent();

                            var joinDefinition = {};
                            joinDefinition.no = $record.find(
                                    "input[name='joinNo']").val();
                            joinDefinition.tableId = $record.find(
                                    "input[name='joinTableId']").val();
                            joinDefinition.condition = $record.find(
                                    "input[name='joinCondition']").val();

                            var $dialog = $("#joinDefinitionModal");
                            $dialog.find("input[name='no']").val(
                                    joinDefinition.no);
                            $dialog.find("select[name='tableName']").val(
                                    joinDefinition.tableId);
                            $dialog.find("textarea[name='condition']").val(
                                    joinDefinition.condition);
                            $dialog.modal("show");
                        });

                // 一つ上に移動する。
                $("#joinDefinition .dropUpRecord").off("click");
                $("#joinDefinition .dropUpRecord").click(function (e) {
                    e.preventDefault();
                    $.joinDefinition.dropUpRecord($(this));
                    $.joinDefinition.resetNo($("#joinDefinition"));
                });

                // 一つ下に移動する。
                $("#joinDefinition .dropDownRecord").off("click");
                $("#joinDefinition .dropDownRecord").click(function (e) {
                    e.preventDefault();
                    $.joinDefinition.dropDownRecord($(this));
                    $.joinDefinition.resetNo($("#joinDefinition"));
                });

                $("#joinDefinitionModal").modal("hide");
            });

    // 結合テーブル削除処理
    $("#joinDefinition .removeRecord").click(function (e) {
        e.preventDefault();
        $.joinDefinition.removeRecord($(this));
        $.joinDefinition.resetNo($("#joinDefinition"));
    });

    // 一つ上に移動する。
    $("#joinDefinition .dropUpRecord").off("click");
    $("#joinDefinition .dropUpRecord").click(function (e) {
        e.preventDefault();
        $.joinDefinition.dropUpRecord($(this));
        $.joinDefinition.resetNo($("#joinDefinition"));
    });

    // 一つ下に移動する。
    $("#joinDefinition .dropDownRecord").off("click");
    $("#joinDefinition .dropDownRecord").click(function (e) {
        e.preventDefault();
        $.joinDefinition.dropDownRecord($(this));
        $.joinDefinition.resetNo($("#joinDefinition"));
    });
});