package com.hotmail.hboutilier1996.MineSkills;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.weather.LightningStrikeEvent;

public class OtherListeners implements Listener{
    @EventHandler (priority  = EventPriority.MONITOR, ignoreCancelled = true)
    public void BlockBroken (BlockBreakEvent event){
        //BlockBreakCodeHere
    }
}
