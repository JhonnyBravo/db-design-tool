(function ($) {
    var FieldMaster = {
        /**
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
        /**
         * 新規レコードをテーブルへ追加する。
         * 
         * @param $template
         *            コピー対象とするテンプレートを指定する。
         */
        addRecord : function ($template) {
            $(this).find("tbody").append($template.clone());
        },
        /**
         * レコードを削除する。
         */
        removeRecord : function () {
            $(this).parent().parent().remove();
        },
        /**
         * レコードを一つ上に移動する。
         */
        dropUpRecord : function () {
            var src = $(this).parent().parent();
            var dst = src.prev();
            src.insertBefore(dst);
        },
        /**
         * レコードを一つ下に移動する。
         * 
         * @param $td
         *            操作対象とする要素を指定する。
         */
        dropDownRecord : function () {
            var src = $(this).parent().parent();
            var dst = src.next();
            src.insertAfter(dst);
        }
    };

    /**
     * fieldMaster プラグインに登録されているメソッドを実行する。
     * 
     * @param method
     *            実行するメソッドの名前を指定する。
     */
    $.fn.fieldMaster = function (method) {
        if (FieldMaster[method]) {
            return FieldMaster[method].apply(this, Array.prototype.slice.call(
                    arguments, 1));
        } else {
            $.error(method + ": 未定義のメソッドです。");
        }
    };

    var SourceDialog = {
        /**
         * 結合条件入力ダイアログの画面表示を初期化する。
         * 
         * @param definition
         *            ダイアログへ渡す初期値を設定したオブジェクトを指定する。<br>
         *            {no: no, tableId: 取得元エンティティの table_id, condition: 結合条件}
         */
        init : function (definition) {
            $(this).find("input[name='no']").val(definition.no);
            $(this).find("select[name='tableName']").val(definition.tableId);
            $(this).find("textarea[name='condition']")
                    .val(definition.condition);
        },
        /**
         * @return definition 結合条件入力ダイアログに設定されている入力値をオブジェクトに変換して返す。<br>
         *         {no: no, tableId: 取得元エンティティの table_id, tableName:
         *         取得元エンティティの名前, condition: 結合条件}
         */
        getDefinition : function () {
            var definition = {};

            definition.no = $(this).find("input[name='no']").val();
            definition.tableId = $(this).find("select[name='tableName']").val();
            definition.tableName = $(this).find("select[name='tableName']")
                    .find("option:selected").text();
            definition.condition = $(this).find("textarea[name='condition']")
                    .val();

            return definition;
        },
        /**
         * @return definition 結合条件入力ダイアログのデフォルト設定値を指定したオブジェクトを返す。<br>
         *         {no: no, tableId: 取得元エンティティの table_id, condition: 結合条件}
         */
        createDefinition : function () {
            var definition = {};

            definition.no = 0;
            definition.tableId = $(this).find(
                    "select[name='tableName'] option:first-child").val();
            definition.condition = null;

            return definition;
        }
    };

    /**
     * sourceDialog プラグインに登録されているメソッドを実行する。
     * 
     * @param method
     *            実行するメソッドの名前を指定する。
     */
    $.fn.sourceDialog = function (method) {
        if (SourceDialog[method]) {
            return SourceDialog[method].apply(this, Array.prototype.slice.call(
                    arguments, 1));
        } else {
            $.error(method + ": 未定義のメソッドです。");
        }
    };

    var SourceMaster = $.extend({}, FieldMaster);
    SourceMaster = $
            .extend(
                    SourceMaster,
                    {
                        /**
                         * No 列の自動採番を実行する。
                         */
                        resetNo : function () {
                            $(this).find("tbody th").each(
                                    function (index) {
                                        $(this).text(index + 1).append(
                                                "<input type='hidden' name='joinNo' value='"
                                                        + (index + 1) + "'/>");
                                    });
                        },
                        /**
                         * 結合条件の定義を新規登録する。
                         * 
                         * @param $template
                         *            テンプレートとして使用する要素を指定する。
                         * @param definition
                         *            入力値として使用するオブジェクトを指定する。<br>
                         *            {tableId: 取得元エンティティの table_id, tableName:
                         *            取得元エンティティの名前, condition: 結合条件}
                         */
                        addRecord : function ($template, definition) {
                            var template = $template.clone();

                            // テーブル名
                            var $tableName = template.find("td").eq(0);
                            $tableName.text(definition.tableName);

                            // テーブル ID
                            var $tableIdInput = $("<input type='hidden' name='joinTableId'>");
                            $tableIdInput.val(definition.tableId);
                            $tableName.append($tableIdInput);

                            // 結合条件
                            var $condition = template.find("td").eq(1);
                            $condition.text(definition.condition);

                            var $conditionInput = $("<input type='hidden' name='joinCondition'>");
                            $conditionInput.val(definition.condition);
                            $condition.append($conditionInput);

                            $(this).append(template);
                        },
                        /**
                         * 登録済み結合条件の定義を更新する。
                         * 
                         * @param definition
                         *            更新値として使用するオブジェクトを指定する。<br>
                         *            {no: no, tableId: 取得元エンティティの table_id,
                         *            tableName: 取得元エンティティの名前, condition: 結合条件}
                         */
                        updateRecord : function (definition) {
                            var $no = $(this).find(
                                    "input[name='joinNo'][value='"
                                            + definition.no + "']").parent();

                            // テーブル名とテーブル ID の更新
                            var $tableName = $no.next();

                            var $tableIdInput = $tableName
                                    .find("input[name='joinTableId']");
                            $tableIdInput.val(definition.tableId);

                            $tableName.text(definition.tableName);
                            $tableName.append($tableIdInput);

                            // 結合条件の更新
                            var $condition = $tableName.next();

                            var $conditionInput = $condition
                                    .find("input[name='joinCondition']");
                            $conditionInput.val(definition.condition);

                            $condition.text(definition.condition);
                            $condition.append($conditionInput);
                        },
                        /**
                         * @return definition 登録済み結合条件の定義をオブジェクトに変換して返す。<br>
                         *         {no: no, tableId: 取得元エンティティの table_id,
                         *         condition: 結合条件}
                         */
                        getDefinition : function () {
                            var record = $(this).parent().parent();
                            var definition = {};

                            definition.no = record.find("input[name='joinNo']")
                                    .val();
                            definition.tableId = record.find(
                                    "input[name='joinTableId']").val();
                            definition.condition = record.find(
                                    "input[name='joinCondition']").val();

                            return definition;
                        }
                    });

    /**
     * sourceMaster プラグインに登録されているメソッドを実行する。
     * 
     * @param method
     *            実行するメソッドの名前を指定する。
     */
    $.fn.sourceMaster = function (method) {
        if (SourceMaster[method]) {
            return SourceMaster[method].apply(this, Array.prototype.slice.call(
                    arguments, 1));
        } else {
            $.error(method + ": 未定義のメソッドです。");
        }
    };
})(jQuery);