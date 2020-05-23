<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<label for="tableMaster.physicalName"> <c:choose>
        <c:when test="${entityType == 1}">テーブル名</c:when>
        <c:when test="${entityType == 2}">クエリ名</c:when>
    </c:choose>
</label>
<div class="input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">物理名</span>
    </div>
    <input type="text" name="tableMaster.physicalName" class="form-control"
        value="${tableMaster.physicalName}">
</div>
<c:choose>
    <c:when test="${tableMaster.physicalNameError != null}">
        <p>
            <c:out value="${tableMaster.physicalNameError}" />
        </p>
    </c:when>
</c:choose>
<div class="input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">論理名</span>
    </div>
    <input type="text" name="tableMaster.logicalName" class="form-control"
        value="${tableMaster.logicalName}">
</div>
<c:choose>
    <c:when test="${tableMaster.logicalNameError != null}">
        <p>
            <c:out value="${tableMaster.logicalNameError}" />
        </p>
    </c:when>
</c:choose>
<input type="hidden" name="tableMaster.tableId" value="${tableId}" />
<input type="hidden" name="tableMaster.entityType" value="${entityType}" />
<input type="hidden" name="tableMaster.deleteFlag" value="0" />