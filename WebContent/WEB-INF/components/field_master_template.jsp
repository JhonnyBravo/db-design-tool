<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table id="fieldMasterTemplate" class="table">
    <tbody>
        <tr>
            <th scope="row">1<input type="hidden" name="fieldMaster.no" value="1" /></th>
            <td>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">物理名</span>
                    </div>
                    <input type="text" name="fieldMaster.physicalName" class="form-control">
                </div>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">論理名</span>
                    </div>
                    <input type="text" name="fieldMaster.logicalName" class="form-control">
                </div> <input type="hidden" name="fieldMaster.fieldId" value="0" />
            </td>
            <td>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">型</span>
                    </div>
                    <select class="custom-select" name="fieldMaster.dataType">
                        <c:forEach var="dataType" items="${dataTypeList}">
                            <option value="${fn:escapeXml(dataType.id)}">
                                <c:out value="${dataType.typeName}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">桁数</span>
                    </div>
                    <input type="text" name="fieldMaster.dataSize" class="form-control">
                </div>
            </td>
            <td><textarea name="fieldMaster.description" class="form-control" rows="3"></textarea></td>
            <td><a href="#" class="removeRecord"><i class="material-icons text-danger">
                        remove </i></a></td>
            <td><a href="#" class="dropUpRecord"><i class="material-icons text-info">
                        arrow_drop_up </i></a> <a href="#" class="dropDownRecord"><i
                    class="material-icons text-info"> arrow_drop_down </i></a></td>
        </tr>
    </tbody>
</table>