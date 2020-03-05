package example.controller.dashboard;

import example.domain.Post;
import example.domain.PostImage;
import example.repo.PostImageRepo;
import example.repo.PostRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dashboard/posts")
public class PostController {
    private final PostRepo postRepo;
    private final PostImageRepo postImageRepo;
    @Value("${upload.path}")
    private String uploadPath;

    public PostController(PostRepo postRepo, PostImageRepo postImageRepo) {
        this.postRepo = postRepo;
        this.postImageRepo = postImageRepo;
    }

    @GetMapping
    public List<Post> list() {
        return postRepo.findAll();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postRepo.save(post);
    }

    @PutMapping("{id}")
    public Post update(
            @PathVariable("id") Post postFromDb,
            @RequestBody Post post
    ) {
        BeanUtils.copyProperties(post, postFromDb, "id");
        return postRepo.save(postFromDb);
    }

    @PostMapping("/{id}/images")
    public PostImage uploadImage(
            @PathVariable("id") Post post,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        PostImage postImage = new PostImage();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            postImage.setPost(post);
            postImage.setFilePath(uploadPath + "/" + resultFileName);

            postImageRepo.save(postImage);
        }

        return postImage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Post post) {
        postRepo.delete(post);
    }
}
