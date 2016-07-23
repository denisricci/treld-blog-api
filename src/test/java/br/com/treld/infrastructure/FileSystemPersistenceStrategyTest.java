package br.com.treld.infrastructure;

import br.com.treld.TreldTest;
import br.com.treld.infrastructure.impl.FileSystemPersistenceStrategy;
import br.com.treld.model.FileLocation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

/**
 * Created by rsouza on 23/07/16.
 */
@TreldTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FileSystemPersistenceStrategyTest {

    @Autowired
    private FileSystemPersistenceStrategy fileSystemPersistenceStrategy;

    @Test
    public void saveTest() throws Exception {
        String originalName = "image-text.jpg";
        InputStream is = getClass().getResourceAsStream("/files-for-test/test-image.jpg");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", originalName, "image/jpeg", is);
        FileLocation fileLocation = fileSystemPersistenceStrategy.save(mockMultipartFile);

        Assert.assertEquals(MediaType.IMAGE_JPEG.toString(), fileLocation.getMimeType());
        Assert.assertEquals(originalName, fileLocation.getOriginalName());

        Assert.assertNotNull(fileSystemPersistenceStrategy.get(fileLocation.getId()));
    }
}
