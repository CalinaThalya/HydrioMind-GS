package br.com.fiap.hydriomind.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.hydriomind.entity.Usuario;
import br.com.fiap.hydriomind.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();
    }

    public String obterUsuarioNome(Long id) {
        return usuarioRepository.findById(id)
                .map(Usuario::getUsername) // Aqui supondo que o método getUsername() existe em Usuario
                .orElse("Usuário não encontrado");
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}