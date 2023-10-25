package com.api.travelsisters.service.autenticacao;

import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.service.autenticacao.dto.UsuarioDetalhesDto;
import com.api.travelsisters.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        UsuarioModel usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt == null){
            throw new UsernameNotFoundException(String.format("usuario:%s nao encontrado",username));
        }
        return new UsuarioDetalhesDto(usuarioOpt);
    }
}
