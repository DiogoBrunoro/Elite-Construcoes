# ELITE CONSTRUÇÕES


**Bernardo Melgaço Soares, bemelgaco13galo@gmail.com**

**Diogo Caribé Brunouro, diogocaribebrunoro@gmail.com**

**Lucas Ferreira Ribeiro, lucasfribeiro2005@gmail.com**

**Paulo Henrique Fonseca de Assis, passis@sga.pucminas.br**

**Pedro Henrique Maia Alves, pedro.alves.1515716@sga.pucminas.br**

**Pedro Rodrigues Duarte, artedudurty@gmail.com**

---

Professores:

**Aline Norberta de Brito**

**Eveline Alonso Veloso**

**Juliana Amaral Baroni de Carvalho**

---

_Curso de Engenharia de Software_

Instituto de Informática e Ciências Exatas – Pontifícia Universidade Católica de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil


O trabalho apresenta uma solução tecnológica para otimizar a gestão de obras no setor da construção civil. Desenvolvemos um sistema de software que integra funcionalidades para cadastro e acompanhamento de obras e gestão de usuários (clientes e fornecedores). O objetivo principal é centralizar e simplificar o gerenciamento de projetos, oferecendo aos clientes maior transparência sobre o andamento de suas obras e aos fornecedores ferramentas eficazes para organizar serviços e prazos, como gráficos que atulaizados com novos dados permitem supervisão contínua e entendimento claro. Como resultado, a solução proposta melhora a clareza, reduz atrasos e aumenta a satisfação dos clientes ao proporcionar maior controle e nitidez no processo de construção.



## 1. Introdução

Nós da Elite contruções, temos como objetivo oferecer um sistema para empreiteiras gerir eficientemente suas obras e também dispoibilizar ao cliente um feedback contínuo do seu andamento.

### 1.1 Contextualização

O setor da construção civil é essencial para o desenvolvimento urbano e a infraestrutura de um país. As empreiteiras, especializadas em serviços como terraplenagem e construção de grandes estruturas, desempenham um papel fundamental nesse processo.
A Elite Construções atua como uma aliada das empreiteiras e construtoras, oferecendo um software de gestão de obras, onde pode organizar trabalhos que vão desde a preparação de terrenos até a execução de grandes projetos. De acordo com a notícia feita pela "Visor Notícias" sobre o futuro do cenário imobiliário[1.1], o mercado de empreiteiras está em crescimento, impulsionado pela demanda por novos empreendimentos e pela modernização da infraestrutura.
Este trabalho examinará o impacto da Elite Construções no setor da construção civil, destacando como contribui para o desenvolvimento urbano e os desafios que enfrenta.


### 1.2 Problema

No mercado atual da construção civil, muitos clientes enfrentam grandes desafios quando se trata de gerenciar suas obras de maneira eficaz. Uma das principais dificuldades está na organização e no acompanhamento do progresso das construções. Muitas vezes, os clientes não têm acesso fácil a informações detalhadas sobre o andamento dos trabalhos, o que pode gerar incertezas e frustrações. Além disso, a comunicação entre os envolvidos no projeto, como engenheiros, arquitetos, fornecedores e clientes, é frequentemente ineficaz e fragmentada, o que resulta em falta de clareza sobre o status das atividades e a execução das etapas planejadas.
Esses problemas são bastante comuns e refletem uma realidade recorrente no cotidiano de quem lida com obras. Como mencionado no blog da Banib [1.2], muitas pessoas têm extrema dificuldade em estabelecer diálogos rápidos e eficazes durante o processo de construção. Isso inclui desde a troca de informações entre os responsáveis até o acesso simplificado a atualizações sobre o desenvolvimento do projeto. A falta de uma comunicação eficiente, de uma gestão clara, concisa e resumiada e de um fluxo de informações acessível pode resultar em mal-entendidos, atrasos e até mesmo em custos adicionais, comprometendo a satisfação do cliente e o sucesso do projeto como um todo.

### 1.3 Objetivo geral

O objetivo deste trabalho é desenvolver um sistema onde nosso software a irá otimizar e facilitar o gerenciamento de projetos e propor um sistema de atendimento, feedback e suporte ao cliente eficaz. 

#### 1.3.1 Objetivos específicos

Na aba gerenciamento de usuário, podem ser cadastrados tanto os perfis de clientes, que podem acompanhar o andamento da obra, quanto os perfis de fornecedores, como o engenheiro, que pode modificar o andamento da obra por exemplo e ter acesso aos suas diversas obras.

Na gerenciamento dos serviço, é onde podemos cadastrar os diferentes serviços disponibilizados no catálogo. Nele, é possível registrar o tipo de serviço oferecido, vizualizar serviços já cadastrados, possui também a disponibilidade de editar também e por fim a exclusão caso o serviço não seja mais oferecido por exemplo. Essa tela deve ser preenchida pelo fornecedor.

No acompanhamento e gestão de obra, após o início de uma obra, é nessa tela onde o cliente vizualiza como está o andamento que é atualizado continuamente pelo responsável da mesma. Essa tela pode ser visualizada pelo cliente e editada pelo fornecedor.

Por fim em solicitação da obra é onde o cliente solicita uma obra, escolhendo os serviços dela e finaliza a compra, que por sua vez chega a empresa e é gerado um planejamento, que volta ao cliente para analisar se está condizente com o que necessita, caso esteja as obras se iniciam.

### 1.4 Justificativas

O objetivo de nosso trabalho é facilitar a gestão de obras e propor um sistema de feedback do andamento da obra para o cliente, pois é um problema recorrente entre o fornecedor disponibilizar algum tipo de progresso ao cliente. A Elite construções preza por um melhor entendimento tanto do fornecedor quanto do cliente sobre a obra em andamento.

## 2. Participantes do processo

<h3>Clientes</h3>

Idade: Indiferente<br>
Gênero: Diverso<br>
Papel: Busca por uma empreenteira para a relização de obras maiores como a construção de projetos comerciais, como a reforma de e manutenção de propriedades maiores ou apenas encontrar matériais e pequenos serviços para a realização de reformas em suas casas.<br>

<h3>Fornecedor</h3>
idade:Adulto maior de 18 anos<br>
Gênero: Diverso<br>
Papel: Mão de obra responsável por realizar as atividades laborais dos processos, com o fornecimento de serviço e a execução dos mesmos.<br>___


## 3. Modelagem do processo de negócio

### 3.1. Análise da situação atual

Atualmente, a gestão de obras na construção civil é amplamente realizada de forma descentralizada, utilizando métodos tradicionais como planilhas, anotações manuais e ferramentas de controle de cronogramas que não se integram entre si. A comunicação entre a equipe de obra e os clientes é lenta e fragmentada, ocorrendo por telefone ou e-mail, o que frequentemente leva a atrasos e falhas na troca de informações. Essa abordagem limita a visibilidade do progresso das obras e dificulta o controle preciso de recursos e prazos, resultando em ineficiências e retrabalhos. Estudos indicam que a falta de monitoramento em tempo real pode aumentar significativamente esses problemas, enquanto o uso de tecnologias adequadas pode melhorar a eficiência em até 50% e reduzir os retrabalhos em até 20% [1.3]. Mesmo quando há uso de tecnologias, estas geralmente se restringem a softwares isolados que ajudam na organização, mas não oferecem uma solução integrada. A falta de monitoramento em tempo real e a ausência de uma plataforma centralizada obrigam os gestores a realizarem grande parte das tarefas manualmente, sem a agilidade necessária para uma gestão eficaz. Essa realidade evidencia a necessidade de uma solução que centralize todas essas funções, oferecendo uma visão clara e atualizada do progresso das obras e facilitando a comunicação entre todas as partes envolvidas.

### 3.2. Descrição geral da proposta de solução

Nossa proposta de solução é implementar um software capaz de oferecer ferramentas que auxiliem em uma gestão mais eficaz. O software tem como objetivo proporcionar um ambiente de fácil utilização para a venda de serviços relacionados às mais diversas obras, com um método simples de cadastro, do cliente ou do fornecedor. No software, o fornecedor pode facilmente cadastrar e organizar serviços, como pinturas ou instalação elétrica, e então o cliente pode selecionar um dos diversos serviços apresentados e solicitá-los, informando as características essenciais para o cumprimento da obra. Além disso, buscamos oferecer gráficos que exibem a evolução das obras, mostrando metas a serem alcançadas até determinada data, prazos para o início de etapas específicas e permitindo a análise do progresso diário. Isso possibilita uma comparação entre o planejado e o realizado, proporcionando maior transparência para os clientes que desejam acompanhar o andamento de suas obras, mesmo que estejam ocupados ou distantes. 

### 3.3. Modelagem dos processos

[PROCESSO 1.1 - Gerenciamento de Cliente](processo-Gerenciamento-de-cliente.md "Detalhamento do Processo 1.")

[PROCESSO 1.2 - Gerenciamento de Fornecedor](processo-Gerenciamento-de-fornecedor.md "Detalhamento do Processo 1.")

[PROCESSO 2 - Gerenciamento dos Serviços](processo-Gerenciamento-dos-serviço.md "Detalhamento do Processo 2.")

[PROCESSO 3 - Acompanhamento e gestão da obra](processo-Acompanhamento-e-gestão-de-obra.md "Detalhamento do Processo 3.")

[PROCESSO 4 - Solicitar Obra](processo-Solicitar-obra.md "Detalhamento do Processo 4.")

## 4. Projeto da solução

_O documento a seguir apresenta o detalhamento do projeto da solução. São apresentadas duas seções que descrevem, respectivamente: modelo relacional e tecnologias._

[Projeto da solução](solution-design.md "Detalhamento do projeto da solução: modelo relacional e tecnologias.")


## 5. Indicadores de desempenho

_O documento a seguir apresenta os indicadores de desempenho dos processos._

[Indicadores de desempenho dos processos](performance-indicators.md)


## 6. Interface do sistema

_A sessão a seguir apresenta a descrição do produto de software desenvolvido._ 

[Documentação da interface do sistema](interface.md)

## 7. Conclusão

Este trabalho apresentou o desenvolvimento de um sistema para gestão de obras, focado em solucionar desafios como a falta de organização e transparência no setor da construção civil. A proposta centralizou informações, otimizou o acompanhamento de obras e aprimorou a comunicação entre fornecedores e clientes, contribuindo para maior eficiência e satisfação dos envolvidos. Com processos modelados de forma simplificada e recursos como gráficos de progresso, o sistema proporciona benefícios como redução de atrasos e melhoria no gerenciamento de obras. A solução destaca-se pela capacidade de atender às principais necessidades do setor, trazendo um impacto positivo para a gestão de projetos de construção civil.

# REFERÊNCIAS

_Como um projeto de software não requer revisão bibliográfica, a inclusão das referências não é obrigatória. No entanto, caso você deseje incluir referências relacionadas às tecnologias, padrões, ou metodologias que serão usadas no seu trabalho, relacione-as de acordo com a ABNT._

_Verifique no link abaixo como devem ser as referências no padrão ABNT:_

http://portal.pucminas.br/imagedb/documento/DOC_DSC_NOME_ARQUI20160217102425.pdf

**[1.1]** - _VISOR Notícias. 
CONSTRUTORA registra crescimento de 162% nas vendas no primeiro semestre de 2024. Visor Notícias, 2024. Disponível em: https://visornoticias.com.br/construtora-registra-crescimento-de-162-nas-vendas-no-primeiro-semestre-de-2024/. Acesso em: 20 ago. 2024, às 13:56.

**[1.2]** - _BANIB. Problemas de comunicação na construção civil. Disponível em: https://blog.banib.com/problemas-de-comunicacao-na-construcao-civil/amp/._

**[1.3]** - _McKinsey & Company. Reinventing Construction: A Route to Higher Productivity. 2017. Disponível em: https://www.mckinsey.com/industries/capital-projects-and-infrastructure/our-insights/reinventing-construction-through-a-productivity-revolution._

# APÊNDICES


## Apêndice A - Código fonte

[Código do front-end](../demo/src/main/java/com/example/demo/front) -- repositório do código do front-end

[Código do back-end](../demo/src/main/java/com/example/demo/back/)  -- repositório do código do back-end


## Apêndice B - Apresentação final


[Slides da apresentação final](presentations/)


[Vídeo da apresentação final](video/)






