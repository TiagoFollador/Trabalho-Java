import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class app {
    boolean Ligado = true;
    static ArrayList<Medico> medicos = new ArrayList<>();
    static ArrayList<Paciente> pacientes = new ArrayList<>();

    static ArrayList<Consulta> consultas = new ArrayList<>();
    static  ArrayList<JButton> buttons = new ArrayList<>();

    static ArrayList<ActionListener> button_onclick = new ArrayList<>();


    public static void main(String[] Arg) throws Inexistent_User_Exception {
        int x=500,y=600;
        JFrame frame = new JFrame("Hospital");
        JPanel painel = new JPanel();
        frame.setSize(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(painel);
        show_options_interface(x,y,painel);
        frame.setVisible(true);

        abrir_dados_medicos("Medicos");
        abrir_dados_pacientes("Pacientes");
        abrir_dados_consultas("Consultas");






    }

    public static ArrayList<Medico> abrir_dados_medicos(String diretorio) { // funcao responsavel por abrir diretorio Medicos
        File dir = new File(diretorio);
        try {
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".ser")) {
                            String fileName = file.getName();
                            try {

                                Medico medico = Medico.abrir(diretorio,fileName);
                                medicos.add(medico);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                     JOptionPane.showMessageDialog(null,"O diretório está vazio ou não contém arquivos.");
                }
            } else {
                JOptionPane.showMessageDialog(null,"O diretório não existe ou não é um diretório.");
            }
            return medicos;
        }
        catch( Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static ArrayList<Paciente> abrir_dados_pacientes(String diretorio){
        File dir = new File(diretorio);
        try {
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".ser")) {
                            String fileName = file.getName();
                            try {

                                Paciente paciente = Paciente.abrir(diretorio, fileName);
                                pacientes.add(paciente);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                     JOptionPane.showMessageDialog(null,"O diretório está vazio ou não contém arquivos.");
                }
            } else {
                 JOptionPane.showMessageDialog(null,"O diretório não existe ou não é um diretório.");
            }
            return pacientes;
        }
        catch( Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static ArrayList<Consulta> abrir_dados_consultas(String diretorio){
        File dir = new File(diretorio);
        try {
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".ser")) {
                            String fileName = file.getName();
                            try {

                                Consulta consulta = Consulta.abrir(diretorio,fileName);
                                consultas.add(consulta);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                     JOptionPane.showMessageDialog(null,"O diretório está vazio ou não contém arquivos.");
                }
            } else {
                 JOptionPane.showMessageDialog(null,"O diretório não existe ou não é um diretório.");
            }
            return consultas;
        }
        catch( Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void show_options_interface(int x, int y,JPanel painel){
        painel.setLayout(null);
        JTextArea opcoes_base = new JTextArea(show_opcoes_base());
        opcoes_base.setBounds(0, 50, 500, 200);
        painel.add(opcoes_base);

        create_buttons(painel, x,y,8);

        int num = 1;
        for(JButton b: buttons){
            ActionListener button_reader = new LeitorBotoes(num);
            b.addActionListener(button_reader);
            num++;
            button_onclick.add(button_reader);
        }
    }

    public static ArrayList<JButton> create_buttons(JPanel painel, int x, int y,int num_final){
        int button_x=0, button_Y=400, button_width = 80, button_height=25;
        for (int i = 0; i < num_final; i++) {
            if(button_width * (i+1) > x && (i)%6 == 0 ){
                button_Y += 30;
            }
            JButton botao = new JButton(String.valueOf(i+1));
            botao.setBounds((i%6)*button_width , button_Y, button_width, button_height);
            painel.add(botao);
            buttons.add(botao);



        }
        return null;
    }



    public static String show_opcoes_base() {
        return "*** OPÇÕES *** \n1. Listar pacientes de um medico \n2. Listar consultas agendadas de um medico durante certo periodo de tempo" +
                " \n3. Listar todos os medicos que um paciente ja consultou ou tem consulta marcada \n4. Listar as consultas com determinado medico" +
                " \n5. Listar consultas agendadas de determinado paciente \n6. Listar pacientes de um medico que nao o consultam ha um tempo \n7. Marcar consulta" +
                "\n8. Salvar e Sair";
    }



    public static Paciente select_Paciente() throws Inexistent_User_Exception{
        double cpf = 0;
        boolean find = false;
        String dados = new String();

        for (Paciente p : pacientes) dados +=  p.mostrar_informacoes();

        String input = JOptionPane.showInputDialog(null, dados +"\nDigite o CPF do paciente que deseja:");
        cpf = Double.parseDouble(input);

        for (Paciente j : pacientes) {
            if (cpf == j.getCpf()) {
                find = false;
                return j;
            }
        }


        if (!find){
            throw new Inexistent_User_Exception("CPF do paciente nao encontrado");
        }


        return null;
    }
    // funcao para selecionar um medico  por meio do id
    public static Medico select_Medico() throws Inexistent_User_Exception {
        int codigo = 0;
        boolean find = false;
        String dados = new String();

        for (Medico m : medicos)  dados += m.mostrar_informacoes();

        String input = JOptionPane.showInputDialog(null,dados +"\nDigite o ID do medico que deseja:");
        codigo = Integer.parseInt(input);

        for (Medico j : medicos) {
            if (codigo == j.getCodigo()) {
                find = true;
                return j;
            }
        }
        if (!find){
            throw new Inexistent_User_Exception("ID de medico nao encontrado");
        }




        return null;
    }



    //1
    public static void listar_pacientes_medico(){
        String dados = new String();
        try {
            Medico medico = select_Medico();
            if (medico.listar_pacientes().isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Esse medico nao possui pacientes ainda");
            } else {
                HashSet<Paciente> Pacientes_do_medico = new HashSet<>(medico.listar_pacientes());
                for (Paciente p : Pacientes_do_medico) {
                    dados += p.mostrar_informacoes();

                }
                JOptionPane.showMessageDialog(null,String.format("Os pacientes do medico " + medico.get_Nome() + "\nSão: \n" + dados));

            }
        }
        catch (Inexistent_User_Exception e){
            JOptionPane.showMessageDialog(null,e);

        }
    }


    //2 funcao para realizar pesquisa sobre consultas agendadas
    public static void consultas_Agendadas() throws Data_fail_Exception{
        boolean find = false;
        Medico medico;
        ArrayList<LocalDateTime> data_Hora_consulta = new ArrayList<LocalDateTime>();


            String Data_inicial = JOptionPane.showInputDialog(null, (String.format("Digite a data de inicio, no padrao yyyy-MM-dd")));

            String Data_final = JOptionPane.showInputDialog(null, String.format("Digite a data final, no padrao yyyy-MM-dd"));

            String Hora_inicial = JOptionPane.showInputDialog(null,String.format("Digite a hora de inicio, no padrao HH:mm:ss"));

            String Hora_final =  JOptionPane.showInputDialog(null,String.format("Digite a hora final, no padrao HH:mm:ss"));

            try {
                LocalDate Data_inicial_pesquisa = LocalDate.parse(Data_inicial);
                LocalDate Data_final_pesquisa = LocalDate.parse(Data_final);

                LocalTime Hora_inicial_pesquisa = LocalTime.parse(Hora_inicial);
                LocalTime Hora_final_pesquisa = LocalTime.parse(Hora_final);

                // utiliza comparacoes de data e hora afim de facilitar caso a data de pesquisa seja no mesmo dia, para verificar se as horas sao diferentes
                int comparacao_data = Data_final_pesquisa.compareTo(Data_inicial_pesquisa), comparacao_hora = Hora_final_pesquisa.compareTo(Hora_inicial_pesquisa);

                JOptionPane.showMessageDialog(null ,String.format(
                        "================================================================" + "\n" +
                                "Dia e hora de inicio: " + Data_inicial_pesquisa + " " + Hora_inicial_pesquisa + "\n" +
                                "Dia e hora de fim: " + Data_final_pesquisa + " " + Hora_final_pesquisa + "\n" +
                                "================================================================"));

                if(comparacao_data > 0){ // se a data inicial for menor que a final


                        medico = select_Medico();

                        for (Consulta c : consultas) {


                            if (c.get_Medico().getCodigo() == medico.getCodigo()) {
                                // comparamos data da consulta
                                int comaparacao_consulta_inicial_data = Data_inicial_pesquisa.compareTo(c.get_Data()), comparacao_consulta_final_data = Data_final_pesquisa.compareTo(c.get_Data());

                                if (comaparacao_consulta_inicial_data < 0 && comparacao_consulta_final_data > 0) {
                                    data_Hora_consulta.add(c.get_Data().atTime(c.get_Horario()));

                                    find = true;
                                }
                                else if (comaparacao_consulta_inicial_data == 0)//se for no dia de inicio da pesquisa
                                     {

                                    int comparacao_consulta_inicial_hora = Hora_inicial_pesquisa.compareTo(c.get_Horario());
                                    //compara se a hora inicial é anterior a hora da consulta
                                    if (comparacao_consulta_inicial_hora <= 0 ) {
                                        data_Hora_consulta.add(c.get_Data().atTime(c.get_Horario()));
                                        find = true;
                                    }
                                }
                                else if (comparacao_consulta_final_data == 0)//se for no dia final da pesquisa
                                {
                                    int  comparacao_consulta_final_hora = Hora_final_pesquisa.compareTo(c.get_Horario());
                                    //verifica se a hora da consulta é anterior a hora final da pesquisa
                                    if (comparacao_consulta_final_hora >= 0){
                                        data_Hora_consulta.add(c.get_Data().atTime(c.get_Horario()));
                                        find = true;
                                    }

                                }
                            }
                        }
                    if (!(find)){
                        JOptionPane.showMessageDialog(null, "O medico inserido nao possui consultas");
                    }
                    else{
                        Collections.sort(data_Hora_consulta);
                        String dados = new String();
                        for(LocalDateTime d: data_Hora_consulta){
                            DateTimeFormatter Formato_data = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                            String Data_hora_formatada = Formato_data.format(d);
                            dados += ("O medico tem uma consulta agendada para: " + Data_hora_formatada + "\n");
                        }
                        JOptionPane.showMessageDialog(null, dados);
                    }
                }

                // se a pesquisa for pra um dia especifico
                else if(comparacao_data == 0){
                    if(comparacao_hora >= 0) {


                        medico = select_Medico();

                        for (Consulta c : consultas) {
                            if (c.get_Medico().getCodigo() == medico.getCodigo()) {

                                int comparacao_consulta_inicial_hora = Hora_inicial_pesquisa.compareTo(c.get_Horario()), comparacao_consulta_final_hora = Hora_final_pesquisa.compareTo(c.get_Horario());
                                if (comparacao_consulta_inicial_hora < 0 && comparacao_consulta_final_hora > 0){

                                    data_Hora_consulta.add(c.get_Data().atTime(c.get_Horario()));

                                    find = true;
                                }
                            }
                        }

                        if (!(find)){
                            JOptionPane.showMessageDialog(null, "O medico inserido nao possui consultas");
                    }

                    }

                }



                else {
                    throw new Data_fail_Exception("Parece que voce digitou uma data ou hora inicial maior que a final!");
                   }
            }

            catch (Exception e) {
                 JOptionPane.showMessageDialog(null,e);
            }


    }

    //3 funcao para listar todos os medicos que um paciente ja consultou ou tem consulta marcada
    public static void obter_medicos_consultas_paciente(){
        boolean keepAsking = true;
        StringBuilder dados = new StringBuilder();

            try {
                Paciente paciente = select_Paciente();

                    if (paciente.consulta().isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Esse paciente nao tem consultas ainda");
                    } else {
                        ArrayList<Consulta> consultas_paciente = paciente.consulta();

                        for (Consulta c : consultas_paciente) {
                            Medico medico = c.get_Medico();

                            dados.append(String.format("\nNo dia: " + c.get_Data() + " \nAs: " + c.get_Horario() + "\n"));
                            dados.append(medico.mostrar_informacoes());
                        }
                        JOptionPane.showMessageDialog(null, String.format("O paciente %s, possui consulta(s) com o(s) Medico(s):"+ "\n", paciente.getNome() ) + dados);
                    }

            } catch (Inexistent_User_Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
    }
    //4 funcao para listar as consultas com determinado medico
    public static void obter_medicos_consultas_paciente_passadas() {
            try {
                Paciente paciente = select_Paciente();
                Medico medico = select_Medico();
                StringBuilder dados = new StringBuilder();


                try {
                    ArrayList<Paciente> Pacientes_do_medico = medico.listar_pacientes();
                    ArrayList<Consulta> Consultas_do_Paciente = paciente.consulta();

                    //Verifica se o paciente ou o medico possuem consultas
                    if ((Pacientes_do_medico.isEmpty()) || (Consultas_do_Paciente.isEmpty())) {
                        JOptionPane.showMessageDialog(null,"Parece que o Medico ou o Paciente nao possuem nenhum paciente ou consulta, respectivamente");

                    } else {
                        boolean find = false;

                        for (Consulta c : Consultas_do_Paciente) {
                            int comparacao_data_hora = (LocalDateTime.now()).compareTo(c.get_Date_time());
                            //verifica se o medico é o medico procurado, e verifica se é uma consulta que ja ocorreu
                            if (c.get_Medico().getCodigo() == medico.getCodigo() && comparacao_data_hora > 0) {
                               dados.append(String.format("\nO paciente: " + paciente.getNome() + " teve uma consulta agendada com o doutor: " +
                                        medico.get_Nome() + "\nNo dia: " + c.get_Data() + "\nAs: " + c.get_Horario()));
                                find = true;
                            }

                        }
                        // para caso nao encontre o medico ou o paciente nas pesquisas
                        if (!(find)) {
                         JOptionPane.showMessageDialog(null,"Parece que o Medico: " + medico.get_Nome() + " e o Paciente: " + paciente.getNome() + " nao realizaram nenhuma consulta ainda");
                        }
                        else{
                             JOptionPane.showMessageDialog(null,dados);
                        }

                    }
                } catch (Exception e) {
                     JOptionPane.showMessageDialog(null, e);
                }

            } catch (Inexistent_User_Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }

    }

    //5 funcao para listar consultas agendadas de determinado paciente
    public static void todas_Consultas_Agendadas(){

            try {
                Paciente paciente = select_Paciente();

                try {
                    ArrayList<Consulta> Consultas_do_Paciente = paciente.consulta();

                    StringBuilder dados = new StringBuilder();

                    //caso o paciente nao possua consultas
                    if (paciente.consulta().isEmpty()) {
                         JOptionPane.showMessageDialog(null, "Esse paciente nao tem consultas marcadas ainda");
                    }
                    else {
                        boolean find = false;

                        for (Consulta c : Consultas_do_Paciente) {
                            int comparacao_data_hora = (LocalDateTime.now()).compareTo(c.get_Date_time());
                            //verifica se o paciente possui consultas agendadas e verifica se a data delas ja passou
                            if (comparacao_data_hora < 0) {
                                dados.append(String.format("\nO paciente: " + paciente.getNome() + " tem uma consulta agendada para \nDia: "
                                        + c.get_Data() + "\nAs: " + c.get_Horario()));
                                find = true;
                            }

                        }
                        if (!(find)) {
                            JOptionPane.showMessageDialog(null,"Parece que o Paciente: " + paciente.getNome() + " nao tem nenhuma consulta marcada");

                        }
                        else {
                             JOptionPane.showMessageDialog(null,dados);
                        }


                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,e);
                }
            } catch (Inexistent_User_Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
    }

    private static int periodo_de_meses()throws Data_fail_Exception{
        String meses;
        meses = JOptionPane.showInputDialog(null,"Digite a quantidade de meses para fazer a pesquisa");
        if (meses.contains(",") || meses.contains(".")){
            throw new Data_fail_Exception("Valor de meses nao aceita, por favor inserir um valor inteiro");
        }
        return Integer.parseInt(meses);


    }

    //6 funcao para listar pacientes de um medico que nao o consultam ha um tempo
    public static void pacientes_Inativos(){
        boolean keepAsking = true;
        int periodo_meses = 0;
        StringBuilder dados = new StringBuilder();
        try {
            Medico medico = select_Medico();
            //o hash retira todos os itens em comum, deixando apenas um
            HashSet<Paciente> pacientes_recentes = new HashSet<>();
            HashSet<Paciente> pacientes_Inativos = new HashSet<>();

                try {



                    periodo_meses = periodo_de_meses();

                    LocalDate Data_pesquisa = (LocalDate.now()).minusMonths(periodo_meses);

                    ArrayList<Paciente> Pacientes_do_medico = medico.listar_pacientes();
                    // verifica se a lista de pacientes do medico esta vazia
                    if (Pacientes_do_medico.isEmpty()) {
                         JOptionPane.showMessageDialog(null,"Parece que esse medico nao possui nenhum paciente ainda");
                        keepAsking = false;
                    } else {
                        for (Paciente p : Pacientes_do_medico) {
                            for (Consulta c : p.consulta()) {
                                if(c.get_Medico().getCodigo() == medico.getCodigo()) {
                                    int comparacao_data = c.get_Data().compareTo(Data_pesquisa);
                                    //compara se a data é menor que o periodo de meses, e veridica se o paciente ja teve alguma consulta no periodo de meses pre-estabelecido
                                    if (comparacao_data < 0 && !(pacientes_recentes.contains(p))) {
                                        pacientes_Inativos.add(p);
                                    } else {
                                        pacientes_recentes.add(p); //adiciona a pacientes recentes
                                        pacientes_Inativos.remove(p); // remove de pacientes inativos
                                    }
                                }
                            }

                        }

                        if (pacientes_Inativos.isEmpty()) {
                             JOptionPane.showMessageDialog(null,"Todos os pacientes visitaram ou tem consultas agendads com o medico no periodo de " + periodo_meses + " mes(es)");
                        }
                        for (Paciente p : pacientes_Inativos) {
                            dados.append("\nO Paciente " + p.getNome() + " nao ve o Doutor " + medico.get_Nome() + " ha mais de " + periodo_meses + " mes(es)");
                        }
                        if(!dados.isEmpty()){
                             JOptionPane.showMessageDialog(null,dados);
                        }



                    }
                } catch (Data_fail_Exception e) {
                     JOptionPane.showMessageDialog(null, e );
                }




        }
        catch (Inexistent_User_Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    // 7 inserir consulta

    public static void marcar_consulta() {
        try {
            Medico medico = select_Medico();
            Paciente paciente = select_Paciente();
            boolean isPacient = false, medico_is_Free = true,  paciente_is_Free = true;
            HashSet<Paciente> Pacientes_do_medico = new HashSet<>(medico.listar_pacientes());

            try {
                String Data = JOptionPane.showInputDialog(null, (String.format("Digite a data da consulta no padrao yyyy-MM-dd")));
                String Horario = JOptionPane.showInputDialog(null, (String.format("Digite o horario da consulta no padrao HH:mm:ss")));

                LocalDate Data_consulta = LocalDate.parse(Data);
                LocalTime Horario_consulta = LocalTime.parse(Horario);

                for(Consulta c: consultas){

                    if(Data_consulta.equals(c.get_Data()) && Horario_consulta.equals( c.get_Horario()) && c.get_Medico().getCodigo() == medico.getCodigo()){
                        medico_is_Free = false;
                    }
                }
                for(Consulta c: paciente.consulta()){
                    if(Data_consulta.equals(c.get_Data()) && Horario_consulta.equals(c.get_Horario())){
                        paciente_is_Free = false;
                    }
                }
                if(medico_is_Free && paciente_is_Free) {
                    for (Paciente p : Pacientes_do_medico) {
                        if (p.getCpf() == paciente.getCpf()) {
                            isPacient = true;
                        }
                    }

                    if (!isPacient) {
                        medico.incluir(paciente);
                    }

                    Consulta consulta = new Consulta(Data_consulta.getYear(), Data_consulta.getMonthValue(), Data_consulta.getDayOfMonth(), Horario_consulta.getHour(), Horario_consulta.getMinute(), Horario_consulta.getSecond(), medico);
                    paciente.incluir(consulta);
                    consultas.add(consulta);
                }
                else if(!medico_is_Free) {
                    JOptionPane.showMessageDialog(null, "O medico ja possui consulta marcada para o dia: " + Data_consulta + " no horario " + Horario_consulta);
                }
                else if(!paciente_is_Free) {
                    JOptionPane.showMessageDialog(null, "O paciente ja possui consulta marcada para o dia: " + Data_consulta + " no horario " + Horario_consulta);
                }
            }
            catch (Exception e){
                 JOptionPane.showMessageDialog(null,e);
            }

        }
        catch (Inexistent_User_Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
    }

    // 8 salvar dados

    public static void save_data(){
        open_csv.save_medicos(medicos);
        open_csv.save_pacientes(pacientes);
        open_csv.save_consultas(consultas);
    }
}






