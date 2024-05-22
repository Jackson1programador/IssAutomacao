package com.auomacaoISSFortaleza.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auomacaoISSFortaleza.demo.domain.model.GrupoPermissao;

@Repository
public interface GrupoPermissaoRepository extends JpaRepository<GrupoPermissao, Long> {

}
