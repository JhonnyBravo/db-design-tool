<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<input type="hidden" name="tableId" value="${tableId}" />
<input type="hidden" name="deleteFlag" value="0" />