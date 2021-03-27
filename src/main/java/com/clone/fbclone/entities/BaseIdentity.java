package com.clone.fbclone.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Aminul Hoque
 * @since 2021-03-16
 */
@MappedSuperclass
public abstract class BaseIdentity<T extends BaseIdentity<T>> {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    public String getId() {
        return this.id;
    }

    @SuppressWarnings("unchecked")
    public T setId(String id) {
        this.id = id;
        return (T) this;
    }
}
