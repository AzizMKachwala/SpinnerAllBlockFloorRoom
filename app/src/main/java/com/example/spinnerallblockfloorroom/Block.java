package com.example.spinnerallblockfloorroom;

import java.util.ArrayList;
import java.util.List;

public class Block {
    String blockName;
    List<Floor> floors;

    public Block(String blockName) {
        this.blockName = blockName;
        this.floors = new ArrayList<>();
    }

    public String getBlockName() {
        return blockName;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }
}