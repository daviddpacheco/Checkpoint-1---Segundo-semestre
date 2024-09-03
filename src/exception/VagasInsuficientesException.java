package exception;

public class VagasInsuficientesException extends Exception{
    public VagasInsuficientesException() {
        super("Estacionamente lotado. Não há mais vagas disponíveis");
    }

    public VagasInsuficientesException(String mensagem) {
        super(mensagem);
    }
}