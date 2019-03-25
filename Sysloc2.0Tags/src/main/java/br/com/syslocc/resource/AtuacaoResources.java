package br.com.syslocc.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.syslocc.model.Atuacao;
import br.com.syslocc.repository.AtuacaoRepository;

@RestController()
@RequestMapping("/atuacao")
public class AtuacaoResources {

	@Autowired
	AtuacaoRepository atuacaoRepository;
	
	@GetMapping
	public List<Atuacao> listarAll(){
		return atuacaoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public Atuacao atuacao(@PathVariable Long codigo) {
		System.out.println("Teste");
		return atuacaoRepository.findOne(codigo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Atuacao> criar(@RequestBody Atuacao atuacao, HttpServletResponse response) {
		
		Atuacao atuacaoSalva =  atuacaoRepository.save(atuacao);
		
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequestUri()
			.path("/{codigo}").buildAndExpand(atuacaoSalva.getCodigo()).toUri();
					
			response.setHeader("Location", uri.toASCIIString());
		
			return ResponseEntity.created(uri).body(atuacaoSalva);
	}
	
	
	
}
