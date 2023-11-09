package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/microservicio_usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * retorna todos los usuarios
     * @return lista de usuarios
     */
    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> getAllUsuarios(){
       return usuarioRepository.findAll();
    }

    /**
     * retorna un usuario por id
     * @param id
     * @return usuario correspondiente al id o null si no se encuentra
     */
    @GetMapping("/usuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario getUsuario(@PathVariable int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * actualizar num telefono
     * @param id
     * @param num
     */
    @PutMapping("/actualizarNum/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void actualizarNum(@PathVariable int id, @RequestBody String num){
        Usuario usuarioActual = usuarioRepository.findById(id).orElse(null);
        if(usuarioActual != null){
            usuarioActual.setNum_de_celular(num);
            usuarioRepository.save(usuarioActual);
        }
    }

    /**
     * actualizar email
     * @param id
     * @param email
     */
    @PutMapping("/actualizarEmail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void actualizarEmail(@PathVariable int id, @RequestBody String email){
        Usuario usuarioActual = usuarioRepository.findById(id).orElse(null);
        if(usuarioActual!=null){
            usuarioActual.setEmail(email);
            usuarioRepository.save(usuarioActual);
        }
    }

    /**
     * borra un usuario
     * @param id
     */
    @DeleteMapping("/eliminarUsuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void eliminarUsuario(@PathVariable int id){
        usuarioRepository.delete(usuarioRepository.findById(id).orElse(null));
    }


}

