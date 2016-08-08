package com.sfpay.airscape.server.vo.auxiliary;

import java.io.Serializable;

/**
 * @author sfhq593 2015年12月28日16:48:56 解析SVN库信息
 * 
 */
public class SVN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String kind;
	private String state;
	private String name;
	private long version;
	private String applicationName;

	private String repositoryRoot;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRepositoryRoot() {
		return repositoryRoot;
	}

	public void setRepositoryRoot(String repositoryRoot) {
		this.repositoryRoot = repositoryRoot;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	/**
	 * 
	 * @return String
	 */
	public String getApplicationName() {
		if (!"".equals(this.getRepositoryRoot())) {
			applicationName = this.getRepositoryRoot().substring(this.getRepositoryRoot().lastIndexOf("/") + 1,
					this.getRepositoryRoot().length());
		}
		return applicationName;
	}

}
