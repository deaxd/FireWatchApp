package foi.hr.members.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import foi.hr.members.R;
import hr.foi.air.database.database.entities.Fireman;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.ViewHolder> {

    private List<Fireman> memberList = new ArrayList<>();

    private Context context;

    public MembersAdapter(List<Fireman> memberList, Context context) {
        this.memberList = memberList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.memberName.setText(memberList.get(position).getName());
        holder.memberUsername.setText(memberList.get(position).getUsername());

        //TODO if image is ever supported in members list
        /*
        Glide.with(context)
                .load(memberList.get(position).getImageUrl())
                .into(holder.memberPhoto);
                */
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView memberPhoto;

        TextView memberName;

        TextView memberUsername;


        public ViewHolder(View itemView) {
            super(itemView);
            memberPhoto = (ImageView) itemView.findViewById(R.id.iv_member_photo);
            memberName = (TextView) itemView.findViewById(R.id.tv_member_name);
            memberUsername = (TextView) itemView.findViewById(R.id.tv_member_username);
        }
    }
}
