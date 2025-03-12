### 3.3.2 Processo 2 – GERENCIAMENTO DOS SERVIÇOS

O Processo de gerir os serviços pode ser melhorado ao disponibilizar ao fornecedor um só lugar onde é permitido efetuar o controle de todos os serviços que podem ser oferecidos em uma obra, esse controle consiste em 4 etapas, cadastro de um novo serviço no catálogo, , vizualização dos serviços existentes, edição de serviços previamente cadastrados e por fim a exclusão.

![Novo modelo](https://github.com/user-attachments/assets/d6013771-b6a0-47e0-b1a0-d0e4793b7685)



#### Detalhamento das atividades

**Cadastrar Serviço**:  
Fornecedor preenche um formulário contendo todos os dados que o serviço oferecido contém, por exemplo o que será feito e como será a forma de calcular o preço.

**Visualizar serviço**:  
O fornecedor consegue visualizar um seviço já cadastrado e todos os seus detalhes.

**Alterar informações já cadastradas**:  
O fornecedor caso necessite de fazer uma alteração em um serviço, nessa atividade é possível mudar todos os campos do serviço que já foram cadastrados anteriormente.


 **Cadastrar Serviço**

| **Campo**      | **Tipo**         | **Restrições**       | **Valor default** |
|----------------|------------------|----------------------|-------------------|
| Nome do Serviço| Caixa de Texto   | Até 32 caracteres    |                   |
| Preço          | Numérico         |                      |                   |
| Unidade de Medida | Seleção Única | Seleção Única        |                   |
| Descrição | Área de Texto         |                      |                   |

 **Comandos**

| **Comando**           | **Destino**                    | **Tipo**    |
|-----------------------|---------------------------------|-------------|
| Confirmar Cadastro    | Stand by      | cancel      |
| Cancelar    | Volta pra tela de visualização     | cancel      |


**Visualizar serviço**

| **Campo**                  | **Tipo**         | **Restrições**                       | **Valor default**  |
|----------------------------|------------------|--------------------------------------|--------------------|
| Nome do Serviço             | Caixa de Texto            | Somente leitura                      | Nome preenchido    |
| Preço          | Numérico         |                      |                   |
| Unidade de Medida | Seleção Única | Seleção Única        |                   |
| Descrição              | Caixa de Texto            | Somente leitura                      | Descrição preenchida    |

**Comandos** 

| **Comando**                 | **Destino**                   | **Tipo**               |
|-----------------------------|-------------------------------|------------------------|
| Editar |  Modal editar serviço     |   Cancel            |
| Excluir |  Excluir Serviço      |           |
| Cadastrar Servico |  Modal cadastrar serviço      |   Cancel            |

**Editar serviço**

| **Campo**                  | **Tipo**        | **Restrições**                      | **Valor default**  |
|----------------------------|-----------------|-------------------------------------|--------------------|
| Nome do Serviço             | Texto          |                      |                   |
| Preço                       | Numérico       |                      |                   |
| Descrição                   | Área de Texto  |                      |                   |
| Unidade de Medida           | Seleção Única  | Seleção Única        |                   |

**Comandos** 

| **Comando**                 | **Destino**                   | **Tipo**               |
|-----------------------------|-------------------------------|------------------------|
| Salvar alterações         | Stand by | Botão                  |
| Cancelar | visualizar serviço              | Botão                  |
