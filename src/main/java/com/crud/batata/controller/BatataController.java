package com.crud.batata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.batata.model.BatataModel;
import com.crud.batata.repository.BatataRepository;


@RestController
@RequestMapping("/batatas")
public class BatataController {

	//indica automaticamente uma instância do repositório para o banco de dados//
    @Autowired
    private BatataRepository batataRepository;

    
    @PostMapping
    public ResponseEntity<BatataModel> criarBatata(@RequestBody BatataModel batata) {
    	//salva a nova batata no banco de dados através do repositório
        BatataModel novaBatata = batataRepository.save(batata);
        //retorna  a batata salva com o HTTP 201 (created)
        return new ResponseEntity<>(novaBatata, HttpStatus.CREATED);
    }

    
    @GetMapping
    public ResponseEntity<List<BatataModel>> listarBatatas() {
    	//serve para recuperar todas as batatas do banco de dados
        List<BatataModel> batatas = batataRepository.findAll();
        //retorna a lista de batata com o HTTP 200 (ok)
        return new ResponseEntity<>(batatas, HttpStatus.OK);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<BatataModel> buscarBatataPorId(@PathVariable Long id) {
    	//busca a batata pelo ID, retornando um optional
        Optional<BatataModel> batata = batataRepository.findById(id);
        // verifica se a batata existe e retorna o status HTTP correspondente
        return batata.map(ResponseEntity::ok)//se a batata for encontrada retorna 200 (ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BatataModel> atualizarBatata(@PathVariable Long id, @RequestBody BatataModel batataAtualizada) {
    	//verifica se a batata existe no banco de dados.
        Optional<BatataModel> batataExistente = batataRepository.findById(id);

        if (batataExistente.isPresent()) {
        	//serve pra atualiza os dados da batata existente.
            BatataModel batata = batataExistente.get();
            batata.setTipo(batataAtualizada.getTipo());
            batata.setOrigem(batataAtualizada.getOrigem());
            batata.setPreco(batataAtualizada.getPreco());
            
            //salva as alterações no banco.
            BatataModel batataSalva = batataRepository.save(batata);
            return new ResponseEntity<>(batataSalva, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBatata(@PathVariable Long id) {
    	//verifica se a batata existe pelo ID
        if (batataRepository.existsById(id)) {
        	//caso exista, remove ela pelo Id usando o repositório
            batataRepository.deleteById(id);
            //retorna 204 (no content), indicando que foi excluído com sucesso
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // se a batata não existir, retorna 404 (not found)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
