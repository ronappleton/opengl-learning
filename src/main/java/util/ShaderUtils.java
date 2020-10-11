package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShaderUtils {
    private String filepath;
    private String shaderSource;
    private String[] sources;

    public ShaderUtils(String filepath) {
        this.filepath = filepath;
        try {
            shaderSource = new String(Files.readAllBytes(Paths.get(filepath)));
            sources = shaderSource.split("#type");
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "Error: Could not open file for shader: '" + filepath + "'";
        }
    }

    public String getShader(String type) {
        if (!shaderSource.contains(type + StringUtils.eol())) {
            assert false : "The loaded shader source does not contain the required shader in '" + filepath + "', Type requested: '" + type + "'" + StringUtils.eol();
        }

        for (int i = 1; i <= sources.length; i++) {
            if (sources[i].contains(type + StringUtils.eol())) {
                return sources[i].replace(type, "").trim();
            }
        }

        assert false : "Shader type could not be found.";
        return null;
    }
}
