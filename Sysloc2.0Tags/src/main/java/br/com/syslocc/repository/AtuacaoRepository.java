package br.com.syslocc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.syslocc.model.Atuacao;

@Repository
public interface AtuacaoRepository extends JpaRepository<Atuacao, Long>{

}
