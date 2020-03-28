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
            <label for="physicalTableName">クエリ名</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">物理名</span>
                </div>
                <input type="text" name="physicalTableName" class="form-control"
                    value="${tableMaster.physicalName}">
            </div>
            <c:choose>
                <c:when test="${tableMaster.physicalNameError != null}">
                    <p>${tableMaster.physicalNameError}</p>
                </c:when>
            </c:choose>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">論理名</span>
                </div>
                <input type="text" name="logicalTableName" class="form-control"
                    value="${tableMaster.logicalName}">
            </div>
            <c:choose>
                <c:when test="${tableMaster.logicalNameError != null}">
                    <p>${tableMaster.logicalNameError}</p>
                </c:when>
            </c:choose>
            <input type="hidden" name="tableId" value="${tableId}" /> <input type="hidden"
                name="deleteFlag" value="0" />
            <table id="joinDefinition" class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">テーブル名</th>
                        <th scope="col">結合条件</th>
                        <th scope="col"></th>
                        <th scope="col"><a href="#" class="addRecord"><i
                                class="material-icons md-green"> add </i></a></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <table id="tableDefinition" class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">フィールド名</th>
                        <th scope="col">データ型</th>
                        <th scope="col">取得元</th>
                        <th scope="col">説明</th>
                        <th scope="col"><a href="#" class="addRecord"><i
                                class="material-icons md-green"> add </i></a></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="fieldMaster" items="${fieldMasterArray}" varStatus="status">
                        <tr>
                            <th scope="row">${fieldMaster.no}<input type="hidden" name="no"
                                value="${fieldMaster.no}" /></th>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">物理名</span>
                                    </div>
                                    <input type="text" name="physicalFieldName" class="form-control"
                                        value="${fieldMaster.physicalName}">
                                </div> <c:choose>
                                    <c:when test="${fieldMaster.physicalNameError != null}">
                                        <p>${fieldMaster.physicalNameError}</p>
                                    </c:when>
                                </c:choose>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">論理名</span>
                                    </div>
                                    <input type="text" name="logicalFieldName" class="form-control"
                                        value="${fieldMaster.logicalName}">
                                </div> <input type="hidden" name="fieldId" value="${fieldMaster.fieldId}" />
                                <c:choose>
                                    <c:when test="${fieldMaster.logicalNameError != null}">
                                        <p>${fieldMaster.logicalNameError}</p>
                                    </c:when>
                                </c:choose>

                            </td>
                            <td>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">型</span>
                                    </div>
                                    <select class="custom-select" name="dataType">
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 1}">
                                                <option value="1" selected>String</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="1">String</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 2}">
                                                <option value="2" selected>Integer</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="2">Integer</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 3}">
                                                <option value="3" selected>Long</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="3">Long</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 4}">
                                                <option value="4" selected>Single</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="4">Single</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 5}">
                                                <option value="5" selected>Double</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="5">Double</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 6}">
                                                <option value="6" selected>Date</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="6">Date</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == '7'}">
                                                <option value="7" selected>Boolean</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="7">Boolean</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">桁数</span>
                                    </div>
                                    <input type="text" name="dataSize" class="form-control"
                                        value="${fieldMaster.dataSize}">
                                </div> <c:choose>
                                    <c:when test="${fieldMaster.dataSizeError != null}">
                                        <p>${fieldMaster.dataSizeError}</p>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td><textarea name="recordSource" class="form-control" rows="3"></textarea>
                                <!--  
                                <c:choose>
                                    <c:when test="${fieldMaster.descriptionError != null}">
                                        <p>${fieldMaster.descriptionError}</p>
                                    </c:when>
                                </c:choose>
                                --></td>
                            <td><textarea name="description" class="form-control" rows="3">${fieldMaster.description}</textarea>
                                <c:choose>
                                    <c:when test="${fieldMaster.descriptionError != null}">
                                        <p>${fieldMaster.descriptionError}</p>
                                    </c:when>
                                </c:choose></td>
                            <td><a href="#" class="removeRecord"><i
                                    class="material-icons text-danger"> remove </i></a></td>
                            <td><a href="#" class="dropUpRecord"><i
                                    class="material-icons text-info"> arrow_drop_up </i></a> <a href="#"
                                class="dropDownRecord"><i class="material-icons text-info">
                                        arrow_drop_down </i></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:choose>
                <c:when test="${fieldMasterError != null}">
                    <p>${fieldMasterError}</p>
                </c:when>
            </c:choose>
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
                    <form>
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
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">閉じる</button>
                    <button type="button" class="btn btn-primary">登録</button>
                </div>
            </div>
        </div>
    </div>
    <section id="template">
        <table id="joinDefinitionTemplate" class="table">
            <tbody>
                <tr>
                    <th scope="row">1<input type="hidden" name="no" value="1"></th>
                    <td></td>
                    <td></td>
                    <td><a href="#" class="updateRecord"><i
                            class="material-icons text-info"> tune </i></a></td>
                    <td><a href="#" class="removeRecord"><i
                            class="material-icons text-danger"> remove </i></a></td>
                    <td><a href="#" class="dropUpRecord"><i
                            class="material-icons text-info"> arrow_drop_up </i></a> <a href="#"
                        class="dropDownRecord"><i class="material-icons text-info">
                                arrow_drop_down </i></a></td>
                </tr>
            </tbody>
        </table>
        <table id="definitionTemplate" class="table">
            <tbody>
                <tr>
                    <th scope="row">1<input type="hidden" name="no" value="1" /></th>
                    <td>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">物理名</span>
                            </div>
                            <input type="text" name="physicalFieldName" class="form-control">
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">論理名</span>
                            </div>
                            <input type="text" name="logicalFieldName" class="form-control">
                        </div> <input type="hidden" name="fieldId" value="0" />
                    </td>
                    <td>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">型</span>
                            </div>
                            <select class="custom-select" name="dataType">
                                <option value="1">String</option>
                                <option value="2">Integer</option>
                                <option value="3">Long</option>
                                <option value="4">Single</option>
                                <option value="5">Double</option>
                                <option value="6">Date</option>
                                <option value="7">Boolean</option>
                            </select>
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">桁数</span>
                            </div>
                            <input type="text" name="dataSize" class="form-control">
                        </div>
                    </td>
                    <td><textarea name="recordSource" class="form-control" rows="3"></textarea></td>
                    <td><textarea name="description" class="form-control" rows="3"></textarea></td>
                    <td><a href="#" class="removeRecord"><i
                            class="material-icons text-danger"> remove </i></a></td>
                    <td><a href="#" class="dropUpRecord"><i
                            class="material-icons text-info"> arrow_drop_up </i></a> <a href="#"
                        class="dropDownRecord"><i class="material-icons text-info">
                                arrow_drop_down </i></a></td>
                </tr>
            </tbody>
        </table>
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
    <script type="text/javascript" src="resources/js/select_definition/index.js"></script>
</body>
</html>