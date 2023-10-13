package filmesdao;

import filmeconexao.Conexao;
import filmedados.Filmes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FilmesDAO {

    private Conexao filmeconexao;
    private Connection conn;

    public FilmesDAO() {
        this.filmeconexao = new Conexao();
        this.conn = this.filmeconexao.getConexao();
    }

    public void Inserir(Filmes filme) {

        String sql = "INSERT INTO filmes(nome, datalancamento, categoria) VALUES "
                + "(?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, filme.getNome());
            stmt.setDate(2, filme.getDatalancamento());
            stmt.setString(3, filme.getCategoria());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao inserir o filme: " + e.getMessage());
        }
    }

    public void Editar(Filmes filme) {
        String sql = "UPDATE filmes SET nome=?, datalancamento=?, categoria=? WHERE id=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, filme.getNome());
            stmt.setDate(2, filme.getDatalancamento());
            stmt.setString(3, filme.getCategoria());
            stmt.setInt(4, filme.getId());

            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao editar o filme: " + e.getMessage());
        }
    }

    public void Excluir(int id) {

        String sql = "DELETE FROM filmes WHERE id = ?";
        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();

        } catch (Exception e) {
            System.out.println("Erro ao excluir o filme: " + e.getMessage());
        }

    }

    public Filmes getFilmesById(int id){
        String sql = "SELECT * FROM filmes WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            Filmes filme = new Filmes();
            if (rs.next()) {
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDatalancamento(rs.getDate("datalancamento"));
                filme.setCategoria(rs.getString("categoria"));
                return filme;
            } else {
                return null;
            }
        } catch(Exception e){
            System.out.println("Erro by Id: " + e.getMessage());
            return null;
        }
    }
    
    public Filmes getFilmesByNome(String nome){
        String sql = "SELECT * FROM filmes WHERE nome LIKE ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            Filmes filme = new Filmes();
            if (rs.next()) {
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDatalancamento(rs.getDate("datalancamento"));
                filme.setCategoria(rs.getString("categoria"));
                return filme;
            } else {
                return null;
            }
        } catch(Exception e){
            System.out.println("Erro by Nome: " + e.getMessage());
            return null;
        }
        
    }
    
    public List<Filmes> getFilmes(String filtro) {
        String sql = " SELECT * FROM filmes ";
        if (!filtro.isEmpty()){
            sql = sql + " WHERE nome LIKE ? ";
        }
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            
            if (!filtro.isEmpty()){
                stmt.setString(1, "%" + filtro + "%");
            }
            
            ResultSet rs = stmt.executeQuery();
            
            List<Filmes> listaFilmes = new ArrayList<>();
            
            while(rs.next()) {
                Filmes filme = new Filmes();
            
                filme.setId(rs.getInt("id"));
                filme.setNome(rs.getString("nome"));
                filme.setDatalancamento(rs.getDate("datalancamento"));
                filme.setCategoria(rs.getString("categoria"));
                listaFilmes.add(filme);
            }
            return listaFilmes;
        }catch(Exception e){
            return null;
        }
    }

      
    
}
