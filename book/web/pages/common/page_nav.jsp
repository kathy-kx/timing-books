<%--
   分页条
  Created by IntelliJ IDEA.
  User: kxzhu
  Date: 2022/10/12
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--首页和上一页：大于首页，才显示--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--	页码输出的开始--%>
    <c:choose>
        <%-- 情况1: 总页码<=5，页码范围是:1~总页码 --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var ="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%-- 情况2: 总页码大于5 --%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%-- 小情况1: 当前页码<=3，页码范围是:1~5 --%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%-- 小情况2: 当前页码为最后3个, pageNo>=pageTotal-2, 页码范围是:(pageTotal-4)~pageTotal --%>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal-2}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%-- 小情况3: 当前页面为其他，3<pageNo<pageTotal-2, 页码范围是:(pageNo-2)~(pageNo+2)--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
<%--        当前页，显示【】，不是链接--%>
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
<%--        非当前页，点链接可以去--%>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--	页码输出的结束--%>


    <%--下一页和末页：如果已经是最后一页，则不显示;不是最后一页，才显示--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页, ${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
<%--        param是从客户端获取请求的参数--%>
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function (){
            //跳到指定页码
            $("#searchPageBtn").click(function (){
                var pageNo = $("#pn_input").val(); //获得ID=pn_input输入框输入的内容

                //总页码的获取：
                <%--var pageTotal = ${requestScope.page.pageTotal};--%>

                // javaScript语言中提供了一个location地址栏对象
                // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                // href属性可读可写，所以可以赋值：
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
<%--分页条的结束--%>


