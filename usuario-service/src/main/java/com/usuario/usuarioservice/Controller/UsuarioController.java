package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microservicio_usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    //retorna todos los usuarios
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> getAllUsuarios(){
       return usuarioRepository.findAll();
    }

    //retorna un usuario por id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario getUsuario(@PathVariable int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    //creo un usuario, no se puede crear un usuario con un correo electronico ya registrado en la BD.
    @PostMapping("/crearUsuario")
    @ResponseStatus(HttpStatus.OK)
    public void crearUsuario(@RequestBody Usuario usuario){
        if (usuarioRepository.findByEmail(usuario.getEmail()) == null) {
            usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }
    }
    //actualizar num telefono
    @PutMapping("/actualizarNum/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void actualizarNum(@PathVariable int id, @RequestBody int num){
        Usuario usuarioActual = usuarioRepository.findById(id).orElse(null);
        if(usuarioActual != null){
            usuarioActual.setNum_de_celular(num);
            usuarioRepository.save(usuarioActual);
        }
    }

    //actualizar email
    @PutMapping("/actualizarEmail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void actualizarEmail(@PathVariable int id, @RequestBody String email){
        Usuario usuarioActual = usuarioRepository.findById(id).orElse(null);
        if(usuarioActual!=null){
            usuarioActual.setEmail(email);
            usuarioRepository.save(usuarioActual);
        }
    }

    //borra un usuario.
    @DeleteMapping("/eliminarUsuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarUsuario(@PathVariable int id){
        usuarioRepository.delete(usuarioRepository.findById(id).orElse(null));
    }
}

