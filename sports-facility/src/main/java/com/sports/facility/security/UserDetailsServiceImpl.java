package com.sports.facility.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.sports.facility.model.Players;
import com.sports.facility.repository.PlayersRepository;


@Component
class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   PlayersRepository playerRepo;
   
    @Override
    public UserDetails loadUserByUsername(String email)  {
        Players player = playerRepo.findByEmail(email);
        return UserDetailsImpl.build(player);
}

}