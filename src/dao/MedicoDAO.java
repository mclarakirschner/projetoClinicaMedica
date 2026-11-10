package dao;

import beans.Medico;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    private Conexao conexao;
    private Connection conn;

    public MedicoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Medico m) throws SQLException {
        String sql = "INSERT INTO medico (nome, crm, especialidade, telefone) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.setString(3, m.getEspecialidade());
            stmt.setString(4, m.getTelefone());
            stmt.execute();
        }
    }

    public void atualizar(Medico m) throws SQLException {
        String sql = "UPDATE medico SET nome=?, crm=?, especialidade=?, telefone=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.setString(3, m.getEspecialidade());
            stmt.setString(4, m.getTelefone());
            stmt.setInt(5, m.getId());
            stmt.execute();
        }
    }

    public void excluir(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM medico WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    public Medico getMedico(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM medico WHERE id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return montar(rs);
            }
        }
        return null;
    }

    public List<Medico> listar() throws SQLException {
        List<Medico> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM medico ORDER BY nome");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(montar(rs));
        }
        return lista;
    }

    private Medico montar(ResultSet rs) throws SQLException {
        Medico m = new Medico();
        m.setId(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setCrm(rs.getString("crm"));
        m.setEspecialidade(rs.getString("especialidade"));
        m.setTelefone(rs.getString("telefone"));
        return m;
    }
}