package com.sjs.dz.rzxt2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sjs.dz.rzxt2.R;

import java.util.HashMap;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

	//多选checkBox
	//private boolean isMultiChoose = false;
	private int isCheckBoxVisiable = View.GONE;
	
	private HashMap<Integer, Boolean> isSelectedMap;
	
	private Context context;
	private List<Contact> ContactLists;
	
	private LayoutInflater inflater;
	
	public ContactAdapter(Context context,
						  List<Contact> ContactLists,
						  boolean isMultiChoose){
		this.context = context;
		inflater = LayoutInflater.from(context);
		
		this.ContactLists = ContactLists;
		
		isSelectedMap = new HashMap<Integer, Boolean>();
		
		if(isMultiChoose){
			isCheckBoxVisiable = CheckBox.VISIBLE;
		}else{
			isCheckBoxVisiable = View.GONE;
		}
	}
	
	@Override
	public int getCount() {
		return ContactLists.size();
	}

	@Override
	public Object getItem(int position) {
		return ContactLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_clear, null);
			holder.cbContactMulti = (CheckBox)convertView.findViewById(R.id.cb_multi);
			/***
			 *
			 */
			holder.tv_ln_com_name = (TextView)convertView.findViewById(R.id.item_tv_clear_com_name);
			holder.tv_ln_com_no = (TextView)convertView.findViewById(R.id.item_tv_clear_com_no);
			holder.tv_ln_task_status = (TextView)convertView.findViewById(R.id.item_tv_clear_task_status);
			holder.tv_ln_task_time_limit = (TextView)convertView.findViewById(R.id.item_tv_clear_end_date);
//			holder.tv_ln_task_type = (TextView)convertView.findViewById(R.id.tv_ln_task_type);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}

		holder.cbContactMulti.setVisibility(isCheckBoxVisiable);

		String ln_cif_name = ContactLists.get(position).task_com_name;
		holder.tv_ln_com_name.setText(ln_cif_name);
		String ln_com_no = ContactLists.get(position).task_com_no;
		holder.tv_ln_com_no.setText(ln_com_no);
		String ln_task_status = ContactLists.get(position).task_status;
		holder.tv_ln_task_status.setText(ln_task_status);
		String ln_task_time_limit = ContactLists.get(position).task_end_date;
		holder.tv_ln_task_time_limit.setText(ln_task_time_limit);

//
//		holder.tvContactRemark.setText("是否被选中："+ getisSelectedAt(position));
		holder.cbContactMulti.setChecked(getisSelectedAt(position));

		return convertView;
	}
	/***
	 *
	 */
	private class ViewHolder{
		CheckBox cbContactMulti;
		TextView tv_ln_task_type;
		TextView tv_ln_task_no;
		TextView tv_ln_com_no;
		TextView tv_ln_com_name;
		TextView tv_ln_task_time_limit;
		TextView tv_ln_task_status;
	}
	
	public void setDate(List<Contact> ContactLists){
		this.ContactLists = ContactLists;
		notifyDataSetChanged();
	}
	
	public boolean getisSelectedAt(int position){
		
		//如果当前位置的key值为空，则表示该item未被选择过，返回false，否则返回true
		if(isSelectedMap.get(position) != null){
			return isSelectedMap.get(position);
		}
		return false;
	}
	
	public void setItemisSelectedMap(int position, boolean isSelectedMap){
		this.isSelectedMap.put(position, isSelectedMap);
		notifyDataSetChanged();
	}
	
	public HashMap<Integer, Boolean> getAllSelected(){
		return isSelectedMap;
	}
	
	public void removeSelected(int position){
		//被选择才能删除
		if(getisSelectedAt(position)){
			isSelectedMap.remove(position);
		}
		notifyDataSetChanged();
	}
}
