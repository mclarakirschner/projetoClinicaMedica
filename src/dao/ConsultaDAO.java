
package dao;

import beans.Consulta;
import beans.ItemConsulta;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Maria Clara
 */
public class ConsultaDAO {
        private Conexao conexao;
    private Connection conn;

    public ConsultaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Consulta c, List<ItemConsulta> itens) throws SQLException {
        String sql = "INSERT INTO consulta (id_paciente, id_medico, data_hora, status, observacoes) VALUES (?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            int idNovo;
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, c.getIdPaciente());
                stmt.setInt(2, c.getIdMedico());
                stmt.setTimestamp(3, Timestamp.valueOf(c.getDataHora()));
                stmt.setString(4, c.getStatus());
                stmt.setString(5, c.getObservacoes());
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    rs.next();
                    idNovo = rs.getInt(1);
                }
            }
            inserirItens(idNovo, itens);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void atualizar(Consulta c) throws SQLException {
        String sql = "UPDATE consulta SET id_paciente=?, id_medico=?, data_hora=?, status=?, observacoes=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, c.getIdPaciente());
            stmt.setInt(2, c.getIdMedico());
            stmt.setTimestamp(3, Timestamp.valueOf(c.getDataHora()));
            stmt.setString(4, c.getStatus());
            stmt.setString(5, c.getObservacoes());
            stmt.setInt(6, c.getId());
            stmt.execute();
        }
    }
    
    public void inserir(Consulta c) throws SQLException {
        String sql = "INSERT INTO consulta (id_paciente, id_medico, data_hora, status, observacoes) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, c.getIdPaciente());
            stmt.setInt(2, c.getIdMedico());
            stmt.setTimestamp(3, Timestamp.valueOf(c.getDataHora()));
            stmt.setString(4, c.getStatus());
            stmt.setString(5, c.getObservacoes());
            stmt.execute();
        }
    }

    public void excluir(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM consulta WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    public Consulta getConsulta(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM consulta WHERE id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Consulta c = new Consulta();
                    c.setId(rs.getInt("id"));
                    c.setIdPaciente(rs.getInt("id_paciente"));
                    c.setIdMedico(rs.getInt("id_medico"));
                    c.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                    c.setStatus(rs.getString("status"));
                    c.setObservacoes(rs.getString("observacoes"));
                    return c;
                }
            }
        }
        return null;
    }

    public List<Consulta> listar() throws SQLException {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.id_paciente, c.id_medico, p.nome AS paciente, m.nome AS medico, "
                   + "c.data_hora, c.status, c.observacoes "
                   + "FROM consulta c "
                   + "JOIN paciente p ON p.id = c.id_paciente "
                   + "JOIN medico m ON m.id = c.id_medico "
                   + "ORDER BY c.data_hora";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id"));
                c.setIdPaciente(rs.getInt("id_paciente"));
                c.setIdMedico(rs.getInt("id_medico"));
                c.setNomePaciente(rs.getString("paciente"));
                c.setNomeMedico(rs.getString("medico"));
                c.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                c.setStatus(rs.getString("status"));
                c.setObservacoes(rs.getString("observacoes"));
                lista.add(c);
            }
        }
        return lista;
    }
}
