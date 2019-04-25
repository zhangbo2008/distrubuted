package com.hc.distributed.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    String group;

    String filePath;

    String name;
}
