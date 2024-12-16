<h1>QuickMart - E-commerce Simples</h1>

<p>QuickMart é uma aplicação de e-commerce simples criada com o objetivo de fornecer um estudo de como seria a estrutura de código de um e-commerce real. A aplicação foi construída usando tecnologias modernas e populares como Spring Boot, Docker, OAuth2 e bancos de dados relacionais e não relacionais, para entender como integrar diferentes componentes de um sistema de e-commerce.</p>

<h2>Tecnologias Utilizadas</h2>

<ul>
  <li><strong>Spring Boot</strong>: Framework Java utilizado para construir a aplicação de backend.</li>
  <li><strong>Docker</strong>: Para facilitar o desenvolvimento, deploy e containerização da aplicação.</li>
  <li><strong>OAuth2</strong>: Implementação de autenticação e autorização segura.</li>
  <li><strong>MySQL</strong>: Banco de dados relacional utilizado para armazenar dados principais como clientes, produtos e vendedores.</li>
  <li><strong>MongoDB</strong>: Banco de dados NoSQL utilizado para armazenar informações de pedidos (orders), permitindo maior flexibilidade para salvar dados não estruturados.</li>
</ul>

<h2>Entidades Principais</h2>

<p>A aplicação QuickMart possui 5 principais entidades:</p>

<ol>
  <li><strong>Client (Cliente)</strong>: Representa os clientes do e-commerce. Os dados armazenados incluem informações pessoais, como nome, email, senha (crypografada), e outros detalhes necessários para o gerenciamento do cliente na plataforma.</li>
  
  <li><strong>Order (Pedido)</strong>: Cada pedido realizado por um cliente. O pedido contém os produtos comprados, status de pagamento, data do pedido, endereço de entrega e informações sobre o cliente que realizou a compra. Este dado é armazenado no MongoDB para maior flexibilidade e escalabilidade.</li>
  
  <li><strong>Product (Produto)</strong>: Produtos vendidos no e-commerce, com informações como nome, descrição, preço e estoque disponível. O produto é a entidade central para o catálogo de compras da plataforma.</li>
  
  <li><strong>Image (Imagem)</strong>: Imagens associadas aos produtos. Cada produto pode ter uma ou mais imagens para exibição na página do produto, melhorando a experiência do usuário.</li>
  
  <li><strong>Seller (Vendedor)</strong>: Representa os vendedores que listam produtos no e-commerce. Eles podem gerenciar seu estoque, visualizar seus pedidos e atualizar informações sobre seus produtos.</li>
</ol>

<h2>Funcionalidades</h2>

<ul>
  <li><strong>Cadastro e Login de Cliente com OAuth2</strong>: Autenticação segura utilizando OAuth2 para garantir que apenas clientes autorizados possam acessar certas funcionalidades.</li>
  <li><strong>Criação e Gerenciamento de Pedidos</strong>: Os clientes podem adicionar produtos ao carrinho, realizar o checkout e concluir a compra. As informações dos pedidos são salvas no MongoDB.</li>
  <li><strong>Cadastro de Produtos</strong>: Vendedores podem cadastrar, editar e excluir produtos.</li>
  <li><strong>Visualização de Produtos</strong>: Os clientes podem navegar pela lista de produtos disponíveis no marketplace, com imagens associadas.</li>
  <li><strong>Gerenciamento de Estoque</strong>: Cada vendedor pode gerenciar seu estoque de produtos, ver quantos itens restam e fazer alterações quando necessário.</li>
</ul>

<h2>Arquitetura</h2>

<h3>Backend</h3>
<p>A aplicação segue uma arquitetura de microserviços, utilizando Spring Boot para criar as APIs RESTful que gerenciam as entidades do sistema. A autenticação e autorização são gerenciadas com OAuth2, permitindo que usuários possam fazer login via redes sociais ou autenticação via JWT.</p>

<h3>Banco de Dados</h3>
<ul>
  <li><strong>MySQL</strong>: Usado para armazenar dados estruturados (clientes, vendedores, produtos e imagens).</li>
  <li><strong>MongoDB</strong>: Usado para armazenar pedidos (orders), permitindo maior flexibilidade para manipular dados de forma não relacional e escalável.</li>
</ul>

<h3>Docker</h3>
<p>A aplicação está containerizada usando Docker, o que facilita o processo de desenvolvimento e deploy. Usar o Docker permite que a aplicação seja executada em qualquer ambiente, independentemente de diferenças de configuração.</p>

<h2>Fluxo de Autenticação</h2>

<ol>
  <li><strong>Login</strong>: O cliente ou vendedor realiza o login utilizando OAuth2.</li>
  <li><strong>Autorização</strong>: O cliente é redirecionado para um provedor de autenticação (Google, Facebook, etc.) e, ao ser autenticado, o token é retornado.</li>
  <li><strong>Acesso Autorizado</strong>: Após a autenticação, o cliente ou vendedor tem acesso às suas informações e funcionalidades dentro da plataforma.</li>
</ol>
