/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 * 
 * Quark is Open Source and distributed under the
 * [ADD-LICENSE-HERE]
 * 
 * File Created @ [10/06/2016, 18:08:12 (GMT)]
 */
package vazkii.quark.experimental.features;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import com.sun.jna.platform.unix.X11.Screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.client.event.ScreenshotEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import vazkii.quark.base.module.Feature;

public class PanoramaMaker extends Feature {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");

	File panoramaDir;
	File currentDir;
	int panoramaStep;
	boolean takingPanorama;
	int currentWidth, currentHeight;

	@SubscribeEvent
	public void takeScreenshot(ScreenshotEvent event) {
		if(takingPanorama)
			return;

		if(GuiScreen.isCtrlKeyDown() && GuiScreen.isShiftKeyDown()) {
			takingPanorama = true;
			panoramaStep = 0;

			if(panoramaDir == null)
				panoramaDir = new File(event.getScreenshotFile().getParentFile(), "panoramas");
			if(!panoramaDir.exists())
				panoramaDir.mkdirs();

			int i = 0;
			String ts = getTimestamp();
			do {
				if(i == 0)
					currentDir = new File(panoramaDir, ts);
				else currentDir = new File(panoramaDir, ts + "_" + i);
			} while(currentDir.exists());

			currentDir.mkdirs();

			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();

		if(takingPanorama) {
			if(event.phase == Phase.START) {
				if(panoramaStep == 0) {
					mc.gameSettings.hideGUI = true;
					currentWidth = mc.displayWidth;
					currentHeight = mc.displayHeight;
					mc.resize(256, 256);	
				}

				switch(panoramaStep) {
				case 1:
					mc.thePlayer.rotationYaw = 180;
					mc.thePlayer.rotationPitch = 0;
					break;
				case 2:
					mc.thePlayer.rotationYaw = 90;
					mc.thePlayer.rotationPitch = 0;
					break;
				case 3:
					mc.thePlayer.rotationYaw = 0;
					mc.thePlayer.rotationPitch = 0;
					break;
				case 4:
					mc.thePlayer.rotationYaw = -90;
					mc.thePlayer.rotationPitch = 0;
					break;
				case 5:
					mc.thePlayer.rotationYaw = 180;
					mc.thePlayer.rotationPitch = -90;
					break;
				case 6:
					mc.thePlayer.rotationYaw = 180;
					mc.thePlayer.rotationPitch = 90;
					break;
				}
				mc.thePlayer.prevRotationYaw = mc.thePlayer.rotationYaw;
				mc.thePlayer.prevRotationPitch = mc.thePlayer.rotationPitch;
			} else {
				if(panoramaStep > 0)
					saveScreenshot(currentDir, "panorama_" + (panoramaStep - 1) + ".png", mc.displayWidth, mc.displayHeight, mc.getFramebuffer());
				panoramaStep++;
				if(panoramaStep == 7) {
					mc.gameSettings.hideGUI = false;
					takingPanorama = false;
					mc.resize(currentWidth, currentHeight);
				}
			}
		}
	}

	public static void saveScreenshot(File dir, String screenshotName, int width, int height, Framebuffer buffer) {
		try {
			BufferedImage bufferedimage = ScreenShotHelper.createScreenshot(width, height, buffer);
			File file2 = new File(dir, screenshotName);

			net.minecraftforge.client.event.ScreenshotEvent event = net.minecraftforge.client.ForgeHooksClient.onScreenshot(bufferedimage, file2);
			ImageIO.write(bufferedimage, "png", (File)file2);
		} catch(Exception exception) { }
	}

	private static String getTimestamp() {
		String s = DATE_FORMAT.format(new Date()).toString();
		return s;
	}

	@Override
	public boolean hasSubscriptions() {
		return isClient();
	}

}
