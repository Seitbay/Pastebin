package ru.bull.pastebin.service;


import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import ru.bull.pastebin.model.Post;
import ru.bull.pastebin.repository.PostRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostService {
    private final PostRepository postRepo;

    public PostService(PostRepository postRepo){
        this.postRepo = postRepo;
    }

    public Post createPost(String content) {
        try {
            String hash = DigestUtils.sha256Hex(content).substring(0, 16);

            Firestore db = FirestoreClient.getFirestore();
            Map<String, Object> data = new HashMap<>();
            data.put("content", content);
            data.put("createdAt", FieldValue.serverTimestamp());

            String firestorePath = "posts/" + hash;
            DocumentReference docRef = db.document(firestorePath);
            docRef.set(data).get();

            Post post = new Post();
            post.setHash(hash);
            post.setLink(firestorePath);

            return postRepo.save(post);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании поста", e);
        }
    }
}
