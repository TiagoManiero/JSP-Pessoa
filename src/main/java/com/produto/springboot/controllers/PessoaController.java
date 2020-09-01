package com.produto.springboot.controllers;

import java.util.Optional;

import com.produto.springboot.model.Pessoa;
import com.produto.springboot.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value="/cadastropessoa")
    public ModelAndView inicio(){
        ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value="**/salvarpessoa")
    public ModelAndView save(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        ModelAndView andView = new ModelAndView("/cadastro/cadastropessoa");
        Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
        andView.addObject("pessoas", pessoaIt);
        andView.addObject("pessoaobj", new Pessoa());
        return andView;
    }

    @RequestMapping(method= RequestMethod.GET, value="/listapessoas")
    public ModelAndView pessoas(){
        ModelAndView andView = new ModelAndView("/cadastro/cadastropessoa");
        Iterable<Pessoa> pessoaIt = pessoaRepository.findAll();
        andView.addObject("pessoas", pessoaIt);
        andView.addObject("pessoaobj", new Pessoa());
        return andView; 
    }

    @RequestMapping(method = RequestMethod.GET, value="editarpessoa/{idpessoa}")
    public ModelAndView edit(@PathVariable("idpessoa") Long idpessoa){
        Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
        ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", pessoa.get());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value="removerpessoa/{idpessoa}")
    public ModelAndView remove(@PathVariable("idpessoa") Long idpessoa){
        pessoaRepository.deleteById(idpessoa);
        ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @PostMapping("**/pesquisarpessoa")
    public ModelAndView search(@RequestParam("nomepesquisa") String nomepesquisa){
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findPessoaByNome(nomepesquisa));
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }
    
}