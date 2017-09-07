package tech.alvarez.cinefilo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tech.alvarez.cinefilo.MainActivity;
import tech.alvarez.cinefilo.R;
import tech.alvarez.cinefilo.model.Pelicula;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.DeviceViewHolder> {

    private Context context;
    private List<Pelicula> dataset;
    private OnPeliculaSelectedListener onPeliculaSelectedListener;

    public interface OnPeliculaSelectedListener {
        void onPeliculaSelected(Pelicula departamento);
    }

    public PeliculasAdapter(List<Pelicula> dataset, MainActivity context) {
        this.dataset = dataset;
        this.context = context;
//        this.onPeliculaSelectedListener = (OnPeliculaSelectedListener) context;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        Pelicula p = dataset.get(position);

        holder.tituloTextView.setText(p.getTitle());
        holder.descripcionTextView.setText(p.getOverview());

        String url = "http://image.tmdb.org/t/p/w185" + p.getImagen();

        Glide.with(context).load(url).into(holder.fotoImageView);

//        holder.setOnPeliculaItemClick(p, onPeliculaItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        TextView tituloTextView;
        TextView descripcionTextView;
        ImageView fotoImageView;

        public DeviceViewHolder(View itemView) {
            super(itemView);

            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            descripcionTextView = (TextView) itemView.findViewById(R.id.descripcionTextView);
            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
        }

        public void setDeviceSelectedListener(final Pelicula departamento, final OnPeliculaSelectedListener onPeliculaSelectedListener) {
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onPeliculaSelectedListener.onPeliculaSelected(departamento);
//                }
//            });
        }
    }

    public void add(Pelicula pelicula) {
        dataset.add(pelicula);
        notifyDataSetChanged();
    }

    public void setDataset(List<Pelicula> unidadesEducativas) {
        if (unidadesEducativas == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = unidadesEducativas;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

}
