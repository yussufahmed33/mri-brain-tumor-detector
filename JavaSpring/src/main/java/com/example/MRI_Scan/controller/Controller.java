package com.example.MRI_Scan.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private RestTemplate restTemplate;

@PostMapping("/")
    public String scanMri(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,Model model) throws IOException {
    String fastApiUrl = "http://127.0.0.1:8000/get_predict";
    ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
        @Override
        public String getFilename() {
            return file.getOriginalFilename();
        }
    };
   
    MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
    form.add("file", fileAsResource);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(form, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, request, String.class);
    String fileName = file.getOriginalFilename();
    String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
    String imageSrc = "data:image/jpeg;base64," + base64Image; // لو PNG غيّر jpeg إلى png

redirectAttributes.addFlashAttribute("imagePath", imageSrc);
    String result = response.getBody();
    redirectAttributes.addFlashAttribute("result", result);
return "redirect:/";
}
    @GetMapping("/")
    public String index() {

        return "index";
    }


}
