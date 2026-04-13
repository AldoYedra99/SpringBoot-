package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class UsuarioController {

    @GetMapping("/usuarios")
    public List<Usuarios> obtenerUsuarios(){

        Scanner obj = new Scanner(System.in);
        List<Usuarios> usuarios = new ArrayList<>();

        System.out.print("Número de usuarios: ");
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
        return usuarios;
    }
}
