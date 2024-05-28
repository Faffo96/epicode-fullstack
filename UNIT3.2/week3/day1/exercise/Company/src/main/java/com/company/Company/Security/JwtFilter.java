package com.company.Company.Security;

import com.company.Company.Entity.Employee;
import com.company.Company.Exception.EmployeeNotFoundException;
import com.company.Company.Exception.UnauthorizedException;
import com.company.Company.Service.EmployeeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private EmployeeService employeeService;

    @Override //metodo per verificare che nella richiesta ci sia il token, altrimenti non si è autorizzati
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Error in authorization, token missing!");
        }

        String token = authHeader.substring(7);

        jwtTool.verifyToken(token);

        int employeeId = jwtTool.getIdFromToken(token);

        Employee employee =  employeeService.getEmployeeById(employeeId);

        if (employee != null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else{
            throw new EmployeeNotFoundException("Employee with id=" + employeeId + " not found");
        }
        filterChain.doFilter(request, response);
    }

    @Override //permette di non effettuare l'autenticazione per usare i servizi di autenticazione
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}