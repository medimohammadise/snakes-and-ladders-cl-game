                                                                                    
# Snakes and Ladders Game - A Java Command Line Sample Practice
When I was a child I used to play Snakes&Ladders game with my brothers . Still I like this game because even though it is related to dice and chance You can learn that even though Ladders can help You to go up but mistakes could be harmful to your life  and rollback all your efforts .

## problem
    There is one square board (n*n) containing multiple squares inside . We put a unique sequence for each square. Player starts from first square and by throwing a dice will start walking and taking adventures .Adventures could be one of the following things:
    1- Snake  : If player put his/her step in snake's square will get sniffed by the snake.   
    2- Ladder : If Player put his/her step in some squares it might be a ladder and can expedite the trip.  Winner is the one that who sucessfully reach last square  
    
## Design
  I have simple Domain classes (in domain package) . 
  Game generate a Borad, Board contains n*n squares . Player moves between squares. Ladder or Snake's head and tail could be located at each square.
  We have 3 Menus for communicating with user (in menu package).
  For IO because of clearity and ease of configuration I choose to use Json file.
  
   
## Limitations
   This version is limited to single player only by the way it would be easy to extend it
## Development
   Configuration data is being load from Json and Game stated also is being saved in Json file. It is totally clear can user can change the configuration data using Json file.
   I did not use any external library and I had do rely on JDK8 only and This was the most challenging part for development. For validating Json I have used JDK*'th java script engine(nashorn).
   Project got very clear packaging structure and I have tried hard to make it as simple as possible.

## Testing
    - Just user command line it's self-explanatory enough to deal with the game
    Java -jar SnakesLaddersGame-0.0.1-SNAPSHOT.jar
## Configuration
   According that configuration is reading from and writing into Json file , It is clear and You can adjust default setting for the game by adjusting json . You can find json files it in PlayersProfile in the same directory that You are running the game .   Json files named by Player's ID.
   ```javascript
   {
	"numberOfSqures": "8",
	"playerInfo": {
		"star": "0",
		"name": "Mehdi",
		"id": "maryam",
		"currentposition": "13"
	},
	"ladders": {
		"Sucess": {
			"x": "5",
			"y": "12"
		},
		"Up": {
			"x": "10",
			"y": "68"
		},
		"Win": {
			"x": "36",
			"y": "80"
		},
		"Hope": {
			"x": "52",
			"y": "20"
		}
	},
	"sankes": {
		"Bova": {
			"x": "80",
			"y": "43"
		},
		"Kobra": {
			"x": "34",
			"y": "20"
		},
		"Python": {
			"x": "11",
			"y": "8"
		},
		"Piton": {
			"x": "52",
			"y": "8"
		}
		}
}
```
 Note : for Snakes always specify head at top x>y and do vice versa for the Ladder x<y. numberOfSqures specify Game's Board dimension
 
## Future extensions
    - MultiPlayer feature . Right now It is just supporting one player
    - Change configuration by player. Even though now It could be done by changing json config file
    - Player can fight with sankes
    - Buy using Ladders will get positive and by winning on Snakes will get much more power and vice versa
    - It would be possible to create new  characters other than Snake or Ladder
    - It would be possible to have multiple player on network
    
    
   
   