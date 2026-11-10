package dao;

import beans.ItemConsulta;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemConsultaDAO {
    private Conexao conexao;
    private Connection conn;

    public ItemConsultaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(ItemConsulta i) throws SQLException {
        String sql = "INSERT INTO item_consulta (id_consulta, id_procedimento, quantidade, valor_unitario) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, i.getIdConsulta());
            stmt.setInt(2, i.getIdProcedimento());
            stmt.setInt(3, i.getQuantidade());
            stmt.setDouble(4, i.getValorUnitario());
            stmt.execute();
        }
    }

    public void excluir(int idItem) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM item_consulta WHERE id=?")) {
            stmt.setInt(1, idItem);
            stmt.execute();
        }
    }

    public List<ItemConsulta> listarPorConsulta(int idConsulta) throws SQLException {
        List<ItemConsulta> lista = new ArrayList<>();
        String sql = "SELECT i.id, i.id_consulta, i.id_procedimento, p.nome, i.quantidade, i.valor_unitario "
                   + "FROM item_consulta i "
                   + "JOIN procedimento p ON p.id = i.id_procedimento "
                   + "WHERE i.id_consulta = ? ORDER BY i.id";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idConsulta);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemConsulta i = new ItemConsulta();
                    i.setId(rs.getInt("id"));
                    i.setIdConsulta(rs.getInt("id_consulta"));
                    i.setIdProcedimento(rs.getInt("id_procedimento"));
                    i.setNomeProcedimento(rs.getString("nome"));
                    i.setQuantidade(rs.getInt("quantidade"));
                    i.setValorUnitario(rs.getDouble("valor_unitario"));
                    lista.add(i);
                }
            }
        }
        return lista;
    }
}