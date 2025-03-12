// Função para concluir o pagamento
async function concluirPagamento() {
    // Seleciona o formulário de pagamento e os valores dos campos
    const pagamentoForm = document.getElementById('pagamentoForm');

    const numeroCartao = document.getElementById('numeroCartao').value;
    const nomeCartao = document.getElementById('nomeCartao').value;
    const validadeCartao = document.getElementById('validadeCartao').value;
    const cvv = document.getElementById('cvv').value;

    // Verifica se o formulário é válido
    if (!pagamentoForm.checkValidity()) {
        alert("Preencha todos os campos obrigatórios corretamente.");
        return;
    }

    // Dados do pagamento em formato JSON
    const pagamentoData = {
        numeroCartao: numeroCartao,
        nomeCartao: nomeCartao,
        validadeCartao: validadeCartao,
        cvv: cvv
    };

    try {
        // Envia os dados ao backend
        const response = await fetch('http://localhost:8080/pagamento', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}` // Token de autenticação
            },
            body: JSON.stringify(pagamentoData)
        });

        // Verifica a resposta do servidor
        if (!response.ok) {
            throw new Error(`Erro no pagamento: ${response.statusText}`);
        }

        // Confirma o pagamento e redireciona
        alert("Pagamento concluído com sucesso!");
        window.location.href = 'home.html'; // Redireciona para a página de confirmação

    } catch (error) {
        console.error("Erro ao processar o pagamento:", error);
        alert("Erro ao concluir o pagamento. Tente novamente.");
    }
}
