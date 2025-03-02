import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefas implements Comparable<Tarefas> {

    private String descricao;
    private LocalDateTime dataVencimento;

    public Tarefas(String descricao, LocalDateTime dataVencimento) {
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
    }

    
    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int compareTo(Tarefas outraTarefa) {
        int resultado = this.descricao.compareTo(outraTarefa.getDescricao());
        if (resultado == 0) {
            return this.dataVencimento.compareTo(outraTarefa.getDataVencimento());
        }
        return resultado;
    }
    
    @Override
    public String toString() {
        return descricao + " - Vence em: " + dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }




}
