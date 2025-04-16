package org.example.staticdata;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class AbstractFIO {
    protected List<String> names;
    protected List<String> surnames;
}
