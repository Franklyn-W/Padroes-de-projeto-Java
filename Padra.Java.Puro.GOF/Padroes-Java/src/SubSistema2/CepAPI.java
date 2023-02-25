package SubSistema2;

public class CepAPI {
    
    private static CepAPI instancia = new CepAPI();
    
    private CepAPI(){
        super();
    }

    public static  CepAPI getInstancia() {
        return instancia;
    }

    public String recuperarCidade(String CEP){
        return "Cidade";
    }

    public String recuperarEstado(String CEP){
        return "Estado";
    }
}
