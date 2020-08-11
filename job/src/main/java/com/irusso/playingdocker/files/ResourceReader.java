package com.irusso.playingdocker.files;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceReader {

  public Properties getProperties(String path) throws ResourceNotFoundException {
    try {
      ClassLoader classLoader = this.getClass().getClassLoader();
      if (classLoader == null) {
        throw new ResourceNotFoundException();
      }

      InputStream resource = classLoader.getResourceAsStream(path);
      if (resource == null) {
        throw new ResourceNotFoundException();
      }

      Properties props = new Properties();
      props.load(resource);
      return props;
    } catch (IOException e) {
      throw new ResourceNotFoundException();
    }
  }
}
