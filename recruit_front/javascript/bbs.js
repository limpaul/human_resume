
function setEditBbs(){
    const searchparams = new URLSearchParams(location.search);
    const url = `http://localhost:8080/api/board/check/bbsPw`;
    const bbsId = searchparams.get("id");
    const bbsPw = prompt('글 수정', '비밀번호');
    const data = {
        bbsId,
        bbsPw,
    }

    $.ajax({
        url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response){
            if(response){
                $(location).attr('href',`edit_bbs.html?id=${bbsId}`);
            }else{
                alert('비밀번호가 올바르지 않습니다. ');
            }
        }
    })
}
function setRemoveBbs(){
    const searchParams = new URLSearchParams(location.search);
    const bbsId = searchParams.get('id');
    var bbsPw = prompt("비밀번호", "삭제 비밀번호");
    const url = `http://localhost:8080/api/board/remove/bbs`;
    const data = {
        bbsId,
        bbsPw
    }
    $.ajax({
        url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response){
            if(response){
                $(location).attr('href','community.html');
            }else{
                alert('비밀번호 올바르지 않습니다.');
            }
           
        }
    })
}
function setBbsView(bbs){
    $('.bbs_form > p:nth-child(1)').append(bbs.bbsId);
    $('.bbs_form > p:nth-child(2)').append(bbs.bbsWriter);
    $(".bbs_form > input").attr('value', `${bbs.bbsTitle}`)
    $('.bbs_form > textarea').append(bbs.bbsContent);
}
function setReviewView(review){
    
    for(var i = 0 ; i < review.length ; i++){
        $('.review_form').prepend(
            `
            <ul>
                <li>${review[i].reviewWriter}</li>
                <li>${review[i].reviewContent}</li>
                <li>${review[i].reviewDate}</li>
            </ul>
            `
            );   
    }
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
            setReviewView(data.review);
        }
    });
}
function sendReviewForm(){
    const searchParams = new URLSearchParams(location.search);
    
    const url = 'http://localhost:8080/api/board/write/review';
    const bbsId = searchParams.get('id');
    const reviewContent = $('#review_content').val();
    const reviewPw = $('#review_pw').val();
    
    const userId = 13;
    const reviewWriter = 'tester';

    const data ={
        bbsId,
        reviewContent,
        reviewPw,
        userId,
        reviewWriter,
    };
    $.ajax({
        url,
        type : 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(res){
            console.log(res);
        }
    });
    location.reload();

}
$(document).ready(()=>{
  getBbsIdInfo();
})