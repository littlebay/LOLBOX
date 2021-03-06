package com.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("champion", "championId", Champion.class);
		arp.addMapping("equip", "equipName", Equip.class);
		arp.addMapping("gamedetails", "detailId", Gamedetails.class);
		arp.addMapping("gamematch", "matchId", Gamematch.class);
		arp.addMapping("pagecache", "playerName", Pagecache.class);
		arp.addMapping("player", "playerId", Player.class);
	}
}

