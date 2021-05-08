package com.lunz.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class Sort implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String field;
    private boolean sort;

}