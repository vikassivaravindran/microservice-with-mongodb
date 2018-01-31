/**
 * 
 */
package com.accn.ppes.magellan.seq;

/**
 * @author vikas.sivaravindran
 *
 */
public interface SequenceDao {

	long getNextSequenceId(String key);
	
}
