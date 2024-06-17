#Sistema de Gestão Hospitalar em Java
##Visão Geral
Esta aplicação Java simula um sistema de gestão hospitalar. Ela processa dados de um arquivo CSV para gerenciar interações envolvendo médicos, pacientes e consultas.

##Recursos
- Médico: Identificado por um ID e nome, gerencia uma lista de pacientes.
- Paciente: Identificado por um CPF e nome, gerencia uma lista de consultas.
- Consulta: Contém uma data e médico associado.

##Aplicações
- open_csv.java: Abre o arquivo CSV e salva os dados em formato .ser.
- app.java: Abre os arquivos .ser e interage com o usuário através de uma interface de formulário.

##Opções do Usuário
- Listar pacientes de um médico.
- Listar consultas agendadas de um médico em um período específico.
- Listar todos os médicos que um paciente já consultou ou tem consulta marcada.
- Listar as consultas com um médico específico.
- Listar consultas agendadas de um paciente.
- Listar pacientes de um médico que não o consultam há algum tempo.
- Marcar consulta.
- Salvar e Sair.
##Como Executar
Execute open_csv.java para ler o arquivo CSV e serializar os dados.
Rode app.java para iniciar a interação com o usuário através da interface de formulário.
