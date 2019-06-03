package teste;

import com.krismorte.iswing.view.IPanelDados;
import com.krismorte.iswing.view.JDialogPadrao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;

public class panelPessoa extends IPanelDados implements ActionListener {

    public panelPessoa() {
        super("teste", Pessoa.class, "", new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 12), new Font(Font.SANS_SERIF, Font.ROMAN_BASELINE, 12), false);
    }

    @Override
    public Object[][] getRows() {
        List<Pessoa> pessoas = Arrays.asList(new Pessoa(), new Pessoa());
        try {
            return loadLines(pessoas);
        } catch (Exception ex) {
            Logger.getLogger(panelPessoa.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public JMenuItem[] getItens() {
        JMenuItem[] itens = new JMenuItem[1];
        JMenuItem editar = new JMenuItem("editar");
        itens[0] = editar;
        return itens;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        panelPessoa panel = new panelPessoa();
        new JDialogPadrao(null, true, panel);
    }

}
