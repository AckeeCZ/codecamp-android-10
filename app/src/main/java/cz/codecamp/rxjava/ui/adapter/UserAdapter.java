package cz.codecamp.rxjava.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.codecamp.rxjava.R;
import cz.codecamp.rxjava.domain.model.User;

/**
 * Recycler view for user
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public static final String TAG = UserAdapter.class.getName();
    private List<User> users;

    public UserAdapter(@NonNull List<User> users) {
        this.users = users;
    }

    public void setData(@NonNull List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_item_user)
        ImageView imageItemUser;
        @Bind(R.id.text_item_user_login)
        TextView textItemUserLogin;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull User user) {
            textItemUserLogin.setText(user.getLogin());
            Picasso.with(imageItemUser.getContext())
                    .load(user.getAvatarUrl())
                    .into(imageItemUser);
        }
    }
}
