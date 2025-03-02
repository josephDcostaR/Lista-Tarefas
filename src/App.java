import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);
    static List<Tarefas> tarefas = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) throws Exception {            
        Menu();    
                
        sc.close();    
    }

    public static void Menu(){
        limparConsole();
        System.out.println("""
            1 - Adicionar tarefa.
            2 - Remover tarefa.
            3 - Listar Tarefas.
            4 - Listar em ordem alfabética.
            5 - Listar em ordem cronológica.
            6 - Sair.\n
            """);

        System.out.print("Escolha: ");
        int op = sc.nextInt();
        sc.nextLine();
        System.out.println();

        switch (op) {
        case 1:
            AdicionarTarefa();
            break;
        case 2:
            RemoverTarefa();
            break;
        case 3:
            ListarTarefas();
            break;
        case 4:
            ListarTarefasEmAlfabetico();
            break;
        case 5:
            ListarTarefasEmCronologico();
            break;
        case 6:
            Sair();
            break;

        default:
            System.out.println("Opção inválida!");;
        }

        // Aguarda a interação do usuário antes de retornar ao menu
        System.out.println("\nPressione Enter para continuar...");
        sc.nextLine();

        Menu();

    }
    
    public static void limparConsole() {
        try{
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else {
                System.out.println("\\033[H\\033[2J");
                System.out.flush();
            }

        } catch (Exception e) {
            System.out.println("Erro ao limpar o console!");
        }
    }
    
    public static void AdicionarTarefa() {
        if (!tarefas.isEmpty()) {
            System.out.println("Já temos algumas tarefas adicionadas.");  
        }

        System.out.print("Descrição da tarefa: ");
        String descricao = sc.nextLine();
        System.out.print("Data de vencimento (dd/MM/yyyy HH:mm): ");
        String dataStr = sc.nextLine();

        try {
            LocalDateTime data = LocalDateTime.parse(dataStr, formatter);
            tarefas.add(new Tarefas(descricao, data));
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Formato de data inválido! Tente novamente.");
        }
        
    }

    public static void RemoverTarefa() {
        if (tarefas.isEmpty()) {
            System.out.println("A lista de tarefas está vazia.");
            return;
        }else {
            ListarTarefas();
            System.out.print("Digite o índice da tarefa a ser removida (ou -1 para cancelar): ");
            int indice = sc.nextInt();
          
            if (indice >= 0 &&  indice < tarefas.size()) {
                System.out.println("Deseja remover \""+ tarefas.get(indice) + "\". (0 - Sim | 1 - Não)");
                int escolha = sc.nextInt();

                if (escolha == 0) {
                    tarefas.remove(indice);
                    System.out.println("Tarefa removida com sucesso!");
                } else {
                    System.out.println("Ação cancelada.");
                }
                
            } else if (indice == -1) {
                System.out.println("Ação cancelada.");
            }else {
                System.out.println("Índice inválido.");
            }
        }
    }

    public static void ListarTarefas() {
            if (tarefas.isEmpty()) {
            System.out.println("não há tarefas na lista.");
           
            } else {
                System.out.println("Tarefas disponíveis: ");
                for (int i = 0; i < tarefas.size(); i++) {
                    System.out.println(i + " - " + tarefas.get(i));
                }
        } 
    }
        
    public static void ListarTarefasEmAlfabetico() {
        if (tarefas.isEmpty()) {
            System.out.println("não há tarefas na lista.");
            tarefas.sort(Comparator.comparing(Tarefas::getDescricao));
        }else {
            Collections.sort(tarefas);
            System.out.println("Tarefas disponíveis em ordem alfabeticas: ");
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println(i + " - " + tarefas.get(i));
            } 
        } 
    }
        
    public static void ListarTarefasEmCronologico() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            Collections.sort(tarefas, Comparator.comparing(Tarefas::getDataVencimento));
            System.out.println("\nTarefas em ordem cronológica:");
            for (Tarefas t : tarefas) {
                System.out.println(t);
            }
        }   
    }

    public static void Sair() {
        System.out.println("Saindo do programa...");
        System.exit(0);      
    }
}
