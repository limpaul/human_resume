
function appendBbsList(bbs){
  
   for(var i = 0 ; i < bbs.length ; i++){
    
    $('.bbs_content').append(
        `
        <tr>
            <td>${bbs[i].bbsId}</td>
            <td><a href=bbs.html?id=${bbs[i].bbsId}>${bbs[i].bbsTitle}</a></td>
            <td><a href="#">${bbs[i].bbsWriter}</a></td>
            <td>${bbs[i].bbsDate}</td>
            <td>${bbs[i].bbsRead}</td>
        </tr>
        `);
   }
}

function pageSetting(page){
    for(var i = 0 ; i < page ; i++){
        $('.page > p').append(`<a href="community.html?page=${i+1}">${i+1}</a>`);
    }
}

function searchContent(){
       const searchType = $('#searchType').val();
       const content = $('#searchContent').val();
       if(searchType === '' || searchType === 'none' || content===''){
           return;
       }
       const url = `http://localhost:8080/api/board/search?${searchType}=${content}`
       $.ajax({
           url,
           type: 'GET',
           success: function(data){
               console.log(data);
               $('.bbs_content').empty();
               $('.page > p').empty();
               appendBbsList(data.bbsListDto);
               pageSetting(data.page);
           }
       })
}
function loadBbs(){
    var page = new URLSearchParams(location.search).get('page');
    if(page == null){
        page = 1;
    }
    const url = `http://localhost:8080/api/board?page=${page}`;
    $.ajax({
        url,
        type: 'GET',
        success: function(data){
            console.log(data);
            appendBbsList(data.bbsListDto);
            pageSetting(data.page);
        }

    });
}
$(document).ready(()=>{
    loadBbs();
})