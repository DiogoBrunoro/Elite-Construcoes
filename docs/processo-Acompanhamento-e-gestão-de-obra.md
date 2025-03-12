### 3.3.3 Processo 3 – Acompanhamento e gestão da obra

Nesse processo a empresa oferece ao seu cliente um possibilidade de acompanhamento da obra e o esclarecimento de dúvidas. Com a atualização sobre a progressão da obra pelo fornecedor, o cliente receberá uma notificação sobre novas informações adicionadas sobre a atividade.

![Exemplo de um Modelo BPMN do PROCESSO 3](https://github.com/user-attachments/assets/6ff7363c-729d-4ab5-bbd0-4a1a4268082f)


#### Detalhamento das atividades

**Cadastrar gestão de obra**<br>
Nessa tela o fornecedor irá preencher as informações sobre a nova gestão de obra que está sendo inicializada

**Visualizar gestão de Obra**<br>
O forncedor poderá ver o andamento do gráfico e as informações que foram preenchidas anteriormente

**Analisar progresso da obra/Visualizar Gestão de Obra**<br>
O cliente irá visualizar e ter acesso as informações de como está o andamento da sua obra solicitada.


**Cadastrar gestão de obra**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Data de atualização      | Data   |                |                   |
| Barra de progresso | Double        | Porcentagem    |                   |
| Comentário | Caixa de texto   |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---      |
| Salvar gestão de obras   |                            | Botão  |
| Concluir Obra   |                            | Botão  |

**Visualizar gestão de Obra**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Data atualização | data        | Apenas visualização |              |
| Progresso | Double        |Apenas visualização    |                   |
| Comentário | Caixa de texto   |   Apenas visualização            |                   |

**Analisar progresso da obra/Visualizar Gestão de Obra**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Data atualização | data        | Apenas visualização |              |
| Barra de progresso | Double        |Apenas visualização    |                   |
| Comentário | Caixa de texto   |   Apenas visualização            |                   |
