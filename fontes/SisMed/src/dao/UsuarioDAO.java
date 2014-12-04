package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UsuarioDAO {

	private static Connection conexao = FabricaDeConexoes.getConnection();

	/**
	 * Adiciona um novo usuario na tabela de usuarios
	 * 
	 * @param usuario
	 *            Objeto usuario a ser adicionado.
	 * @throws Exception
	 *             Caso o usuario já exista.
	 */
	public static void registraUsuario(Usuario usuario) throws Exception {
		String sql = "insert into usuarios (login, senha, nome, cpf, dataNascimento, email, telefone, estado, especialidade, sala) values (?,?,?,?,?,?,?,?,?,?)";
		if (!existeUsuario(usuario)) {
			try {
				// prepared statement para inserção
				PreparedStatement stmt = conexao.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, usuario.getLogin());
				stmt.setString(2, usuario.getSenha());
				stmt.setString(3, usuario.getNome());
				stmt.setString(4, usuario.getCpf());
				stmt.setString(5, usuario.getDataNasc());
				stmt.setString(6, usuario.getEmail());
				stmt.setString(7, usuario.getTelefone());
				stmt.setBoolean(8, usuario.isStatus());
				stmt.setString(9, usuario.getEspecialidade());
				stmt.setString(10, usuario.getSala());

				// executa
				stmt.execute();
				System.out.println("Usuario " + usuario.getNome()
						+ " adicionado com sucesso.");
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new Exception("Usuario " + usuario.getNome() + " já existe!");
		}
	}

	/**
	 * Verifica se um usuario já existe no sistema.
	 * @param usuario Usuario que se deseja verificar.
	 * @return True caso o usuario já exista, False caso não exista.
	 */
	private static boolean existeUsuario(Usuario usuario) {
		try {
			buscarPeloLogin(usuario.getLogin());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Valida os campos do usuario
	 * 
	 * @param usuario
	 *            Usuario que se deseja validar
	 * @throws Exception
	 *             Caso algum campo não esteja preenchido corretamente.
	 */
	
	/*
	private static void validar(Usuario usuario) throws Exception {
		List<String> erros = new ArrayList<String>();
		if (usuario.getLogin() == null || usuario.getLogin().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher o login.");
		}
		if (usuario.getSenha() == null || usuario.getSenha().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher a senha.");
		}
		if (usuario.getNome() == null || usuario.getNome().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher o nome.");
		}
		if (usuario.getCpf() == null || usuario.getCpf().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher o CPF.");
		}
		if (usuario.getDataNasc() == null || usuario.getDataNasc().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher a data de nascimento.");
		}
		if (usuario.getEmail() == null || usuario.getEmail().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher o E mail.");
		}
		if (usuario.getTelefone() == null || usuario.getTelefone().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher o telefone.");
		}
		if (usuario.getEspecialidade() == null || usuario.getEspecialidade().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher a especialidade.");
		}
		if (usuario.getSala() == null || usuario.getSala().equals(BRANCO)) {
		 	erros.add("É obrigatório preencher a sala.");
		}
		if (erros.size() != 0) {
		 	throw new Exception(erros.toString());
		}
		if (erros.size() != 0) {
			throw new Exception(erros.toString());
		}
	}
*/
	/**
	 * Busca um usuario a partir do seu login
	 * 
	 * @param login
	 *            Login do usuario que se deseja buscar
	 *            
	 * @return Instância do usuario com o login informado, ou null caso o usuario não
	 *         seja encontrado..
	 * @throws Exception
	 *             Caso não seja encontrado o usuario.
	 *             
	 */
	public static Usuario buscarPeloLogin(String login) throws Exception {
		try {
			Usuario usuario = null;
			PreparedStatement stmt = conexao
					.prepareStatement("select * from usuarios where login = ?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				// cria um objeto Usuario
				usuario = new Usuario();
				usuario.setLogin(login);
				usuario.setSenha(rs.getString("senha"));
				usuario.setNome(rs.getString("nome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setDataNasc(rs.getString("dataNascimento"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setStatus(rs.getBoolean("estado"));
				usuario.setEspecialidade(rs.getString("especialidade"));
				usuario.setSala(rs.getString("sala"));
			} else {
				throw new Exception("Usuario com " + login + " não encontrado.");
			}
			rs.close();
			stmt.close();
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

