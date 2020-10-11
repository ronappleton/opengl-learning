package renderer;

import org.joml.*;
import org.lwjgl.BufferUtils;
import util.ShaderUtils;
import util.StringUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class Shader {
    private int shaderProgramId;

    private String vertexSource;
    private String fragmentSource;
    private String filepath;

    public Shader(String filepath) {
        this.filepath = filepath;
        ShaderUtils utils = new ShaderUtils(filepath);
        vertexSource = utils.getShader("vertex");
        fragmentSource = utils.getShader("fragment");
    }

    public void compile() {
        /// ##############################################################
        /// Compile and Link Shaders
        /// ##############################################################

        /// First load and compile the vertex shader
        int vertexId = glCreateShader(GL_VERTEX_SHADER);
        /// Pass the shader source code to the GPU
        glShaderSource(vertexId, vertexSource);
        glCompileShader(vertexId);

        /// Check for errors in compilation
        int success = glGetShaderi(vertexId, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexId, GL_INFO_LOG_LENGTH);
            System.out.println("Error: '" + filepath + "'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexId, len));
            assert false : "";
        }

        /// Now load and compile the fragment shader
        int fragmentId = glCreateShader(GL_FRAGMENT_SHADER);
        /// Pass the shader source code to the GPU
        glShaderSource(fragmentId, fragmentSource);
        glCompileShader(fragmentId);

        /// Check for errors in compilation
        success = glGetShaderi(fragmentId, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentId, GL_INFO_LOG_LENGTH);
            System.out.println("Error: '" + filepath + "'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentId, len));
            assert false : "";
        }

        /// Link shaders and check for errors
        shaderProgramId = glCreateProgram();
        glAttachShader(shaderProgramId, vertexId);
        glAttachShader(shaderProgramId, fragmentId);
        glLinkProgram(shaderProgramId);

        /// Check for errors in linking
        success = glGetProgrami(shaderProgramId, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgramId, GL_INFO_LOG_LENGTH);
            System.out.println("Error: '" + filepath +"'\n\tShader program linking failed.");
            System.out.println(glGetProgramInfoLog(shaderProgramId, len));
            assert false : "";
        }

        System.out.println("Compiling and linking completed.");
    }

    public void use() {
        // Bind shader program
        glUseProgram(shaderProgramId);
    }

    public void detach() {
        glUseProgram(0);
    }

    public void uploadMat4f(String varName, Matrix4f mat4) {
        int varLocation = glGetUniformLocation(shaderProgramId, varName);
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation, false, matBuffer);
    }
}
