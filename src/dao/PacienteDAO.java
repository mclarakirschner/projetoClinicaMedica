package dao;

import beans.Paciente;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private Conexao conexao;
    private Connection conn;

    public PacienteDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Paciente p) throws SQLException {
        String sql = "INSERT INTO paciente (nome, cpf, telefone, data_nascimento) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            stmt.setDate(4, p.getDataNascimento());
            stmt.execute();
        }
    }

    public void atualizar(Paciente p) throws SQLException {
        String sql = "UPDATE paciente SET nome=?, cpf=?, telefone=?, data_nascimento=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            stmt.setDate(4, p.getDataNascimento());
            stmt.setInt(5, p.getId());
            stmt.execute();
        }
    }

    public void excluir(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM paciente WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    public Paciente getPaciente(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM paciente WHERE id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return montar(rs);
            }
        }
        return null;
    }

    public List<Paciente> listar() throws SQLException {
        List<Paciente> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM paciente ORDER BY nome");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(montar(rs));
        }
        return lista;
    }

    private Paciente montar(ResultSet rs) throws SQLException {
        Paciente p = new Paciente();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setCpf(rs.getString("cpf"));
        p.setTelefone(rs.getString("telefone"));
        p.setDataNascimento(rs.getDate("data_nascimento"));
        return p;
    }
}