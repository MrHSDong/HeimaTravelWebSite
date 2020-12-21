//此函数用post分页请求route/queryByPage数据，四个参数分别是cid，页码，条例数以及搜索关键字
function listByPage(cid, pageIndex, itemCount,searchName){
    //因为这是异步请求，所以先判断当前页书否是route_list.html,
    // 如果不是则先跳转到route_list页面,并传入点击的category的cid
    if(!/route_list.html/.test(location.href)){
        location.href = "route_list.html?cid="+cid;
    }

    //改变地址栏显示的cid字段和searchName字段，如果searchName字段没有传入数据，则searchName实际传入的是'null’；
    if(searchName==null || searchName.length===0 || searchName==='null'){
        history.replaceState(null, "","?cid="+cid);
    }else{
        history.replaceState(null, "","?cid="+cid+"&search="+searchName);
    }
    //清除其他li的nav-class属性，将当前li设置为nav-active
    $("ul[class='nav'] li").attr("class","");
    $("ul[class='nav'] li:eq("+cid+")").attr("class","nav-active");
    //根据参数向servlet发送异步请求
    $.post("route/queryByPage",{"cid": cid,"pageIndex": pageIndex, "itemCount": itemCount,"search":searchName},function (data) {
        //将返回的pageBean字符串数据转为json数据
        var json = JSON.parse(data);
        //获得总页数
        var totalPage = json["totalPage"];
        //获得总条目数
        var totalCount = json["totalCount"];
        //设置总页数和总条数
        $("#totalPage").html(totalPage);
        $("#totalCount").html(totalCount);
        var content = "";
        for(var i of json["routeList"]){

            //将每个Route字符串解析成json对象
            var item = JSON.parse(i);
            console.log(item["rid"]);
            //根据Route向li中填入数据
            var li = '<li><div class="img"><img src="imageUrl" style="width:300px;"></div><div class="text1"><p>'+item["rname"]+'</p><br/>'+
                '<p style="margin-top:-20px;">'+item["routeIntroduce"]+'</p></div><div class="price"><p class="price_num"><span>&yen;</span> <span>'+item["price"]+
                '</span><span>起</span></p><p><a href="route_detail.html?rid='+item["rid"]+'">查看详情</a></p></div></li>';
            li = li.replace("imageUrl",item["rimage"]);
            content+=li;
        }
        //向ul中填入商品信息
        $("#ul").html(content);
        //设置页面底部的页码
        var fcontent;
        //设置上一页按钮的onclick时间，如果当前页码小于等于1，则onclick仍然查询第一页的数据
        if(pageIndex<=1){
             fcontent = '<li onclick="listByPage('+cid+','+1+','+itemCount+','+'\''+searchName+'\''+')"><a href="javascript:void(0)">首页</a></li><li class="threeword" onclick="listByPage('+cid+','+1+','+itemCount+','+'\''+searchName+'\''+')"><a href="#">上一页</a></li>';
        }else{ //如果当前页码不是第一页，则onclick时间绑定查询上一页的listByPage
            fcontent = '<li onclick="listByPage('+cid+','+1+','+itemCount+','+'\''+searchName+'\''+')"><a href="javascript:void(0)">首页</a></li><li class="threeword" onclick="listByPage('+cid+','+(pageIndex-1)+','+itemCount+','+'\''+searchName+'\''+')"><a href="#">上一页</a></li>';

        }
        //设置页码分页条幅的开始和结束索引
        var start;
        var end;
        //设定分页条幅最多出现5个li
        //如果总页数小于等于5
        if(totalPage<=5){
            start = 1;
            end=totalPage;
            //总页数大于5，并且当前页索引小于等于2，则显示从1到5的页码
        }else if(pageIndex<=2){
            start=1;
            end = 5;
            //当前页码靠近总页数
        }else if(pageIndex>=totalPage-1){
            start = totalPage-4;
            end = totalPage;
            //当前页码在中间，则显示左2条，有两条
        }else{
            start = pageIndex-2;
            end = pageIndex+2;
        }
        //向ul中添加页码
        for(var i = start; i <= end; i++){
            //如果是当前页码索引，则设置为nav-activ状态，并绑定onclick的listByPage
            if(pageIndex===i){
            fcontent+='<li class="curPage" onclick="listByPage('+cid+','+i+','+itemCount+','+'\''+searchName+'\''+')"><a href="javascript:void(0)">'+i+'</a></li>';
            }else{                                                              //注意searchName左右需要添加\'字符，不然会出现两"矛盾，console会出现error input错误
                fcontent+='<li onclick="listByPage('+cid+','+i+','+itemCount+','+'\''+searchName+'\''+')"><a href="javascript:void(0)">'+i+'</a></li>';

            }

        }
        //同上一页一样绑定下一页和末页的单击事件
        if(pageIndex>=totalPage){
            fcontent+='<li onclick="listByPage('+cid+','+totalPage+','+itemCount+','+'\''+searchName+'\''+')" class="threeword"><a href="javascript:void(0);">下一页</a></li><li onclick="listByPage('+cid+','+totalPage+','+itemCount+','+'\''+searchName+'\''+')" class="threeword"><a href="javascript:void(0);">末页</a></li>';

        }else{
            fcontent+='<li onclick="listByPage('+cid+','+(pageIndex+1)+','+itemCount+', '+'\''+searchName+'\''+')" class="threeword"><a href="javascript:void(0);">下一页</a></li><li onclick="listByPage('+cid+','+totalPage+','+itemCount+','+'\''+searchName+'\''+')" class="threeword"><a href="javascript:void(0);">末页</a></li>';
        }
        //向ul中填充li
        $("#pageBt").html(fcontent);
        //向w页面滑向顶端
        window.scrollTo(0,0);
    })
}