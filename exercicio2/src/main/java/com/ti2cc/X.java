package com.ti2cc;

public class X {

	public static void main(String[] args) {

		XDAO xdao = new XDAO();

		xdao.conectar();

		// Inserir um elemento na tabela
		XUsuario usuario = new XUsuario(11, "pablo", "pablo", 'M');
		if (xdao.inserirUsuario(usuario) == true) {
			System.out.println("Inserção com sucesso -> " + usuario.toString());
		}

		// Mostrar usuários do sexo masculino
		System.out.println("==== Mostrar usuários do sexo masculino === ");
		XUsuario[] usuarios = xdao.getUsuariosMasculinos();
		for (int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}

		// Atualizar usuário
		usuario.setSenha("nova senha");
		xdao.atualizarUsuario(usuario);

		// Mostrar usuários do sexo masculino
		System.out.println("==== Mostrar usuários === ");
		usuarios = xdao.getUsuarios();
		for (int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}

		// Excluir usuário
		xdao.excluirUsuario(usuario.getCodigo());

		// Mostrar usuários
		usuarios = xdao.getUsuarios();
		System.out.println("==== Mostrar usuários === ");
		for (int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}

		xdao.close();
	}
}