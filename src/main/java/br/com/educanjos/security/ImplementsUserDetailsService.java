package br.com.educanjos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.educanjos.models.entities.Usuario;
import br.com.educanjos.repositories.UsuarioRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		
		return usuario;
	}

}
