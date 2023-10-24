package com.api.travelsisters.service.autenticacao.dto;

import com.api.travelsisters.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsuarioDetalhesDto implements UserDetails{
    private final String nome;
    private final String email;
    private final String senha;

    public UsuarioDetalhesDto(UsuarioModel usuarioModel) {
        this.nome = usuarioModel.getNome();
        this.email = usuarioModel.getEmail();
        this.senha = usuarioModel.getSenha();
    }
    public String getNome(){
        return nome;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
    }
    @Override
    public String getPassword(){
        return senha;
    }
    @Override
    public String getUsername(){
        return email;
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
}
