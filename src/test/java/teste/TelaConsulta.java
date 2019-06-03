package teste;

import com.krismorte.iswing.view.TelaConsultas;
import java.awt.Font;

public class TelaConsulta {

    public static void main(String[] args) {
        TelaConsultas t = new TelaConsultas("Consulta de Linhas", new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 12), Pessoa.class, new Business(), null);
        t.setVisible(true);
    }

}
