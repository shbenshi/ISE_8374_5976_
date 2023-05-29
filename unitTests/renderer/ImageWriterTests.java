package renderer;
import primitives.Color;
import org.junit.jupiter.api.Test;

// does it does?
import static org.junit.jupiter.api.Assertions.*;
// try
/**
 * Unit tests for the ImageWriter class.
 *
 * @authors
 * Tzofiya David (209918374)
 * Shira Ben Shimol (326065976)
 */

class ImageWriterTests {
    /**
     * Test method for {@link renderer.ImageWriter#writeToImage()}.
     */
    @Test
    void testWriteToImage() {
        // Create a new ImageWriter object with the specified parameters
        ImageWriter imageWriter = new ImageWriter("testGoldGrid", 800, 500);

        // Calculate the value for the fringes
        double fringes = imageWriter.getNx() / 16;

        // Iterate over the image pixels
        for (int i = 0; i < 800; i++)
        {
            for (int j = 0; j < 500; j++) {
                // Check if the current pixel is on a fringe
                if (j % fringes == 0 || i % fringes == 0) {
                    // Write the pixel color as black
                    imageWriter.writePixel(i, j, Color.BLACK);
                } else {
                    // Write the pixel color as gold
                    imageWriter.writePixel(i, j, Color.GOLD);
                }
            }
        }
        // Write the image to a file
        imageWriter.writeToImage();
    }
}