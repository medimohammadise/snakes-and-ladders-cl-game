 $$$$$$\                      $$\                                                           $$\       $$\                 $$\       $$\                               
$$  __$$\                     $$ |                                                          $$ |      $$ |                $$ |      $$ |                              
$$ /  \__|$$$$$$$\   $$$$$$\  $$ |  $$\  $$$$$$\   $$$$$$$\        $$$$$$\  $$$$$$$\   $$$$$$$ |      $$ | $$$$$$\   $$$$$$$ | $$$$$$$ | $$$$$$\   $$$$$$\   $$$$$$$\ 
\$$$$$$\  $$  __$$\  \____$$\ $$ | $$  |$$  __$$\ $$  _____|       \____$$\ $$  __$$\ $$  __$$ |      $$ | \____$$\ $$  __$$ |$$  __$$ |$$  __$$\ $$  __$$\ $$  _____|
 \____$$\ $$ |  $$ | $$$$$$$ |$$$$$$  / $$$$$$$$ |\$$$$$$\         $$$$$$$ |$$ |  $$ |$$ /  $$ |      $$ | $$$$$$$ |$$ /  $$ |$$ /  $$ |$$$$$$$$ |$$ |  \__|\$$$$$$\  
$$\   $$ |$$ |  $$ |$$  __$$ |$$  _$$<  $$   ____| \____$$\       $$  __$$ |$$ |  $$ |$$ |  $$ |      $$ |$$  __$$ |$$ |  $$ |$$ |  $$ |$$   ____|$$ |       \____$$\ 
\$$$$$$  |$$ |  $$ |\$$$$$$$ |$$ | \$$\ \$$$$$$$\ $$$$$$$  |      \$$$$$$$ |$$ |  $$ |\$$$$$$$ |      $$ |\$$$$$$$ |\$$$$$$$ |\$$$$$$$ |\$$$$$$$\ $$ |      $$$$$$$  |
 \______/ \__|  \__| \_______|\__|  \__| \_______|\_______/        \_______|\__|  \__| \_______|      \__| \_______| \_______| \_______| \_______|\__|      \_______/ 
                                                                                                                                                                     
                                                                                                                   
# Snakes and Ladders Game - A Java Command Line Sample Practice
When I was a child I used to play SnakesAndLadder game with my brothers . Still I like this game because even though it is related to dice and chance You can learn that even though Ladders can help You to go up but mistakes could be harmful to your life  and rollback all your efforts .

## problem
    There is one square board (n*n) containing multiple squares inside . We put a unique sequence for each square. Player starts from first square and by throwing a dice will start walking and taking adventures .Adventures could be one of the following things:
    1- Snake  : If player put his/her step in snake's square will get sniffed by the snake.   
    2- Ladder : If Player put his/her step in some squares it might be a ladder and can expedite the trip.  Winner is the one that who reach last square  
    
## Design
  Below is the class diagram proposed for the implementation.
   

## Development
   Configuration data is being load from Json and Game stated also is being saved in Json file. It is totally clear can user can change the configuration data using Json file.
   I did not use any external library and I had do rely on JDK8 only and This was the most challenging part for development. 
   Choosing the 
## Testing
    - Just user command line it's self-explanatory enough to deal with the game
    Java -jar SnakesLaddersGame-0.0.1-SNAPSHOT.jar
## Future extensions
    - MultiPlayer feature . Right now It is just supporting one player
    - Change configuration by player. Even though now It could be done by changing json config file
    - Player can fight with sankes
    - Buy using Ladders will get positive and by winning on Snakes will get much more power and vice versa
    - It would be possible to create new  characters other than Snake or Ladder
    - It would be possible to have multiple player on network
    
    
   
   