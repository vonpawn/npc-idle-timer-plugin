package com.npcidletimer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.inject.Inject;
import net.runelite.api.Point;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

public class NPCIdleTimerOverlay extends Overlay
{

	private final NPCIdleTimerPlugin plugin;
	private final NPCIdleTimerConfig config;

    protected String lastFont = "";
    protected int lastFontSize = 0;
    protected boolean useRunescapeFont = true;
    protected NPCIdleTimerConfig.FontStyle lastFontStyle = NPCIdleTimerConfig.FontStyle.DEFAULT;
    protected Font font = null;


	NumberFormat format = new DecimalFormat("#");

	@Inject
	NPCIdleTimerOverlay(NPCIdleTimerPlugin plugin, NPCIdleTimerConfig config)
	{
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		this.plugin = plugin;
		this.config = config;
	}

	protected void handleFont(Graphics2D graphics) {
        if (font != null) {
            graphics.setFont(font);
            if (useRunescapeFont) {
                graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            }
        }
    }

	@Override
	public Dimension render(Graphics2D graphics)
	{
		updateFont();
		handleFont(graphics);
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
	private void updateFont() {
		//only perform anything within this function if any settings related to the font have changed
		if (!lastFont.equals(config.fontName()) || lastFontSize != config.fontSize() || lastFontStyle != config.fontStyle()) {
			if (config.customFont()) {
				lastFont = config.fontName();
			}
			lastFontSize = config.fontSize();
			lastFontStyle = config.fontStyle();

			//use runescape font as default
			if (config.fontName().equals("") || config.customFont() == false) {
				if (config.fontSize() < 16) {
					font = FontManager.getRunescapeSmallFont();
				} else if (config.fontStyle() == NPCIdleTimerConfig.FontStyle.BOLD || config.fontStyle() == NPCIdleTimerConfig.FontStyle.BOLD_ITALICS) {
					font = FontManager.getRunescapeBoldFont();
				} else {
					font = FontManager.getRunescapeFont();
				}

				if (config.fontSize() > 16) {
					font = font.deriveFont((float) config.fontSize());
				}

				if (config.fontStyle() == NPCIdleTimerConfig.FontStyle.BOLD) {
					font = font.deriveFont(Font.BOLD);
				}
				if (config.fontStyle() == NPCIdleTimerConfig.FontStyle.ITALICS) {
					font = font.deriveFont(Font.ITALIC);
				}
				if (config.fontStyle() == NPCIdleTimerConfig.FontStyle.BOLD_ITALICS) {
					font = font.deriveFont(Font.ITALIC | Font.BOLD);
				}

				useRunescapeFont = true;
				return;
			}

			int style = Font.PLAIN;
			switch (config.fontStyle()) {
				case BOLD:
					style = Font.BOLD;
					break;
				case ITALICS:
					style = Font.ITALIC;
					break;
				case BOLD_ITALICS:
					style = Font.BOLD | Font.ITALIC;
					break;
			}

			font = new Font(config.fontName(), style, config.fontSize());
			useRunescapeFont = false;
		}
	}

}
