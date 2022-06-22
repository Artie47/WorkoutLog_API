package REST_API.contorller;

import REST_API.model.Schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
    public Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public Date getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public Date ParseStringToDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("E MM dd kk:mm:ss z yyyy");
        return df.parse(date);
    }

    public List<Date> getListOfDates(List<Schedule> list){
        List<Date> dates = new ArrayList<>();

        for(Schedule l: list){
            dates.add(l.getStart_time());
        }
        return dates;
    }

    public List<Date> cleanList(List<Date> list, Date date){
        list.removeIf(d -> d.before(date));
        return list;
    }

    public String DateOfEndOfAbonement(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        return String.valueOf(cal);
    }
}
