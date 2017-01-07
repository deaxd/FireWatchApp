package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.report.R;

import java.util.List;

import hr.foi.air.database.database.entities.Intervention;

/**
 * Created by DELL on 2.1.2017..
 */

public class InterventionRecyclerAdapter extends RecyclerView.Adapter<InterventionViewHolder> {

    List<Intervention> listIntervention;
    Context context;

    public InterventionRecyclerAdapter(List<Intervention> listIntervention, Context context){
        super();
        this.context = context;
        this.listIntervention = listIntervention;
    }

    @Override
    public InterventionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_intervetion_list, viewGroup, false);
        return new InterventionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InterventionViewHolder holder, int position) {
        holder.mIntId.setText(listIntervention.get(position).getInterventionId());
    }

    @Override
    public int getItemCount() {
        return listIntervention.size();
    }
}