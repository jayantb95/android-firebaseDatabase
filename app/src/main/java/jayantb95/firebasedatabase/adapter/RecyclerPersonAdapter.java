package jayantb95.firebasedatabase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jayantb95.firebasedatabase.R;
import jayantb95.firebasedatabase.dataModel.PersonModel;

/**
 * Created by Jayant on 31-01-2018.
 */

public class RecyclerPersonAdapter extends RecyclerView.Adapter<RecyclerPersonAdapter.PersonHolder> {

    List<PersonModel> personList;

    public RecyclerPersonAdapter(List<PersonModel> personList) {
        this.personList = personList;
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_person_item, parent, false);
        return new PersonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder {
        public PersonHolder(View itemView) {
            super(itemView);
        }
    }
}
