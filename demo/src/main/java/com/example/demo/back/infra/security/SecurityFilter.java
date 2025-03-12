package com.example.demo.back.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.back.DAO.ICliente;
import com.example.demo.back.DAO.IFornecedor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ICliente clienteDao;

    @Autowired
    private IFornecedor fornecedorDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        // Recuperar o token da requisição
        var token = recoverToken(request);
        
        if (token != null) {
            // Validar o token JWT
            String login = tokenService.validateToken(token);
            
            if (login != null) {
                // Buscar o usuário pelo login (primeiro Cliente, depois Fornecedor)
                UserDetails user = clienteDao.findByEmail(login);
                
                if (user == null) {
                    user = fornecedorDao.findByEmailCorporativo(login);
                }

                // Se o usuário foi encontrado, configurar a autenticação
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        
        // Continuar a execução do filtro
        filterChain.doFilter(request, response);
    }

    /**
     * Recuperar o token JWT do cabeçalho da requisição.
     */
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        
        // Verificar se o cabeçalho Authorization contém um token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        
        return null;
    }
}
