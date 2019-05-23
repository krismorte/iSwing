package teste;

import com.krismorte.iswing.iBusiness;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Business implements Serializable, iBusiness{
    
    List<Pessoa> pessoas = Arrays.asList(new Pessoa(),new Pessoa());

    @Override
    public List selecionarTodos(Boolean ativo) {
        
        return pessoas;
    }

    @Override
    public List selecionarPeriodo(Date inicio, Date fim) {
        return pessoas;
    }

    @Override
    public List selecionarPeriodo(Date inicio, Date fim, Boolean ativo) {
        return pessoas;
    }

 

}