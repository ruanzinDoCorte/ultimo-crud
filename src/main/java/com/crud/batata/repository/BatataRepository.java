package com.crud.batata.repository;

//importa o JpaRepository, que fornece métodos padrão para acesso ao banco de dados
import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.batata.model.BatataModel;

//define o BatataRepository que estende JpaRepository, parametriza com o BatataModel e o tipo Long (ID)
public interface BatataRepository extends JpaRepository<BatataModel, Long> {

}
