<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会员登录-小众点评网</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="./resources/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="./resources/raty/lib/jquery.raty.css">

    <script src="./resources/jquery.3.3.1.min.js"></script>
    <script src="./resources/bootstrap/bootstrap.min.js"></script>
    <style>
        .container {
            padding: 0px;
            margin: 0px;
        }

        .row {
            padding: 0px;
            margin: 0px;
        }

        .col- * {
            padding: 0px;
        }

        .description p {
            text-indent: 2em;
        }

        .description img {
            width: 100%;
        }

    </style>

</head>
<body>
<!--<div style="width: 375px;margin-left: auto;margin-right: auto;">-->
<div class="container ">
    <nav class="navbar navbar-light bg-white shadow">
        <ul class="nav">
            <li class="nav-item">
                <a href="index.html">
                    <img src="./resources/images/logo2.png" class="mt-1"
                         style="width: 100px">
                </a>
            </li>
        </ul>
    </nav>


    <div class="container mt-2 p-2 m-0">
        <form id="frmLogin">
            <div class="passport bg-white">
                <h4 class="float-left">会员登录</h4>
                <h6 class="float-right pt-2"><a href="reg.html">会员注册</a></h6>
                <div class="clearfix"></div>
                <div class="alert d-none mt-2" id="tips" role="alert">

                </div>

                <div class="input-group  mt-2 ">
                    <input type="text" id="username" name="username" class="form-control p-4" placeholder="请输入用户名"
                           aria-label="Username" aria-describedby="basic-addon1">
                </div>

                <div class="input-group  mt-4 ">
                    <input id="password" name="password" class="form-control p-4" placeholder="请输入密码" type="password"
                           aria-describedby="basic-addon1">
                </div>

                <div class="input-group mt-4 ">
                    <div class="col-5 p-0">
                        <input type="text" id="verifyCode" name="vc" class="form-control p-4" placeholder="验证码">
                    </div>
                    <div class="col-4 p-0 pl-2 pt-0">
                        <!-- src为 验证码的接口服务-->
                        <img id="imgVerifyCode" src="verifyCode"
                             style="width: 120px;height:50px;cursor: pointer">
                    </div>

                </div>

                <a id="btnSubmit" class="btn btn-success  btn-block mt-4 text-white pt-3 pb-3">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>
            </div>
        </form>

    </div>
</div>

<script>
    //点击验证码图片刷新验证码
    $("#imgVerifyCode").click(function () {
        reloadVerifyCode();
    });
    //重新发送请求,刷新验证码
    function reloadVerifyCode(){
        //请在这里实现刷新验证码,请求中设置时间错。
        $('#imgVerifyCode').attr("src","verifyCode?tp="+new Date().getTime());
    }
    //提交表单的操作
    $(function () {
        $('#btnSubmit').click(function () {
            $.ajax({
                url:'/xzw/checkLogin',
                type:'post',
                dataType:'json',
                data:$('#frmLogin').serialize(),
                success:function (data) {
                    if(data.code=="ok_vc"){
                        alert(data.msg);
                        //去首页
                        window.location="/xzw/index.html?tp="+new Date().getTime();
                    }else {
                        //刷新页面，重新登录
                        alert(data.msg);
                        $('#password').val("")
                        $('#nickname').val("")
                        $('#verifyCode').val("")
                        reloadVerifyCode();
                    }
                }
            });

        });
    });
</script>
</body>
</html>