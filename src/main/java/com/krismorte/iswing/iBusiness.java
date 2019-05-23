/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import java.util.Date;
import java.util.List;

/**
 *
 * @author krisnamourtscf
 */
public interface iBusiness {

    public List selecionarTodos(Boolean ativo);

    public List selecionarPeriodo(Date inicio, Date fim);

    public List selecionarPeriodo(Date inicio, Date fim, Boolean ativo);

}
