package com.example.spinnerallblockfloorroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.BlockViewHolder>{

    Context context;
    List<String> blockNames;

    public BlockAdapter(Context context, List<String> blockNames) {
        this.context = context;
        this.blockNames = blockNames;
    }

    @NonNull
    @Override
    public BlockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.block_item, parent, false);
        return new BlockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockViewHolder holder, int position) {
        String blockName = blockNames.get(position);
        holder.blockNameTextView.setText(blockName);
    }

    @Override
    public int getItemCount() {
        return blockNames.size();
    }

    public class BlockViewHolder extends RecyclerView.ViewHolder{

        TextView blockNameTextView;

        public BlockViewHolder(@NonNull View itemView) {
            super(itemView);
            blockNameTextView = itemView.findViewById(R.id.txtBlock);
        }
    }
}
