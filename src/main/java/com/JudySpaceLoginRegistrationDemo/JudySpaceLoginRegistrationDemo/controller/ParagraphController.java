package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.model.request.ParagraphRequest;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.service.ParagraphService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paragraph/")
@AllArgsConstructor
public class ParagraphController {
    private final ParagraphService paragraphService;

    @PostMapping("add")
    public void MakeNewParagraph(@RequestBody ParagraphRequest paragraphRequest){
        System.out.println("hehe");
    }
}
