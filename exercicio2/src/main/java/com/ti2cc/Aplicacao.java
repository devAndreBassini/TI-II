package com.ti2cc;

import java.util.Scanner;

public class Aplicacao {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		XDAO dao = new XDAO();

		// Conectar ao banco de dados
		if (dao.conectar()) {
			System.out.println("Conectado ao banco de dados!");

			int opcao = 0;
			do {
				System.out.println("\nMenu:");
				System.out.println("1) Inserir");
				System.out.println("2) Listar");
				System.out.println("3) Atualizar");
				System.out.println("4) Excluir");
				System.out.println("5) Sair");
				System.out.print("Escolha uma opção: ");

				opcao = scanner.nextInt();
				scanner.nextLine(); // Limpa o buffer do scanner

				switch (opcao) {
				case 1:
					// Inserir novo usuário
					System.out.print("Código: ");
					int codigo = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Login: ");
					String login = scanner.nextLine();
					System.out.print("Senha: ");
					String senha = scanner.nextLine();
					System.out.print("Sexo (M/F): ");
					char sexo = scanner.nextLine().charAt(0);

					XUsuario novoUsuario = new XUsuario(codigo, login, senha, sexo);
					if (dao.inserirUsuario(novoUsuario)) {
						System.out.println("Usuário inserido com sucesso!");
					} else {
						System.out.println("Erro ao inserir usuário.");
					}
					break;

				case 2:
					// Listar todos os usuários
					XUsuario[] usuarios = dao.getUsuarios();
					if (usuarios != null) {
						for (XUsuario usuario : usuarios) {
							System.out.println(usuario);
						}
					} else {
						System.out.println("Nenhum usuário encontrado.");
					}
					break;

				case 3:
					// Atualizar usuário existente
					System.out.print("Código do usuário a ser atualizado: ");
					int codigoAtualizar = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Novo login: ");
					String novoLogin = scanner.nextLine();
					System.out.print("Nova senha: ");
					String novaSenha = scanner.nextLine();
					System.out.print("Novo sexo (M/F): ");
					char novoSexo = scanner.nextLine().charAt(0);

					XUsuario usuarioAtualizado = new XUsuario(codigoAtualizar, novoLogin, novaSenha, novoSexo);
					if (dao.atualizarUsuario(usuarioAtualizado)) {
						System.out.println("Usuário atualizado com sucesso!");
					} else {
						System.out.println("Erro ao atualizar usuário.");
					}
					break;

				case 4:
					// Excluir usuário
					System.out.print("Código do usuário a ser excluído: ");
					int codigoExcluir = scanner.nextInt();

					if (dao.excluirUsuario(codigoExcluir)) {
						System.out.println("Usuário excluído com sucesso!");
					} else {
						System.out.println("Erro ao excluir usuário.");
					}
					break;

				case 5:
					// Sair
					System.out.println("Encerrando a aplicação...");
					break;

				default:
					System.out.println("Opção inválida!");
					break;
				}
			} while (opcao != 5);

			// Fechar conexão com o banco de dados
			if (dao.close()) {
				System.out.println("Conexão encerrada.");
			} else {
				System.out.println("Erro ao encerrar conexão.");
			}
		} else {
			System.out.println("Não foi possível conectar ao banco de dados.");
		}

		scanner.close();
	}
}
