package com.usuario.usuarioservice.Controller;

import com.usuario.usuarioservice.DTO.CuentaDTO;
import com.usuario.usuarioservice.Model.Cuenta;
import com.usuario.usuarioservice.Model.CuentasDeUsuario;
import com.usuario.usuarioservice.Model.Usuario;
import com.usuario.usuarioservice.Repository.CuentaRepository;
import com.usuario.usuarioservice.Repository.CuentasDeUsuarioRepository;
import com.usuario.usuarioservice.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/microservicio_usuario")
public class CuentasDeUsuarioController {

    @Autowired
    CuentasDeUsuarioRepository cuentasDeUsuarioRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * enlazo el usuario que se encuentra logueado en la plataforma con la cuenta que coincida con los datos que
     * son mandados por parametro
     * @param usuario
     * @param user
     * @param pass
     */
    @PostMapping("/enlazarCuenta/{user}/{pass}")
    @ResponseStatus(HttpStatus.OK)
    public void enlazarCuenta(@RequestBody Usuario usuario, @PathVariable String user, @PathVariable String pass){

        Cuenta cuenta = cuentaRepository.buscarCuenta(user,pass);
        if( cuenta != null){
            CuentasDeUsuario relacion = new CuentasDeUsuario();
            relacion.setUsuario(usuario);
            relacion.setCuenta(cuenta);
            cuentasDeUsuarioRepository.save(relacion);
        }
    }

    /**
     * Lista de todas las cuentas que esten asociadas con un usuario X
     * @param id
     * @return
     */
    @GetMapping("/cuentasAsociadasAusuario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaDTO> cuentasDelUsuario(@PathVariable int id){
       return cuentasDeUsuarioRepository.cuentasDelUsuarioID(id);
    }

}
