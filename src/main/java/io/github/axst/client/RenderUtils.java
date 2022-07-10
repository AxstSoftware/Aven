package io.github.axst.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static net.minecraft.client.gui.Gui.drawRect;

/**
 * Useful class for render things on your screen
 * @since 0.1
 * @author sdxqw
 */
public class RenderUtils {

    private static final Map<String, ResourceLocation> playerSkins = new HashMap<>();

    /**
     * Render profile head with text
     * Override this method for modify things on it
     * @param locationHead String the location of the head
     *                     eg: client/heads/
     */
    public static void renderProfile(String locationHead) {
        if(Minecraft.getMinecraft().getSession().getUsername() != null){
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(Minecraft.getMinecraft().getSession().getUsername(), 28, 11, new Color(255, 255, 255,255).getRGB());
            RenderUtils.drawRoundedOutline(7, 7, (float)36 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(Minecraft.getMinecraft().getSession().getUsername()), 24, 4f, 2.0f,new Color( 255,255,255 ).getRGB());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation headLocation = RenderUtils.getHeadLocation(Minecraft.getMinecraft().getSession().getUsername(), locationHead);
            Minecraft.getMinecraft().getTextureManager().bindTexture(headLocation);
            Gui.drawModalRectWithCustomSizedTexture(12, 9, 0,0,12,12, 12, 12);
        }
    }

    /**
     * Get head location
     * @param displayName String the name of the player
     * @param locationHead String the location of the head
     *                     eg: client/heads/
     *                     please make sure to have a 32x image of the head stave named stave!
     * @return the player head transformed.
     */
    public static ResourceLocation getHeadLocation(String displayName, String locationHead) {
        ResourceLocation playerSkin = playerSkins.getOrDefault( displayName, new ResourceLocation( locationHead + displayName + ".png" ) );
        if (!playerSkins.containsKey( displayName )) {
            ThreadDownloadImageData skinData = new ThreadDownloadImageData( null, "https://minotar.net/helm/" + displayName + "/32.png", new ResourceLocation( locationHead + "steve.png" ), null );
            Minecraft.getMinecraft().getTextureManager().loadTexture( playerSkin, skinData );
            playerSkins.put( displayName, playerSkin );
        }
        return playerSkin;
    }

    /**
     * Set color method
     * @param color int color
     */
    public static void setColor(final int color) {
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glColor4f(r, g, b, a);
    }

    /**
     * Draw rounded rect on screen
     * @param x float of the x
     * @param y float of the y
     * @param x1 float of the x1
     * @param y1 float of the y1
     * @param radius float of the radius
     * @param lineWidth float of the line with
     * @param color int color fo the rect
     */
    public static void drawRoundedOutline(float x, float y, float x1, float y1, final float radius, final float lineWidth, final int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2.0;
        y *= 2.0;
        x1 *= 2.0;
        y1 *= 2.0;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        setColor(color);
        GL11.glEnable(2848);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(2);
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * radius * -1.0, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius * -1.0);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * radius * -1.0, y1 - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius * -1.0);
        }
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x1 - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y1 - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x1 - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
    }

    /**
     * Draw Horizontal Line
     * @param startX int start of the line
     * @param endX int end of the line
     * @param y int the Y of the line
     * @param color int color of the line
     */
    public static void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int i = startX;
            startX = endX;
            endX = i;
        }
        drawRect(startX, y, endX + 1, y + 1, color);
    }

    /**
     * Draw Vertical Line
     * @param x int the X of the line
     * @param startY int start of the line
     * @param endY int end of the line
     * @param color int color of the line
     */
    public static void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int i = startY;
            startY = endY;
            endY = i;
        }
        drawRect(x, startY + 1, x + 1, endY, color);
    }

    /**
     * Draw Hallow Rect
     * @param x int x of the rect
     * @param y int y of the rect
     * @param w int with of the rect
     * @param h int height of the rect
     * @param color int color of the rect
     */
    public static void drawHollowRect(int x, int y, int w, int h, int color) {
        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);
        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);
    }
}
