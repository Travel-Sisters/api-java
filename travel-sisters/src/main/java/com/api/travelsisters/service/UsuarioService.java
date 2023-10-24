package com.api.travelsisters.service;

import com.api.travelsisters.dto.LoginDTO;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.service.autenticacao.configuration.security.jwt.GerenciadorTokenJwt;
import com.api.travelsisters.service.autenticacao.dto.UsuarioTokenDto;
import com.api.travelsisters.service.dto.UsuarioCriacaoDto;
import com.api.travelsisters.service.dto.UsuarioMapper;
import com.api.travelsisters.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;
import com.api.travelsisters.service.autenticacao.dto.UsuarioLoginDto;
@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto) {
        final UsuarioModel novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    public ResponseEntity<UsuarioTokenDto> autenticar(LoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        UsuarioModel usuarioAutenticado = usuarioRepository.findByEmail(usuarioLoginDto.getEmail());

        if(usuarioAutenticado == null){
            return ResponseEntity.status(403).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return ResponseEntity.status(200).body(UsuarioMapper.of(usuarioAutenticado, token));
    }
}


