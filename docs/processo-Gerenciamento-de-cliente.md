### 3.3.1 Processo 1 – Gerenciamento de cliente

O processo de gerenciamento de usuário tem como objetivo simplificar o processo de gestão de perfil do cliente e do fornecerdor. Durante esse processo, o cliente tem a capacidade de criar um perfil ou então logar para poder realizar qualquer alteração no perfil que deseja ser feita, como a alteração de algum dado cadastral, tal como  nome, senha ou nome de usuário e exlcuir o usuário caso deseje remove-ló.

![Exemplo de um Modelo BPMN do PROCESSO 1](images/Gerenciamento-de-usuário.png "Modelo BPMN do Processo 1.")

#### Detalhamento das atividades

**Logar**<br>
Usuário preenche dados de login

**Preencher dados de cadastro**<br>
Usuário preenche os dados necessários para a realização do cadastro

**Acessar tela de gestão de perfil** <br>
O usuário acessa a tela de gestão de perfil

**Editar perfil**<br>
Caso tenha selecionado essa opção, o usuário edita os dados que deseja alterar do seu perfil

**Excluir usuário**<br>
Caso tenha selecionado essa opção, o usuário exclui seu perfil 
<br>
<br>


 
**Logar**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Email           | Caixa de texto | formato de e-mail|                   |
| senha           | Caixa de Texto   | mínimo de 8 caracteres |           |
| Tipo-Usuario           | Seleção Única   | Seleção Única |           |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| logar               | Verificar dados digitados             | default     |
| Voltar                 | Usuário retorna para última tela                            |cancel               |

**Preencher dados de cadastro**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome Completo| Caixa de texto |                |                   |
| Nome de usuário | Caixa de texto |                |                   |
| Data de nascimento | Data |  Formato de data              |                   |
| Email | Caixa de texto |  Formato de email              |                   |
| Senha | Caixa de texto | mínimo 8 caracteres            |                   |
| Confirmar senha | Caixa de texto |  Ser igual a senha              |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Cadastrar| Validar dados do cadastro | default |
| Alterar cadastro para Fornecedor | cadastro de fornecedor | default |

**Acessar tela de gestão de perfil**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome Completo| Caixa de texto |                |                   |
| Nome de usuário | Caixa de texto |                |                   |
| Data de nascimento | Data |  Formato de data              |                   |
| Email | Caixa de texto |  Formato de email              |                   |

**Editar dados**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome Completo| Caixa de texto |                |                   |
| Nome de usuário | Caixa de texto |                |                   |
| Data de nascimento | Data |  Formato de data              |                   |
| Senha | Caixa de texto | mínimo 8 caracteres            |                   |
| Confirmar senha | Caixa de texto |  Ser igual a senha              |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Editar Perfil| Validar alterações do perfil | default |
| Salvar Alterações | Tela com visualização de informações | default |
| Cancelar| Tela com visualização de informações | default |

**Excluir usuário**

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Excluir Perfil| Abrir confirmação de ação | default |
| Excluir | Confimar exclusão do perfil | default |
| Cancelar | Sair da tela de edição de cadastro | cancel |








