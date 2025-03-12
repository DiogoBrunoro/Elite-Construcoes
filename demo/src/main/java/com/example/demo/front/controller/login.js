// Variáveis do login
var formLogin = document.getElementById("forms-login");
var btnLogin = document.getElementById("btn-login");
var emailLogin = document.querySelector(".email");
var senhaLogin = document.querySelector(".password");
var tipoLogin = document.getElementById("tipo-login");

// Adiciona evento de clique ao botão de login
btnLogin.addEventListener("click", function (e) {
  e.preventDefault();

  console.log(emailLogin.value);
  console.log(senhaLogin.value);

  // Determina o URL com base no tipo de login selecionado
  var urlLogin = tipoLogin.value === 'fornecedor'
                 ? "http://localhost:8080/fornecedor/login"
                 : "http://localhost:8080/cliente/login";

  // Monta os dados a serem enviados
  var data = tipoLogin.value === 'fornecedor'
             ? {
                 emailCorporativo: emailLogin.value,
                 senhaFornecedor: senhaLogin.value
               }
             : {
                 email: emailLogin.value,
                 senha: senhaLogin.value
               };
if(tipoLogin.value == 'fornecedor'){
  sessionStorage.setItem("tipoUsuario",'fornecedor')
}
else{
  sessionStorage.setItem("tipoUsuario",'cliente')
}

  console.log("Dados a serem enviados:", data);

  // Faz a requisição
  axios.post(urlLogin, data, {
    headers: {
      'Content-Type': 'application/json'
    }
  })
  .then(response => {
    console.log('Dados retornados do servidor:', response.data);
    localStorage.setItem("token", response.data.token);

    // Acessa o id corretamente
    if (response.data.id) {
      localStorage.setItem("userId", response.data.id);
    } else {
      console.warn('userId não encontrado na resposta do servidor.');
    }

    alert("Login realizado com sucesso");
    console.log('Token armazenado:', localStorage.getItem("token"));
    console.log('userId armazenado:', localStorage.getItem("userId"));
    window.location.href = "home.html";
  })
  .catch(error => {
    if (error.response) {
      console.log("Data:", error.response.data);
      console.log("Status:", error.response.status);
      console.log("Headers:", error.response.headers);
    } else if (error.request) {
      console.log("Request:", error.request);
    } else {
      console.log("Error:", error.message);
    }
    console.log("Config:", error.config);
  });
});

