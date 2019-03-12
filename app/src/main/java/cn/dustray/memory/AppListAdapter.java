package cn.dustray.memory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.dustray.photoncleanup.R;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.AppListHolder> {

    Context context;
    List<AppInfo> list;

    public AppListAdapter(Context context, List<AppInfo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AppListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_app_list, viewGroup, false);
        AppListHolder holder = new AppListHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppListHolder appListHolder, int i) {
        AppInfo info= list.get(i);
        appListHolder.addData(info);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AppListHolder extends RecyclerView.ViewHolder {

        ImageView appIcon;
        TextView appName, appPackageName;

        public AppListHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.app_icon);
            appName = itemView.findViewById(R.id.app_name);
            appPackageName = itemView.findViewById(R.id.app_package_name);
            itemView.setBackgroundResource(R.drawable.item_ripple);
        }

        public void addData(AppInfo appInfo) {

            appIcon.setImageDrawable(appInfo.getAppIcon());
            appName.setText(appInfo.getAppName());
            appPackageName.setText(appInfo.getPackageName());
        }
    }
}
