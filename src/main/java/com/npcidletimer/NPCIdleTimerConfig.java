package com.npcidletimer;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("npcidletimerplugin")
public interface NPCIdleTimerConfig extends Config
{
	@ConfigItem(
			position = 0,
			keyName = "showOverlay",
			name = "Show timer over chosen NPCs",
			description = "Configures whether or not to have a timer over the chosen NPCs"
	)
	default boolean showOverlay()
	{
		return true;
	}

	@ConfigItem(
			position = 1,
			keyName = "npcToShowTimer",
			name = "NPC Names",
			description = "Enter names of NPCs where you wish to use this plugin"
	)
	default String npcToShowTimer()
	{
		return "";
	}

	@Alpha
	@ConfigItem(
			position = 2,
			keyName = "normalTimerColor",
			name = "Normal time color",
			description = "Configures the color of the timer"
	)
	default Color normalTimerColor()
	{
		return Color.WHITE;
	}

	@Alpha
	@ConfigItem(
			position = 3,
			keyName = "lowTimerColor",
			name = "Low time color",
			description = "Configures the color of the timer when remaining time is low"
	)
	default Color lowTimerColor()
	{
		return Color.RED;
	}

	@Range(
			max = 300
	)
	@ConfigItem(
			position = 4,
			keyName = "maxDisplay",
			name = "Time to start timer",
			description = "This defines at what amount of seconds/ticks the timer will be shown. Before this time the npc won't show a timer"
	)
	default int maxDisplay()
	{
		return 300;
	}

	@ConfigItem(
			position = 5,
			keyName = "customTimer",
			name = "Enable custom timer",
			description = "This option enables you to set a custom timer that fits the actual respawn timer of your npc or for other timing related manners"
	)
	default boolean customTimer()
	{
		return false;
	}

	@Range(
			max = 999
	)
	@ConfigItem(
			position = 6,
			keyName = "customTiming",
			name = "Custom timer",
			description = "The starting tick counter on the npc. The default timer of this plugin is set at 300 seconds. You may change this to fit the actual despawn timer"
	)
	default int customTiming()
	{
		return 300;
	}

	@Range(
			max = 999
	)
	@ConfigItem(
			position = 7,
			keyName = "lowDisplay",
			name = "Timer low value",
			description = "The maximum seconds at which the timer is considered low"
	)
	default int lowDisplay()
	{
		return 30;
	}

	@Range(
			max = 300
	)
	@ConfigItem(
			position = 8,
			keyName = "timerHeight",
			name = "Height of timer",
			description = "Change the vertical offset of the timer above the npc"
	)
	default int timerHeight()
	{
		return 25;
	}

	@ConfigItem(
			position = 9,
			keyName = "showOverlayTicks",
			name = "Show timer in ticks",
			description = "Configures whether or not the timer should be shown in tick or seconds"
	)
	default boolean showOverlayTicks()
	{
		return false;
	}

	@ConfigItem(
			position = 10,
			keyName = "showTimingType",
			name = "Show type of timer used",
			description = "Shows the type of timer used in the suffix of the actual time displayed"
	)
	default boolean showTimingType()
	{
		return false;
	}
}
