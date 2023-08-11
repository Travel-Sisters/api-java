package com.api.travelsisters.crud;

import com.api.travelsisters.model.CadastroModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CadastroCRUD extends CrudRepository<CadastroModel, Integer> {
    //Listar cadastros
    //comando para select
    List<CadastroModel> findAll();

    //Pesquisar por Id
    //vai procurar pelo id
    CadastroModel findById(int id);

    //Remover cadastro
    // comando de deletar
    void delete(CadastroModel cadastro);

    //Cadastrar e alterar  cadastro
    <CadMod extends CadastroModel> CadMod save(CadMod cadastro);

}

