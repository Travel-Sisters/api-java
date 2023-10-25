package com.api.travelsisters.service.dto;

import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.service.autenticacao.dto.UsuarioTokenDto;
import org.springframework.web.bind.annotation.CrossOrigin;

public class UsuarioMapper {
    public static UsuarioModel of(UsuarioCriacaoDto usuarioCriacaoDto) {
        UsuarioModel usuario = new UsuarioModel();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());

        return usuario;
    }
    public static UsuarioTokenDto of(UsuarioModel usuario, String token){
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }


}
