<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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