package com.algaworks.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    http.authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated()
        .and().httpBasic().and().csrf().disable().headers().cacheControl().disable();

    // csrf - é um tipo de proteção colocado na aplicação para evitar ataques (pesquisar sobre
    // isso).

  }

}
