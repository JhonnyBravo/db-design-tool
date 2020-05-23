<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table id="fieldSourceDefinition" class="table">
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
        <c:forEach var="fieldSourceDefinition" items="${fieldSourceDefinitionArray}">
            <tr>
                <th scope="row"><c:out value="${fieldSourceDefinition.no}" /><input
                    type="hidden" name="fieldSourceDefinition.no"
                    value="${fn:escapeXml(fieldSourceDefinition.no)}"></th>
                <td>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">物理名</span>
                        </div>
                        <input type="text" name="fieldSourceDefinition.physicalName"
                            class="form-control"
                            value="${fn:escapeXml(fieldSourceDefinition.physicalName)}">
                    </div> <c:choose>
                        <c:when test="${fieldSourceDefinition.physicalNameError != null}">
                            <p>
                                <c:out value="${fieldSourceDefinition.physicalNameError}" />
                            </p>
                        </c:when>
                    </c:choose>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">論理名</span>
                        </div>
                        <input type="text" name="fieldSourceDefinition.logicalName"
                            class="form-control"
                            value="${fn:escapeXml(fieldSourceDefinition.logicalName)}">
                    </div> <input type="hidden" name="fieldSourceDefinition.fieldId"
                    value="${fn:escapeXml(fieldSourceDefinition.fieldId)}"> <c:choose>
                        <c:when test="${fieldSourceDefinition.logicalNameError != null}">
                            <p>
                                <c:out value="${fieldSourceDefinition.logicalNameError}" />
                            </p>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">型</span>
                        </div>
                        <select class="custom-select" name="fieldSourceDefinition.dataType">
                            <c:choose>
                                <c:when test="${fieldSourceDefinition.dataType == 1}">
                                    <option value="1" selected>String</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="1">String</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${fieldSourceDefinition.dataType == 2}">
                                    <option value="2" selected>Integer</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="2">Integer</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${fieldSourceDefinition.dataType == 3}">
                                    <option value="3" selected>Long</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="3">Long</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${fieldSourceDefinition.dataType == 4}">
                                    <option value="4" selected>Single</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="4">Single</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${fieldSourceDefinition.dataType == 5}">
                                    <option value="5" selected>Double</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="5">Double</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${fieldSourceDefinition.dataType == 6}">
                                    <option value="6" selected>Date</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="6">Date</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${fieldSourceDefinition.dataType == '7'}">
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
                        <input type="text" name="fieldSourceDefinition.dataSize"
                            class="form-control"
                            value="${fn:escapeXml(fieldSourceDefinition.dataSize)}">
                    </div> <c:choose>
                        <c:when test="${fieldSourceDefinition.dataSizeError != null}">
                            <p>
                                <c:out value="${fieldSourceDefinition.dataSizeError}" />
                            </p>
                        </c:when>
                    </c:choose>
                </td>
                <td><textarea name="fieldSourceDefinition.sourceDefinition"
                        class="form-control" rows="3"><c:out
                            value="${fieldSourceDefinition.sourceDefinition}" /></textarea> <input
                    type="hidden" name="fieldSourceDefinition.definitionId"
                    value="${fn:escapeXml(fieldSourceDefinition.definitionId)}"> <c:choose>
                        <c:when test="${fieldSourceDefinition.sourceDefinitionError != null}">
                            <p>
                                <c:out value="${fieldSourceDefinition.sourceDefinitionError}" />
                            </p>
                        </c:when>
                    </c:choose></td>
                <td><textarea name="fieldSourceDefinition.description" class="form-control"
                        rows="3"><c:out value="${fieldSourceDefinition.description}" /></textarea>
                    <c:choose>
                        <c:when test="${fieldSourceDefinition.descriptionError != null}">
                            <p>
                                <c:out value="${fieldSourceDefinition.descriptionError}" />
                            </p>
                        </c:when>
                    </c:choose></td>
                <td><a href="#" class="removeRecord"><i class="material-icons text-danger">
                            remove </i></a></td>
                <td><a href="#" class="dropUpRecord"><i class="material-icons text-info">
                            arrow_drop_up </i></a> <a href="#" class="dropDownRecord"><i
                        class="material-icons text-info"> arrow_drop_down </i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<c:choose>
    <c:when test="${fieldSourceDefinitionError != null}">
        <p>
            <c:out value="${fieldSourceDefinitionError}" />
        </p>
    </c:when>
</c:choose>