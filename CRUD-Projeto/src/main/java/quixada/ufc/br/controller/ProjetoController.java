package quixada.ufc.br.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.service.ProjetoService;

@Controller
public class ProjetoController {

	@Inject
	private ProjetoService pc;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String index(){
		log.info("controller: projeto - action: index");
		return "index";
	}
	
	@RequestMapping(value = "/projetos/new", method = RequestMethod.POST)
	public String adicionarProjeto(@Valid Projeto projeto, BindingResult result, SessionStatus status){
		log.info("controller: projeto - action: AdicionarProjetos");
		if(result.hasErrors()){
			return "redirect:/index";
		}else{
			this.pc.salvar(projeto);
			status.setComplete();
			return "redirect:/confirmacao";
		}
	}
	
	
	
	@RequestMapping(value="/projetos/list")  
    public ModelAndView listOfTeams() {  
        ModelAndView modelAndView = new ModelAndView("list-of-teams");  
          
        List<Projeto> projeto = pc.findAll();  
        modelAndView.addObject("projetos", projeto);  
          
        return modelAndView;  
    } 
	
}