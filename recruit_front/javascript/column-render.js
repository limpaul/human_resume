function regionRender(num){
   if (num === 0) {
       return "상관 없음";
   }
   if (num === 1) {
       return "서울";
   }
   if (num === 2) {
       return "경기";
   }
   if (num === 3) {
       return "대전";
   }
   if (num === 4) {
       return "대구";
   }
   if (num === 5) {
       return "강원";
   }
   if (num === 6) {
       return "경남";
   }
   if (num === 7) {
       return "경북";
   }
   if (num === 8) {
       return "전남";
   }
   if (num === 9) {
       return "전북";
   }
   if (num === 10) {
       return "제주";
   }
}
function finalScoreRender(num){
    if(num == 1){
        return '졸업';
    }
    if(num == 2){
        return '편입/졸업';
    }
    if(num == 3){
        return '졸업예정';
    }
    if(num == 4){
        return '자퇴';
    }
}

function salaryRender(num){
    if(num == 0){
        return "비공개";
    }
    if(num == 1){
        return "1천만 이상";
    }
    if(num == 2){
        return "2천만 이상";
    }
    if(num == 3){
        return "3천만 이상";
    }
    if(num == 4){
        return "4천만 이상";
    }
    if(num == 5){
        return "5천만 이상";
    }
    if(num == 10){
        return "1억 이상";
    }
}

function isNewRender(num){
    if(num == true){
        return '신입';
    }
    if(num == false){
        return '경력';
    }
}
function jobRender(num){
    if(num == 0){
        return "없음";
    }
    if(num == 1){
        return "IT";
    }
    if(num == 2){
        return "생산";
    }
    if(num == 3){
        return "영업";
    }
    if(num == 4){
        return "경영";
    }
    if(num == 5){
        return "회계";
    }
}
