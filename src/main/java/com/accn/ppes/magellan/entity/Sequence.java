package com.accn.ppes.magellan.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="sequence")
public class Sequence {

	@Id
	private String SequenceId;

	private Long seq;

	public String getSequenceId() {
		return SequenceId;
	}

	public void setSequenceId(String sequenceId) {
		SequenceId = sequenceId;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		seq = seq;
	}

	@Override
	public String toString() {
		return "Sequence [SequenceId=" + SequenceId + ", seq=" + seq + "]";
	}

	

}
