# NPC Idle Timer
A generalized solution to ConradicalMel's MasterFarmer plugin found [here](https://github.com/ConradicalMel/master-farmer)

Displays an overhead countdown timer above select NPCs that starts at 300 seconds (5 minutes) and resets every
time the NPC moves. The timer is useful while pickpocketing an NPC trapped in a 1x2 tile corridor. If the NPC does not
move for 300 seconds, the NPC will disappear and respawn elsewhere. When the timer approaches 15-30 seconds, give the
NPC room to move and reset their timer. Be careful not to wait until too close to 0 seconds remaining, or the NPC may
not willingly wander in time before respawning.

![Multi Timer Example](https://i.imgur.com/yM3KoHB.png)

Sample Config:

![Multi Timer Config Example](https://i.imgur.com/JtAtihS.png)
