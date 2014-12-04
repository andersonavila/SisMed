package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

/**
 * Servlet implementation class ControleUsuarioServlet
 */
@WebServlet("/ControleUsuarioServlet")
public class ControleUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BRANCO = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

    	String login = request.getParameter("login");
    	String senha = request.getParameter("senha");
    	String nome = request.getParameter("nome");
    	String cpf = request.getParameter("cpf");
    	String dataNasc = request.getParameter("dataNasc");
    	String email = request.getParameter("email");
    	String telefone = request.getParameter("telefone");
    	String especialidade = request.getParameter("especialidade");
    	String sala = request.getParameter("sala");
		
    	
    	
    	
    	boolean status = false;
    	    	
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setCpf(cpf);
		usuario.setDataNasc(dataNasc);
		usuario.setEmail(email);
		usuario.setTelefone(telefone);
		usuario.setStatus(status);
		usuario.setEspecialidade(especialidade);
		usuario.setSala(sala);

		try {
			validar(usuario);
		} catch (Exception e) {
			request.setAttribute("erro", e);
			String url = "/erros.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
		try {
			UsuarioDAO.registraUsuario(usuario);
			request.setAttribute("usuario", usuario);
			String url = "/sucessoRegistrarUsuario.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			request.setAttribute("erro", e);
			String url = "/erros.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}

	}

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
//		if (usuario.getEspecialidade() == null || usuario.getEspecialidade().equals(BRANCO)) {
//		 	erros.add("É obrigatório preencher a especialidade.");
//		}
//		if (usuario.getSala() == null || usuario.getSala().equals(BRANCO)) {
//		 	erros.add("É obrigatório preencher a sala.");
//		}
		if (erros.size() != 0) {
		 	throw new Exception(erros.toString());
		}
		if (erros.size() != 0) {
			throw new Exception(erros.toString());
		}
	}
}
