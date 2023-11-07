package edu.iastate.coms309.flatfinder.images;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * Controller class for serving image files.
 */
@RestController
@RequestMapping(value = "/images")
public class ImageController
{
    /**
     * Root path where the image files are stored.
     */
    private static final String FILE_PATH_ROOT = "/home/images/";

    /**
     * Retrieves an image file based on its filename.
     *
     * @param filename The name of the file to retrieve.
     * @return ResponseEntity with the image file data.
     */
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename)
    {
        byte[] image = new byte[0];

        try
        {
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + filename));
        } catch(IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}