
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwu.mobile.week16.myparkapplication.R


class ParkAdapter(private var parkList: List<ParkInfo>) :
    RecyclerView.Adapter<ParkAdapter.ParkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.park_info, parent, false)
        return ParkViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val park = parkList[position]
        holder.tvParkName.text = park.pName
        holder.tvParkAddress.text = park.pAddr
    }

    override fun getItemCount(): Int {
        return parkList.size
    }

    fun updateData(parks: List<ParkInfo>) {
        parkList = parks
        notifyDataSetChanged()
    }

    class ParkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvParkName: TextView = itemView.findViewById(R.id.tvParkName)
        val tvParkAddress: TextView = itemView.findViewById(R.id.tvParkAddress)
    }
}
