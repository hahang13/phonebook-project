/**
 * 
 */
 
const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnlogin-popup');
const iconClose = document.querySelector('.icon-close');

const iconbtn = document.querySelector('.btn');

iconbtn.addEventListener('click',()=> {
  wrapper.classList.add('active');
  });




registerLink.addEventListener('click',()=> {
  wrapper.classList.add('active');
  });

loginLink.addEventListener('click',()=> {
  wrapper.classList.remove('active');
  });

btnPopup.addEventListener('click', ()=> {
   wrapper.classList.add('active-popup');
   });  

iconClose.addEventListener('click',()=> {
  wrapper.classList.remove('active-popup');
  });
  
   /**
 비밀번호 일치 확인 
 */

  function test() {
  var p1 = document.getElementById('password1').value;
  var p2 = document.getElementById('password2').value;
  if( p1 != p2 ) {
      alert("비밀번호가 일치 하지 않습니다");
      return false;
  } else{
      alert("비밀번호가 일치합니다");
      return true;
  }
  
  }

  /**
 아이디 중복 확인 
 */  
 
function checkId(email) {
   $.ajax({
      url: '/phonebooks/checkid',
      type: 'post',
      data: {email:email},
      success: function(check) {
         if (check == 0) {
            $('.id_ok').css("display", "inline-block");
            $('.id_already').css("display", "none");
         } else {
            $('.id_already').css("display", "inline-block");
            $('.id_ok').css("display", "none");
            $('email').val('');
         }
      }
   });
};
 
 
 
 
 
 
 
 