package com.codepath.simpletodo;

/**
 * Created by uttamavillain on 1/17/16.
 */
public class Utility {
    public static int getArtResourceForPriority(String priority) {
        if(priority.equals("High"))
            return R.drawable.high_priority;
        if(priority.equals("Medium"))
            return R.drawable.medium_priority;
        if(priority.equals("Low"))
            return R.drawable.low_priority;
        return -1;
    }

    public static String getPriority(String priority) {
        if(priority.equals("High Priority"))
            return "High";
        if(priority.equals("Medium Priority"))
            return "Medium";
        if(priority.equals("Low Priority"))
            return "Low";
        return "";
    }

    public static int getPriorityId(String priority) {
        if(priority.equals("High"))
            return R.id.high_prioirty;
        if(priority.equals("Medium"))
            return R.id.medium_prioirty;
        if(priority.equals("Low"))
            return R.id.low_prioirty;
        return -1;
    }
}