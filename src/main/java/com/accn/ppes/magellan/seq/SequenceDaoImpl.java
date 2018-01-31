/**
 * 
 */
package com.accn.ppes.magellan.seq;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.accn.ppes.magellan.entity.Sequence;

/**
 * @author vikas.sivaravindran
 *
 */
@Repository
public class SequenceDaoImpl implements SequenceDao{
	
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public long getNextSequenceId(String key) {
		// TODO Auto-generated method stub
		
		Query query = new Query(Criteria.where("_id").is(key));
		
		Update update = new Update();
		update.inc("seq",1);
		
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		
		Sequence seq = mongoOperations.findAndModify(query , update, options, Sequence.class);
		
		if (seq == null) {
			throw new SequenceException("Unable to get sequence id for key : " + key);
		}
		
		return seq.getSeq();
	} 

}
