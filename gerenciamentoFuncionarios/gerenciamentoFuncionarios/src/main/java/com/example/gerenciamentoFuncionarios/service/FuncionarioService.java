package com.example.gerenciamentoFuncionarios.service;

import com.example.gerenciamentoFuncionarios.dto.FuncionarioRequestDTO;
import com.example.gerenciamentoFuncionarios.dto.FuncionarioResponseDTO;
import com.example.gerenciamentoFuncionarios.model.FuncionarioModel;
import com.example.gerenciamentoFuncionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<FuncionarioResponseDTO> listarTodos(){
        return repository
                .findAll()
                .stream()
                .map(f -> new FuncionarioResponseDTO(
                        f.getNome(),
                        f.getEmail(),
                        f.getTelefone()))
                .toList();
    }

    public FuncionarioModel salvarFuncionario(FuncionarioRequestDTO dto){
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("Funcionário Já cadastrado");
        }

        FuncionarioModel novoFuncionario = new FuncionarioModel();
        novoFuncionario.setNome(dto.getNome());
        novoFuncionario.setEmail(dto.getEmail());
        novoFuncionario.setTelefone(dto.getTelefone());
        novoFuncionario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return repository.save(novoFuncionario);
    }
    // Buscar por ID
    public FuncionarioResponseDTO buscarPorId(Long id) {
        FuncionarioModel funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return new FuncionarioResponseDTO(funcionario.getNome(), funcionario.getEmail(), funcionario.getTelefone());
    }
    // Atualizar
    public void atualizar(Long id, FuncionarioRequestDTO dto) {
        FuncionarioModel funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setTelefone(dto.getTelefone());

        // Se a senha for alterada, precisa de nova criptografia
        funcionario.setSenha(passwordEncoder.encode(dto.getSenha()));

        repository.save(funcionario);
    }
    // Excluir funcionário
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        repository.deleteById(id);
    }
}
