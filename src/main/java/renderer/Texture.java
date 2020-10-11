package renderer;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture {
    private String filepath;
    private int texId;

    public Texture(String filepath) {
        this.filepath = filepath;

        // Generate Texture on GPU
        texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);

        // Set texture parameters
        // Repeat image in both directions
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        // Tell OpenGl to pixelate when stretching
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        // Tell OpenGl to pixelate when shrinking
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        ByteBuffer image = stbi_load(filepath, width, height, channels, 0);

        if (image == null) {
            assert false : "Error: (Texture) Could not load image '" + filepath + "'";
        }

        if (channels.get(0) != 3 && channels.get(0) != 4) {
            // We don't know how to handle these channels
            stbi_image_free(image);
            assert false : "Error: (Texture) Unknown number of colour channels in texture";
        }

        int CHANNELS = channels.get(0) == 3 ? GL_RGB : GL_RGBA;

        glTexImage2D(
                GL_TEXTURE_2D,
                0,
                CHANNELS,
                width.get(0),
                height.get(0),
                0,
                CHANNELS,
                GL_UNSIGNED_BYTE,
                image
        );

        // stbi accesses memory via c so release that memory to prevent leaks!! Important
        stbi_image_free(image);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texId);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getId() {
        return texId;
    }
}
