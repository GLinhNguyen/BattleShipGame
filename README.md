# BattleShipGame
Data Structure and Algorithms Project

## Members:
+ Nguyễn Ngọc Gia Linh 
+ Nguyễn Gia Huy
+ Âu Nguyễn Nhật Thư
+ Phạm Hoài Nhật Nam

## Special Features:
+ Undo and Redo.
+ 2 AI difficult levels.
+ Bot vs Bot mode (players choose AI difficulties and let the bots showcase their ability).

## Game Logic:
+ **Main panel:** When players initiate the battleship game, they will be prompted to select one of two versions. In the first version,  they will engage in a one-on-one battle against a computer-controlled opponent. In the second version, they can spectate and enjoy the showdown between two AI bots as they engage in strategic naval warfare.

<p align="center">
  <img src="https://github.com/GLinhNguyen/BattleShipGame/assets/146911978/96642dac-b8b7-48d2-aff6-0dfc25b699d0">
</p>
</br>

+ **Settings:** Players are allowed to adjust AI difficult level and time limit for each placing/firing turn.
<p align="center">
  <img src="https://github.com/GLinhNguyen/BattleShipGame/assets/146911978/3b2150cb-7979-4f6c-837a-c61862ff2873">
</p>
</br>

+ **Game Panel:** panel where gameplay between Bot and player takes place

<p align="center">
  <img src="https://github.com/GLinhNguyen/BattleShipGame/assets/146911978/fa0e0148-a99a-4460-9788-807f6f3a8761">
</p>
</br>

+ **Bot vs Bot simulation:**
If players wish to witness a battle between two AI bots, they have the option to initiate a bot vs bot combat by clicking on the respective button. Players are allowed to select the desired difficulty level for the two AI bots involved in the combat.

<p align="center">
  <img src="https://github.com/GLinhNguyen/BattleShipGame/assets/146911978/b27649e3-7980-4775-b522-f64842a6fa6a">
</p>
</br>

Two bots will take turns to strategically shoot at each other, and when one bot successfully hits a ship, the corresponding grid cell on the opponent's grid will glow yellow. Conversely, if a bot misses its target, the opponent's grid cell will turn Blue. The game continues in this manner until one of the bots successfully sinks all of the opponent's ships, achieving victory.
![image](https://github.com/GLinhNguyen/BattleShipGame/assets/146911978/78130dd1-d875-4571-a6a1-56938abc12c1)
</br>

## Future Development:
+ Advance **Redo** functionality by allowing players to redo while placing ship and after destroying a ship.
+ Advance **Nightmare Bot** by implementing **Depth first search (DFS)** to find next possible moves after hitting a ship. This method is expected to increase the accuracy of the bot moves by exploring all the spots in 4 directions after hitting a ship, if hitted, continue hitting. Otherwise, change the direction. This method will make sure the hitted ship is completely destroyed before trying to hit a new one.

## References: 
Squirrelbear 2021, Battleship, GitHub 
