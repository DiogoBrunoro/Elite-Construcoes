// Função chamada ao carregar a página
document.addEventListener("DOMContentLoaded", async () => {
    try {
        let urlParams = new URLSearchParams(window.location.search);
        let obraId = urlParams.get('id');

        if (!obraId) {
            obraId = localStorage.getItem('obraId');
            if (!obraId) {
                alert("ID da obra não encontrado.");
                return;
            }
        } else {
            localStorage.setItem('obraId', obraId);
        }

        // Buscar os dados da obra
        const obraData = await fetchObra(obraId);

        // Atualizar o botão com base no estado da obra
        if (obraData.estado === "Concluida") {
        }
    } catch (error) {
        console.error("Erro ao carregar dados da obra:", error);
        alert(error.message || "Erro ao carregar dados da obra.");
    }
});

async function concluirObra() {
    let urlParams = new URLSearchParams(window.location.search);
    let obraId = urlParams.get('id');

    if (!obraId) {
        obraId = localStorage.getItem('obraId');
        if (!obraId) {
            alert("ID da obra não encontrado.");
            return;
        }
    } else {
        localStorage.setItem('obraId', obraId);
    }

    try {
        // Buscar os dados da obra
        const obraData = await fetchObra(obraId);

        // Verificar se a obra já está concluída
        if (obraData.estado === "Concluida") {
            alert("A obra já está concluída.");
            return;
        }

        // Verificar gestões de obra
        const hasInvalidGestao = await verificarGestoesDeObra(obraId, obraData.dataFim);

        // Atualizar o estado da obra para 'Concluida'
        await atualizarEstado(obraId);

        // Atualizar 'noPrazo' com base na validação das gestões
        if (!hasInvalidGestao) {
            await atualizarNoPrazo(obraId);
            alert("Obra concluída com sucesso e marcada como entregue no prazo.");
        } else {
            alert("Obra concluída, mas não foi marcada como entregue no prazo devido a gestões posteriores à data de término.");
        }

        // Atualizar botão após concluir a obra

    } catch (error) {
        console.error("Erro ao concluir obra:", error);
        alert(error.message || "Erro ao concluir obra.");
    }
}

// Funções auxiliares
async function fetchObra(obraId) {
    const response = await fetch(`http://localhost:8080/obra/${obraId}`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || `Erro ao buscar dados da obra. Status: ${response.status}`);
    }

    return response.json();
}

async function verificarGestoesDeObra(obraId, dataFim) {
    const response = await fetch(`http://localhost:8080/gestaoDeObra/obra/${obraId}`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Erro ao buscar gestões de obra.");
    }

    const gestoes = await response.json();

    // Verificar se existe gestão com data maior que dataFim
    return gestoes.some(gestao => new Date(gestao.dataAtualizacao) > new Date(dataFim));
}

async function atualizarEstado(obraId) {
    const response = await fetch(`http://localhost:8080/obra/${obraId}/estado`, {
        method: "PUT",
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ estado: "Concluida" })
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || `Erro ao atualizar estado da obra. Status: ${response.status}`);
    }
}

async function atualizarNoPrazo(obraId) {
    const response = await fetch(`http://localhost:8080/obra/${obraId}/noPrazo`, {
        method: "PUT",
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ noPrazo: true })
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || `Erro ao atualizar 'noPrazo' da obra. Status: ${response.status}`);
    }
}

/* substituirBotaoConcluirPorExcluir() {
    const concluirButton = document.querySelector(".btnConcluirObra");
    if (concluirButton) {
        concluirButton.textContent = "Excluir obra";
        concluirButton.onclick = excluirObra;
    }
}

// Função para excluir a obra
async function excluirObra() {
    let urlParams = new URLSearchParams(window.location.search);
    let obraId = urlParams.get('id') || localStorage.getItem('obraId');

    if (!obraId) {
        alert("ID da obra não encontrado.");
        return;
    }

    try {
        const resposta = confirm("Tem certeza de que deseja excluir esta obra?");
        if (!resposta) return;

        const response = await fetch(`http://localhost:8080/obra/${obraId}`, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`
            }
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || `Erro ao excluir obra. Status: ${response.status}`);
        }

        alert("Obra excluída com sucesso.");
        window.location.href = "../pages/inicio.html"; // Redirecionar para a página de obras
    } catch (error) {
        console.error("Erro ao excluir obra:", error);
        alert(error.message || "Erro ao excluir obra.");
    }
} */
