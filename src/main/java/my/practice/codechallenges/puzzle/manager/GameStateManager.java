package my.practice.codechallenges.puzzle.manager;

import java.util.Map;

public class GameStateManager {
  public static int getPreviousePosition(Map<String,Object> gameConfiguration) {
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get("playerInfo");
	  if (playerInfoMap.get("currentposition")!=null&& !"".equals(playerInfoMap.get("currentposition")) && Integer.valueOf(playerInfoMap.get("currentposition").toString())>0)
		  return Integer.valueOf(playerInfoMap.get("currentposition").toString());
	  else
		  return 0;
  }
  public static Map<String,Object> svaePosition(Map<String,Object> gameConfiguration,int position) {
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get("playerInfo");
	  playerInfoMap.put("currentposition", position);
	  return gameConfiguration;
  }
  public static  Map<String,Object> saveAsWinner(Map<String,Object> gameConfiguration){
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get("playerInfo");
	  if (playerInfoMap.get("star")!=null&& !"".equals(playerInfoMap.get("star")) && Integer.valueOf(playerInfoMap.get("star").toString())>0)
		  playerInfoMap.put("currentposition", Integer.valueOf(playerInfoMap.get("star").toString())+1);
	  else
		  playerInfoMap.put("star", 1);
	return playerInfoMap; 
	  
  }
  
}
