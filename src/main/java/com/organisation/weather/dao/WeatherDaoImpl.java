package com.organisation.weather.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.organisation.weather.domain.WeatherEntity;
import com.organisation.weather.dto.WeatherDto;

@Repository("weatherDao")
public class WeatherDaoImpl implements WeatherDao {
	
    @PersistenceContext
    protected EntityManager entityManager;
    
    @Override
    public WeatherEntity getWeatherData(WeatherDto dto) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<WeatherEntity> cr = cb.createQuery(WeatherEntity.class);
    Root<WeatherEntity> root = cr.from(WeatherEntity.class);
    
    Predicate[] predicates = new Predicate[2];
    predicates[0] = cb.like(root.get("country"), dto.getCountry().toLowerCase());
    predicates[1] = cb.like(root.get("city"), dto.getCity().toLowerCase());
    cr.select(root).where(predicates);
    
    TypedQuery<WeatherEntity> query = entityManager.createQuery(cr);
    
    if(query.getResultList()!=null && !query.getResultList().isEmpty()) {
    	return query.getResultList().get(0);
    }
     
     return null;
    }
    
    @Override
    public void save(WeatherEntity entity) {
    	entityManager.persist(entity);
    }

}
