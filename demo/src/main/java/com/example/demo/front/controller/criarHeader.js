const container = document.querySelector(".divNav");

if (sessionStorage.getItem("tipoUsuario") == 'cliente')
{ innerHtml= 
    container.innerHTML = '';
    container.innerHTML += `
    <nav>
    <ul>
      <li><a href="../pages/home.html">Home</a></li>
      <li><a href="../pages/login.html">Login</a></li>
      <li><a href="../pages/dadosCliente.html">Perfil</a></li>
      <li><a href="../pages/viewService.html">Adquirir serviços</a></li>
      <li><a href="../pages/inicio.html">Ver minhas obras</a></li>
      <li><a id="btnLogout">Logout</a></li>
    </ul>
  </nav>`;
}

if (sessionStorage.getItem("tipoUsuario") == 'fornecedor')
{ innerHtml= 
    container.innerHTML = '';
    container.innerHTML += `
    <nav>
        <ul>
          <li><a href="../pages/home.html">Home</a></li>
          <li><a href="../pages/login.html">Login</a></li>
          <li><a href="../pages/dadosFornecedor.html">Perfil</a></li>
          <li><a href="../pages/visualizar.html">Cadastro de Serviço</a></li>
          <li><a href="../pages/inicio.html">Ver minhas obras</a></li>
          <li><a id="btnLogout">Logout</a></li>
        </ul>
      </nav>`;
}

function logout() {
    localStorage.removeItem('userId');
    localStorage.removeItem('token');
    sessionStorage.removeItem("tipoUsuario");
    window.location.href = 'login.html';
}

const btnLogout = document.getElementById('btnLogout');
if (btnLogout) {
    btnLogout.addEventListener("click", function (e) {
        e.preventDefault();
        logout();
    });
} else {
    console.error("Botão de logout não encontrado no DOM.");
}