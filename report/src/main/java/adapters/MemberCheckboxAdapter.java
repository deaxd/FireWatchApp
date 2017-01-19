package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.database.database.entities.Fireman;
import listeners.OnChexboxClickListener;

/**
 * Created by Matija on 19/01/2017.
 */

public class MemberCheckboxAdapter extends RecyclerView.Adapter<MemberCheckboxAdapter.ViewHolder> {

    private List<Fireman> memberList = new ArrayList<>();

    private Context context;

    private OnChexboxClickListener checkboxClickListener;

    public MemberCheckboxAdapter(List<Fireman> memberList, Context context,OnChexboxClickListener checkboxClickListener) {
        this.memberList = memberList;
        this.context = context;
        this.checkboxClickListener = checkboxClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_member, parent, false),checkboxClickListener);
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


        TextView memberName;

        TextView memberUsername;

        CheckBox checkBox;


        public ViewHolder(View itemView, final OnChexboxClickListener checkboxClickListener) {
            super(itemView);

            memberName = (TextView) itemView.findViewById(R.id.tv_member_name);
            memberUsername = (TextView) itemView.findViewById(R.id.tv_member_username);
            checkBox =(CheckBox) itemView.findViewById(R.id.chbox);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkboxClickListener.onMemberCheckboxClicked(memberList.get(getAdapterPosition()));
                }
            });
        }
    }
}
