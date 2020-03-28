/**
 * db-design-tool/table_definition で使用する処理を定義する。
 */
$(function () {
    // テーブル定義削除処理
    $("button.btn-danger").click(function () {
        $("input[name='deleteFlag']").val(1);
        $("form").submit();
    });

    // 新規フィールド定義追加処理
    $(".addRecord").click(
            function (e) {
                e.preventDefault();
                $("#tableDefinition").fieldMaster("addRecord",
                        $("#definitionTemplate tbody tr"));
                $("#tableDefinition").fieldMaster("resetNo");

                // フィールド定義削除処理
                $(".removeRecord").click(function (e) {
                    e.preventDefault();
                    $(this).fieldMaster("removeRecord");
                    $("#tableDefinition").fieldMaster("resetNo");
                });

                // 一つ上に移動する。
                $(".dropUpRecord").off("click");
                $(".dropUpRecord").click(function (e) {
                    e.preventDefault();
                    $(this).fieldMaster("dropUpRecord");
                    $("#tableDefinition").fieldMaster("resetNo");
                });

                // 一つ下に移動する。
                $(".dropDownRecord").off("click");
                $(".dropDownRecord").click(function (e) {
                    e.preventDefault();
                    $(this).fieldMaster("dropDownRecord");
                    $("#tableDefinition").fieldMaster("resetNo");
                });
            });

    // フィールド定義削除処理
    $(".removeRecord").click(function (e) {
        e.preventDefault();
        $(this).fieldMaster("removeRecord");
        $("#tableDefinition").fieldMaster("resetNo");
    });

    // 一つ上に移動する。
    $(".dropUpRecord").click(function (e) {
        e.preventDefault();
        $(this).fieldMaster("dropUpRecord");
        $("#tableDefinition").fieldMaster("resetNo");
    });

    // 一つ下に移動する。
    $(".dropDownRecord").click(function (e) {
        e.preventDefault();
        $(this).fieldMaster("dropDownRecord");
        $("#tableDefinition").fieldMaster("resetNo");
    });
});