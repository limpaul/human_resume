
function checkResumeExsit(){
    const userNo = 13; // 사용자 ID
    const basicId = 2; // 이력서 ID
    const url = 'http://localhost:8080/api/resume/basicinfo/search';
    const data = {
        userNo,
        basicId
    }
    $.ajax({
        url,
        type:'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(res){
            console.log(res);
        }
    })

}
function sendBasicInfo(){
    const name = $('#name').val();
    const birth = $('#birth').val();
    const email = $('#email').val();
    const phoneNum = $('#phoneNum').val();
    const homeNum = $('#homeNum').val();
    const address = $('#address').val();
    const gender = $('#gender').val();
    const imgName = $('#upload_file').val();
    const userNo = 13; // 사용자 ID
    const url = 'http://localhost:8080/api/resume/basicinfo/write';
    const data = {
        name,
        birth,
        email,
        phoneNum,
        homeNum,
        address,
        gender,
        imgName,
        userNo
    }
    console.log(data);
    $.ajax({
        url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response){
            console.log(response);
        }
    })
}
function imageSend(frm){
    var fileCheck = frm.upload_file.value;
    if(!fileCheck){
        alert('업로드할 파일을 선택해주세요');
        return false;
    }
   const fileName = fileCheck.split("\\")[2];
    var formData = new FormData(frm);
    const url = 'http://localhost:8080/api/resume/uploadimage';
    const imageUrl = 'http://localhost:8080/img/'+fileName;
    $.ajax({
        url,
        type: 'POST',
        dataType: 'html',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        data: formData,
        success: function(res){
            $('#profile').attr('src', imageUrl);
        }
    }) 
    
}
function infoRender(data){
    const name = $('#name').val(data.name);
    const birth = $('#birth').val(data.birth);
    const email = $('#email').val(data.email);
    const phoneNum = $('#phoneNum').val(data.phoneNum);
    const homeNum = $('#homeNum').val(data.homeNum);
    const address = $('#address').val(data.address);
    const gender = $('#gender').val(data.gender);
    const fileName = data.imgName;
    const imageUrl = 'http://localhost:8080/img/'+fileName;
    $('#profile').attr('src', imageUrl);
    $('#send').text('수정하기');
}
/**basicId로 기존 게시글 있는지 조회 */
function findExistResume(basicId){
    const url = 'http://localhost:8080/api/resume/basicinfo/search?basicId='+basicId;
    $.ajax({
        url,
        type: 'POST',
        success: function(res){
            console.log(res);
            if(res!=null){
                infoRender(res);
            }else{
                return;
            }
        }
    })
}
$(document).ready(()=>{
    findExistResume(2);
})