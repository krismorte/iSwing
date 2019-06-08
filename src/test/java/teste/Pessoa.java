package teste;

import com.krismorte.iswing.GenField;
import java.util.Date;
import java.util.Random;

public class Pessoa {

    @GenField(fieldText = "ID", columnName = "ID", fieldEnable = false, type = GenField.LONG)
    private Long id;
    @GenField(fieldText = "Número", columnName = "Número", isRepresentation = true)
    private String numero;
    @GenField(fieldText = "Data Aquisição", columnName = "Data Aquisição", type = GenField.DATE)
    private Date dataAquisicao;

    public Pessoa() {
        
        id =Math.round(Math.random()*100);
    }
    
    
    

}
