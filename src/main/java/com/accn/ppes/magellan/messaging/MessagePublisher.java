package com.accn.ppes.magellan.messaging;

import com.accn.ppes.magellan.entity.Product;


/**
 * A simple interface for product publisher.
 *
 *<p>
 *An implementation class can implement MessagePublisher 
 *and describe where to post the product 
 *</p>
 */
public interface MessagePublisher {

	void publishMessage(Product product);
		
}
