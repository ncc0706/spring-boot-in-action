package io.arukas.service;

import io.arukas.entity.Tag;
import io.arukas.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public Tag save(String tagName){
        Tag tag = new Tag();
        tag.setTagName(tagName);
       return tagRepository.save(tag);
    }

}
