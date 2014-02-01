package com.hotmail.hboutilier1996.MineSkills;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

//import org.bukkit.event.player.;

public class PlayerListeners implements Listener{
    @EventHandler (priority  = EventPriority.MONITOR, ignoreCancelled = true)
    public void BlockBroken (BlockBreakEvent event){
        //BlockBreakCodeHere
    }
}
