<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <title>黑名单用户管理</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap-theme.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/jquery.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style type="text/css">
        html,body {
            height: 100%;
        }
        .box {
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6699FF', endColorstr='#6699FF'); /*  IE */
            background-image:linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-o-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-moz-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-webkit-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-ms-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);

            margin: 0 auto;
            position: relative;
            width: 100%;
            height: 100%;
        }
        .login-box {
            width: 100%;
            max-width:500px;
            height: 400px;
            position: absolute;
            top: 50%;

            margin-top: -200px;
            /*设置负值，为要定位子盒子的一半高度*/

        }
        @media screen and (min-width:500px){
            .login-box {
                left: 50%;
                /*设置负值，为要定位子盒子的一半宽度*/
                margin-left: -250px;
            }
        }

        .form {
            width: 100%;
            max-width:500px;
            height: 275px;
            margin: 25px auto 0px auto;
            padding-top: 25px;
        }
        .login-content {
            height: 300px;
            width: 100%;
            max-width:500px;
            background-color: rgba(255, 250, 2550, .6);
            float: left;
        }


        .input-group {
            margin: 0px 0px 30px 0px !important;
        }
        .form-control,
        .input-group {
            height: 40px;
        }

        .form-group {
            margin-bottom: 0px !important;
        }
        .login-title {
            padding: 20px 10px;
            background-color: rgba(0, 0, 0, .6);
        }
        .login-title h1 {
            margin-top: 10px !important;
        }
        .login-title small {
            color: #fff;
        }

        .link p {
            line-height: 20px;
            margin-top: 30px;
        }
        .btn-sm {
            padding: 8px 24px !important;
            font-size: 16px !important;
        }
    </style>

</head>

<body>
<div class="box">
    <div class="login-box">
        <div class="login-title text-center">
            <h1><small>黑名单用户管理查询展示</small></h1>
        </div>
        <div class="login-content ">
            <div class="form">
                <form action="#" method="post">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr class="success">
                            <td align="center" style="font-weight: bolder">编号</td>
                            <td align="center" style="font-weight: bolder">姓名</td>
                            <td align="center" style="font-weight: bolder">账号</td>
                            <td align="center" style="font-weight: bolder">邮箱</td>
                            <td align="center" style="font-weight: bolder">操作</td>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach  items="${list}"  var="user"   varStatus="i">
                            <tr class="warning">
                                <td align="center">${i.count}</td>
                                <td align="center">${user.uname}</td>
                                <td align="center">${user.uaccount}</td>
                                <td align="center">${user.uemail}</td>

                                <td align="center">
                                    <a href="<%=request.getContextPath()%>/manager/agree.action?id=${user.uid}">同意</a>
                                    <a href="<%=request.getContextPath()%>/manager/reject.action?id=${user.uid}">驳回</a>
                                </td>

                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

                    <div class="form-group">
                        <%--<div class="col-xs-6 link">--%>
                            <%--<p class="text-center remove-margin"><small>黑名单管理?</small> <a href="<%=request.getContextPath()%>/manager/blacklist.action" ><small>管理</small></a>--%>
                            <%--</p>--%>
                        <%--</div>--%>

                        <div class="col-xs-6 link">
                            <p class="text-center remove-margin"><small>导出Excel?</small> <a href="<%=request.getContextPath()%>/manager/exportBlackListToExcel.action" ><small>导出</small></a>
                            </p>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

</body>

</html>