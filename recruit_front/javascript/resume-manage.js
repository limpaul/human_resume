// 이력서 공개, 비공개 기능 
function publicOrPrivateResume(enrollId, isPublic){
    const url = 'http://localhost:8080/api/resume/active';
    const userNo = localStorage.getItem('userNo');
    const data = {
        userNo,
        enrollId,
        isPublic
    }
    $.ajax({
        url,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(res){
            console.log(res);
        }
    })
}
function publicClick(aa, enrollId){ // 공개키 누르면 
    $(`#private${aa}`).css({"background-color": "gray"})
    $(`#public${aa}`).css({"background-color": "blue"})
    alert('이력서를 공개하셨습니다.');
    publicOrPrivateResume(enrollId, true);
}
function privateClick(aa, enrollId){ // 비 공개키 누르면
    $(`#public${aa}`).css({"background-color": "gray"})
    $(`#private${aa}`).css({"background-color": "blue"})
    alert('이력서를 비공개하셨습니다.');
    publicOrPrivateResume(enrollId, false);
}
function publicOrPrivateButtonColor(color, i){
    if(color == true){
        $(`#private${i}`).css({"background-color": "gray"})
        $(`#public${i}`).css({"background-color": "blue"})
    }else{
        $(`#public${i}`).css({"background-color": "gray"})
        $(`#private${i}`).css({"background-color": "blue"})
    }
}

function editPageLink(enrollId, isNew){
    const userNo = localStorage.getItem("userNo");
    if(userNo == null){
        alert('로그인 바랍니다');
        return;
    }
    console.log(userNo+'/ '+enrollId+'/ '+isNew);
    location.href = `resume-update.html?userNo=${userNo}&enrollId=${enrollId}&new=${isNew}`
    //http://localhost:8080/api/resume/edit?userNo=${userNo}&enrollId=${enrollId}&new=${isNew}
}

function modelingReumseList(data){
    for(var i = 0 ; i < data.length ; i++){
        const enrollTitle = data[i].enrollTitle;
        const hopeJob = data[i].hopeJob;
        const hopeSalary = data[i].hopeSalary;
        const hopeRegion = data[i].hopeRegion;
        const userNo = data[i].userNo;
        const enrollId = data[i].enrollId;
        const isNewComer = data[i].isNewComer;
        const isPublic = data[i].isPublic;
        console.log(hopeSalary+"/ "+hopeRegion);
        $('.mid-container').append(
            `
            <div class="resume_info">
                <p><b>${enrollTitle}</b></p>
                <ul>
                    <li>${isNewRender(isNewComer)}</li>
                    <li>${jobRender(hopeJob)}</li>
                </ul>
                <ul>
                    <li>급여: ${salaryRender(hopeSalary)}</li>
                    <li>희망지역: ${regionRender(hopeRegion)}</li>
                </ul>
                <div class="setting">
                    <button id="public${i}" onclick="publicClick(${i}, ${data[i].enrollId})">공개</button>
                    <button id="private${i}" onclick="privateClick(${i}, ${data[i].enrollId})">비공개</button>
                    <b>입사 지원 내역  </b>
                    <div class="edit">
                        <button onClick="editPageLink(${enrollId}, ${isNewComer})">수정하기</button>
                    </div>
                </div>
            </div>
            `
        )
        if(data.length > 0){
            localStorage.setItem('userNo', userNo);
        }
        publicOrPrivateButtonColor(isPublic, i);
    }
}
function resumeList(){
    const userNo = 13;
    const url = `http://localhost:8080/api/resume/list/${userNo}`;
    $.ajax({
        url,
        type: 'POST',
        success: function(res){
            console.log(res);
            modelingReumseList(res);
        }
    })
}
$(document).ready(()=>{
   resumeList();
});