package com.redsocial.backend.config;

import com.redsocial.backend.model.Post;
import com.redsocial.backend.model.User;
import com.redsocial.backend.repository.PostRepository;
import com.redsocial.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        
        // Solo ejecutar si no hay usuarios en la base de datos
        if (userRepository.count() == 0) {
            System.out.println("Iniciando seeder de datos...");
            
            // Crear usuarios de prueba
            User user1 = new User(
                    "juan_dev",
                    "juan@example.com",
                    passwordEncoder.encode("password123"),
                    "Juan",
                    "Pérez"
            );
            user1.setBio("Desarrollador Full Stack apasionado por la tecnología");
            user1.setProfileImageUrl("https://randomuser.me/api/portraits/men/1.jpg");
            
            User user2 = new User(
                    "maria_design",
                    "maria@example.com",
                    passwordEncoder.encode("password123"),
                    "María",
                    "García"
            );
            user2.setBio("Diseñadora UX/UI creativa y amante del arte digital");
            user2.setProfileImageUrl("https://randomuser.me/api/portraits/women/2.jpg");
            
            User user3 = new User(
                    "carlos_data",
                    "carlos@example.com",
                    passwordEncoder.encode("password123"),
                    "Carlos",
                    "López"
            );
            user3.setBio("Científico de datos enfocado en machine learning");
            user3.setProfileImageUrl("https://randomuser.me/api/portraits/men/3.jpg");
            
            User user4 = new User(
                    "ana_mobile",
                    "ana@example.com",
                    passwordEncoder.encode("password123"),
                    "Ana",
                    "Martínez"
            );
            user4.setBio("Desarrolladora móvil especializada en Flutter y React Native");
            user4.setProfileImageUrl("https://randomuser.me/api/portraits/women/4.jpg");
            
            User user5 = new User(
                    "pedro_backend",
                    "pedro@example.com",
                    passwordEncoder.encode("password123"),
                    "Pedro",
                    "Rodríguez"
            );
            user5.setBio("Backend Developer experto en Java Spring y microservicios");
            user5.setProfileImageUrl("https://randomuser.me/api/portraits/men/5.jpg");
            
            // Guardar usuarios
            user1 = userRepository.save(user1);
            user2 = userRepository.save(user2);
            user3 = userRepository.save(user3);
            user4 = userRepository.save(user4);
            user5 = userRepository.save(user5);
            
            // Crear posts de prueba
            Post post1 = new Post(
                    "¡Hola a todos! Acabo de terminar mi nuevo proyecto en React. " +
                    "Es increíble lo que se puede lograr con las nuevas herramientas de desarrollo.",
                    user1
            );
            
            Post post2 = new Post(
                    "Trabajando en un nuevo diseño para una app móvil. " +
                    "El proceso creativo es fascinante, cada día aprendo algo nuevo sobre UX/UI.",
                    user2
            );
            post2.setImageUrl("https://picsum.photos/400/300?random=1");
            
            Post post3 = new Post(
                    "Analizando datos de ventas con Python y Pandas. " +
                    "Los insights que se pueden obtener son realmente valiosos para el negocio.",
                    user3
            );
            
            Post post4 = new Post(
                    "Desarrollando una nueva funcionalidad en Flutter. " +
                    "Me encanta cómo esta tecnología permite crear apps nativas tan fácilmente.",
                    user4
            );
            post4.setImageUrl("https://picsum.photos/400/300?random=2");
            
            Post post5 = new Post(
                    "Implementando microservicios con Spring Boot. " +
                    "La arquitectura distribuida tiene sus desafíos pero los beneficios son enormes.",
                    user5
            );
            
            Post post6 = new Post(
                    "Compartiendo algunas reflexiones sobre desarrollo sostenible de software. " +
                    "Es importante pensar en el impacto a largo plazo de nuestras decisiones técnicas.",
                    user1
            );
            
            Post post7 = new Post(
                    "Nuevo mockup terminado! La clave está en entender realmente " +
                    "las necesidades del usuario antes de empezar a diseñar.",
                    user2
            );
            post7.setImageUrl("https://picsum.photos/400/300?random=3");
            
            Post post8 = new Post(
                    "Explorando algoritmos de machine learning para predicción de tendencias. " +
                    "Los resultados preliminares son muy prometedores.",
                    user3
            );
            
            // Guardar posts
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            postRepository.save(post4);
            postRepository.save(post5);
            postRepository.save(post6);
            postRepository.save(post7);
            postRepository.save(post8);
            
            System.out.println("Seeder completado exitosamente!");
            System.out.println("Usuarios creados: 5");
            System.out.println("Posts creados: 8");
            System.out.println("Credenciales de prueba:");
            System.out.println("- Usuario: juan_dev, Contraseña: password123");
            System.out.println("- Usuario: maria_design, Contraseña: password123");
            System.out.println("- Usuario: carlos_data, Contraseña: password123");
            System.out.println("- Usuario: ana_mobile, Contraseña: password123");
            System.out.println("- Usuario: pedro_backend, Contraseña: password123");
        } else {
            System.out.println("La base de datos ya contiene datos. Saltando seeder...");
        }
    }
}