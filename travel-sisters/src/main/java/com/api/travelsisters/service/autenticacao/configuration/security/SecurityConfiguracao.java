package com.api.travelsisters.service.autenticacao.configuration.security;

import com.api.travelsisters.service.autenticacao.AutenticacaoService;
import com.api.travelsisters.service.autenticacao.configuration.security.jwt.GerenciadorTokenJwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguracao {
    private static final String ORIGENS_PERMITIDAS = "*";

    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired AutenticacaoEntryPoint autenticacaoEntryPoint;

    private  static final AntPathRequestMatcher[] URLS_PERMITIDAS = {
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),

            new AntPathRequestMatcher("/usuarios/entrar"),
            new AntPathRequestMatcher("/usuarios/cadastrar"),
            new AntPathRequestMatcher("/usuarios/listar"),
            new AntPathRequestMatcher("/usuarios/buscarPorId/{id}"),
            new AntPathRequestMatcher("/usuarios/alterar/{idUsuario}"),
            new AntPathRequestMatcher("/usuarios/deletar/{id}"),
            new AntPathRequestMatcher("/usuarios/verificar-perfil/{idUsuario}"),

            new AntPathRequestMatcher("/motoristas/listar/{id}"),
            new AntPathRequestMatcher("/motoristas/alterar/{idMotorista}"),
            new AntPathRequestMatcher("/motoristas/buscarPorId/{id}"),
            new AntPathRequestMatcher("/motoristas/cadastrar/{idUsuario}"),
            new AntPathRequestMatcher("/motoristas/deletar/{id}"),

            new AntPathRequestMatcher("/viagens/listar"),
            new AntPathRequestMatcher("/viagens/cadastrar/{idMotorista}"),
            new AntPathRequestMatcher("/viagens/alterar/{id}"),
            new AntPathRequestMatcher("/viagens/deletar/{id}"),
            new AntPathRequestMatcher("/viagens/csv/{idMotorista}"),
            new AntPathRequestMatcher("/viagens/txt/{idMotorista}"),
            new AntPathRequestMatcher("/viagens/pilha/{idMotorista}"),
            new AntPathRequestMatcher("/viagens/fila/{idMotorista}"),
            new AntPathRequestMatcher("/viagens/listarPorId/{idMotorista}"),
            new AntPathRequestMatcher("/viagens/listarPorIdUsuario/{idUsuario}"),
            new AntPathRequestMatcher("/viagens/cadastrarUsuarioViagem/{idViagem}/{idUsuario}"),

            new AntPathRequestMatcher("/chat/publicar"),
            new AntPathRequestMatcher("/chat/{idViagem}"),
            new AntPathRequestMatcher("/chat/{idViagem}/ultima"),

            new AntPathRequestMatcher("/enderecos/"),

            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/api/public/authenticate"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/usuarios/login/**"),
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/error/**")
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer<HttpSecurity>::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(URLS_PERMITIDAS)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(autenticacaoEntryPoint))
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(new AutenticacaoProvider(autenticacaoService, passwordEncoder()));
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AutenticacaoEntryPoint jwtAuthenticationEntryPointBean() {
        return new AutenticacaoEntryPoint();
    }

    @Bean
    public AutenticacaoFilter jwtAuthenticationFilterBean() {
        return new AutenticacaoFilter(autenticacaoService, jwtAuthenticationUtilBean());
    }

    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean() {
        return new GerenciadorTokenJwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuracao = new CorsConfiguration();
        configuracao.applyPermitDefaultValues();
        configuracao.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.TRACE.name()));

        configuracao.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));

        UrlBasedCorsConfigurationSource origem = new UrlBasedCorsConfigurationSource();
        origem.registerCorsConfiguration("/**", configuracao);

        return origem;
    }
}


