package com.clone.fbclone.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Aminul Hoque
 * @since 2021-03-21
 */

@Entity
@Table(name = "mydays")
@Data
public class Myday extends BaseIdentity<Myday>{

    private String name;
    private String userImg;
    private String imgUrl;
}
