/******************************************************************************
 *  Compilation:  javac -d bin Rabbitmqmodel.java
 *  Execution:    
 *               
 *  
 *  Purpose:       create rabbitmq model using  lombok
 *
 *  @author  pandit walde
 *  @version 1.0
 *  @since  25-11-2019
 *
 ******************************************************************************/
package com.bridgelabz.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rabbitmqmodel  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String body;
	private String subject;


}
