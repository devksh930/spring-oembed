package me.devksh930.oembed.controller;

import me.devksh930.oembed.exception.ClientForbiddenException;
import me.devksh930.oembed.exception.ProviderNotMatchingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OembedRestControllerTest {


    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setUp() {
        //MockMvc 설정
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("youtube : oEbmed리소스를 가져온다")
    void getOembedResource_youtube() throws Exception {
        mvc.perform(get("/oembeds")
                        .queryParam("url", "https://www.youtube.com/watch?v=dBD54EZIrZo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("vimeo : oEbmed리소스를 가져온다")
    void getOembedResource_vimeo() throws Exception {
        mvc.perform(get("/oembeds")
                        .queryParam("url", "https://vimeo.com/20097015")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("twitter : oEbmed리소스를 가져온다")
    void getOembedResource_twitter() throws Exception {
        mvc.perform(get("/oembeds")
                        .queryParam("url", "https://twitter.com/hellopolicy/status/867177144815804416")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("실패 : instagram : oEbmed리소스를 가져온다")
    void getOembedResource_instagram() throws Exception {
        mvc.perform(get("/oembeds")
                        .queryParam("url", "https://www.instagram.com/p/BUawPlPF_Rx")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect((result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(ClientForbiddenException.class))))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("실패 : 잘못된 URL을 입력하여 Exception발생")
    void getOembedResource_wrongParam() throws Exception {
        mvc.perform(get("/oembeds")
                        .queryParam("url", "https://x")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andDo(print())
                .andExpect((result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(ProviderNotMatchingException.class))))
                .andExpect(status().isBadRequest());
    }
}