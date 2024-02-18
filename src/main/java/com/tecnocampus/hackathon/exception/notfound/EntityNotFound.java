package com.tecnocampus.hackathon.exception.notfound;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(Class entity, Object valueId){
        super("Entity "+entity.getName()+" not found with id " + valueId+".");
    }
}
