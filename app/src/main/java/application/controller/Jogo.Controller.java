package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Jogo;
import application.model.JogoRepository;

@Controller
@RequestMapping("/jogo")
public class JogoController {
    @Autowired
    private JogoRepository jogoRepo;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("jogos", jogoRepo.findAll());
        return "/jogo/list";
    }

    @RequestMapping("/insert")
    public String insert() {
        return "/jogo/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome, @RequestParam("titulo") String titulo) {
        Jogo jogo = new Plataforma();
        plataforma.setNome(nome);
        plataforma.setTitulo();
        plataformaRepo.save(plataforma);

        return "redirect:/jogo/list";
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Plataforma> plataforma = plataformaRepo.findById(id);

        if(plataforma.isPresent()) {
            model.addAttribute("jogo", plataforma.get());
            return "/jogo/update";
        }

        return "redirect:/jogo/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
        @RequestParam("id") int id,
        @RequestParam("nome") String nome,
        @RequestParam("fornecedor") String fornecedor
    ) {
        Optional<Plataforma> plataforma = plataformaRepo.findById(id);

        if(plataforma.isPresent()) {
            plataforma.get().setNome(nome);
            plataforma.get().setFornecedor(fornecedor);

            plataformaRepo.save(plataforma.get());
        }
        
        return "redirect:/jogo/list";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Plataforma> plataforma = plataformaRepo.findById(id);

        if(plataforma.isPresent()) {
            model.addAttribute("plataforma", plataforma.get());
            return "jogo/delete";
        }

        return "redirect:/jogo/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        jogoRepo.deleteById(id);

        return "redirect:/jogo/list";
    }
}