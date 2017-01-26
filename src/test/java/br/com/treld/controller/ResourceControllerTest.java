package br.com.treld.controller;

import br.com.treld.TreldTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rsouza on 23/07/16.
 */
@TreldTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceControllerTest extends AbstractControllerTest{

    private MockMvc mockMvc;

    @Before
    public void before() throws Exception{
        mockMvc = getMockMvc();
        doLogin(mockMvc);
    }

    @Test
    public void uploadTest() throws Exception {
        MockMultipartFile file = getImageForTest();

        mockMvc.perform(
                fileUpload(getBaseUrl() + "/resource")
                        .file(file)
                        .session(getSession()))
                        .andExpect(status().is2xxSuccessful());
    }

    /**
     * Test if only logged people can upload files
     */
    @Test
    public void failedUploadTest() throws Exception {
        MockMultipartFile file = getImageForTest();

        mockMvc.perform(
                fileUpload(getBaseUrl() + "/resource")
                        .file(file))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getImageTest() throws Exception{
        MockMultipartFile file = getImageForTest();
        Map<String, String> resultMap = new HashMap<>();
        mockMvc.perform(
                fileUpload(getBaseUrl() + "/resource")
                        .file(file)
                        .session(getSession()))
                .andExpect(status().is2xxSuccessful())
        .andDo((result) -> resultMap.put("location", result.getResponse().getHeader("Location")));

        String location = resultMap.get("location");

        mockMvc.perform(get(location))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG));
    }

    private MockMultipartFile getImageForTest() throws IOException {
        InputStream is = ResourceControllerTest.class.getResourceAsStream("/files-for-test/test-image.jpg");
        return new MockMultipartFile("file", "image-text.jpg", "image/jpeg", is);
    }

}
