/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krismorte.iswing;

import java.util.List;

/**
 *
 * @author c007329
 */
public class IPanelList {

    private GenField anotacao;
    private List list;

    public IPanelList(GenField anotacao, List list) {
        this.anotacao = anotacao;
        this.list = list;
    }
    

    public static List getList(List<IPanelList> lists, GenField anotacao) {
        List list = null;
        for (IPanelList i : lists) {
            if (i.getAnotacao().fieldText().equals(anotacao.fieldText())) {
                list = i.getList();
                break;
            }
        }
        return list;
    }

    public static IPanelList getIPanelList(List<IPanelList> lists, GenField anotacao) {
        IPanelList list = null;
        for (IPanelList i : lists) {
            if (i.getAnotacao().fieldText().equals(anotacao.fieldText())) {
                list = i;
                break;
            }
        }
        return list;
    }

    /**
     * @return the anotacao
     */
    public GenField getAnotacao() {
        return anotacao;
    }

    /**
     * @param anotacao the anotacao to set
     */
    public void setAnotacao(GenField anotacao) {
        this.anotacao = anotacao;
    }

    /**
     * @return the list
     */
    public List getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List list) {
        this.list = list;
    }

}
