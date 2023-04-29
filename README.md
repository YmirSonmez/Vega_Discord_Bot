# VegaBot
VegaBot is one of the bots I made while learning Java. It is not yet complete.
It is designed for [Discord](https://discord.com/) using the [JDA Library](https://github.com/DV8FromTheWorld/JDA).

As I learned new things over time, I tried to improve it.

### Features

Commands  | Description 
------------- | ------------- 
SetPrefix  | Changes the prefix for the server.
Blacksmith  | Crafts a pickaxe.
Furnace  | Turns ores into ingots.
Item  | Shows information about an item.
Inventory  | Shows the player's inventory and information.
Job | Shows job features and allows the player to choose a job.
Mine | Mines the specified amount of blocks.
Pickaxe | Manages pickaxes. (Send, Delete, Use)
Sell | Sells ores. (All or selected)
SendMoney | Allows players to transfer money to other players.
SendResource | Allows players to transfer resources to other players.
Start | Creates a new player profile.
CoinFlip | Doubles the amount of money bet with a 50% chance.
Bet | Increases the amount of money bet by 1.5 times.
Fight | A chance-based fighting game. (2 players, betting)

Job  | Pickaxe Slot | Description 
------------- | ------------- | -------------
Unemployed | 1 | A job that new players have by default.
Blacksmith | 4 | A job that can craft pickaxes.
Smelter | 2 | A job that can turn ores into ingots.
Miner | 2 | No special features added.

Ores  | Id | Buy P. | Sell P.  | Level  | Chance  | Production E.  | Result when smelted
------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | -------------
Junk | 0 | -1 | 0 | 0 | 0 | - | null
Cobblestone | 1 | 5 | 1 | 0 | 15 | + | Stone
Stone | 2 | 5 | 2 | 0 | 0 | - | Junk
Coal | 3 | 100 | 1 | 10 | 1 | - | Junk
Iron Ore | 4 | 50 | 5 | 20 | 6 | - | Iron
Iron | 5 | -1 | 10 | 1 | 0 | + | Junk
Gold Ore | 6 | 500 | 25 | 40 | 5 | - | Gold
Gold | 7 | -1 | 50 | 2 | 0 | + | Junk
Diamond Ore | 8 | 100 | 50 | 60 | 5 | - | Diamond
Diamond | 9 | -1 | 75 | 3 | 0 | + | Junk
Emerald | 10 | -1 | -1 | 100 | 1 | - | Junk

Ores with a chance of 0 will not be mined. The value of -1 indicates that the function is not available (Cannot be bought or sold). Production E. indicates the usability of the ore in production.

# Contact: Discord: YmirSG#5599
