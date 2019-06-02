package com.adminneg.bussines.administrar.negocio.adminneg.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adminneg.bussines.administrar.negocio.adminneg.model.ChildModel;
import com.adminneg.bussines.administrar.negocio.adminneg.model.HeaderModel;
import com.adminneg.bussines.administrar.negocio.adminneg.R;


import java.util.List;

class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<HeaderModel> listHeader;

    public ExpandableListAdapter(Context context
            , List<HeaderModel> listHeader) {

        this.context = context;
        this.listHeader = listHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listHeader.get(groupPosition).getChildModelList().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ChildModel childText = (ChildModel) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.navigation_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText.getTitle());

        if (childText.isSelected()) {
            txtListChild.setTypeface(null, Typeface.BOLD);
        } else {
            txtListChild.setTypeface(null, Typeface.NORMAL);
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try {
            return this.listHeader.get(groupPosition).getChildModelList().size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        HeaderModel header = (HeaderModel) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.navigation_list_group, null);
        }

        RelativeLayout layoutGroup = convertView.findViewById(R.id.layout_group);
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        ImageView ivGroupIndicator = convertView.findViewById(R.id.ivGroupIndicator);
        ImageView iconMenu = convertView.findViewById(R.id.icon_menu);
        TextView isNew = convertView.findViewById(R.id.is_new);

        lblListHeader.setText(header.getTitle());

        if (header.getResource() != -1)
            iconMenu.setBackgroundResource(header.getResource());

        if (header.isHasChild()) {
            lblListHeader.setTypeface(null, Typeface.NORMAL);
            ivGroupIndicator.setVisibility(View.VISIBLE);
        } else {
            ivGroupIndicator.setVisibility(View.GONE);
            if (header.isSelected()) {
                lblListHeader.setTypeface(null, Typeface.BOLD);
            } else {
                lblListHeader.setTypeface(null, Typeface.NORMAL);
            }
        }

        if (header.isNew()) {
            isNew.setVisibility(View.VISIBLE);

        } else {
            isNew.setVisibility(View.GONE);
        }

        if (isExpanded) {
            ivGroupIndicator.setImageResource(R.drawable.arriba2);
        } else {
            ivGroupIndicator.setImageResource(R.drawable.abajo);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}