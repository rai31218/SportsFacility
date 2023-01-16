package com.sports.facility.security;

import java.util.Collection;
import java.util.Objects;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sports.facility.model.Players;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;


	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String i,String email, String password) {
		this.id = i;
		this.email = email;
		this.password = password;
	}

	public static UserDetailsImpl build(Players player) {
//		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRoles().getRoleName()));
//				user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
//				.collect(Collectors.toList());

		return new UserDetailsImpl(
				player.getId(),  
				player.getEmail(),
				player.getPassword()
				
				);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}


	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorities, email, id, password);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	
	

	
}
