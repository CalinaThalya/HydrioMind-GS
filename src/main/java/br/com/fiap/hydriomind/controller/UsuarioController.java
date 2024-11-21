package br.com.fiap.hydriomind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fiap.hydriomind.entity.Usuario;
import br.com.fiap.hydriomind.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "form"; 
    }

    @GetMapping("/req/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "signup"; // Retorna signup.html
    }

    @PostMapping("/req/signup")
    public String registerUser(Usuario usuario) {
        // Verifica se o usuário já existe
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            return "redirect:/req/signup?error=usuarioExistente";
        }

        // Criptografa a senha antes de salvar
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
        } else {
            return "redirect:/req/signup?error=senhaObrigatoria";
        }

        return "redirect:/req/login"; // Redireciona para a página de login
    }

    @GetMapping("/req/login")
    public String showLoginForm() {
        return "login"; // Retorna login.html
    }

    @GetMapping("/home")
    public String home() {
        return "home"; // Retorna home.html
    }
}
