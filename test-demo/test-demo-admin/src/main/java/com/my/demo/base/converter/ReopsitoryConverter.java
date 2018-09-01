package com.my.demo.base.converter;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;

public class ReopsitoryConverter<T extends ConversionService & ConverterRegistry>
		implements ConditionalGenericConverter, ApplicationContextAware {

	private T conversionService;

	private Repositories repositories;

	public ReopsitoryConverter(T conversionService) {
		this.conversionService = conversionService;
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(Object.class,
				Object.class));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
//		RepositoryInformation info = repositories
//				.getRepositoryInformationFor(targetType.getType());

		// Serializable id = conversionService.convert(source,
		// info.getIdType());
		
		Object repository = repositories.getRepositoryFor(targetType.getType());

		Serializable id = ((Persistable<Serializable>) source).getId();

		if (null == id) {
			return null;
		}

		Object entity = null;
		if (repository instanceof CrudRepository) {
			entity = ((CrudRepository<?, Serializable>) repository).findOne(id);
		}

		return entity;
	}

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		// if (!repositories.hasRepositoryFor(targetType.getType())) {
		// return false;
		// }
		//
		// return conversionService.canConvert(sourceType.getType(),
		// repositories.getRepositoryInformationFor(targetType.getType()).getIdType());

		return sourceType.equals(targetType)
				&& sourceType.isAssignableTo(TypeDescriptor
						.valueOf(Persistable.class))
				&& repositories.hasRepositoryFor(targetType.getType());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.repositories = new Repositories(applicationContext);
		this.conversionService.addConverter(this);
	}
}
