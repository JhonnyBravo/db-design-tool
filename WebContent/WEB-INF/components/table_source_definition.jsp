<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table id="tableSourceDefinition" class="table">
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
        <c:forEach var="tableSourceDefinition" items="${tableSourceDefinitionArray}">
            <tr>
                <th scope="row"><c:out value="${tableSourceDefinition.no}" /> <input
                    type="hidden" name="tableSourceDefinition.no"
                    value="${fn:escapeXml(tableSourceDefinition.no)}"></th>
                <td><c:out value="${tableSourceDefinition.sourceName}" /><input type="hidden"
                    name="tableSourceDefinition.sourceId"
                    value="${fn:escapeXml(tableSourceDefinition.sourceId)}"> <input
                    type="hidden" name="tableSourceDefinition.sourceName"
                    value="${fn:escapeXml(tableSourceDefinition.sourceName)}"></td>
                <td><c:out value="${tableSourceDefinition.joinCondition}" /><input
                    type="hidden" name="tableSourceDefinition.joinCondition"
                    value="${fn:escapeXml(tableSourceDefinition.joinCondition)}"> <c:choose>
                        <c:when test="${tableSourceDefinition.joinConditionError != null}">
                            <p>
                                <c:out value="${tableSourceDefinition.joinConditionError}" />
                            </p>
                        </c:when>
                    </c:choose></td>
                <td><a href="#" class="updateRecord"><i class="material-icons text-info">
                            tune </i></a> <input type="hidden" name="tableSourceDefinition.definitionId"
                    value="${fn:escapeXml(tableSourceDefinition.definitionId)}"></td>
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
    <c:when test="${tableSourceDefinitionError != null}">
        <p>
            <c:out value="${tableSourceDefinitionError}" />
        </p>
    </c:when>
</c:choose>