## 5. Indicadores de desempenho


| **Indicador** | **Objetivos** | **Descrição** | **Fonte de dados** | **Fórmula de cálculo** |
| ---           | ---           | ---           | ---             | ---             |
| Percentual de Progresso da Obra | Avaliar quantitativamente o progresso em relação ao tempo total da obra. | Percentual de tempo transcorrido em relação ao tempo total da obra. | Tabela `Obra`      |  (CURDATE() - data_inicio) / (data_fim - data_inicio) * 100 |
| Taxa de Entrega de Obras no Prazo              | Avaliar a eficiência das empresas na entrega das obras dentro do prazo.      | Mede a % de obras entregues dentro do prazo de término.         | Tabela `Obra`       | (total_obras_no_prazo / total_obras) * 100 |
| Taxa de Atrasos nas Obras           | Avaliar o impacto dos atrasos no cronograma das obras | Mede a % de obras que estão atrasadas em relação ao prazo de término | Tabela `Obra`      | (número de obras atrasadas / total_obras) * 100 |

