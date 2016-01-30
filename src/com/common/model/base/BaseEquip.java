package com.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseEquip<M extends BaseEquip<M>> extends Model<M> implements IBean {

	public void setEquipName(java.lang.String equipName) {
		set("equipName", equipName);
	}

	public java.lang.String getEquipName() {
		return get("equipName");
	}

	public void setEquipProperty(java.lang.String equipProperty) {
		set("equipProperty", equipProperty);
	}

	public java.lang.String getEquipProperty() {
		return get("equipProperty");
	}

	public void setEquipIMG(java.lang.String equipIMG) {
		set("equipIMG", equipIMG);
	}

	public java.lang.String getEquipIMG() {
		return get("equipIMG");
	}

}