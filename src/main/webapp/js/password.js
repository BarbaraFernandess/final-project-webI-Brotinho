function exibirSenha() {
   var password = document.getElementById('password');
   var btnExibir = document.getElementById('btn-senha');

   if(password.type === 'password'){
    password.setAttribute('type', 'text');
    seepassword.className = 'bi bi-eye-fill';
   }
   else{
    password.setAttribute('type', 'password');
    seepassword.className ='bi bi-eye-slash-fill';
   }
}