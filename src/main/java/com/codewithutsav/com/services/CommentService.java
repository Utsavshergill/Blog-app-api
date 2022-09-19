package com.codewithutsav.com.services;

import com.codewithutsav.com.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);

}
