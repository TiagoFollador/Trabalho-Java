import java.io.*;
import java.util.ArrayList;

public class Paciente implements Serializable {
    private String nome;

    private double cpf;

    private ArrayList<Consulta>  consultas = new ArrayList<Consulta>();;

    public Paciente(String nome, double cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public String mostrar_informacoes(){
        return String.format("\nNome: %s \nCPF: %.0f", nome, cpf) + barreira();
    }


    public double getCpf(){
        return cpf;
    }
    public String getNome(){
        return nome;
    }
    public void incluir(Consulta c) {
        consultas.add(c); }

    public ArrayList<Consulta> consulta(){

        return consultas;
    }

    public void salvar(String nome_arquivo)throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(this);
        gravador.close();
        arquivo.close();
    }

    public static Paciente abrir(String diretorio,String nome_arquivo) throws IOException, ClassNotFoundException {
        Paciente paciente = null;

        FileInputStream arquivo = new FileInputStream(diretorio + File.separator + nome_arquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);

        paciente = (Paciente) restaurador.readObject();

        restaurador.close();
        arquivo.close();

        return paciente;
    }

    private String barreira(){
        return String.format("\n================================================================");
    }
}
