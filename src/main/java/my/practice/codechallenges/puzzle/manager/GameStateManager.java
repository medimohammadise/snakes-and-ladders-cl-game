package my.practice.codechallenges.puzzle.manager;

import java.util.Map;

import my.practice.codechallenges.puzzle.domain.Player;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_NAME;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_ID;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_STAR;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION;
public class GameStateManager {
  public static int getPreviousePosition(Map<String,Object> gameConfiguration) {
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  if (playerInfoMap.get(GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION)!=null&& !"".equals(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION)) && Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION).toString())>0)
		  return Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION).toString());
	  else
		  return 0;
  }
  public static Map<String,Object> svaePosition(Map<String,Object> gameConfiguration,int position) {
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  playerInfoMap.put(GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION, position);
	  return gameConfiguration;
  }
  public static  Map<String,Object> saveAsWinner(Map<String,Object> gameConfiguration){
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  if (playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR)!=null&& !"".equals(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR)) && Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR).toString())>0)
		  playerInfoMap.put(GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION, Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR).toString())+1);
	  else
		  playerInfoMap.put(GAME_INFO_KEY_PLAYER_STAR, 1);
	return playerInfoMap; 
	  
  }
  public static Player getPlayerInfo(Map<String,Object> gameConfiguration) {
	  Map<String, Object> playerInfoMap = (Map<String, Object>) gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  return new Player(playerInfoMap.get(GAME_INFO_KEY_PLAYER_NAME).toString(),playerInfoMap.get(GAME_INFO_KEY_PLAYER_ID).toString(),Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR).toString()) ,Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CUSRRENT_POSITION).toString()));
	  
  }
  
}
