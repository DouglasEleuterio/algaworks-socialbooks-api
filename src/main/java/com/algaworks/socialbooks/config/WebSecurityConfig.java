package com.algaworks.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.algaworks.socialbooks.enumeration.AuthenticationTypeEnum;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${authentication.type}")
  private String authenticationType;

  @Value("${authentication.user}")
  private String authenticationUser;

  @Value("${authentication.password}")
  private String authenticationPassword;

  @Value("${authentication.role}")
  private String authenticationRole;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    if (AuthenticationTypeEnum.fromName(authenticationType).equals(AuthenticationTypeEnum.MEMORY)) {
      auth.inMemoryAuthentication().withUser(authenticationUser).password(authenticationPassword)
          .roles(authenticationRole);
    }
    // TODO implementar outros maneiras de autenticação. Ex: Banco de dados.
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests() //
        // Essa linha é para não solicitar autenticação no console do H@
        .antMatchers("/h2-console/**").permitAll() //
        // Essa linha é para resolver a questão do CORS para permitira que as chamadas com OPTIONS
        // não necessite ser autenticada
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //
        // Essa linha é para informar que todas as requisições requerem autenticação
        .anyRequest().authenticated() //
        // Essa linha informa o tipo de autenticação utilizado nesse caso BASIC
        .and().httpBasic() //
        // Desabilita o CSRF
        .and().csrf().disable()
        // Desabilita o cache control do Spring Security para permitir fazer cache nas apis
        .headers().cacheControl().disable();

    // csrf - é um tipo de proteção colocado na aplicação para evitar ataques (pesquisar sobre
    // isso).

  }

}
