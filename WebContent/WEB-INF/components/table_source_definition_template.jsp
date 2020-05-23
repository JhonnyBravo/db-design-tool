<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table id="tableSourceDefinitionTemplate" class="table">
    <tbody>
        <tr>
            <th scope="row">1<input type="hidden" name="tableSourceDefinition.no"></th>
            <td>Table1 <input type="hidden" name="tableSourceDefinition.sourceId"></td>
            <td>Table1.field1 = Table2.field1 <input type="hidden"
                name="tableDefinition.joinCondition"></td>
            <td><a href="#" class="updateRecord"><i class="material-icons text-info">
                        tune </i></a> <input type="hidden" name="tableSourceDefinition.definitionId"
                value="0"></td>
            <td><a href="#" class="removeRecord"><i class="material-icons text-danger">
                        remove </i></a></td>
            <td><a href="#" class="dropUpRecord"><i class="material-icons text-info">
                        arrow_drop_up </i></a> <a href="#" class="dropDownRecord"><i
                    class="material-icons text-info"> arrow_drop_down </i></a></td>
        </tr>
    </tbody>
</table>