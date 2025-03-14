// Função para carregar os dados do fornecedor
async function carregarDadosfornecedor() {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        console.error("userId não encontrado no localStorage.");
        return; 
    }

    const token = localStorage.getItem("token");
    const myHeaders = new Headers();
    myHeaders.append("Authorization", `Bearer ${token}`);

    const urlFornecedor = `http://localhost:8080/fornecedor/${userId}`;
    const urlObrasFornecedor = `http://localhost:8080/obra/fornecedor/indicador/${userId}`;

    try {
        // Buscar dados do fornecedor
        const fornecedorResponse = await fetch(urlFornecedor, {
            method: "GET",
            headers: myHeaders,
            redirect: "follow"
        });

        if (!fornecedorResponse.ok) {
            throw new Error('Erro ao buscar dados do fornecedor: ' + fornecedorResponse.statusText);
        }

        const fornecedor = await fornecedorResponse.json();
        document.getElementById('nomeFornecedor').textContent = fornecedor.nomeFornecedor || '';
        document.getElementById('cnpj').textContent = fornecedor.cnpj || '';
        document.getElementById('endereco').textContent = fornecedor.endereco || '';
        document.getElementById('emailCorporativo').textContent = fornecedor.emailCorporativo || '';

        // Preencher os campos de edição
        document.getElementById('nomeFornecedorInput').value = fornecedor.nomeFornecedor || '';
        document.getElementById('cnpjInput').value = fornecedor.cnpj || '';
        document.getElementById('enderecoInput').value = fornecedor.endereco || '';

        // Buscar dados das obras
        const obrasResponse = await fetch(urlObrasFornecedor, {
            method: "GET",
            headers: myHeaders,
            redirect: "follow"
        });

        
        if (!obrasResponse.ok) {
            throw new Error('Erro ao buscar dados das obras: ' + obrasResponse.statusText);
        }

        const obras = await obrasResponse.json();
        console.log(obras);

        // Filtrar obras concluídas
        const obrasConcluidas = obras.filter(obra => obra.estado === "Concluida");
        const totalObras = obrasConcluidas.length;

        // Calcular obras no prazo e atrasadas
        const obrasNoPrazo = obrasConcluidas.filter(obra => obra.noPrazo === true).length;
        const obrasAtrasadas = obrasConcluidas.filter(obra => obra.noPrazo === false).length;

        // Calcular taxas
        const taxaEntrega = totalObras > 0 ? (obrasNoPrazo / totalObras) * 100 : 0;
        const taxaAtraso = totalObras > 0 ? (obrasAtrasadas / totalObras) * 100 : 0;

        // Exibir taxas no perfil
        document.getElementById('taxaEntrega').textContent = `${taxaEntrega.toFixed(2)}%`;
        document.getElementById('taxaAtraso').textContent = `${taxaAtraso.toFixed(2)}%`;

    } catch (error) {
        console.error('Erro ao carregar os dados do fornecedor:', error);
    }
}


// Chama a função para carregar os dados ao carregar a página
document.addEventListener('DOMContentLoaded', function() {
    carregarDadosfornecedor();

    // Evento de clique no botão de salvar
    const btnSalvar = document.getElementById('editarPerfil');
    if (btnSalvar) {
        btnSalvar.addEventListener("click", async function (e) {
            e.preventDefault();
            await editarPerfil(); 
        });
    } else {
        console.error("Botão 'editarPerfil' não encontrado no DOM.");
    }
});

async function editarPerfil() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem("token");
    
    // Verifica se o userId e o token estão disponíveis
    if (!userId || !token) {
        console.error("userId ou token não encontrados no localStorage.");
        return; // Impede a execução se não houver userId ou token
    }

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", `Bearer ${token}`);

    // Obtém os valores dos campos de entrada
    const nomeFornecedor = document.getElementById('nomeFornecedorInput').value; // Campo para nome do fornecedor
    const cnpj = document.getElementById('cnpjInput').value; // Campo para CNPJ
    const endereco = document.getElementById('enderecoInput').value; // Campo para endereço
    const senhaFornecedor = document.getElementById('senhaInput').value; // Campo para senha
    const confirmarSenha = document.getElementById('senhaConfirmar').value; // Campo para confirmar senha

    // Verifica se a senha e a confirmação de senha são iguais
    if (senhaFornecedor !== confirmarSenha) {
        alert("A senha e a confirmação de senha devem ser iguais.");
        return; // Impede a execução se as senhas não coincidirem
    }

    // Monta o objeto JSON com os campos corretos
    const raw = JSON.stringify({
        "nomeFornecedor": nomeFornecedor, 
        "cnpj": cnpj, 
        "endereco": endereco, 
        ...(senhaFornecedor ? { "senhaFornecedor": senhaFornecedor } : {})
    });

    console.log(raw, "raw");

    const urledicaoPerfil = `http://localhost:8080/fornecedor/${userId}`;

    const requestOptions = {
        method: "PUT",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };

    try {
        const response = await fetch(urledicaoPerfil, requestOptions);
        
        if (!response.ok) {
            throw new Error('Erro ao atualizar perfil: ' + response.statusText);
        }
        
        const result = await response.text(); 
        console.log(response);
        console.log(result);
        alert("Perfil atualizado com sucesso");
        window.location.reload(); 
    } catch (error) {
        console.error('Erro ao editar perfil:', error);
    }
}


async function excluirPerfil() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem("token");

    // Verifica se o userId e o token estão disponíveis
    if (!userId || !token) {
        console.error("userId ou token não encontrados no localStorage.");
        return; // Impede a execução se não houver userId ou token
    }

    const myHeaders = new Headers();
    myHeaders.append("Authorization", `Bearer ${token}`);

    const urlExcluirPerfil = `http://localhost:8080/fornecedor/delete/${userId}`;

    const requestOptions = {
        method: "DELETE",
        headers: myHeaders,
        redirect: "follow"
    };

    try {
        const response = await fetch(urlExcluirPerfil, requestOptions);
        
        if (!response.ok) {
            throw new Error('Erro ao excluir perfil: ' + response.statusText);
        }

        alert("Perfil excluído com sucesso");
        // Opcional: redirecionar para a página de login ou página inicial
        window.location.href = 'login.html'; // Altere para a página desejada após exclusão

    } catch (error) {
        console.error('Erro ao excluir perfil:', error);
    }
}

// Evento de clique no botão de excluir
const btnExcluir = document.getElementById('confirmarExclusao');
if (btnExcluir) {
    btnExcluir.addEventListener("click", async function (e) {
        e.preventDefault();
        await excluirPerfil(); 
    });
} else {
    console.error("Botão 'confirmarExclusao' não encontrado no DOM.");
}

