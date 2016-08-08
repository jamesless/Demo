package com.sfpay.airscape.server.decorate;

/**
 * 
 * @author  sfhq1588 
 *
 */
public class Dependences {
	private Integer dependenceId;
	private String relation;
	private Skeleton skeleton;
	private War war;
	
	public Integer getDependenceId() {
		return dependenceId;
	}
	public void setDependenceId(Integer dependenceId) {
		this.dependenceId = dependenceId;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public Skeleton getSkeleton() {
		return skeleton;
	}
	public void setSkeleton(Skeleton skeleton) {
		this.skeleton = skeleton;
	}
	public War getWar() {
		return war;
	}
	public void setWar(War war) {
		this.war = war;
	}

}
