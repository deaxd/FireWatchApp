package foi.hr.members.listeners;


import hr.foi.air.database.database.entities.Fireman;

public interface FragmentActionListener {

    void newMemberAddingFinished();

    void onMemberClicked(Fireman fireman);

    void invalidMemberLoaded();

    void memberUpdateFinished();
}
