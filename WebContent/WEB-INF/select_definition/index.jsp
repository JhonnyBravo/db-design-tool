<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ja">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
    crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="resources/css/select_definition/index.css">
<title>クエリ定義(Select 文)</title>
</head>
<body>
    <jsp:include page="../components/navbar.jsp" />
    <main role="main" class="container">
        <c:choose>
            <c:when test="${error != null}">
                <p>${error}</p>
            </c:when>
        </c:choose>
        <form method="post" action="select_definition">
            <jsp:include page="../components/table_master.jsp" />
            <jsp:include page="../components/table_source_definition.jsp" />
            <jsp:include page="../components/field_source_definition.jsp" />

            <button type="submit" class="btn btn-primary">保存</button>

            <c:choose>
                <c:when test="${tableId != null}">
                    <button type="button" class="btn btn-danger">削除</button>
                </c:when>
            </c:choose>

        </form>
    </main>
    <div class="modal" id="joinDefinitionModal" tabindex="-1" role="dialog"
        aria-labelledby="joinDefinitionModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="joinDefinitionModalLabel">取得元テーブル</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="no">
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">テーブル名</label> <select
                            class="form-control" name="tableName">
                            <option value="1">Table 1</option>
                            <option value="2">Query 1</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">結合条件</label>
                        <textarea class="form-control" rows="5" name="condition"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">閉じる</button>
                    <button type="button" class="btn btn-primary">登録</button>
                </div>
            </div>
        </div>
    </div>
    <section id="template">
        <jsp:include page="../components/table_source_definition_template.jsp" />
        <jsp:include page="../components/field_source_definition_template.jsp" />
    </section>
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
    <!-- Optional JavaScript -->
    <script type="text/javascript" src="resources/js/core/index.js"></script>
    <script type="text/javascript" src="resources/js/select_definition/index.js"></script>
</body>
</html>