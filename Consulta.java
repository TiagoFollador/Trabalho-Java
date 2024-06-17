import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Consulta implements Serializable {
    private LocalDate data;

    private LocalTime horario;

    private Medico medico;



    public Consulta(int ano, int mes, int dia, int hora, int minuto, int segundo, Medico medico) {
        this.data = LocalDate.of(ano,mes,dia);
        this.horario = LocalTime.of(hora, minuto, segundo);
        this.medico = medico;
    }


    public LocalDate get_Data() {
        return this.data;
    }

    public LocalTime get_Horario() { return this.horario; }

    public LocalDateTime get_Date_time(){
        return data.atTime(horario);
    }
    public void salvar(String nome_arquivo)throws IOException {
        FileOutputStream arquivo = new FileOutputStream(nome_arquivo);
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(this);
        gravador.close();
        arquivo.close();
    }
    public static Consulta abrir(String diretorio, String nome_arquivo) throws IOException, ClassNotFoundException {
        Consulta consulta = null;

        FileInputStream arquivo = new FileInputStream(diretorio + File.separator + nome_arquivo);
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);

        consulta = (Consulta) restaurador.readObject();

        restaurador.close();
        arquivo.close();

        return consulta;
    }
    public String DateTime_as_String(){
        LocalDateTime dataTime = data.atTime(horario);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String dataTime_format = dataTime.format(formato);
        return dataTime_format;
    }
    public Medico get_Medico(){
        return medico;
    }

    private void barreira(){
        System.out.println("================================================================");
    }


}
