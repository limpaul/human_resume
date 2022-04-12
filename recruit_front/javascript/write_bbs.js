
function send_bbs(){
    const bbsWriterId = '13';
    const bbsWriter = 'test';
    const bbsTitle = $('#bbs_title').val();
    const bbsContent = $('#bbs_content').val();
    const bbsPw = $('#bbs_pw').val();

    const url = 'http://localhost:8080/api/board/write/bbs';
    const data ={
        bbsWriterId,
        bbsWriter,
        bbsTitle,
        bbsContent,
        bbsPw,
    }
    $.ajax({
        url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response){
            console.log(response);
        }
    })
    $(location).attr('href','community.html');
}

$(document).ready(()=>{
 })