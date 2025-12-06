# Allan Stock Management System

Um sistema de gestão de clientes e estoque desenvolvido em **Java**, focado na aplicação de Padrões de Projeto e arquitetura limpa. O sistema permite gerenciar clientes dos tipo `Business` e `Industry`, calculando o custo, valor e descontos totais de um determinado estoque dependendo do regime tributário selecionado.

A partir do padrão *Abstract Factory* foi possível oferecer suporte tanto para **Terminal (Console)** quanto para **Interface Gráfica (Swing)**..

## Funcionalidades

* **Gestão de Clientes:**
  * Cadastro completo com validação de Endereço (`State`) e Telefone (`DDD`).
  * Suporte a  clientes `Business` e `Industry`.

* **Controle de Estoque:**
  * Adição e remoção de produtos individualizados por cliente.
  * Cálculo de valores associados ao estoque

* **Funcionamento:**
  * **Cálculo de Custos:** Indústria possui *overhead* de manufatura, Comércio considera custo base.
  * **Regras Tributárias:** Aplicação de descontos baseadas nos regimes **Simples Nacional**, **Lucro Presumido**, **Lucro Real** via *Strategy Pattern*.
  * **Margem de Lucro:** Cálculo automático do lucro estimado.

* **Persistência de Dados:**
  * Salvamento e carregamento automático via Serialização binária.

* **Interface Dual:**
  * O sistema pode ser executado tanto em modo Texto quanto em modo Gráfico (GUI).

### Design Patterns Implementados

1.  **Abstract Factory:**
    * Utilizado para abstrair a criação da interface do usuário. A classe `Factory` define o contrato, enquanto `ConsoleFactory` e `GuiFactory` fornecem as implementações concretas. Isso permite trocar de Terminal para GUI sem alterar uma linha da lógica de negócio.

2.  **Strategy Pattern:**
    * Aplicado nos Regimes Tributários (`TaxRegime`). As classes `LucroReal`, `LucroPresumido` e `SimplesNacional` encapsulam algoritmos diferentes de cálculo de imposto, intercambiáveis em tempo de execução.

3.  **Bridge Pattern:**
    * Estrutura que desacopla a abstração da implementação para que ambas possam variar independentemente. A hierarquia de **Clientes** (`BusinessCustomer`, `IndustryCustomer`) atua como a *Abstração Refinada*, mantendo uma referência para a hierarquia de **Regimes Tributários** (`TaxRegime`), que atua como o *Implementador*. Isso permite adicionar novos tipos de clientes ou novos regimes tributários sem que uma hierarquia obrigue a alteração da outra.
