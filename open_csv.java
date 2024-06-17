import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class open_csv {

    static ArrayList<Medico> medicos = new ArrayList<>();
    static ArrayList<Paciente> pacientes = new ArrayList<>();

    static ArrayList<Consulta> consultas = new ArrayList<>();


    public static void main(String[] args) {

        abrir_dados_medicos();
        abrir_dados_pacientes();
        abrir_dados_consultas();



        save_medicos(medicos);
        save_pacientes(pacientes);
        save_consultas(consultas);

    }
    public static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }
    public static void save_medicos(ArrayList<Medico> medicos){
        String diretorio = "Medicos";
        File dir = new File(diretorio);
        try {

            if (dir.exists()){
               deleteDirectory(dir);
            }

                if (dir.mkdirs()) {
                     JOptionPane.showMessageDialog(null,"Diretório criado com sucesso: " + diretorio);
                } else {
                     JOptionPane.showMessageDialog(null,"Falha ao criar o diretório: " + diretorio);
                    return;
                }


            for (Medico m : medicos) {
                String caminhoArquivo = diretorio + File.separator + m.get_Nome() + ".ser";
                m.salvar(caminhoArquivo);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Medico> abrir_dados_medicos(){ // funcao responsavel por abrir arquivo medicos.csv
        String NOME_ARQUIVO = "dados-csv/medicos.csv";
        String DIVISOR = ",";

        try
        {
            File arquivo = new File(NOME_ARQUIVO);
            Scanner scanner_arquivo = new Scanner(arquivo);
            String linha = scanner_arquivo.nextLine();
            while(scanner_arquivo.hasNextLine())
            {
                linha = scanner_arquivo.nextLine();
                Scanner scanner_linha = new Scanner(linha);
                scanner_linha.useDelimiter(DIVISOR);

                String nome = scanner_linha.next();
                int codigo = scanner_linha.nextInt();

                Medico medico = new Medico(nome, codigo);
                medicos.add(medico);
            }
            return medicos;
        }

        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static void save_pacientes(ArrayList<Paciente> pacientes){
        String diretorio = "Pacientes";
        File dir = new File(diretorio);
        try {
            if (dir.exists()){
                deleteDirectory(dir);
            }
                if (dir.mkdirs()) {
                     JOptionPane.showMessageDialog(null,"Diretório criado com sucesso: " + diretorio);
                } else {
                     JOptionPane.showMessageDialog(null,"Falha ao criar o diretório: " + diretorio);
                }


            for (Paciente p : pacientes) {
                String caminhoArquivo = diretorio + File.separator + p.getNome() + ".ser";
                p.salvar(caminhoArquivo);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Paciente> abrir_dados_pacientes(){
        String NOME_ARQUIVO = "dados-csv/pacientes.csv";
        String DIVISOR = ",";

        try
        {
            File arquivo = new File(NOME_ARQUIVO);
            Scanner scanner_arquivo = new Scanner(arquivo);
            String linha = scanner_arquivo.nextLine();
            while(scanner_arquivo.hasNextLine())
            {
                linha = scanner_arquivo.nextLine();
                Scanner scanner_linha = new Scanner(linha);
                scanner_linha.useDelimiter(DIVISOR);

                String nome = scanner_linha.next();
                double cpf = scanner_linha.nextDouble();

                Paciente paciente = new Paciente(nome, cpf);
                pacientes.add(paciente);
            }
            return pacientes;
        }

        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static void save_consultas(ArrayList<Consulta> consultas){
        String diretorio = "Consultas";
        File dir = new File(diretorio);
        try {
            if (dir.exists()){
                deleteDirectory(dir);
            }
                if (dir.mkdirs()) {
                     JOptionPane.showMessageDialog(null,"Diretório criado com sucesso: " + diretorio);
                } else {
                     JOptionPane.showMessageDialog(null,"Falha ao criar o diretório: " + diretorio);
                    return;
                }


            for (Consulta c : consultas) {
                Medico m = c.get_Medico();
                String caminhoArquivo = diretorio + File.separator + c.DateTime_as_String() + '-' + m.get_Nome() + ".ser";
                c.salvar(caminhoArquivo);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Consulta> abrir_dados_consultas(){
        String NOME_ARQUIVO = "dados-csv/consultas.csv";
        String DIVISOR = ",";

        try
        {
            File arquivo = new File(NOME_ARQUIVO);
            Scanner scanner_arquivo = new Scanner(arquivo);
            String linha = scanner_arquivo.nextLine();
            while(scanner_arquivo.hasNextLine())
            {
                linha = scanner_arquivo.nextLine();
                Scanner scanner_linha = new Scanner(linha);
                scanner_linha.useDelimiter(DIVISOR);



                int ano = scanner_linha.nextInt();
                int mes = scanner_linha.nextInt();
                int dia = scanner_linha.nextInt();
                int hora = scanner_linha.nextInt();
                int minuto = scanner_linha.nextInt();
                int segundo = scanner_linha.nextInt();
                int codigo = scanner_linha.nextInt();
                double cpf = scanner_linha.nextDouble();

                for (Medico m : medicos) {
                    if (codigo == m.getCodigo()) {
                        Consulta consulta = new Consulta(ano, mes, dia, hora, minuto, segundo, m);
                        consultas.add(consulta);
                        for (Paciente p : pacientes) {
                            if (cpf == p.getCpf()) {
                                p.incluir(consulta);
                                m.incluir(p);
                            }
                        }
                    }
                }


            }
            return consultas;
        }

        catch (Exception e)
        {
            return null;
        }

    }



}
