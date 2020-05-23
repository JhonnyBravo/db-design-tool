<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table id="fieldMaster" class="table">
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
                <th scope="row"><c:out value="${fieldMaster.no}" /><input type="hidden"
                    name="fieldMaster.no" value="${fn:escapeXml(fieldMaster.no)}" /></th>
                <td>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">物理名</span>
                        </div>
                        <input type="text" name="fieldMaster.physicalName" class="form-control"
                            value="${fn:escapeXml(fieldMaster.physicalName)}">
                    </div> <c:choose>
                        <c:when test="${fieldMaster.physicalNameError != null}">
                            <p>
                                <c:out value="${fieldMaster.physicalNameError}" />
                            </p>
                        </c:when>
                    </c:choose>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">論理名</span>
                        </div>
                        <input type="text" name="fieldMaster.logicalName" class="form-control"
                            value="${fn:escapeXml(fieldMaster.logicalName)}">
                    </div> <input type="hidden" name="fieldMaster.fieldId"
                    value="${fn:escapeXml(fieldMaster.fieldId)}" /> <c:choose>
                        <c:when test="${fieldMaster.logicalNameError != null}">
                            <p>
                                <c:out value="${fieldMaster.logicalNameError}" />
                            </p>
                        </c:when>
                    </c:choose>
                </td>
                <td>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">型</span>
                        </div>
                        <select class="custom-select" name="fieldMaster.dataType">
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
                        <input type="text" name="fieldMaster.dataSize" class="form-control"
                            value="${fn:escapeXml(fieldMaster.dataSize)}">
                    </div> <c:choose>
                        <c:when test="${fieldMaster.dataSizeError != null}">
                            <p>
                                <c:out value="${fieldMaster.dataSizeError}" />
                            </p>
                        </c:when>
                    </c:choose>
                </td>
                <td><textarea name="fieldMaster.description" class="form-control" rows="3"><c:out
                            value="${fieldMaster.description}" /></textarea> <c:choose>
                        <c:when test="${fieldMaster.descriptionError != null}">
                            <p>
                                <c:out value="${fieldMaster.descriptionError}" />
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
    <c:when test="${fieldMasterError != null}">
        <p>
            <c:out value="${fieldMasterError}" />
        </p>
    </c:when>
</c:choose>