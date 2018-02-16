package my.practice.codechallenges.puzzle.manager;

import java.util.Map;

import my.practice.codechallenges.puzzle.domain.Player;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_NAME;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_ID;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_STAR;
import static my.practice.codechallenges.puzzle.setting.Constants.GAME_INFO_KEY_PLAYER_CURRENT_POSITION;
public class GameStateManager {
  public static int getPreviousePosition(Map<String,Object> gameConfiguration) {
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  if (playerInfoMap.get(GAME_INFO_KEY_PLAYER_CURRENT_POSITION)!=null&& !"".equals(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CURRENT_POSITION)) && Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CURRENT_POSITION).toString())>0)
		  return Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CURRENT_POSITION).toString());
	  else
		  return 0;
  }
  //TODO for now just we are supporting save player info at future it is easy to save configuration also
  public static Map<String,Object> svaeGame(Map<String,Object> gameConfiguration,Player player) {
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  playerInfoMap.put(GAME_INFO_KEY_PLAYER_CURRENT_POSITION, player.getCurrentPosition());
	  playerInfoMap.put(GAME_INFO_KEY_PLAYER_NAME, player.getName());
	  playerInfoMap.put(GAME_INFO_KEY_PLAYER_ID, player.getId());
	  playerInfoMap.put(GAME_INFO_KEY_PLAYER_STAR, player.getStar());
	  
	  return gameConfiguration;
  }
  public static  Map<String,Object> saveAsWinner(Map<String,Object> gameConfiguration){
	  Map<String,Object> playerInfoMap=(Map<String,Object>)gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  if (playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR)!=null&& !"".equals(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR)) && Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR).toString())>0)
		  playerInfoMap.put(GAME_INFO_KEY_PLAYER_CURRENT_POSITION, Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR).toString())+1);
	  else
		  playerInfoMap.put(GAME_INFO_KEY_PLAYER_STAR, 1);
	return playerInfoMap; 
	  
  }
  public static Player getPlayerInfo(Map<String,Object> gameConfiguration) {
	  Map<String, Object> playerInfoMap = (Map<String, Object>) gameConfiguration.get(GAME_INFO_KEY_PLAYER);
	  return new Player(playerInfoMap.get(GAME_INFO_KEY_PLAYER_NAME).toString(),playerInfoMap.get(GAME_INFO_KEY_PLAYER_ID).toString(),Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_STAR).toString()) ,Integer.valueOf(playerInfoMap.get(GAME_INFO_KEY_PLAYER_CURRENT_POSITION).toString()));
	  
  }
  
}
