package adapters;

import com.hfad.core.CurrentActivity;
import com.hfad.report.IntervetionDetailFragment;
import com.hfad.report.R;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.database.database.entities.Intervention;

/**
 * Created by DELL on 2.1.2017..
 */

public class InterventionViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    InterventionRecyclerAdapter mAdapter;

    TextView mIntId;

    TextView mKindOfIntervention;

    TextView mAddress;

    private Intervention mIntervenion;

    View mInterventionView;

    public InterventionViewHolder(View interventionView, InterventionRecyclerAdapter adapter) {
        super(interventionView);
        mInterventionView = interventionView;
        mAdapter = adapter;

        bindViews(interventionView);

        ButterKnife.bind(this, interventionView);
    }

    private void bindViews(View v) {
        //Ne razumijem tocno sto je ovo, tj. sto bi to trebalo predstavljati, Aleksandra prvom prilikom popravi
        //mIntId = (TextView) v.findViewById(R.id.type_of_int);

        mKindOfIntervention = (TextView) v.findViewById(R.id.type_of_int);
        mAddress = (TextView) v.findViewById(R.id.address_int);
    }

    public void bind(Intervention intervention) {
        mIntervenion = intervention;
        mIntId.setText(intervention.getInterventionId());
        mKindOfIntervention.setText(intervention.getKindOfIntervention());
        mAddress.setText(intervention.getAddress());

    }

    @OnClick
    public void interventionSelected() {
        Bundle args = new Bundle();
        args.putInt("interventionId", mIntervenion.getInterventionId());

        IntervetionDetailFragment idf = new IntervetionDetailFragment();
        idf.setArguments(args);

        FragmentTransaction ft = CurrentActivity.getActivity().getFragmentManager().beginTransaction();
        //ft.replace(R.id.fragment_content, idf);
        ft.addToBackStack("IntervetionDetailFragment");
        ft.commit();
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
