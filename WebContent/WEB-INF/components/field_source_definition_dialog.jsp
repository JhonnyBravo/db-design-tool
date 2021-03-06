<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="modal" id="fieldDefinitionModal" tabindex="-1" role="dialog"
    aria-labelledby="fieldDefinitionModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="fieldDefinitionModalLabel">取得元フィールド</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="no">
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">テーブル名</label> <select
                        class="form-control" name="tableMaster.physicalName">
                        <option></option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">フィールド名</label> <select
                        class="form-control" name="fieldMaster.physicalName">
                        <option></option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">閉じる</button>
                <button type="button" class="btn btn-primary">登録</button>
            </div>
        </div>
    </div>
</div>
