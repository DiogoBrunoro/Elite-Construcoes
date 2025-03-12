document.addEventListener('DOMContentLoaded', function () {
    console.log("DOM completamente carregado e analisado.");

    const container = document.querySelector(".container");

    // Verificar tipo de usuário
    console.log('Tipo de Usuário:', sessionStorage.getItem("tipoUsuario"));

    // Defina userId e token de acordo com a sua lógica de autenticação
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (sessionStorage.getItem("tipoUsuario") === 'fornecedor') {
        async function getObras() {
            const urlServicos = `http://localhost:8080/obra/fornecedor/${userId}`;
            try {
                const response = await fetch(urlServicos, {
                    method: "GET",
                    headers: { Authorization: `Bearer ${token}` }
                });

                if (!response.ok) {
                    throw new Error(`Erro ao obter obras: ${response.statusText}`);
                }

                const data = await response.json(); // Obtenha a resposta da API
                console.log('Resposta da API (Fornecedor):', data);  // Verifique a estrutura dos dados

                const obras = Array.isArray(data.content) ? data.content : [];

                if (obras.length === 0) {
                    container.innerHTML = '<p>Nenhuma obra encontrada.</p>';
                    return;
                }

                // Limpar o conteúdo da container antes de adicionar novas obras
                container.innerHTML = '';

                // Adicionar obras no HTML
                obras.forEach(obra => {
                    container.innerHTML += `
                        <div class="col-md-3 mb-4">
                            <div class="card1">
                                <div class="card-body1">
                                    <h5 class="card-title1">${obra.nomeObra}</h5>
                                    <p class="card-text">Área Construída: ${obra.areaConstruida} m²</p>
                                    <p class="card-text">Endereço: ${obra.endereco}</p>
                                    <p class="card-text">Observação: ${obra.observacaoCliente}</p>
                                    <p class="card-text">Cliente: ${obra.cliente.nomeCompleto}</p>
                                    <p class="card-text">Estado: ${obra.estado}</p>
                                    <div class="button-container">
                                        <button type="button" class="btn-concluir" data-obra-id="${obra.idObra}">Gerir Obra</button>
                                    </div>
                                </div>
                            </div>
                        </div>`;
                });

                // Adicionar comportamento aos botões
                attachButtons();
            } catch (error) {
                console.error('Erro ao buscar obras:', error);
                container.innerHTML = '<p></p>';
            }
        }

        // Chamar a função para obter as obras
        getObras();

        function attachButtons() {
            const buttons = document.querySelectorAll('.btn-concluir');
            buttons.forEach(button => {
                button.addEventListener('click', function () {
                    const obraId = button.getAttribute('data-obra-id');
                    // Redirecionar para a página de gestão da obra, passando o ID da obra na URL
                    window.location.href = `../pages/gerirObraFornecedor.html?id=${obraId}`;
                });
            });
        }
    }

    if (sessionStorage.getItem("tipoUsuario") === 'cliente') {
        async function getObras() {
            const urlServicos = `http://localhost:8080/obra/cliente/${userId}`;
            try {
                const response = await fetch(urlServicos, {
                    method: "GET",
                    headers: { Authorization: `Bearer ${token}` }
                });

                if (!response.ok) {
                    throw new Error(`Erro ao obter obras: ${response.statusText}`);
                }

                const data = await response.json(); // Obtenha a resposta da API
                console.log('Resposta da API (Cliente):', data);  // Verifique a estrutura dos dados

                const obras = Array.isArray(data.content) ? data.content : [];

                if (obras.length === 0) {
                    container.innerHTML = '<p>Nenhuma obra encontrada.</p>';
                    return;
                }

                // Limpar o conteúdo da container antes de adicionar novas obras
                container.innerHTML = '';

                // Adicionar obras no HTML
                obras.forEach(obra => {
                    container.innerHTML += `
                        <div class="col-md-4 mb-4">
                            <div class="card1">
                                <div class="card-body1">
                                    <h5 class="card-title1">${obra.nomeObra}</h5>
                                    <p class="card-text">Área Construída: ${obra.areaConstruida} m²</p>
                                    <p class="card-text">Endereço: ${obra.endereco}</p>
                                    <p class="card-text">Observação: ${obra.observacaoCliente}</p>
                                    <p class="card-text">Fornecedor: ${obra.servico?.fornecedor?.nomeFornecedor || 'Fornecedor não disponível'}</p>
                                    <p class="card-text">Estado: ${obra.estado}</p>
                                    <div class="button-container">
                                        <button type="button" class="btn-concluir" data-obra-id="${obra.idObra}">Gerir Obra</button>
                                    </div>
                                </div>
                            </div>
                        </div>`;
                });

                // Adicionar comportamento aos botões
                attachButtons();
            } catch (error) {
                console.error('Erro ao buscar obras:', error);
                container.innerHTML = '<p></p>';
            }
        }

        // Chamar a função para obter as obras
        getObras();

        // Função para adicionar comportamento aos botões "Gerir Obra"
        function attachButtons() {
            const buttons = document.querySelectorAll('.btn-concluir');
            buttons.forEach(button => {
                button.addEventListener('click', function () {
                    const obraId = button.getAttribute('data-obra-id');
                    // Redirecionar para a página de gestão da obra, passando o ID da obra na URL
                    window.location.href = `../pages/gerirObraCliente.html?id=${obraId}`;
                });
            });
        }

    }
});


