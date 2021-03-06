package com.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePagecache<M extends BasePagecache<M>> extends Model<M> implements IBean {

	public void setPlayerName(java.lang.String playerName) {
		set("playerName", playerName);
	}

	public java.lang.String getPlayerName() {
		return get("playerName");
	}

	public void setServerName(java.lang.String serverName) {
		set("serverName", serverName);
	}

	public java.lang.String getServerName() {
		return get("serverName");
	}

	public void setMatchId(java.lang.String matchId) {
		set("matchId", matchId);
	}

	public java.lang.String getMatchId() {
		return get("matchId");
	}

	public void setHtml(java.lang.String html) {
		set("html", html);
	}

	public java.lang.String getHtml() {
		return get("html");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("updateTime", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("updateTime");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

}
