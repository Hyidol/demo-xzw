<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>${book.bookName}</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="/xzw/resources/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="/xzw/resources/raty/lib/jquery.raty.css">
    <script src="/xzw/resources/jquery.3.3.1.min.js"></script>
    <script src="/xzw/resources/bootstrap/bootstrap.min.js"></script>
    <script src="/xzw/resources/art-template.js"></script>
    <script src="/xzw/resources/raty/lib/jquery.raty.js"></script>
    <style>
        .container {
            padding: 0px;
            margin: 0px;
        }

        .row {
            padding: 0px;
            margin: 0px;
        }

        .alert {
            padding-left: 0.5rem;
            padding-right: 0.5rem;
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

        .highlight {
            background-color: lightskyblue !important;
        }

    </style>
    <script>
        $.fn.raty.defaults.path = '/xzw/resources/raty/lib/images';
        $(function () {
            $(".stars").raty({readOnly: true});
        })

        $(function () {
            //注意，注释
            <!-- 状态不为空，则设置高亮，将查出的阅读状态对应的元素，加为高亮 ？？这俩问号表示不为null-->
            <#if memberReadState ??>/*如果状态存在*/
                $('[data-read-state="${memberReadState.readState}"]').addClass('highlight');
            </#if>

            //如果没有登录，则阅读状态，写评论，点赞，都不能操作
            <#if !loginMember ??>
                $('[data-read-state],#btnEvaluation,[data-evaluation-id]').click(function () {
                    alert("请先登录，才能操作！")
                })
            </#if>

            <#if loginMember ??>
            $('[data-read-state]').click(function () {
                var readState = $(this).data('read-state');
                //post是4个参数
                $.post('/xzw/updateReadState',{
                    "memberId":${loginMember.memberId},
                    "bookId":${book.bookId},
                    "readState":readState
                },function () {
                    if(data.code=="ok_mrs"){
                        $('[data-read-state]').removeClass('highlight');//清除高亮
                        $('[data-read-state="'+readState+'"]').addClass('highlight');
                    }
                },'json');
                window.location.reload(true);
            });

            //点击短评按钮
            $("#btnEvaluation").click(function () {
                $('#score').raty({});//将span转为星形组件
                //显示写短评div
                $('#dlgEvaluation').modal('show');
            })

            //提交点评
            $("#btnSubmit").click(function () {
                var score = $('#score').raty("score");
                var content = $('#content').val();
                //没有内容，不提交
                if(score==0||$.trim(content)==""){
                    return;
                }
                $.post("/xzw/evaluate",{
                    "score":score,
                    "bookId": ${book.bookId},
                    "memberId":${loginMember.memberId},
                    "content":content
                },function (data) {
                    if(data.code=="ok_ev"){
                        //重新加载当前页，得到最新的评论
                        window.location.reload();
                    }
                },'json')
            });
            $('[data-evaluation-id]').click(function(){
                var evaluationId = $(this).data("evaluation-id");
                $.post('/xzw/enjoy',{"evaluationId":evaluationId},function (data) {
                    if(data.code=="ok_en"){
                        window.location.reload();//这句话好像没啥用？！！
                        $('[data-evaluation-id="'+evaluationId+'"] span').text(data.evaluation.enjoy)
                    }
                },'json');
            });
            </#if>
        });

    </script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-white shadow mr-auto">
        <ul class="nav">
            <li class="nav-item">
                <a href="/xzw/index.html">
                    <img src="/xzw/resources/images/logo2.png"  class="mt-1"
                         style="width: 100px">
                </a>
            </li>

        </ul>
    </nav>
    <div class="container mt-2 p-2 m-0" style="background-color:rgb(127, 125, 121)">
        <div class="row">
            <div class="col-4 mb-2 pl-0 pr-0">
                <img style="width: 110px;height: 160px"
                     src="/xzw/resources/images/${book.cover}"><!-- 图书封面 -->
            </div>
            <div class="col-8 pt-2 mb-2 pl-0">
                <h6 class="text-white">${book.bookName}</h6><!-- 图书名 -->
                <div class="p-1 alert alert-warning small" role="alert">
                    ${book.subTitle}<!-- 图书子标题 -->
                </div>
                <p class="mb-1">
                    <span class="text-white-50 small">${book.author}</span><!-- 作者 -->
                </p>
                <div class="row pl-1 pr-2">
                    <div class="col-6 p-1">
                        <button type="button" data-read-state="1" class="btn btn-light btn-sm w-100">
                            <img style="width: 1rem;" class="mr-1"
                                 src="https://img3.doubanio.com/f/talion/cf2ab22e9cbc28a2c43de53e39fce7fbc93131d1/pics/card/ic_mark_todo_s.png"/>想看
                        </button>
                    </div>
                    <div class="col-6 p-1">
                        <button type="button" data-read-state="2" class="btn btn-light btn-sm  w-100">
                            <img style="width: 1rem;" class="mr-1"
                                 src="https://img3.doubanio.com/f/talion/78fc5f5f93ba22451fd7ab36836006cb9cc476ea/pics/card/ic_mark_done_s.png"/>看过
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="background-color: rgba(0,0,0,0.1);">
            <div class="col-2"><h2 class="text-white">${book.evaluationScore}</h2></div><!-- 图书评分 -->
            <div class="col-5 pt-2">
                <span class="stars" data-score="${book.evaluationScore}"></span><!-- 图书评分 -->
            </div>
            <div class="col-5  pt-2"><h5 class="text-white">${book.evaluationQuantity}人已评</h5></div><!-- 评论人数量 -->
        </div>
    </div>

    <div class="row p-2 description"><!-- 图书描述 -->
        ${book.description}
    </div>
    <div class="alert alert-primary w-100 mt-2" role="alert">短评
        <button type="button" id="btnEvaluation" class="btn btn-success btn-sm text-white float-right"
                style="margin-top: -3px;">
            写短评
        </button>
    </div>
    <!-- 短评部分： -->
    <#if evaluationList??>
    <div class="reply pl-2 pr-2">
        <#list evaluationList as evaluation>
            <div>
                <div>
                    <span class="pt-1 small text-black-50 mr-2">${(evaluation.createTime)?string('yyyy.MM.dd HH:mm:ss')}</span><!-- 日期 -->
                    <span class="mr-2 small pt-1">${evaluation.member.nickName}</span><!-- 会员名-->
                    <#--<span class="mr-2 small pt-1">
                        <#list memberList as member>
                            <#if member.memberId==evaluation.memberId>
                                <span class="mr-2 small pt-1">${member.username}</span><!-- 会员名&ndash;&gt;
                            </#if>
                        </#list>
                    </span>-->
                    <span class="stars mr-2" data-score="${evaluation.score}"></span><!-- 评分 -->
                    <!-- 评论编号 -->
                    <button type="button" data-evaluation-id="${evaluation.evaluationId}"
                            class="btn btn-success btn-sm text-white float-right" style="margin-top: -3px;">
                        <img style="width: 24px;margin-top: -5px;" class="mr-1"
                             src="https://img3.doubanio.com/f/talion/7a0756b3b6e67b59ea88653bc0cfa14f61ff219d/pics/card/ic_like_gray.svg"/>
                        <span>${evaluation.enjoy}</span><!-- 点赞数量 -->
                    </button>
                </div><!-- 短评内容-->
                <div class="row mt-2 small mb-3">
                    ${evaluation.content}
                </div>
                <hr/>
            </div>
            <!-- 所有评论。循环遍历--->
        </#list>
    </div>
    </#if>
    <!-- Modal -->
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    您需要登录才可以操作哦~
                </div>
                <div class="modal-footer">
                    <a href="/xzw/login.html" type="button" class="btn btn-primary">去登录</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="dlgEvaluation" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <h6>为《${book.bookName}》写短评</h6>
                    <form id="frmEvaluation">
                        <div class="input-group  mt-2 ">
                            <span id="score"></span>
                        </div>
                        <div class="input-group  mt-2 ">
                            <input type="text" id="content" name="content" class="form-control p-4" placeholder="这里输入短评">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="btnSubmit" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>