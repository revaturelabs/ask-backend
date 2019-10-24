package com.revaturelabs.ask.response;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponseRepository extends CrudRepository<Response, Integer> {

}
