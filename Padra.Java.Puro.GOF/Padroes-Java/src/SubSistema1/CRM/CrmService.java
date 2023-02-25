package SubSistema1.CRM;

public class CrmService {
    
    private CrmService() {
        super();
    }

    public static void gravarCliente(String nome, String CEP, String cidade, String estado) {
        System.out.println("Cliente salvo no sistema.");

        System.out.println(nome);
        System.out.println(CEP);
        System.out.println(cidade);
        System.out.println(estado);
        System.out.println();
    }
}
