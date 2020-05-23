<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                        <c:forEach var="tableMaster" items="${tableMasterList}">
                            <option value="${tableMaster.tableId}">${tableMaster.physicalName}</option>
                        </c:forEach>
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