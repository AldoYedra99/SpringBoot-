package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public List<Usuarios> obtenerUsuarios(){

        //Scanner obj = new Scanner(System.in);
        List<Usuarios> usuarios = new ArrayList<>();

       /* System.out.print("Número de usuarios: ");
        int cantidad = obj.nextInt();
        obj.nextLine();

        for (int i=0; i<cantidad; i++){
            System.out.println("Usuario " + (i+1));

            System.out.print("ID: ");
            int id = obj.nextInt();
            obj.nextLine();

            System.out.print("Nombre: ");
            String nombre = obj.nextLine();

            System.out.print("Email: ");
            String email = obj.nextLine();

            usuarios.add(new Usuarios(id,nombre,email));
        }
        return usuarios;*/
        usuarios.add(new Usuarios(1,"Aldo","aldo@gmail.com"));
        usuarios.add(new Usuarios(2,"Brenda","bren_sn@gmail.com"));

        return usuarios;
    }

    @PostMapping
    public String crearUsuarios(@RequestBody Usuarios usuarios){
        return "Usuario creado con exito: " +usuarios.getNombre();
    }

}
