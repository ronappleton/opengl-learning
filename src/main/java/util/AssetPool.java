package util;

import components.Spritesheet;
import renderer.Shader;
import renderer.Texture;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Shader> shaders = new HashMap<String, Shader>();
    private static Map<String, Texture> textures = new HashMap<String, Texture>();
    private static Map<String, Spritesheet> spritesheets = new HashMap<String, Spritesheet>();
    private static Map<String, byte[]> fonts = new HashMap<String, byte[]>();

    public static Shader getShader(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.shaders.containsKey(file.getAbsolutePath())) {
            return AssetPool.shaders.get(file.getAbsolutePath());
        }
        Shader shader = new Shader(resourceName);
        shader.compile();
        AssetPool.shaders.put(file.getAbsolutePath(), shader);

        return shader;
    }

    public static Texture getTexture(String resourceName) {
        File file = new File(resourceName);
        if (AssetPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetPool.textures.get(file.getAbsolutePath());
        }
        Texture texture = new Texture(resourceName);
        AssetPool.textures.put(file.getAbsolutePath(), texture);

        return texture;
    }

    public static void addSpritesheet(String resourceName, Spritesheet spritesheet) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }

    public static Spritesheet getSpriteSheet(String resourceName) {
        File file = new File(resourceName);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            assert false : "Error spritesheet '" + resourceName + "' not in AssetPool";
        }

        return AssetPool.spritesheets.getOrDefault(file.getAbsolutePath(), null);
    }

    public static byte[] getFont(String resourceName) throws IOException {
        File file = new File(resourceName);
        if (!AssetPool.fonts.containsKey(file.getAbsolutePath())) {
            AssetPool.fonts.put(file.getAbsolutePath(), Files.readAllBytes(file.toPath()));
        }

        return AssetPool.fonts.get(file.getAbsolutePath());
    }
}
