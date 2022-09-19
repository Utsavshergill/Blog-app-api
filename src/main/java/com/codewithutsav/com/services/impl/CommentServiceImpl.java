package com.codewithutsav.com.services.impl;

import com.codewithutsav.com.entities.Comment;
import com.codewithutsav.com.entities.Post;
import com.codewithutsav.com.exceptions.ResourceNotFoundException;
import com.codewithutsav.com.payloads.CommentDto;
import com.codewithutsav.com.repositories.CommentRepo;
import com.codewithutsav.com.repositories.PostRepo;
import com.codewithutsav.com.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment=this.commentRepo.save(comment);


        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {


        Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","CommentId",commentId));

        this.commentRepo.delete(com);
    }
}
