
package conexao;

import java.sql.Connection;

/**
 *
 * @author aluno
 */
public class TesteConexao {

    public static void main(String[] args) {
        Connection con = new Conexao().getConexao();
        if (con != null) {
            System.out.println(" Conectado!");
        } else {
            System.out.println("Falhou");
        }
    }
}
    
