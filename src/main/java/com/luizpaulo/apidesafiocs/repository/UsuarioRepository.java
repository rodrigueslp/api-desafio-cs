package com.luizpaulo.apidesafiocs.repository;

import com.luizpaulo.apidesafiocs.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email)")
    Usuario buscaPorEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email) AND LOWER(u.password) = LOWER(:senha)")
    Usuario buscaPorEmailESenha(@Param("email") String email, @Param("senha") String senha);

}
