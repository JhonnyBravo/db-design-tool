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
<link rel="stylesheet" href="resources/css/table_definition/index.css">
<title>テーブル定義</title>
</head>
<body>
    <jsp:include page="../components/navbar.jsp" />
    <main role="main" class="container">
        <form method="post" action="table_definition">
            <label for="physicalTableName">テーブル名</label>
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
            <table id="tableDefinition" class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">フィールド名</th>
                        <th scope="col">データ型</th>
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
                                </div> <c:choose>
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
                                            <c:when test="${fieldMaster.dataType == 'string'}">
                                                <option value="string" selected>String</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="string">String</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 'integer'}">
                                                <option value="integer" selected>Integer</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="integer">Integer</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 'long'}">
                                                <option value="long" selected>Long</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="long">Long</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 'single'}">
                                                <option value="single" selected>Single</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="single">Single</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 'double'}">
                                                <option value="double" selected>Double</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="double">Double</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 'date'}">
                                                <option value="date" selected>Date</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="date">Date</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${fieldMaster.dataType == 'boolean'}">
                                                <option value="boolean" selected>Boolean</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="boolean">Boolean</option>
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
        </form>
    </main>
    <section id="template">
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
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">型</span>
                            </div>
                            <select class="custom-select" name="dataType">
                                <option value="string">String</option>
                                <option value="integer">Integer</option>
                                <option value="long">Long</option>
                                <option value="single">Single</option>
                                <option value="double">Double</option>
                                <option value="date">Date</option>
                                <option value="boolean">Boolean</option>
                            </select>
                        </div>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">桁数</span>
                            </div>
                            <input type="text" name="dataSize" class="form-control">
                        </div>
                    </td>
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
    <script type="text/javascript" src="resources/js/table_definition/index.js"></script>
</body>
</html>