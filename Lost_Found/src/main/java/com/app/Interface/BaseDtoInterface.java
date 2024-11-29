package com.app.Interface;

import com.app.Entity.Lost_Found;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface BaseDtoInterface {
    String name();
    String description();
    String category();
    String objImage();
    Lost_Found lost_found;
    String place();
    List<String> tags();
    LocalDateTime time();
}
