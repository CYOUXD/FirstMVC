package org.zerock.member.model;

import java.util.ArrayList;
import java.util.List;

public class SampleDTOList {
	
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list = new ArrayList<>();
	}

	public List<SampleDTO> getList() {
		return list;
	}

	public void setList(List<SampleDTO> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
