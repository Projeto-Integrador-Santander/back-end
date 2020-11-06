package br.com.educanjos.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.educanjos.models.entities.Login;
import br.com.educanjos.repositories.LoginRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Login login = loginRepository.findByEmail(email);
		
		if(login == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		
		return new User(login.getEmail(), login.getPassword(), login.getAuthorities());

	}

}