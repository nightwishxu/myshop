<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/static/admin/jsp/include.jsp"%>
    <style>

    </style>
    <script type="text/javascript">
        var id = "${id}";
     v
        $(function() {
            $("#id").val(id);
            console.debug('id='+id+'  v='+v)
        });
        var submitForm = function($dialog, $grid, $pjq) {
            if ($('form').form('validate')) {
                var obj=sy.serializeObject($('form'));
                var url=sy.contextPath + '/userGoods/settle';
                $.post(url, obj, function(result) {
                    if (result.code == 0) {
                        $grid.datagrid('reload');
                        $dialog.dialog('destroy');
                    } else {
                        $pjq.messager.e('结算失败,'+result.msg);
                    }
                }, 'json');
            }
        };
        $(function() {


        });
    </script>
</head>
<body>
<form id="form" method="post">
    <input name="id" id="id" type="hidden" />
    <div style="padding:15px;font-size: 12px">
        <table style="table-layout:fixed;" border="0" cellspacing="0" class="formtable">
            <tr>
                <th >结算金额：</th>
                <td>
                    <input class="easyui-numberbox" id = "sellMoney" name="sellMoney"  data-options="required:true,precision:2,min:0,max:9999999999" style="width:100%" missingMessage="请输入结算金额"/>
                </td>
                <th >备注：</th>
                <td>
                    <input class="easyui-textbox" id = "sellRemark" name = "sellRemark"   style="width:100%" missingMessage="备注"/>
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>