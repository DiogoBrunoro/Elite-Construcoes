### 3.3.4 Processo 4 – SOLICITAR OBRA

No processo de solicitação de obra, o cliente poderá selecionar o serviço que melhor atenda às suas necessidades. Em seguida, preencherá um formulário detalhado com as informações relevantes, como as dimensões e outros aspectos essenciais para a execução da obra. Após a confirmação do pagamento, a empresa iniciará o planejamento, notificando o cliente sobre a data de início dos trabalhos. A partir desse momento, o processo de compra será finalizado e a obra será executada conforme o planejamento.

![Processo4Atualizado](https://github.com/user-attachments/assets/038e7bf3-9ee1-4d25-9260-1719926c2777)

#### Detalhamento das atividades

**Selecionar Obra**<br>
Cliente escolhe qual obra deseja.

**Preencher Informações sobre a obra**<br>
Cliente deverá informar as informações sobre a obra, como por exemplo as dimensões ou alguma restrição que possa existir na obra.

**Preencher Informações pessoais de pagamento**<br>
O cliente irá preencher suas informações pessoais de pagamento e realizar o pagamento.

**Fim**<br>
O processo termina com o cliente adiquirindo a nova obra.


 **Selecionar Obra**

| **Campo**      | **Tipo**         | **Restrições**       | **Valor default** |
|----------------|------------------|----------------------|-------------------|

 **Comandos**

| **Comando**           | **Destino**                    | **Tipo**    |
|-----------------------|---------------------------------|-------------|
| Comprar Obra     | Direciona para Informações da Obra | default     |

 **Tela de Informações da Obra**

| **Campo**             | **Tipo**         | **Restrições**                        | **Valor default** |
|-----------------------|------------------|---------------------------------------|-------------------|
| Data de Início | Campo de Data    | Data válida no formato DD/MM/AAAA     |                   |
| Área a ser Construída | Seleção Única    | Seleção Única     |                   |
| Endereço   | Caixa de Texto   | Mínimo de 10 caracteres               |                   |
| Observação do cliente  | Caixa de Texto grande  | Mínimo de 20 caracteres               |                   | 

 **Comandos** 

| **Comando**           | **Destino**                        | **Tipo**    |
|-----------------------|-------------------------------------|-------------|
| Avançar para Pagamento| Direcionar para tela de pagamento   | default     |

**Informações Pessoais de Pagamento**

| **Campo**               | **Tipo**           | **Restrições**                       | **Valor default** |
|-------------------------|--------------------|--------------------------------------|-------------------|
| Número do Cartão         | Caixa de Texto     | Obrigatório, 16 dígitos              |                   |
| Nome no Cartão           | Caixa de Texto     | Obrigatório, como no cartão          |                   |
| Validade do Cartão       | Caixa de Texto     | Mês/ano (MM/AA)                      |                   |
| CVV                      | Caixa de Texto     | Obrigatório, 3 dígitos               |                   |

 **Comandos**

| **Comando**             | **Destino**                         | **Tipo**    |
|-------------------------|--------------------------------------|-------------|
| Concluir Pagamento       | Finaliza e processa o pagamento      | default     |
| Cancelar                | Retorna para última tela sem pagar   | cancel      |
