var gloNumUniv = 0; // 학교 등록 개수.
var gloIdUniv = 0; // 아이디를 구분하는 인덱스
var gloNumCert = 0;
var gloIdCert = 0; // 아이디를 구분하는 인덱스
var univMap = new Map();
var certMap = new Map();

//univMap.set('univ0', 0);
//certMap.set('cert0', 0);
function minUniv(num){ // 해당하는 자격증 목록 클래스를 가져온다. ex) univ .은 안가져옴 그래서 .${}해준거임
    if(gloNumUniv >= 0){
        $(`.${num}`).remove();
        univMap.delete(num); // ex) 제거 key 가 univ1
        gloNumUniv--;
    }else{
        return;
    }
}

function addUniv(data){
    gloNumUniv++;
    gloIdUniv++;
    univMap.set(('univ'+gloNumUniv), gloIdUniv);
   
    const text = 
    `
<div class=univ${gloNumUniv}>
<hr/>
<ul>
    <li><b>대학</b></li>
    <li>
        <select id="degreeType${gloIdUniv}" value="${data == null ? 0 : data.degreeType}">
            <option value=0>고등학교</option>
            <option value=2>전문대학교(2년)</option>
            <option value=3>전문대학교(3년)</option>
            <option value=4>대학교</option>
            <option value=6>석사</option>
            <option value=8>박사</option>
        </select>
    </li>
</ul>
<ul>
    <li><b>학교명</b><b></b></li>
    <li><input id="univname${gloIdUniv}" type="text" value="${data == null ? '' : data.degreeName}"></li>
</ul>
<ul>
    <li><b>지역</b></li>
    <li>
        <select id="region${gloIdUniv}">
            <option value=0></option>
            <option value=1>서울</option>
            <option value=2>경기</option>
            <option value=3>대전</option>
            <option value=4>대구</option>
            <option value=5>강원</option>
            <option value=6>경남</option>
            <option value=7>경북</option>
            <option value=8>전남</option>
            <option value=9>전북</option>
            <option value=10>제주</option>
        </select> 
    </li>
</ul>
<ul>
    <li><b>재학기간</b></li>
    <li>
        <input id="sc_start${gloIdUniv}" type="date" value="${data == null ? '' : data.degreeStartDate}">
        <input id="sc_end${gloIdUniv}" type="date" value="${data == null ? '' : data.degreeEndDate}">
    </li>
</ul>
<ul>
    <li><b>전공</b></li>
    <li><input id="major${gloIdUniv}" type="text" value="${data == null ? "" : data.degreeMajor}"></li>
</ul>
<ul>
    <li><b>학점</b></li>
    <li><input id="score${gloIdUniv}" type="text" value="${data == null ? '' : data.degreeScore}"></li>
</ul>
<ul>
<li><b>졸업여부</b></li>
<li>
    <select id="gradu${gloIdUniv}" value="${data == null ? 1 : data.isGraduate}">
        <option value="1">졸업</option>
        <option value="2">편입/졸업</option>
        <option value="3">졸업예정</option>
        <option value="4">자퇴</option>
    </select>
</li>
</ul>
<button class="minBtn" onclick="minUniv('univ${gloNumUniv}');">삭제</button>
</div>
`
    $('#univ_spec').append(text);

    if(data.degreeRegion != null){
        $(`#region${gloIdUniv}`).val(data.degreeRegion);
    }

}

function addCert(data){
    gloIdCert++;
    gloNumCert++;
    certMap.set(('cert'+gloNumCert), gloIdCert);


    const text = 
    `
<div class=cert${gloNumCert}>
    <hr/>
        <ul>
            <li><b>취득일</b></li>
            <li><input id="cert_date${gloIdCert}" type="date" value=${data.certDate != null?data.certDate:''}></li>
        </ul>
        <ul>
            <li><b>자격증명</b><b></b></li>
            <li><input id="cert_name${gloIdCert}" type="text"></li>
        </ul>
            <ul>
            <li><b>발행처/기관/언어</b></li>
            <li><input id="public_name${gloIdCert}" type="text"></li>
        </ul>  
    <button class="minBtn" onclick="minCert('cert${gloNumCert}');">삭제</button>
</div>
`
    $('#univ_spec2').append(text);
}

function minCert(num){
    if(gloNumCert >= 1){
        $(`.${num}`).remove();
        certMap.delete(num);
        gloNumCert--;
    }else{
        return;
    }
}


function infoRender(data){
    const name = $('#name').val(data.name);
    const birth = $('#birth').val(data.birth);
    const email = $('#email').val(data.email);
    const phoneNum = $('#phoneNum').val(data.phoneNum);
    const homeNum = $('#homeNum').val(data.homeNum);
    const address = $('#address').val(data.address);
    const gender = $('#gender').val(data.gender == 'm'?'남자':'여자');
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

var gloNumCareer = 0;
var gloIdCareer = 0; // 아이디를 구분하는 인덱스
var careerMap = new Map();


function minCareer(careerInfo){
    $(`#${careerInfo}`).remove();
    careerMap.delete(careerInfo); // 키 삭제

    if(careerMap.size == 0){
        $('.new').css('background-color', 'blue');
        $('.old').css('background-color', '#aaa1a1');
        newOrOldclick = true; // click은 현재 버튼이 신입을 클릭 했는지 경력을 클릭했는지 확인하는 전역 변수 이다.
    }
}

var newOrOldclick = true
function careerButtonEvent(str){
    
    if(str == 'new' && newOrOldclick == false){
        newOrOldclick = true;
        $('.old').css('background-color', '#aaa1a1')
        $('.new').css('background-color', 'blue');
        $('.career_div > *').remove()
        careerMap.clear();
    }else if(str == 'old' && newOrOldclick == true){ // 경력 내용 삭제
        newOrOldclick = false;
        $('.new').css('background-color', '#aaa1a1')
        $('.old').css('background-color', 'blue');
        addCareer();
    }

}
function addCareer(){
    $('.career_div').append(`
        <div id="careerInfo${gloIdCareer}">
        <ul>
            <li><b>회사명</b></li>
            <li><input id="companyName${gloIdCareer}" type="text"></li>
        </ul>
        <ul>
            <li><b>재직기간</b></li>
            <li><input id="startDate${gloIdCareer}" type="date"><input id="endDate${gloIdCareer}" type="date"></li>
        </ul>
        <ul>
            <li><b>직책</b></li>
            <li><input id="position${gloIdCareer}" type="text"></li>
        </ul>   
        <ul>
            <li><b>근무부서</b></li>
            <li><input id="department${gloIdCareer}" type="text"></li>
        </ul>   
        <ul>
            <li><b>근무지역</b></li>
            <li>
                <select id="workRegion${gloIdCareer}">
                    <option value="0"></option>
                    <option value="1">서울</option>
                    <option value="2">경기</option>
                    <option value="3">대전</option>
                    <option value="4">대구</option>
                    <option value="5">강원</option>
                    <option value="6">경남</option>
                    <option value="7">경북</option>
                    <option value="8">전남</option>
                    <option value="9">전북</option>
                    <option value="10">제주</option>
                </select> 
            </li>
        </ul>   
        <ul>
            <li><b>연봉</b></li>
            <li>
                <select id="salary">
                    <option value="0">비공개</option>
                    <option value="1">1천만 이상</option>
                    <option value="2">2천만 이상</option>
                    <option value="3">3천만 이상</option>
                    <option value="4">4천만 이상</option>
                    <option value="5">5천만 이상</option>
                    <option value="10">1억 이상</option>
                </select> 
            </li>
        </ul>  
        <ul>
            <li><b>담당업무</b></li>
            <li><input id="task${gloIdCareer}" type="text"></li>
        </ul>    
        <button class="addBtn" style="float: right;" onclick="addCareer();">추가</button>
        <button class="minBtn" onclick="minCareer('careerInfo${gloIdCareer}');">삭제</button>
    </div>
    
        `);

        careerMap.set(`careerInfo${gloIdCareer}`, gloIdCareer);    //key value 담기
        gloIdCareer++; // 전역 아이디 값 증가 시켜주기 
}

function saveResume(){
    const url = 'http://localhost:8080/api/resume/write';
    var userNo = 13;
    var data = {};
    var degreeArr = [];
    var degree = {};
    var certArr = [];
    var cert = {};
    for(var [key, index] of univMap){
         const degreeType = $(('#degreeType'+[index])).val();
         const degreeName = $(('#univname'+[index])).val();
         const degreeRegion = $(('#region'+[index])).val();
         const degreeStartDate = $(('#sc_start'+[index])).val();
         const degreeEndDate = $(('#sc_end'+[index])).val();
         const degreeMajor =  $(('#major'+[index])).val();
         const degreeScore = $(('#score'+[index])).val();
         const isGraduate = $(('#gradu'+[index]+' option:selected')).val();
         console.log(degreeType+'/ '+degreeName+'/ '+degreeRegion+'/ '+
         degreeStartDate+'/ '+degreeEndDate+'/ '+degreeMajor+'/ '+degreeScore+'/ '+
         isGraduate); 
         degree = {
             degreeType,
             degreeName,
             degreeRegion,
             degreeStartDate,
             degreeEndDate,
             degreeMajor,
             degreeScore,
             isGraduate
         }
         degreeArr.push({
             ...degree
         })
         data['degrees'] = degreeArr;

    }

    careerArr = [];
    for(var [key, index] of certMap){
        const certDate = $((('#cert_date')+index)).val();
        const certName = $((('#cert_name')+index)).val();
        const certType = $((('#public_name')+index)).val();
        console.log(certDate+'/ '+certName+'/ '+certType);
        cert = {
            certDate,
            certName,
            certType
        }
        certArr.push({
            ...cert
        })
        data['certs'] = certArr;
    }
   
    const enrollTitle = $('#enrollTitle').val();
    const enrollContent = $('#enrollContent').val();
    const hopeJob = $('#hopeJob').val();
    const hopeSalary = $('#hopeSalary').val();
    const hopeRegion = $('#hopeRegion').val();
    const isNewComer = newOrOldclick;
   
    console.log(enrollTitle+"/ "+enrollContent+'/ '+hopeJob+'/ '+hopeSalary+'/ '+hopeRegion);
    
    const enroll = {
        userNo,
        enrollTitle,
        enrollContent,
        hopeJob,
        hopeSalary,
        hopeRegion,
        isNewComer
    }
    data['enroll'] = enroll;

    /** career 정보 가져오기 만약 선택 버튼이 경력일 경우만 해당 */
    if(newOrOldclick == false){ // 경력버튼을 선택하였을 경우

        for(var [key, index] of careerMap){
            const startDate = $(('#startDate'+[index])).val();
            const endDate = $(('#endDate'+[index])).val();
            const companyName = $(('#companyName'+[index])).val();
            const position = $(('#position'+[index])).val();
            const department =  $(('#department'+[index])).val();
            const region = $(('#workRegion'+[index])).val();
            const salary = $(('#salary'+[index])).val();
            const task = $(('#task'+[index])).val();
            console.log(startDate+'/ '+endDate+'/ '+companyName+'/ '+
            position+'/ '+department+'/ '+region+'/ '+salary+'/ '+
            task); 
            career = {
               startDate,
               endDate,
               companyName,
               position,
               department,
               region,
               salary,
               task
            }
            careerArr.push({
                ...career
            })
            data['careers'] = careerArr;
        }

    }




    /**
     * 기존 이력서 번호가 존재한다면
     * isCreated : false
     */
    const user = {
        userNo : 13,
        basicId : 2,
        resumeId: null,
        isCreated : true,
    }
    data['userInfo'] = user;
    
    console.log(JSON.stringify(data));
    /** 전송 http://localhost:8080/api/resume/write */
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
function modelResume(data){ // 이력서 제목, 희망급여, 근무지역 데이터 모델링
    const enroll = data.enroll;
    $('#enrollTitle').val(enroll.enrollTitle);
    $('#hopeJob').val(enroll.hopeJob);
    $('#hopeSalary').val(enroll.hopeSalary);
    $('#hopeRegion').val(enroll.hopeRegion);
    $('#enrollContent').val(enroll.enrollContent);
}
function loadResumeInfo(){
    var params = new URLSearchParams(location.search);
    const userNo = params.get('userNo');
    const enrollId = params.get('enrollId');
    const isNew = params.get('new');

    if(localStorage.getItem('userNo')!=userNo){
        location.href = 'http://127.0.0.1:5501/html/community.html';
        return;
    }
    const url = `http://localhost:8080/api/resume/edit?userNo=${userNo}&enrollId=${enrollId}&new=${isNew}`;
    $.ajax({
        url,
        type: "GET",
        success: function(data){
            console.log(data);
            modelResume(data);

            for(var i = 0 ; i < data.degrees.length ; i++){ // 학위를 렌더링..
                addUniv(data.degrees[i]);
            }
        }
    })
}
$(document).ready(()=>{
    $('.new').css('background-color', 'blue');

    const basicId = 2; // 개인정보 아이디  !! 이력서 번호가 아니다 
    findExistResume(basicId);
    loadResumeInfo();
})