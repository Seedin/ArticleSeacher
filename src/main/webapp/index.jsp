<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>文章搜索测试</title>
    <script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
</head>
<body>
    <h3>请输入文章搜索条件</h3>
    <div id="queryKey">
        <p>正文关键词：<input type="text" name="content"/></p>
        <p>标题关键词：<input type="text" name="title"/></p>
        <p>文章类型：
            <select name="articleType" >
                <option value="0">不限</option>
                <option value="1">正文页</option>
                <option value="2">游记</option>
            </select>
        </p>
        <p>文章ID：<input type="text" name="articleId"/></p>
        <p>目的地ID：<input type="text" name="regionId"/></p>
        <p>作者ID：<input type="text" name="authorId"/></p>
        <p>发布时间：
            <select name="dateLimit" >
                <option value="0">不限</option>
                <option value="1">一日内</option>
                <option value="2">一周内</option>
                <option value="3">一月内</option>
                <option value="4">一季内</option>
                <option value="5">一年内</option>
            </select>
        </p>
        <input type="hidden" value="10" name="limit"/>
        <button onclick="queryArticles()">查询</button>
    </div>
    <div>
        <table id="articles"></table>
    </div>
</body>
<script type="application/javascript">
    function queryArticles(){
        var queryKey = {};
        $("#queryKey [name]").each(function(){
            var keyName = $(this).attr('name');
            var keyValue = $(this).val();
            if (keyValue != null && keyValue != ''){
                queryKey[keyName] = keyValue;
            }
        })
        $.getJSON('<c:url value="/article/search"/>',
                queryKey,
                function(result){
                    $('#articles').html('');
                    if (result == null ||
                            result.length == 0){
                        return;
                    }
                    var articleColumns = '<tr><th>文章ID</th><th>文章类型</th><th>目的地ID</th><th>标题</th></tr>';
                    $('#articles').append(articleColumns);
                    $.each(result, function(i, info){
                        var articleInfo = '<tr><td>';
                        articleInfo += info['articleId'];
                        articleInfo += '</td><td>';
                        if (info['articleType'] == 1){
                            articleInfo += '正文页';
                        }
                        else if (info['articleType'] == 2){
                            articleInfo += '游记';
                        }
                        else{
                            articleInfo += '未知';
                        }
                        articleInfo += '</td><td>';
                        articleInfo += info['regionId'];
                        articleInfo += '</td><td>';
                        if (info['articleType'] == 1){
                            articleInfo += '<a href="http://cms.lotour.com:8343/News/NewsEditor?id=';
                            articleInfo += info['articleId'];
                            articleInfo += '" target="_blank">';
                            articleInfo += info['title'];
                            articleInfo += '</a>';
                        }
                        else if (info['articleType'] == 2){
                            articleInfo += '<a href="http://my.lotour.com/i/zuji/';
                            articleInfo += info['articleId'];
                            articleInfo += '" target="_blank">';
                            articleInfo += info['title'];
                            articleInfo += '</a>';
                        }
                        else{
                            articleInfo += info['title'];
                        }
                        articleInfo += '</td></tr>';
                        $('#articles').append(articleInfo);
                    });
                });
    };
</script>
</html>
