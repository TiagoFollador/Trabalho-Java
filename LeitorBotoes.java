import org.ietf.jgss.GSSException;
import sun.security.jgss.spnego.SpNegoContext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JOptionPane;
public class LeitorBotoes implements ActionListener {
    private int opcao;
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton origem = (JButton) e.getSource();
        switch (this.opcao) {
            case 1:
                app.listar_pacientes_medico();
                break;
            case 2:
                try {
                    app.consultas_Agendadas();
                } catch (Data_fail_Exception ex) {
                    JOptionPane.showMessageDialog(null,ex);

                }
                break;
            case 3:
                app.obter_medicos_consultas_paciente();
                break;
            case 4:
                app.obter_medicos_consultas_paciente_passadas();
                break;
            case 5:
                app.todas_Consultas_Agendadas();
                break;
            case 6:
                app.pacientes_Inativos();
                break;
            case 7:
                    app.marcar_consulta();
                break;
            case 8:
                app.save_data();
                JOptionPane.showMessageDialog(null, "Obrigado por utilizar! \nFeito por Tiago de Brito Follador");
                System.exit(0);

            default:
                 JOptionPane.showMessageDialog(null,"Digite um valor entre 1 e 7!");
        }
    }

    public LeitorBotoes(int opcao){
        this.opcao = opcao;
    }


}

