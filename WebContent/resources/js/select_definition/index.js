/**
 * db-design-tool/select_definition で使用する処理を定義する。
 */
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
                $("#tableDefinition").fieldMaster("addRecord",
                        $("#definitionTemplate tbody tr"));
                $("#tableDefinition").fieldMaster("resetNo");

                // フィールド定義削除処理
                $("#tableDefinition .removeRecord").click(function (e) {
                    e.preventDefault();
                    $(this).fieldMaster("removeRecord");
                    $("#tableDefinition").fieldMaster("resetNo");
                });

                // 一つ上に移動する。
                $("#tableDefinition .dropUpRecord").off("click");
                $("#tableDefinition .dropUpRecord").click(function (e) {
                    e.preventDefault();
                    $(this).fieldMaster("dropUpRecord");
                    $("#tableDefinition").fieldMaster("resetNo");
                });

                // 一つ下に移動する。
                $("#tableDefinition .dropDownRecord").off("click");
                $("#tableDefinition .dropDownRecord").click(function (e) {
                    e.preventDefault();
                    $(this).fieldMaster("dropDownRecord");
                    $("#tableDefinition").fieldMaster("resetNo");
                });
            });

    // フィールド定義削除処理
    $("#tableDefinition .removeRecord").click(function (e) {
        e.preventDefault();
        $(this).fieldMaster("removeRecord");
        $("#tableDefinition").fieldMaster("resetNo");
    });

    // 一つ上に移動する。
    $("#tableDefinition .dropUpRecord").click(function (e) {
        e.preventDefault();
        $(this).fieldMaster("dropUpRecord");
        $("#tableDefinition").fieldMaster("resetNo");
    });

    // 一つ下に移動する。
    $("#tableDefinition .dropDownRecord").click(function (e) {
        e.preventDefault();
        $(this).fieldMaster("dropDownRecord");
        $("#tableDefinition").fieldMaster("resetNo");
    });

    // 新規結合テーブル編集用ダイアログを起動する。
    $("#joinDefinition .addRecord").click(
            function (e) {
                e.preventDefault();
                var definition = $("#joinDefinitionModal").sourceDialog(
                        "createDefinition");
                $("#joinDefinitionModal").sourceDialog("init", definition);
                $("#joinDefinitionModal").modal("show");
            });

    $("#joinDefinitionModal button.btn-primary").click(
            function (e) {
                e.preventDefault();
                // 結合テーブル追加処理
                var definition = $("#joinDefinitionModal").sourceDialog(
                        "getDefinition");

                if (definition.no != "0") {
                    $("#joinDefinition tbody").sourceMaster("updateRecord",
                            definition);
                    $("#joinDefinitionModal").modal("hide");
                    return;
                }

                $("#joinDefinition tbody").sourceMaster("addRecord",
                        $("#joinDefinitionTemplate tbody tr"), definition);
                $("#joinDefinition").sourceMaster("resetNo");

                // 結合テーブル削除処理
                $("#joinDefinition .removeRecord").click(function (e) {
                    e.preventDefault();
                    $(this).sourceMaster("removeRecord");
                    $("#joinDefinition").sourceMaster("resetNo");
                });

                // 結合テーブル編集ダイアログ起動処理
                $("#joinDefinition .updateRecord").off("click");
                $("#joinDefinition .updateRecord").click(function (e) {
                    e.preventDefault();
                    var definition = $(this).sourceMaster("getDefinition");
                    $("#joinDefinitionModal").sourceDialog("init", definition);
                    $("#joinDefinitionModal").modal("show");
                });

                // 一つ上に移動する。
                $("#joinDefinition .dropUpRecord").off("click");
                $("#joinDefinition .dropUpRecord").click(function (e) {
                    e.preventDefault();
                    $(this).sourceMaster("dropUpRecord");
                    $("#joinDefinition").sourceMaster("resetNo");
                });

                // 一つ下に移動する。
                $("#joinDefinition .dropDownRecord").off("click");
                $("#joinDefinition .dropDownRecord").click(function (e) {
                    e.preventDefault();
                    $(this).sourceMaster("dropDownRecord");
                    $("#joinDefinition").sourceMaster("resetNo");
                });

                $("#joinDefinitionModal").modal("hide");
            });

    // 結合テーブル削除処理
    $("#joinDefinition .removeRecord").click(function (e) {
        e.preventDefault();
        $(this).sourceMaster("removeRecord");
        $("#joinDefinition").sourceMaster("resetNo");
    });

    // 一つ上に移動する。
    $("#joinDefinition .dropUpRecord").off("click");
    $("#joinDefinition .dropUpRecord").click(function (e) {
        e.preventDefault();
        $(this).sourceMaster("dropUpRecord");
        $("#joinDefinition").sourceMaster("resetNo");
    });

    // 一つ下に移動する。
    $("#joinDefinition .dropDownRecord").off("click");
    $("#joinDefinition .dropDownRecord").click(function (e) {
        e.preventDefault();
        $(this).sourceMaster("dropDownRecord");
        $("#joinDefinition").sourceMaster("resetNo");
    });
});