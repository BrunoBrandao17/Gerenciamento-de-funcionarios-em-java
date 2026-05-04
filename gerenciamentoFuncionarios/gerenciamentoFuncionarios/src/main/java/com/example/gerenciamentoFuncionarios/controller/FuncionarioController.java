package com.example.gerenciamentoFuncionarios.controller;

import com.example.gerenciamentoFuncionarios.dto.FuncionarioRequestDTO;
import com.example.gerenciamentoFuncionarios.dto.FuncionarioResponseDTO;
import com.example.gerenciamentoFuncionarios.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @GetMapping //Listar Todos Funcionários
    public ResponseEntity<List<FuncionarioResponseDTO>> listar(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listarTodos());
    }

    @GetMapping("/{id}") // Buscar único funcionário
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping //Criar Funcionário
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody FuncionarioRequestDTO dto){
        service.salvarFuncionario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Funcionário cadastrado com sucesso"));
    }

    @PutMapping("/{id}") // 2. Atualizar funcionário
    public ResponseEntity<String> atualizar(@PathVariable Long id, @Valid @RequestBody FuncionarioRequestDTO dto) {
        service.atualizar(id, dto);
        return ResponseEntity.ok("Funcionário atualizado com sucesso");
    }

    @DeleteMapping("/{id}")  // 3. Deletar por id
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
