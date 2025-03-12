// Inicialização
window.onload = function () {
    visualizarGestaoDeObra();
};

let graficoProgresso; // Referência ao gráfico
let obra; // Armazena os dados da obra

// Função principal para visualizar a gestão de obra
async function visualizarGestaoDeObra() {
    let urlParams = new URLSearchParams(window.location.search);
    let obraId = urlParams.get('id');

    // Verifique se o ID está na URL; caso contrário, tente recuperá-lo do localStorage
    if (!obraId) {
        obraId = localStorage.getItem('obraId');
        if (!obraId) {
            alert("ID da obra não encontrado.");
            return;
        }
    } else {
        // Salve o ID no localStorage para uso futuro
        localStorage.setItem('obraId', obraId);
    }

    try {
        // Obter informações da obra
        obra = await fetchObra(obraId);
        console.log(obra);

        // Verificar a data de término
        verificarDataFim();

        // Obter gestões de obra
        const gestoesDeObra = await fetchGestoesDeObra(obraId);
        console.log(gestoesDeObra);

        // Ordenar todas as datas em ordem cronológica
        const todasAsDatas = organizarGestoesPorData(gestoesDeObra);

        // Exibir o gráfico
        exibirGrafico(todasAsDatas);

        // Exibir detalhes da última gestão
        exibirUltimaGestao(todasAsDatas);

        // Atualizar progresso temporal com base nas datas de início e fim
        if (obra.dataInicio && obra.dataFim) {
            atualizarProgressoTemporal(obra.dataInicio, obra.dataFim);
        }

    } catch (error) {
        console.error("Erro ao visualizar gestão de obra:", error);
        alert(error.message);
    }
}

// Função para verificar se a obra possui dataFim
function verificarDataFim() {
    const modal = document.getElementById("dataFimModal");
    if (!obra.dataFim) {
        modal.style.display = "block"; // Mostrar modal
    } else {
        modal.style.display = "none"; // Ocultar modal
    }
}

// Obter informações da obra
async function fetchObra(obraId) {
    const response = await fetch(`http://localhost:8080/obra/${obraId}`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    });

    if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Erro ao buscar dados da obra.");
    }

    return response.json();
}

// Obter gestões de obra
async function fetchGestoesDeObra(obraId) {
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

    return response.json();
}

// Ordenar gestões de obra por data (incluindo dataInicio e dataFim)
function organizarGestoesPorData(gestoesDeObra) {
    const todasAsDatas = [];

    // Adicionar início da obra
    todasAsDatas.push({
        data: obra.dataInicio,
        progresso: 0,
        comentario: "Início da obra"
    });

    // Adicionar as gestões
    gestoesDeObra.forEach(gestao => {
        todasAsDatas.push({
            data: gestao.dataAtualizacao,
            progresso: gestao.barraProgresso,
            comentario: gestao.comentario || "Sem comentários"
        });
    });

    // Adicionar término da obra (se existir)
    if (obra.dataFim) {
        todasAsDatas.push({
            data: obra.dataFim,
            progresso: 0,
            comentario: "Término da obra"
        });
    }

    // Ordenar as datas em ordem cronológica
    todasAsDatas.sort((a, b) => new Date(a.data) - new Date(b.data));

    return todasAsDatas;
}

// Exibir o gráfico
function exibirGrafico(todasAsDatas) {
    const ctx = document.getElementById('graficoProgresso').getContext('2d');

    const labels = [];
    const dadosProgresso = [];
    const comentarios = [];
    let progressoAcumulado = 0;
    let indexDataFim = -1;

    todasAsDatas.forEach((item, index) => {
        progressoAcumulado += item.progresso;
        labels.push(formatarDataISO(item.data));
        dadosProgresso.push(progressoAcumulado);
        comentarios.push(item.comentario);

        // Identificar o índice da dataFim
        if (item.data === obra.dataFim) {
            indexDataFim = index;
        }
    });

    // Destruir gráfico anterior, se existir
    if (graficoProgresso) {
        graficoProgresso.destroy();
    }

    // Criar novo gráfico
    graficoProgresso = new Chart(ctx, {
        type: 'line',
        data: {
            labels,
            datasets: [{
                label: 'Progresso Acumulado da Obra',
                data: dadosProgresso,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 2,
                pointBackgroundColor: labels.map((_, idx) =>
                    idx === indexDataFim ? 'red' : 'rgba(75, 192, 192, 1)' // Vermelho para dataFim
                ),
                pointRadius: labels.map((_, idx) =>
                    idx === indexDataFim ? 8 : 4 // Raio maior para dataFim
                ),
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            scales: {
                x: { 
                    title: { 
                        display: true, 
                        text: 'Data de Atualização', 
                        color: 'white' // Define o texto como branco
                    },
                    ticks: {
                        color: 'white' // Cor dos rótulos no eixo X
                    }
                },
                y: { 
                    beginAtZero: true, 
                    title: { 
                        display: true, 
                        text: 'Progresso Acumulado (%)', 
                        color: 'white' // Define o texto como branco
                    },
                    ticks: {
                        color: 'white' // Cor dos rótulos no eixo Y
                    }
                }
            },
            plugins: {
                legend: {
                    labels: {
                        color: 'white' // Cor dos rótulos na legenda
                    }
                }
            }
        }
        
    });
}



// Exibir última gestão
function exibirUltimaGestao(todasAsDatas) {
    if (todasAsDatas.length > 0) {
        let progressoAcumulado = 0;

        // Calcular progresso acumulado
        todasAsDatas.forEach(item => {
            progressoAcumulado += item.progresso;
        });

        // Encontrar a última gestão válida (excluindo a data de fim, se existir)
        const ultimaGestaoValida = todasAsDatas.reverse().find(item => item.data !== obra.dataFim);

        if (ultimaGestaoValida) {
            document.getElementById("gestaoDeObraDetails").style.display = "block";
            document.getElementById("displayDataAtualizacao").innerText = formatarDataISO(ultimaGestaoValida.data) || "Não informado";
            document.getElementById("displayBarraProgresso").innerText = progressoAcumulado.toFixed(2) || "0"; // Mostrar progresso acumulado
            document.getElementById("displayComentario").innerText = ultimaGestaoValida.comentario || "Sem comentários";
        } else {
            document.getElementById("gestaoDeObraDetails").style.display = "none";
        }
    } else {
        document.getElementById("gestaoDeObraDetails").style.display = "none";
    }
}


// Função para formatar a data no formato DD/MM/YYYY
function formatarDataISO(data) {
    const date = new Date(data);
    const offsetMs = date.getTimezoneOffset() * 60 * 1000; // Deslocamento do fuso horário em milissegundos
    const correctedDate = new Date(date.getTime() + offsetMs);
    return `${String(correctedDate.getDate()).padStart(2, '0')}/${String(correctedDate.getMonth() + 1).padStart(2, '0')}/${correctedDate.getFullYear()}`;
}

// Função para atualizar o progresso temporal
function atualizarProgressoTemporal(dataInicio, dataFim) {
    const hoje = new Date();
    const inicio = new Date(dataInicio);
    const fim = new Date(dataFim);

    // Calcular a porcentagem do progresso temporal
    const tempoTotal = fim - inicio;
    const tempoDecorrido = hoje - inicio;

    let progressoTemporal = 0;

    if (tempoDecorrido > 0 && tempoDecorrido < tempoTotal) {
        progressoTemporal = (tempoDecorrido / tempoTotal) * 100;
    } else if (tempoDecorrido >= tempoTotal) {
        progressoTemporal = 100;
    }

    // Exibir progresso temporal
    document.getElementById("progressoTemporal").innerText = `Progresso Temporal: ${progressoTemporal.toFixed(2)}%`;
}

// Função para salvar a data de fim
document.getElementById("formDataFim").addEventListener("submit", async function (event) {
    event.preventDefault(); // Impedir o envio padrão do formulário
    const urlParams = new URLSearchParams(window.location.search);
    const obraId = urlParams.get('id'); // ID da obra
    const dataFim = document.getElementById("inputDataFim").value; // Obter o valor da data
    if (!dataFim) {
        alert("Por favor, informe a data de fim.");
        return;
    }
    try {
        // Requisição para atualizar a obra com a data de fim
        const responseAtualizar = await fetch(`http://localhost:8080/obra/${obraId}`, {
            method: "PUT",
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nomeObra: obra.nomeObra,
                descricao: obra.descricao,
                endereco: obra.endereco,
                dataInicio: obra.dataInicio,
                dataFim: dataFim,
                observacaoCliente: obra.observacaoCliente, 
                areaConstruida: obra.areaConstruida,       
                preco: obra.preco    
            })
        });
        if (responseAtualizar.ok) {
            alert("Data de fim atualizada com sucesso.");
            window.location.reload();
        } else {
            throw new Error("Erro ao atualizar data de fim.");
        }
    } catch (error) {
        console.error("Erro ao atualizar obra:", error);
        alert(error.message);
    }
});
document.getElementById("gestaoDeObraForm").addEventListener("submit", async function (event) {
    event.preventDefault(); // Evitar o envio padrão do formulário

    let urlParams = new URLSearchParams(window.location.search);
    let obraId = urlParams.get('id');

    // Verifique se o ID está na URL; caso contrário, tente recuperá-lo do localStorage
    if (!obraId) {
        obraId = localStorage.getItem('obraId');
        if (!obraId) {
            alert("ID da obra não encontrado.");
            return;
        }
    } else {
        // Salve o ID no localStorage para uso futuro
        localStorage.setItem('obraId', obraId);
    }

    const gestaoDeObraData = {
        dataAtualizacao: document.getElementById("dataAtualizacao").value, // Valor bruto da data
        barraProgresso: parseFloat(document.getElementById("barraProgresso").value) || 0,
        comentario: document.getElementById("comentario").value,
        obra: {
            idObra: obraId
        }
    };

    try {
        const response = await fetch("http://localhost:8080/gestaoDeObra", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            body: JSON.stringify(gestaoDeObraData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Erro ao salvar a gestão de obra.");
        }

        alert("Gestão de obra salva com sucesso!");
        document.getElementById("gestaoDeObraForm").reset();

        // Atualizar a visualização
        visualizarGestaoDeObra();

    } catch (error) {
        console.error("Erro ao salvar gestão de obra:", error);
        alert("Erro ao salvar gestão de obra.");
    }
});
