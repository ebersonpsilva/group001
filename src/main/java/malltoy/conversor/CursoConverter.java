package malltoy.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.unicid.pos.model.dao.CursoDao;
import br.unicid.pos.model.domain.Curso;

@Component
public class CursoConverter implements Converter<String, Curso>{
	
	@Autowired
	private CursoDao dao;

	@Override
	public Curso convert(String source) {
		
		if (source.isEmpty()) return null;

		return dao.buscarPorId(Long.parseLong(source));
	}

}
