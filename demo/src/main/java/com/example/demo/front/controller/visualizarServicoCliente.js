document.addEventListener('DOMContentLoaded', function () {
console.log("DOM completamente carregado e analisado.");

    // Configuração inicial
    const container = document.getElementById('tela');
    const formulario = document.getElementById("cadastroServicoForm");
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    let currentServicoId = null; // Armazena o ID do serviço atual para edição

    // Função para carregar e exibir serviços
    async function getServicos() {
        const urlServicos = 'http://localhost:8080/servico';
        try {
            const response = await fetch(urlServicos, {
                method: "GET",
                headers: { Authorization: `Bearer ${token}` }
            });

            if (!response.ok) {
                throw new Error(`Erro ao obter serviços: ${response.statusText}`);
            }

            const servicos = await response.json();
            container.innerHTML = '';
            servicos.forEach(servico => {
                container.innerHTML += `
                    <div class="col-md-3 mb-4">
                        <div class="card1">
                            <div class="card-body1">
                                <h5 class="card-title">${servico.nomeServico}</h5>
                                <p class="card-text">Preço: R$${servico.preco}</p>
                                <p class="card-text">Unidade de Medida: ${servico.unidadeMedida}</p>
                                <p class="card-text">Descrição: ${servico.descricao}</p>
                                <button  href="../pages/preencherInfoObra.html" class="btn btn-danger btn-comprarObra" data-servico-id="${servico.idServico}">Comprar obra</button>
                            </div>
                        </div>
                    </div>`;
            });
            attachButtons();
        } catch (error) {
            console.error(error);
        }
        function attachButtons() {
            
            const buttons = document.querySelectorAll('.btn-comprarObra');
            
            // Add a click event listener to each button
            buttons.forEach(button => {
                button.addEventListener('click', (event) => {
                    const servicoId = event.target.getAttribute('data-servico-id');
                    // Redirect to "preencherInfoObra.html" with the service ID as a query parameter
                    window.location.href = `../pages/preencherInfoObra.html?servicoId=${servicoId}`;
                });
            });
        }
    }


    // Carrega os serviços quando a página é carregada
    getServicos();
});