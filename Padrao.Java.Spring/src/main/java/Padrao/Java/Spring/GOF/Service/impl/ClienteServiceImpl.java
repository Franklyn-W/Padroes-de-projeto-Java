package Padrao.Java.Spring.GOF.Service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Padrao.Java.Spring.GOF.Model.Cliente;
import Padrao.Java.Spring.GOF.Model.ClienteRepository;
import Padrao.Java.Spring.GOF.Model.Endereco;
import Padrao.Java.Spring.GOF.Model.EnderecoRepository;
import Padrao.Java.Spring.GOF.Service.ClienteService;
import Padrao.Java.Spring.GOF.Service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        SalvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar cliente pelo ID, caso exista
        Optional<Cliente> clienteBD = clienteRepository.findById(id);
        if (clienteBD.isPresent()) {
            SalvarClienteComCep(cliente);
            // Verificar se o endereço do cliente ja existe (pelo CEP)
            // Caso nao exista, integrar com o ViaCep e salvar o retorno
            // Alterar o cliente, vinculando o endereço (novo ou existente)
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void SalvarClienteComCep(Cliente cliente) {
        // Apartir do cep, consulta se o endereço ja existe
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, consulta no ViaCep e retorna os dados do CEP
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente)
        clienteRepository.save(cliente);
    }
}