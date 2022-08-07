package com.ericsson.APIposts.resources;

import com.ericsson.APIposts.domain.Post;
import com.ericsson.APIposts.resources.util.URL;
import com.ericsson.APIposts.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/fullsearch")
            public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "")String minDate,
            @RequestParam(value = "maxDate", defaultValue = "")String maxDate) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0));
        Date max = URL.convertDate(maxDate, new Date(0));
        List<Post> list = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);

    }

}
