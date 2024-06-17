import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Medico implements Serializable {
     private String nome;

     private int codigo;

     private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

    public void incluir(Paciente p) {
            pacientes.add(p);
    }

    public Medico(String nome, int codigo){
        this.nome = nome;
        this.codigo = codigo;
    }
    public String mostrar_informacoes(){
        return String.format("\nNome: %s \nCodigo: %d", nome, codigo) + barreira();

    }

    private String barreira(){
        return String.format("\n================================================================");
    }

    public String get_Nome(){
        return nome;
    }
    public int getCodigo(){
        return this.codigo;
    }
    public ArrayList<Paciente> listar_pacientes(){
        return pacientes;
    }

    public void salvar(String nome_arquivo)throws IOException{
        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(this);
        gravador.close();
        arquivo.close();
    }

    public static Medico abrir(String diretorio, String nome_arquivo) throws IOException, ClassNotFoundException {
        Medico medico = null;

        FileInputStream arquivo = new FileInputStream(diretorio + File.separator + nome_arquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);

        medico = (Medico) restaurador.readObject();

        restaurador.close();
        arquivo.close();

        return medico;
    }


}
