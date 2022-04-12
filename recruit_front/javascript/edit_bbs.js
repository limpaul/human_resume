
function setBbsView(bbs){
    $("#bbs_title").attr('value', `${bbs.bbsTitle}`)
    $('#bbs_content').append(bbs.bbsContent);
}

function getBbsIdInfo(){
    const searchParams = new URLSearchParams(location.search);
    const id = searchParams.get('id');
    const url = `http://localhost:8080/api/board/view/id=${id}`;
    $.ajax({
        url,
        type: 'GET',
        success: function(data){
            console.log(data);
            setBbsView(data);
        }
    });
}


function edit_bbs(){
    const urlParams =  new URLSearchParams(location.search);
    const bbsId = urlParams.get("id");
    const bbsTitle = $('#bbs_title').val();
    const bbsContent = $('#bbs_content').val();
    const bbsPw = $('#bbs_pw').val();
    const url = `http://localhost:8080/api/board/edit/bbs`;
    const data = {
        bbsId,
        bbsTitle,
        bbsContent,
        bbsPw
    }
    $.ajax({
        url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response){
            if(response == 1){
                $(location).attr('href', `bbs.html?id=${bbsId}`);
            }else{
                alert('글 수정 불가.');
            }
        } 
    })
}
$(document).ready(()=>{
   getBbsIdInfo();
})