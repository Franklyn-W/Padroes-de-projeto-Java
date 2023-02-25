package Facade;

import SubSistema1.CRM.CrmService;
import SubSistema2.CepAPI;

public class Facade {
    
    public void migrarCliente(String nome, String CEP){
        String cidade = CepAPI.getInstancia().recuperarCidade(CEP);
        String estado = CepAPI.getInstancia().recuperarEstado(CEP);

        CrmService.gravarCliente(nome, CEP, cidade, estado);
    }
}
