package com.example.recruit.controller;

import com.example.recruit.domain.Bbs;
import com.example.recruit.domain.Review;
import com.example.recruit.dto.BbsContentDto;
import com.example.recruit.dto.BbsListInfo;
import com.example.recruit.service.BbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/board")
public class BbsApiController {
    @Autowired
    private BbsService bbsService;

    @GetMapping(value="")
    public BbsListInfo bbsList(@RequestParam int page){
        return bbsService.bbsListInfo(page);
    }

    @GetMapping("/view/id={bbs_id}")
    public BbsContentDto findBbsById(@PathVariable("bbs_id") int bbsId){
        return bbsService.findBbsContentById(bbsId);
    }

    @PostMapping("/write/review")
    public int writeReview(@RequestBody Review review){
        return bbsService.writeReview(review);
    }
    @PostMapping("/write/bbs")
    public int writeBbs(@RequestBody Bbs bbs){
        bbsService.writeBbs(bbs);
        return 1;
    }
    @GetMapping("/search")
    public BbsListInfo searchByTitleBbsList(@RequestParam(name="title", required = false)String title, @RequestParam(value = "content", required = false)String content, @RequestParam(value = "titleAndContent", required = false) String titleAndContent){
        if(title != null){
            return bbsService.searchByTitleBbsList(title);
        }
        if(content != null){
            return bbsService.searchByContentBbsList(content);
        }
        if(titleAndContent != null){
            return bbsService.searchByTitleAndContentBbsList(titleAndContent);
        }
        return null;
    }

    @PostMapping("/edit/bbs")
    public int editBbs(@RequestBody Bbs bbs){
        return bbsService.editBbs(bbs);
    }

    @PostMapping("/check/bbsPw")
    public Boolean removeBbsPassword(@RequestBody Map<String, String> map){
        int bbsId = Integer.parseInt(map.get("bbsId"));
        String bbsPw = map.get("bbsPw");
        return bbsService.checkBbsPw(bbsId, bbsPw);
    }
    @PostMapping("/remove/bbs")
    public Boolean removeBbs(@RequestBody Map<String, String> map){
        int bbsId = Integer.parseInt(map.get("bbsId"));
        String bbsPw = map.get("bbsPw");
        return bbsService.removeBbs(bbsId, bbsPw);
    }
}
