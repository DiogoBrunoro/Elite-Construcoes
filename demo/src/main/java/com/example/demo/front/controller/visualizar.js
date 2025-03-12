document.addEventListener('DOMContentLoaded', function () {
    console.log("DOM completamente carregado e analisado.");

    // Configuração inicial
    const container = document.getElementById('tela');
    const formulario = document.getElementById("cadastroServicoForm");
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");
    let currentServicoId = null; // Armazena o ID do serviço atual para edição

    // Verificação do formulário de cadastro
    if (!formulario) {
        console.error("Formulário de cadastro não encontrado.");
        return;
    }

    // Função para carregar e exibir serviços
    async function getServicos() {
        const urlServicos = `http://localhost:8080/servico/fornecedor/${userId}`;
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
                                <button class="btn btn-primary btn-block btn-editar mb-2" data-servico-id="${servico.idServico}" data-bs-toggle="modal" data-bs-target="#modalEditarServico">Editar</button>
                                <button class="btn btn-danger btn-excluir" data-servico-id="${servico.idServico}">Excluir</button>
                            </div>
                        </div>
                    </div>`;
            });
            attachButtons();
        } catch (error) {
            console.error(error);
        }
    }

    // Função para carregar dados do serviço para edição
    async function carregarServicoParaEdicao(servicoId) {
        const urlServico = `http://localhost:8080/servico/${servicoId}`;
        try {
            const response = await fetch(urlServico, {
                method: "GET",
                headers: { Authorization: `Bearer ${token}` }
            });

            if (!response.ok) {
                throw new Error(`Erro ao carregar serviço: ${response.statusText}`);
            }

            const servico = await response.json();
            currentServicoId = servico.idServico;
            document.getElementById("nomeServico").value = servico.nomeServico;
            document.getElementById("precoServico").value = servico.preco;
            document.getElementById("unidadeMedida").value = servico.unidadeMedida;
            document.getElementById("descricao").value = servico.descricao;
        } catch (error) {
            console.error(error);
        }
    }

    // Submissão do formulário para cadastro ou edição
    formulario.addEventListener('submit', async function (event) {
        event.preventDefault();

        const nome = document.getElementById("nomeServico").value;
        const preco = parseFloat(document.getElementById("precoServico").value);
        const unidadeMedida = document.getElementById("unidadeMedida").value;
        const descricao = document.getElementById("descricao").value;

        if (!nome || isNaN(preco) || !unidadeMedida || !descricao) {
            alert("Preencha todos os campos corretamente.");
            return;
        }

        const data = {
            nomeServico: nome,
            preco: preco,
            unidadeMedida: unidadeMedida,
            descricao: descricao,
            idFornecedor: userId
        };

        if (currentServicoId) {
            await editarServico(currentServicoId, data);
        } else {
            await cadastrarServico(data);
        }
    });

    // Função para cadastrar um novo serviço
    async function cadastrarServico(data) {
        const urlCadastroServico = 'http://localhost:8080/servico';
        try {
            const response = await fetch(urlCadastroServico, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`
                },
                body: JSON.stringify(data)
            });

            if (!response.ok) {
                throw new Error(`Erro ao cadastrar serviço: ${response.statusText}`);
            }

            alert("Cadastro realizado com sucesso");
            getServicos();
            resetFormulario();
        } catch (error) {
            console.error(error);
        }
    }

    // Função para editar um serviço
    async function editarServico(servicoId) {
        const token = localStorage.getItem("token");
        const urlEditarServico = `http://localhost:8080/servico/${servicoId}`;
    
        if (!token) {
            console.error("Token não encontrado no localStorage.");
            return;
        }
    
        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        myHeaders.append("Authorization", `Bearer ${token}`);
    
        // Use os novos IDs do modal de edição
        const nomeServico = document.getElementById('editNomeServico').value;
        const descricaoServico = document.getElementById('editDescricao').value;
        const precoServico = parseFloat(document.getElementById('editPrecoServico').value);
        const unidadeMedida = document.getElementById('editUnidadeMedida').value;
    
        const data = JSON.stringify({
            nomeServico,
            descricao: descricaoServico,
            preco: precoServico,
            unidadeMedida
        });
    
        const requestOptions = {
            method: "PUT",
            headers: myHeaders,
            body: data,
            redirect: "follow"
        };
    
        try {
            const response = await fetch(urlEditarServico, requestOptions);
    
            if (!response.ok) {
                const errorData = await response.json();
                console.error("Erro ao editar serviço:", errorData);
                throw new Error(`Erro ao editar serviço: ${errorData.message || response.statusText}`);
            }
    
            alert("Serviço editado com sucesso");
            await getServicos();
            resetFormulario();
        } catch (error) {
            console.error("Erro ao editar serviço:", error);
            alert("Erro ao editar serviço. Verifique o console para mais detalhes.");
        }
    }


    // Função para excluir um serviço
    async function excluirServico(servicoId) {
        const urlExcluirServico = `http://localhost:8080/servico/delete/${servicoId}`;
        try {
            const response = await fetch(urlExcluirServico, {
                method: "DELETE",
                headers: { Authorization: `Bearer ${token}` }
            });

            if (!response.ok) {
                throw new Error(`Erro ao excluir serviço: ${response.statusText}`);
            }

            alert("Serviço excluído com sucesso");
            getServicos();
        } catch (error) {
            console.error(error);
            alert("Erro ao excluir serviço. Tente novamente.");
        }
    }

    // Função para anexar eventos de clique aos botões de editar e excluir
    function attachButtons() {
        document.querySelectorAll('.btn-editar').forEach(function (btn) {
            btn.addEventListener('click', async function () {
                const servicoId = this.getAttribute('data-servico-id');
                await carregarServicoParaEdicao(servicoId);
            });
        });

        document.querySelectorAll('.btn-excluir').forEach(function (btn) {
            btn.addEventListener('click', async function () {
                const servicoId = this.getAttribute('data-servico-id');
                await excluirServico(servicoId);
            });
        });

        // Dispara o submit do formulário ao clicar em "Salvar Alterações"
        document.getElementById("editarPerfil").addEventListener("click", function () {
            formulario.dispatchEvent(new Event('submit'));
        });
    }

    // Função para resetar o formulário
    function resetFormulario() {
        formulario.reset();
        currentServicoId = null;
    }

    // Carrega os serviços quando a página é carregada
    getServicos();
});