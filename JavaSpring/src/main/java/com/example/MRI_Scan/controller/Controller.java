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
@GetMapping("/easy")
@ResponseBody
public String yu(){
String url = "http://127.0.0.1:8000/easy";
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
HttpEntity<Void> entity = new HttpEntity<>(headers);
ResponseEntity<String> response = restTemplate.exchange(url
, HttpMethod.GET,
        entity,
        String.class);
return response.getBody();
    }
@PostMapping("/")
    public String scanMri(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,Model model) throws IOException {
    String fastApiUrl = "http://127.0.0.1:8000/get_predict";
    ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
        @Override
        public String getFilename() {
            return file.getOriginalFilename(); // لازم علشان FastAPI يعرف اسم الفايل
        }
    };
    // 3. جسم الرسالة (form-data)
    MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
    form.add("file", fileAsResource);

    // 4. إعداد الهيدر
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

    // 5. إرسال الطلب
    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(form, headers);
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.postForEntity(fastApiUrl, request, String.class);

    String fileName = file.getOriginalFilename();

    // 1. نحول الصورة إلى base64
    String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

    // 2. نجهز src للعرض في <img>
    String imageSrc = "data:image/jpeg;base64," + base64Image; // لو PNG غيّر jpeg إلى png

    // 3. نضيفه للـ model
    //model.addAttribute("imagePath", imageSrc);
    redirectAttributes.addFlashAttribute("imagePath", imageSrc);
    // 6. طباعة الرد من FastAPI
    System.out.println(response.getBody());
    String result = response.getBody();
    redirectAttributes.addFlashAttribute("result", result);
    //model.addAttribute("result",response.getBody());
return "redirect:/";
}
    @GetMapping("/")
    public String index() {

        return "index";
    }


}
