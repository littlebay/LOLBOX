package com.task.spider.thread;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.common.constant.CacheStatus;
import com.common.constant.GlobalStaticVar;
import com.common.model.Gamedetails;
import com.common.model.Gamematch;
import com.common.model.Pagecache;
import com.jfinal.kit.HttpKit;
import com.util.DateUtil;

public class MatchSpiderThread extends Thread {
	private String matchId;
	private String date;
	private String serverName;
	private String playerName;

	public MatchSpiderThread(String matchId, String date, String serverName,
			String playerName) {
		this.matchId = matchId;
		this.date = date;
		this.serverName = serverName;
		this.playerName = playerName;
	}

	@Override
	public void run() {
		String url = "http://lolbox.duowan.com/matchList/ajaxMatchDetail2.php";
		Map<String, String> data = new HashMap<String, String>();
		data.put("serverName", serverName);
		data.put("playerName", playerName);
		data.put("matchId", matchId);
		String response = HttpKit.get(url, data);

		Matcher findplayername = Pattern.compile(
				"<span id=\"playerNames\">.+?</span>").matcher(response);
		while (findplayername.find()) {
			String[] usernames = findplayername.group()
					.replace("<span id=\"playerNames\">", "")
					.replace("</span>", "").split("\\|");
			for (String username : usernames) {
				if (null == Pagecache.dao.findPlayerByPSName(username,
						serverName)) {
					Pagecache pagecache = new Pagecache();
					pagecache.setPlayerName(username);
					pagecache.setServerName(serverName);
					pagecache.setHtml("");
					pagecache.setCreateTime(new Date());
					pagecache.save();

				}
			}

			Map<Integer, String> floor = new HashMap<Integer, String>();
			floor.put(1, "win1l");
			floor.put(2, "win2l");
			floor.put(3, "win3l");
			floor.put(4, "win4l");
			floor.put(5, "win5l");
			floor.put(6, "lost1l");
			floor.put(7, "lost2l");
			floor.put(8, "lost3l");
			floor.put(9, "lost4l");
			floor.put(10, "lost5l");
			Integer mark = 1;

			Matcher findgamematch = Pattern
					.compile(
							"<div class=\"r-top\">[\\s\\S]+?<span>类型：(.+?)</span>[\\s\\S]+?<span>时长：(.+?)</span>[\\s\\S]+?<span>结束：(.+?)</span>[\\s\\S]+?<span>人头：(.+?)</span>[\\s\\S]+?<span>金钱：(.+?)</span>[\\s\\S]+?</div>")
					.matcher(response);
			while (findgamematch.find()) {

				Matcher findgamedetail = Pattern
						.compile(
								"col1[\\s\\S]+?<img src=\"(.+?)_[\\s\\S]+?<a[\\s\\S]+?>(.+?)</a>[\\s\\S]+?<td class=\"col2\">(.+?)</td>[\\s\\S]+?<td class=\"col3\">(\\d+/\\d+/\\d+)</td>[\\s\\S]+?<span class=\"minions-killed\">[\\s\\S]+?补兵：[\\s\\S]+?(\\d+)[\\s\\S]+?</span>[\\s\\S]+?杀死助：[\\s\\S]+?(\\d+/\\d+/\\d+)[\\s\\S]+?</li>")
						.matcher(response);

				while (findgamedetail.find()) {
					String confirm1 = findgamedetail.group(1);
					String username = findgamedetail.group(2);
					String earn = findgamedetail.group(3);
					String KDA = findgamedetail.group(4);
					Integer minionskilled = Integer.parseInt(findgamedetail
							.group(5));
					String minionsKDA = findgamedetail.group(6);
					
					Matcher findgamedetail1 = Pattern
							.compile(
									"<p class=\"tip-user-name\">([\\s\\S]+?)</p>[\\s\\S]+?<img src=\"(.+?)_[\\s\\S]+?<span class=\"tip-tip-user-name2\">(.+?)</span>[\\s\\S]+?<img src=\"([\\s\\S]+?)\"/>[\\s\\S]+?<img src=\"([\\s\\S]+?)\"/>[\\s\\S]+?补兵:</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?<td>(\\d+)</td>[\\s\\S]+?</span>[\\s\\S]+?(\\d+)[\\s\\S]+?</p>[\\s\\S]+?</span>[\\s\\S]+?(\\d+)[\\s\\S]+?</p>[\\s\\S]+?</span>[\\s\\S]+?(\\d+)[\\s\\S]+?</p>[\\s\\S]+?</span>[\\s\\S]+?(\\d+)[\\s\\S]+?</p>[\\s\\S]+?")
							.matcher(response);
					while (findgamedetail1.find()) {

						String username1 = findgamedetail1.group(1);
						String confirm2 = findgamedetail1.group(2).replace("\n", "");
						String summonerskill1 = findgamedetail1.group(4);
						String summonerskill2 = findgamedetail1.group(5);
						String championused = findgamedetail1.group(3);
						Integer killed = Integer.parseInt(findgamedetail1
								.group(6));
						Integer monsterkilled = Integer
								.parseInt(findgamedetail1.group(7));
						Integer downtower = Integer.parseInt(findgamedetail1
								.group(8));
						Integer downbarrack = Integer.parseInt(findgamedetail1
								.group(9));
						Integer placeguard = Integer.parseInt(findgamedetail1
								.group(10));
						Integer wipeguard = Integer.parseInt(findgamedetail1
								.group(11));
						Integer highestkill = Integer.parseInt(findgamedetail1
								.group(12));
						Integer pentakill = Integer.parseInt(findgamedetail1
								.group(13));
						Integer highestcrit = Integer.parseInt(findgamedetail1
								.group(14));
						Integer totalheal = Integer.parseInt(findgamedetail1
								.group(15));
						Integer finaldamage = Integer.parseInt(findgamedetail1
								.group(16));
						Integer bear = Integer.parseInt(findgamedetail1
								.group(17));
						Integer damagechampion = Integer
								.parseInt(findgamedetail1.group(18));
						Integer magicdamage = Integer.parseInt(findgamedetail1
								.group(19));
						Integer physicdamage = Integer.parseInt(findgamedetail1
								.group(20));
						Integer truedamage = Integer.parseInt(findgamedetail1
								.group(21));

						String flo = floor.get(mark);
						GlobalStaticVar.BusinessLog.info("1:"+confirm1);
						GlobalStaticVar.BusinessLog.info("2:"+confirm2);
						if (null == Gamedetails.dao.findByPSName(username,
								serverName, matchId)
								&& confirm1.equals(confirm2)) {
							Gamedetails gamedetails = new Gamedetails();
							gamedetails.setMatchId(matchId);
							gamedetails.setServerName(serverName);
							gamedetails.setMinionsKDA(minionsKDA);
							gamedetails.setPlayerName(username);
							gamedetails.setChampionUsed(championused);
							gamedetails.setFloor(flo);
							gamedetails.setEarn(earn);
							gamedetails.setMinionsKilled(minionskilled);
							gamedetails.setKDA(KDA);
							gamedetails.setBear(bear);
							gamedetails.setSummonerSkill1(summonerskill1);
							gamedetails.setSummonerSkill2(summonerskill2);
							gamedetails.setKilled(killed);
							gamedetails.setMonsterKilled(monsterkilled);
							gamedetails.setDownTower(downtower);
							gamedetails.setDownBarrack(downbarrack);
							gamedetails.setPlaceGuard(placeguard);
							gamedetails.setWipeGuard(wipeguard);
							gamedetails.setHighestKill(highestkill);
							gamedetails.setPentaKill(pentakill);
							gamedetails.setHighestCrit(highestcrit);
							gamedetails.setTotalHeal(totalheal);
							gamedetails.setFinalDamage(finaldamage);
							gamedetails.setMagicDamage(magicdamage);
							gamedetails.setPhysicalDamage(physicdamage);
							gamedetails.setTrueDamage(truedamage);
							gamedetails.setDamageToChampion(damagechampion);
							gamedetails.save();
							mark++;
						}
					}
				}

				// Matcher findPlayerMatchDetail=Pattern.compile(regex);
				String matchtype = findgamematch.group(1);
				String gametime = findgamematch.group(2);
				String endtime = findgamematch.group(3);
				String record = findgamematch.group(4);
				String goldrate = findgamematch.group(5);
				DateUtil dateUtil = new DateUtil();
				String datestring = "2016-" + date + " " + endtime + ":00";
				Date datedb = dateUtil.string2Date(datestring);
				if (null == Gamematch.dao.findMatchByMatchId(matchId)) {
					Gamematch gamematch = new Gamematch();
					gamematch.setEndTime(datedb);
					gamematch.setGameTime(gametime);
					gamematch.setGoldRate(goldrate);
					gamematch.setMatchId(matchId);
					gamematch.setMatchType(matchtype);
					gamematch.setRecord(record);
					gamematch.save();
				}
			}

		}
	}

}
