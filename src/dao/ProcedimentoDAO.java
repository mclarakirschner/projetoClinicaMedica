
package dao;

import beans.Procedimento;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Clara
 */
public class ProcedimentoDAO {
    private Conexao conexao;
    private Connection conn;

    public ProcedimentoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Procedimento p) throws SQLException {
        String sql = "INSERT INTO procedimento (nome, valor) VALUES (?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getValor());
            stmt.execute();
        }
    }

    public void atualizar(Procedimento p) throws SQLException {
        String sql = "UPDATE procedimento SET nome=?, valor=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getValor());
            stmt.setInt(3, p.getId());
            stmt.execute();
        }
    }

    public void excluir(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM procedimento WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    public List<Procedimento> listar() throws SQLException {
        List<Procedimento> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM procedimento ORDER BY nome");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Procedimento p = new Procedimento();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getDouble("valor"));
                lista.add(p);
            }
        }
        return lista;
    }
}
