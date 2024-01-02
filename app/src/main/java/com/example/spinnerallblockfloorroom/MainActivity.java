package com.example.spinnerallblockfloorroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppCompatSpinner blockSpinner, floorSpinner, roomSpinner;
    Button displayButton, resetButton;
    TextView resultTextView;

    BlockAdapter blockAdapter;
    FloorAdapter floorAdapter;
    RoomAdapter roomAdapter;

    List<Block> blockList;
    List<Floor> floorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blockSpinner = findViewById(R.id.blockSpinner);
        floorSpinner = findViewById(R.id.floorSpinner);
        roomSpinner = findViewById(R.id.roomSpinner);

        resultTextView = findViewById(R.id.txtResult);
        displayButton = findViewById(R.id.btnDisplay);
        resetButton = findViewById(R.id.btnReset);

        String[] blockData = {"Select Block", "Block A", "Block B", "Block C","Block D"};
        ArrayAdapter<String> blockAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, blockData);
        blockAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockSpinner.setAdapter(blockAdapter);

        ArrayAdapter<String> floorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Select Floor"});
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinner.setAdapter(floorAdapter);

        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Select Room"});
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(roomAdapter);


        blockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedBlock = blockSpinner.getSelectedItem().toString();
                if (!"Select Block".equals(selectedBlock)) {
                    // Populate data for the Floor Spinner based on the selected Block
                    String[] floorData = generateFloorData(selectedBlock);
                    ArrayAdapter<String> floorAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, floorData);
                    floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    floorSpinner.setAdapter(floorAdapter);
                } else {
                    // Reset the Floor and Room Spinners if "Select Block" is selected
                    ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, new String[]{"Select Floor"});
                    emptyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    floorSpinner.setAdapter(emptyAdapter);
                    roomSpinner.setAdapter(emptyAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        floorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedBlock = blockSpinner.getSelectedItem().toString();
                String selectedFloor = floorSpinner.getSelectedItem().toString();

                if (!"Select Block".equals(selectedBlock) && !"Select Floor".equals(selectedFloor)) {
                    String[] roomData = generateRoomData(selectedBlock, selectedFloor);
                    ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, roomData);
                    roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    roomSpinner.setAdapter(roomAdapter);
                } else {
                    // Reset the Room Spinner if either "Select Block" or "Select Floor" is selected
                    ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, new String[]{"Select Room"});
                    emptyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    roomSpinner.setAdapter(emptyAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedBlock = blockSpinner.getSelectedItem().toString();
                String selectedFloor = floorSpinner.getSelectedItem().toString();
                String selectedRoom = roomSpinner.getSelectedItem().toString();

                if (selectedBlock.equals("Select Block") || selectedFloor.equals("Select Floor") || selectedRoom.equals("Select Room"))
//
                {
                    Toast.makeText(MainActivity.this, "Please select all items.", Toast.LENGTH_SHORT).show();
                } else {
                    String result = "Block: " + selectedBlock + "\nFloor: " + selectedFloor + "\nRoom: " + selectedRoom;
//
                    resultTextView.setText(result);
                    resultTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockSpinner.setSelection(0);
                floorSpinner.setSelection(0);
                roomSpinner.setSelection(0);
                resultTextView.setText("");
            }
        });

    }

    private String[] generateFloorData(String selectedBlock) {
        if ("Block A".equals(selectedBlock)) {
            return new String[]{"Select Floor", "Floor 1", "Floor 2", "Floor 3", "Floor 4", "Floor 5", "Floor 6", "Floor 7", "Floor 8", "Floor 9", "Floor 10"};
        }
        if ("Block B".equals(selectedBlock)) {
            return new String[]{"Select Floor", "Floor 1", "Floor 2", "Floor 3", "Floor 4", "Floor 5", "Floor 6", "Floor 7"};
        }
        if ("Block C".equals(selectedBlock)) {
            return new String[]{"Select Floor", "Floor 1", "Floor 2", "Floor 3", "Floor 4"};
        }
        if ("Block D".equals(selectedBlock)) {
            return new String[]{"Select Floor", "Floor 1", "Floor 2", "Floor 3"};
        }
        return new String[0];
    }

    private String[] generateRoomData(String selectedBlock, String selectedFloor) {
        if ("Select Floor".equals(selectedFloor)) {
            return new String[]{"Select Room"};
        }

        String blockNo = getBlockNumber(selectedBlock);
        int floorNo = getFloorNumber(selectedFloor);

        String[] roomData = new String[6];

        for (int i = 1; i <= 6; i++) {
            String roomNo = blockNo + "-" + floorNo * 10 + i;
            roomData[i-1] = roomNo;
        }

        return roomData;
    }

    private String getBlockNumber(String selectedBlock) {

        String blockNumberStr = selectedBlock.trim().substring(6);
        try {
            return blockNumberStr;
        } catch (NumberFormatException e) {
            return String.valueOf(-1);
        }

    }

    private int getFloorNumber(String selectedFloor) {
        String floorNumberStr = selectedFloor.trim().substring(6);
        try {
            return Integer.parseInt(floorNumberStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
