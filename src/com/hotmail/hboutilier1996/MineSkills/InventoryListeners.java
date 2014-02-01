package com.hotmail.hboutilier1996.MineSkills;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryListeners implements Listener{
    @EventHandler (priority  = EventPriority.MONITOR, ignoreCancelled = true)
    public void BlockBroken (BlockBreakEvent event){
        //BlockBreakCodeHere
    }
}
