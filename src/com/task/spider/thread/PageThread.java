package com.task.spider.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.common.constant.GlobalStaticVar;
import com.jfinal.kit.HttpKit;

public class PageThread extends Thread {
	
	private String serverName;
	private String playerName;
	
	public PageThread(String serverName, String playerName) {
		super();
		this.serverName = serverName;
		this.playerName = playerName;
	}

	@Override
	public void run() {
		String url = "http://lolbox.duowan.com/matchList.php";
		Map<String,String> data = new HashMap<String,String>();
		data.put("serverName", serverName);
		data.put("playerName", playerName);
		String response = HttpKit.get(url, data);
		
		Matcher matcher = Pattern.compile("<li id=\"cli(\\d+)\">").matcher(response);
		Matcher matcher1=Pattern.compile("<span class=\"game\">.+?</span>&nbsp;(.+?)</p>").matcher(response);
		Map<String,String> matchIds = new HashMap<String,String>();
		while(matcher.find()&&matcher1.find()){
			String matchId = matcher.group(1);//.substring(beginIndex, endIndex);
			String date=matcher1.group(1);
			matchIds.put(matchId, date);
		}
		
		for (String matchId : matchIds.keySet()) {
			String date =matchIds.get(matchId);
			GlobalStaticVar.exthreadPool.execute(new MatchSpiderThread(matchId,date,serverName, playerName));
		}
		
	}

}
