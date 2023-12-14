package com.npcidletimer;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

@ConfigGroup("npcidletimerplugin")
public interface NPCIdleTimerConfig extends Config
{
	enum FontStyle {
        BOLD("Bold"),
        ITALICS("Italics"),
        BOLD_ITALICS("Bold and italics"),
        DEFAULT("Default");

        String name;

        FontStyle(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

	@ConfigSection(
			name = "Timer settings",
			description = "Settings relating to Timer",
			position = 1
	)
	String timer_settings = "timer_settings";

	@ConfigSection(
            name = "font settings",
            description = "Settings relating to fonts",
            position = 2
    )
    String font_settings = "font_settings";

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
			description = "Enter names of NPCs where you wish to use this plugin",
			section = timer_settings
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
			description = "Configures the color of the timer",
			section = timer_settings
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
			description = "Configures the color of the timer when remaining time is low",
			section = timer_settings
	)
	default Color lowTimerColor()
	{
		return Color.RED;
	}

	@Range(
			max = 1000
	)
	@ConfigItem(
			position = 4,
			keyName = "maxDisplay",
			name = "Time to start timer",
			description = "This defines at what amount of seconds/ticks the timer will be shown. Before this time the npc won't show a timer",
			section = timer_settings
	)
	default int maxDisplay()
	{
		return 300;
	}

	@ConfigItem(
			position = 5,
			keyName = "customTimer",
			name = "Enable custom timer",
			description = "This option enables you to set a custom timer that fits the actual respawn timer of your npc or for other timing related manners",
			section = timer_settings
	)
	default boolean customTimer()
	{
		return false;
	}

	@ConfigItem(
			position = 6,
			keyName = "customTiming",
			name = "Custom timer",
			description = "The starting tick counter on the npc. The default timer of this plugin is set at 300 seconds. You may change this to fit the actual despawn timer",
			section = timer_settings
	)
	default int customTiming()
	{
		return 300;
	}

	@ConfigItem(
			position = 7,
			keyName = "lowDisplay",
			name = "Timer low value",
			description = "The maximum seconds at which the timer is considered low",
			section = timer_settings
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
			description = "Change the vertical offset of the timer above the npc",
			section = timer_settings
	)
	default int timerHeight()
	{
		return 25;
	}

	@ConfigItem(
			position = 9,
			keyName = "showOverlayTicks",
			name = "Show timer in ticks",
			description = "Configures whether or not the timer should be shown in tick or seconds",
			section = timer_settings
	)
	default boolean showOverlayTicks()
	{
		return false;
	}

	@ConfigItem(
			position = 10,
			keyName = "showTimingType",
			name = "Show type of timer used",
			description = "Shows the type of timer used in the suffix of the actual time displayed",
			section = timer_settings
	)
	default boolean showTimingType()
	{
		return false;
	}
	@ConfigItem(
			position = 11,
			keyName = "customFont",
			name = "Enable custom fonts",
			description = "Enabling this setting makes it possible to use the custom font from the box below this",
			section = font_settings
	)
	default boolean customFont() {
		return true;
	}

	@ConfigItem(
			position = 12,
			keyName = "fontName",
			name = "Font",
			description = "Name of the font to use for the hp shown. Leave blank to use RuneLite setting.",
			section = font_settings
	)
	default String fontName() {
		return "roboto";
	}

	@ConfigItem(
			position = 13,
			keyName = "fontStyle",
			name = "Font style",
			description = "Style of the font to use for the hp shown. Only works with custom font.",
			section = font_settings
	)
	default FontStyle fontStyle() {
		return FontStyle.DEFAULT;
	}

	@ConfigItem(
			position = 14,
			keyName = "fontSize",
			name = "Font size",
			description = "Size of the font to use for XP drops. Only works with custom font.",
			section = font_settings
	)
	default int fontSize() {
		return 12;
	}
}
