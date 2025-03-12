document.addEventListener('DOMContentLoaded', function () { 
    console.log("DOM completamente carregado e analisado.");

    // Extrair o servicoId da URL
    const urlParams = new URLSearchParams(window.location.search);
    const servicoId = urlParams.get('servicoId');

    // Função para cadastrar uma nova obra
    async function cadastrarObra(data) {
        const urlCadastroObra = 'http://localhost:8080/obra';
        try {
            const response = await fetch(urlCadastroObra, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                },
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw new Error(`Erro ao cadastrar obra: ${response.statusText}`);
            }

            alert("Obra cadastrada com sucesso.");
            window.location.href = 'pagamento.html'; // Redireciona para a página de pagamento
        } catch (error) {
            console.error(error);
            alert("Erro ao cadastrar a obra.");
        }
    }

    async function getServicoDetails(servicoId) {
        const urlServicoDetails = `http://localhost:8080/servico/${servicoId}`;
        try {
            const response = await fetch(urlServicoDetails, {
                method: "GET",
                headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
            });

            if (!response.ok) {
                throw new Error(`Erro ao obter detalhes do serviço: ${response.statusText}`);
            }

            const servico = await response.json();
            console.log(servico);

            const containerPreco = document.querySelector(".areaConstruida");
 
           // Define o conteúdo do containerPreco primeiro
        containerPreco.innerHTML = `
        <label for="areaConstruida">Área a Ser Construída (${servico.unidadeMedida}):</label>
        <input type="number" id="areaConstruida" name="areaConstruida" step="0.01" required>
        <div id="precoObra">Preço da obra: </div>`;

    // Agora o elemento #areaConstruida está no DOM, então podemos selecioná-lo
    const inputArea = document.querySelector("#areaConstruida");
    inputArea.addEventListener('input', function () {
        const valorTotal = servico.preco * inputArea.value;
        document.getElementById("precoObra").innerText = `Preço da obra: ${valorTotal}`; });
    
            // Configurar o evento de clique para o botão "Avançar para Pagamento"
            const btnAvancarPagamento = document.getElementById("btnAvancarPagamento");
            if (btnAvancarPagamento) {
                btnAvancarPagamento.addEventListener('click', async function () {
                    const dataInicio = document.getElementById("dataInicio").value;
                    const areaConstruida = parseFloat(document.getElementById("areaConstruida").value);
                    const endereco = document.getElementById("endereco").value;
                    const observacaoCliente = document.getElementById("observacaoCliente").value;

                    console.log(observacaoCliente);

                    if (!dataInicio || isNaN(areaConstruida) || !endereco || !observacaoCliente) {
                        alert("Preencha todos os campos corretamente.");
                        return;
                    }

                    console.log(servico.idServico);

                    const valorTotal = servico.preco * areaConstruida;

                    const userId = localStorage.getItem("userId");

                    // Cria o objeto de dados da obra usando os detalhes do serviço
                    const data = {
                        nomeObra: servico.nomeServico,
                        dataInicio: dataInicio,
                        preco: valorTotal,
                        areaConstruida: areaConstruida,
                        endereco: endereco,
                        observacaoCliente: observacaoCliente,
                        servico: servico.idServico,
                        cliente: userId
                    };
                    console.log(data);
                    await cadastrarObra(data);
                });
            }
        } catch (error) {
            console.error(error);
            alert("Erro ao carregar os detalhes do serviço.");
        }
    }

    // Carregar detalhes do serviço ao carregar a página
    if (servicoId) {
        getServicoDetails(servicoId);
    } else {
        alert("ID do serviço não encontrado.");
    }
});