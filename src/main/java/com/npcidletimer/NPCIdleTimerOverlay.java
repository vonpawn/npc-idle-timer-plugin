package com.npcidletimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

public class NPCIdleTimerOverlay extends Overlay
{

	private final Client client;
	private final NPCIdleTimerPlugin plugin;
	private final NPCIdleTimerConfig config;

	NumberFormat format = new DecimalFormat("#");

	@Inject
	NPCIdleTimerOverlay(Client client, NPCIdleTimerPlugin plugin, NPCIdleTimerConfig config)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		this.client = client;
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (client.isInInstancedRegion())
		{
			return null;
		}

		if (config.showOverlay())
		{
			plugin.getWanderingNPCs().forEach((id, npc) -> renderTimer(npc, graphics));
		}
		return null;
	}

	private void renderTimer(final WanderingNPC npc, final Graphics2D graphics)
	{
		double maxDisplay = config.maxDisplay();

		if (config.customTimer())
		{
			maxDisplay = config.customTiming();
		}

		double timeLeft = maxDisplay - npc.getTimeWithoutMoving();

		double lowDisplay = config.lowDisplay();
		Color timerColor = config.normalTimerColor();

		if (timeLeft < 0)
		{
			timeLeft = 0;
		}

		if (timeLeft <= lowDisplay)
		{
			timerColor = config.lowTimerColor();
		}

		String timeLeftString = String.valueOf(format.format(timeLeft));

		if (config.showTimingType())
		{
			if (config.showOverlayTicks())
			{
				timeLeftString = timeLeftString + ("T");
			}
			else
			{
				timeLeftString= timeLeftString + ("S");
			}
		}



		final Point canvasPoint = npc.getNpc().getCanvasTextLocation(graphics, timeLeftString, npc.getNpc().getLogicalHeight() + config.timerHeight());

		if (canvasPoint != null && (maxDisplay >= timeLeft))
		{
			OverlayUtil.renderTextLocation(graphics, canvasPoint, timeLeftString, timerColor);
		}
	}

}
